/* Project Title: Communicator class
* Description: This is the class that includes part 1 and 3 of the Crypto Lab.
*
* Created by: Amanda, Herbie
* Date: 4/10/17
*/


public class Communicator {
  public long publicBase; // prime, shared by alice and bob
  public long publicModulus; // prime, shared by alice and bob
  public long publicKey;
  private long privateKey; //should be between 2 and publicModulus, relatively prime
  private long sharedSecretKey;
  private String message;

  public Communicator(long b, long m, long k) {
    publicBase = b;
    publicModulus = m;
    if (k >= 2 && k <= publicModulus) {
      privateKey = k;
    }
    else {
      throw new IllegalArgumentException("Private key must be between 2 and the public modulus!");
    }
    publicKey = (exp(publicBase, privateKey)) % publicModulus;
  }

  // Returns the message of any communicator object
  public String getMessage(Communicator c) {
    return c.message;
  }

  // Sets the message of this communicator object
  public void setMessage(String plaintxt) {
    String cipher = "";
    for (int i = 0; i < plaintxt.length(); i++) {
      Character plainchar = plaintxt.charAt(i);
      Character cipherLetter = (char)(((long) plainchar.charValue()) + sharedSecretKey);
      cipher += cipherLetter;
    }
    message = cipher;
  }

  // Decodes the messsage of any communicator object
  public String decode(Communicator c) {
    String plaintxt = "";
    for (int i = 0; i < c.message.length(); i++) {
      Character cipherchar = c.message.charAt(i);
      Character plainchar = (char)((long)(cipherchar.charValue()) - sharedSecretKey);
      plaintxt += plainchar;
    }
    return plaintxt;
  }

  // Helper exponent method: only words for results that aren't too too long
  public static long exp(long b, long e) {
    long res = 1;
    for (int i = 0; i < e; i++) {
      res *= b;
    }
    return res;
  }

  // Sets the shared key between this and another communicator object
  private void setSharedKey(Communicator other) {
    sharedSecretKey = (exp(other.publicKey, this.privateKey)) % publicModulus;
    System.out.println(sharedSecretKey);
  }

  public static void main(String[] args) {
    Communicator alice = new Communicator(13, 11, 7);
    Communicator bob = new Communicator(13, 11, 9);
    Communicator eve = new Communicator(13, 11, 10);
    alice.setSharedKey(bob);
    bob.setSharedKey(alice);
    alice.setMessage("decode me");
    System.out.println(eve.getMessage(alice));
    System.out.println(bob.decode(alice));


  }
}
