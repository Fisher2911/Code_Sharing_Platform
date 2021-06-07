// You can experiment here, it won’t be checked

import java.time.LocalTime;
import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    // put your code here
    final Scanner scanner = new Scanner(System.in);
    final LocalTime firstTime = LocalTime.parse(scanner.next());
    final LocalTime secondTime = LocalTime.parse(scanner.next());
    final int hours = Math.abs(firstTime.getHour() - secondTime.getHour()) * 3600;
    final int minutes = Math.abs(firstTime.getMinute() - secondTime.getMinute()) * 60;
    final int seconds = Math.abs(firstTime.getSecond() - secondTime.getSecond());
    System.out.println(Math.abs(hours + minutes + seconds));
  }
}
