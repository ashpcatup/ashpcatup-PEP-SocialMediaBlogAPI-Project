package Util;

public class ResourceCloser{
  public static void closeResources(AutoCloseable... resources) {
    for(AutoCloseable r : resources){
      if (r != null){
        try{
          r.close();
        }catch(Exception e){ e.printStackTrace();}
      }
    }
  }
}