import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "UTF8");
    Console v = new Console(scanner);
    EncryptionProgram prog = new EncryptionProgram(v);

    while (prog.run()) {

    }

    scanner.close();
  }
}
