import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountMovementParser {
    private static String dateFormat = "dd.MM.yyyy";
    private Double sumIncome = 0.0;
    private Double sumExpense = 0.0;
    private TreeMap<String, Double> analitic = new TreeMap<>();
    private String[] keyArray;

    public List<AccountOperation> parseOperations(Path path) {
        List<AccountOperation> operations = new ArrayList<>();
        try {
            List<String> movementList = Files.readAllLines(path);
            for (String line : movementList) {
                if (line == movementList.get(0))
                    continue;

                String[] fragments = line.split(",", 8);
                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                operations.add(new AccountOperation(
                        fragments[0], //account type
                        new BigInteger(fragments[1]), //account
                        fragments[2], //currency
                        (new SimpleDateFormat(dateFormat)).parse(fragments[3]), //operation date
                        fragments[4], //reference
                        fragments[5], //operation describe
                        Double.parseDouble(fragments[6]), //income
                        Double.parseDouble(fragments[7].replace("\"", "").replace(",", ".")))); //expense
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return operations;
    }

    public void accountMovementAnalitic(List<AccountOperation> operations) {

        keyArray = new String[operations.size()];

        for (AccountOperation op : operations) {
            sumIncome += op.income;
            sumExpense += op.expense;
        }

        for (int i = 0; i < operations.size(); i++) {
            keyArray[i] = operations.get(i).operationDescribe.split("(.+/)|(.+\\\\)")[1];
            keyArray[i] = keyArray[i].split("\\s\\s.+")[0];
            if (analitic.containsKey(keyArray[i]))
                analitic.put(keyArray[i], analitic.get(keyArray[i]) + operations.get(i).expense);
            else
                analitic.put(keyArray[i], operations.get(i).expense);
        }
    }


    public void printAccountMovement() {

        System.out.println("Доход за период: " + sumIncome);
        System.out.println("=============================================");

        for (Map.Entry entry : analitic.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println();
        System.out.println("ИТОГО Расход: " + sumExpense);
    }
}
