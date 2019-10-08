import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AccountOperation {

    String accountType;
    BigInteger account;
    String currency;
    Date operationDate;
    String reference;
    String operationDescribe;
    Double income;
    Double expense;

    public AccountOperation(String accountType, BigInteger account, String currency, Date operationDate, String reference,
            String operationDescribe, Double income, Double expense) {
        this.accountType = accountType;
        this.account = account;
        this.currency = currency;
        this.operationDate = operationDate;
        this.reference = reference;
        this.operationDescribe = operationDescribe;
        this.income = income;
        this.expense = expense;
    }
}
