import java.util.stream.IntStream;

public class SubEncryp {
  private int key;
  String charStr = new String(IntStream.rangeClosed(0, 127).toArray(), 0, 128);
  char[] charSet = charStr.toCharArray();

  public String encrypt(String plainText) {
    String cipher = "";
    char[] chars = plainText.toCharArray();

    for (char c : chars) {
      int charIndex = charStr.indexOf(c);
      if (charIndex >= 0) {
        int pos = (charIndex + key) % 128;
        char substi = charSet[pos];
        cipher += substi;
      } else {
        cipher += c;
      }
    }

    return cipher;
  }

  public String decrypt(String cipher) {
    String plainText = "";
    char[] chars = cipher.toCharArray();

    for (char c : chars) {
      int charIndex = charStr.indexOf(c);
      if (charIndex >= 0) {
        int pos = (charIndex - key) % 128;
        if (pos < 0) {
          pos = charStr.length() + pos;
        }
        char substi = charSet[pos];
        plainText += substi;
      } else {
        plainText += c;
      }
    }

    return plainText;
  }

  public void setKey(int key) {
    if (key < 0 || key > 127) {
      throw new IllegalArgumentException("Key must be an integer between 1 and 127");
    }
    this.key = key;
  }
}
