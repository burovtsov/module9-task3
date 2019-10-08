import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){

        Path path = Paths.get("src/main/resources/movementList.csv");
        AccountMovementParser amp = new AccountMovementParser();

        amp.accountMovementAnalitic(amp.parseOperations(path));
        amp.printAccountMovement();
    }
}
