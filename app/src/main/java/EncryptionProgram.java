import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptionProgram {
  Console view;

  public EncryptionProgram(Console view) {
    this.view = view;
  }

  public boolean run() {
    view.print("Encyption program... \n");

    boolean isEncryption = isEncryption();
    // boolean isTransposition = isTransposition();

    SubEncryp subEncryp = new SubEncryp();
    subEncryp.setKey(view.promptUserPosInt("Enter key (0-255)\n", 0, 255));
    // insert file path... specify if absolute or relative path
    String text = readFile(view.promptUserString("Enter path to text file relative to src-fodler: \n"));

    view.print(text);

    return true;
  }

  private String readFile(String path) {
    Path filePath = Path.of(path);
    // Path filePath = Path.of("c:/temp/demo.txt");
    String content = "";

    try {
      content = Files.readString(filePath);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return content;
  }

  private boolean isEncryption() {
    int input = view.promptUserPosInt("Enter action:\n Encryption (1)    Decryption (2) \n", 1, 2);

    return input == 1;
  }

  private boolean isTransposition() {
    int input = view.promptUserPosInt("Enter method of encryption\nTransposition (1)    Substitution (2)\n", 1, 2);

    return input == 1;
  }

}
