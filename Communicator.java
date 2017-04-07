public class Communicator {
  public long publicBase; // prime
  public long publicModulus; // prime
  public long publicKey;
  private long privateKey; //should be between 2 and publicModulus
  private long sharedSecretKey;

  public Communicator(long b, long m, long k) {
    publicBase = b;
    publicModulus = m;
    if (k >= 2 && k <= publicModulus) {
      privateKey = k;
    }
    else {
      throw new IllegalArgumentException("Private key must be between 2 and the public modulus!");
    }
    publicKey = exp(publicBase, privateKey) % publicModulus;
  }

  public static long exp(long b, long e) {
    long res = 1;
    for (int i = 0; i < e; i++) {
      res *= b;
    }
    return res;
  }

  private void setSharedKey(Communicator other) {
    sharedSecretKey = exp(other.publicKey, this.privateKey) % publicModulus;
  }

  public static void main(String[] args) {
  }
}
