public class SubEncryp {
  private int key;

  public String encrypt(String plainText) {

    return "cipher";
  }

  public String decrypt(String cipher) {

    return "plaintext";
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    if (key < 0 || key > 255) {
      throw new IllegalArgumentException("Key must be an integer between 0 and 255");
    }
    this.key = key;
  }
}
