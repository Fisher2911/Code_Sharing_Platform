import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//         put your code here
        final Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.next());
        dateTime = dateTime.plusDays(scanner.nextInt());
        dateTime = dateTime.minusHours(scanner.nextInt());
        System.out.println(dateTime);
    }
}
