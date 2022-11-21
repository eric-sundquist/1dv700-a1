import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class EncryptionProgram {
  Console view;
  Path filePath;

  public EncryptionProgram(Console view) {
    this.view = view;
  }

  public boolean run() {
    view.print("--- Encryption program ---");
    getFilePath();
    String text = readTextFile();

    if (isTransposition()) {
      text = doTransposition(text, isEncryption());
    } else {
      text = doSubstitution(text, isEncryption());
    }

    writeToTextFile(text);

    return true;
  }

  private String doTransposition(String text, boolean isEncryption) {
    TransEncryp transEncryp = new TransEncryp();
    transEncryp.setKey(view.promptUserString("Enter key. A string between 3-15 chars"));

    if (isEncryption) {
      return transEncryp.encrypt(text);
    } else {
      // return transEncryp.decrypt(text);
      return "";
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
    filePath = Path.of(view.promptUserString("Enter absolute path (ex. /Users/ericsundquist/Desktop/text.txt): \n"));
  }

  private String readTextFile() {

    String content = "";

    try {
      content = Files.readString(filePath);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return content;
  }

  private void writeToTextFile(String text) {
    try {
      Files.writeString(filePath, text, StandardOpenOption.WRITE);
    } catch (Exception e) {
      // TODO: handle exception
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
