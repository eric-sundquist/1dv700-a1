import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptionProgram {
  Console view;
  Path filePath;

  public EncryptionProgram(Console view) {
    this.view = view;
  }

  public boolean run() {
    try {
      view.print("\n\n--- Encryption program ---");
      getFilePath();
      String text = readTextFile();

      if (isTransposition()) {
        text = doTransposition(text, isEncryption());
      } else {
        text = doSubstitution(text, isEncryption());
      }

      writeToTextFile(text);

      return true;

    } catch (Exception e) {
      view.print(e.getMessage());
      return true;
    }
  }

  private String doTransposition(String text, boolean isEncryption) {
    TransEncryp transEncryp = new TransEncryp();
    transEncryp.setKey(view.promptUserString(
        "Key may only contain characters from latin alphabet (a-z) with a length between 3 and 15. \nEnter key: "));

    if (isEncryption) {
      return transEncryp.encrypt(text);
    } else {
      return transEncryp.decrypt(text);
    }
  }

  private String doSubstitution(String text, boolean isEncryption) {
    SubEncryp subEncryp = new SubEncryp();
    subEncryp.setKey(view.promptUserPosInt("Enter key (1-127)\n", 1, 127));

    if (isEncryption) {
      return subEncryp.encrypt(text);
    } else {
      return subEncryp.decrypt(text);
    }
  }

  private void getFilePath() {
    filePath = Path
        .of(view.promptUserString("Enter absolute path from root (ex. /Users/ericsundquist/Desktop/text.txt): \n"));
  }

  private String readTextFile() {

    String content = "";

    try {
      content = Files.readString(filePath);
    } catch (Exception e) {
    }
    return content;
  }

  private void writeToTextFile(String text) {
    try {
      Files.writeString(filePath, text);
    } catch (Exception e) {
    }
  }

  private boolean isEncryption() {
    int input = view.promptUserPosInt("\n Encryption (1)    Decryption (2) \n Enter action: ", 1, 2);

    return input == 1;
  }

  private boolean isTransposition() {
    int input = view.promptUserPosInt("\nTransposition (1)    Substitution (2)\n Enter method: ", 1, 2);

    return input == 1;
  }

}
