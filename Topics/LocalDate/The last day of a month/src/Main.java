import java.util.Scanner;
import java.time.LocalDate;
import java.time.YearMonth;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        final String[] strings = scanner.nextLine().split(" ");
        final LocalDate date = LocalDate.ofYearDay(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        final YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth().getValue());
        System.out.println(date.getDayOfMonth() == yearMonth.atEndOfMonth().getDayOfMonth());
        
    }
}
