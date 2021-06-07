import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        System.out.println(LocalDate.from(dateTime.plusHours(11)));
    }
}
