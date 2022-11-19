import java.util.Scanner;

public class Console {
  Scanner input;

  public Console(Scanner input) {
    this.input = input;
  }

  public void print(String message) {
    System.out.println(message);
  }

  public int promptUserPosInt(String message, int minValue, int maxValue) {
    int number;
    do {
      System.out.print(message);
      while (!input.hasNextInt()) {
        input.nextLine();
        System.out.print("That's not a number\n" + message);
      }
      number = input.nextInt();
      input.nextLine();
    } while (number < minValue || number > maxValue);
    return number;
  }

  /**
   * Prompts user for a string.
   *
   * @param message - the message to display.
   * @return the inputed string.
   */
  public String promptUserString(String message) {
    int minLengthString = 2;
    String inputString;
    do {
      System.out.print(message);
      inputString = input.nextLine().trim();
    } while (inputString.length() < minLengthString);
    return inputString;
  }

  public void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
