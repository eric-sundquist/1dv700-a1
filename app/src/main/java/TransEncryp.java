import java.util.ArrayList;
import java.util.Arrays;

public class TransEncryp {
  private String key;
  private String alphabet = "abcdefghijklmnopqrstuvwxyz";

  public String encrypt(String plainText) {
    ArrayList<ArrayList<Character>> matrix = getMatrix(plainText);

    // Append cols after alpha order to string.
    String cipher = "";
    String sortedKey = keyAlphaOrder();
    char keyArr[] = key.toCharArray();

    for (int i = 0; i < key.length(); i++) {
      char c = sortedKey.charAt(i);
      int col = 0;

      // hitta index char i keyArr
      for (int j = 0; j < keyArr.length; j++) {
        if (keyArr[j] == c) {
          keyArr[j] = ' ';
          col = j;
          break;
        }
      }

      for (Character cha : matrix.get(col)) {
        cipher += cha;
      }
    }
    return cipher;
  }

  public String decrypt(String cipher) {
    ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
    int cols = key.length();
    int rows = (int) Math.ceil(cipher.length() / (double) cols);

    for (int i = 0; i < cols; i++) {
      ArrayList<Character> col = new ArrayList<>();
      for (int j = 0; j < rows; j++) {
        col.add(cipher.charAt((key.length() * i + j)));
      }
      matrix.add(col);
    }

    String plainText = "";
    String sortedKey = keyAlphaOrder();

    for (int j = 0; j < rows; j++) {
      for (int i = 0; i < cols; i++) {
        int col = sortedKey.indexOf(key.charAt(i));
        ArrayList<Character> chars = matrix.get(col);
        plainText += chars.get(j);
      }
    }
    return plainText.trim();

  }

  private ArrayList<ArrayList<Character>> getMatrix(String text) {
    int cols = key.length();
    int rows = (int) Math.ceil(text.length() / (double) cols);
    ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();

    // Fill key column arrs with message
    for (int i = 0; i < cols; i++) {
      ArrayList<Character> col = new ArrayList<>();
      for (int j = 0; j < rows; j++) {
        int charIndex = i + (cols * j);
        if (charIndex < text.length()) {
          col.add(text.charAt(charIndex));
        } else {
          col.add(' ');
        }
      }
      matrix.add(col);
    }

    return matrix;
  }
  // private void printMatrix(ArrayList<ArrayList<Character>> matrix) {
  // System.out.println();

  // }

  private String keyAlphaOrder() {
    char keyArr[] = key.toCharArray();
    Arrays.sort(keyArr);
    return new String(keyArr);
  }

  public void setKey(String key) {
    if (key.length() < 3 || key.length() > 15) {
      throw new IllegalArgumentException("Key must be a string with a length between 3 and 15");
    }

    if (!containsOnlyCharsFromAlphabet(key)) {
      throw new IllegalArgumentException("Key must be one word containing letters from the latin alphabet");
    }

    this.key = key.toLowerCase();
  }

  private boolean containsOnlyCharsFromAlphabet(String str) {
    str = str.toLowerCase();
    char chars[] = str.toCharArray();
    for (char c : chars) {
      if (alphabet.indexOf(c) == -1) {
        return false;
      }
    }
    return true;
  }
}
