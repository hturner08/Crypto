public class OurMath{
  public static long pow(long one, long two){
    long output = 1;
    for(int i = 0; i < two;i++){
      output*=one;
    }
    return output;
  }
}
