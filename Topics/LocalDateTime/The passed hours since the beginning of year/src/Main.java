import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        final LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        final LocalDateTime january = LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0);
        final long hours = Duration.between(january, dateTime).toHours();
        System.out.println(hours);
    }
}