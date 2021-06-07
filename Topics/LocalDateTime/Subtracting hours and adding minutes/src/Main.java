import java.time.LocalDateTime;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        dateTime = dateTime.minusHours(scanner.nextInt());
        dateTime = dateTime.plusMinutes(scanner.nextInt());
        System.out.println(dateTime);
    }
}