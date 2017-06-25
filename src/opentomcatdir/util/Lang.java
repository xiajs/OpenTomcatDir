package opentomcatdir.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Lang {

    public static String getFileAsString(String file){
        String bodyData = "";
        try {
            byte[] buffer = new byte[8192];
            InputStream in = new FileInputStream(file) ; //Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bLen = 0;
            while ((bLen = in.read(buffer)) > 0) {
                baos.write(buffer, 0, bLen);
            }
            bodyData = new String(baos.toByteArray(), "GBK"); // 
        }
        catch (IOException e)
        {
         e.printStackTrace();
        }
        return bodyData;
   }
   
   public static void write(String file, String content){
       if(null==content){
           return;
       }
       FileOutputStream fos = null;
       try {
           fos = new FileOutputStream(file,true);
           fos.write(content.getBytes());
           fos.flush();
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }finally{
           if(fos!=null){
               try {
                   fos.close();
               } catch (IOException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
           }
       }
   }
   
   public static boolean isWindows() {
       return true;
   }
   
   public static boolean isBlank(String s){
       if( null == s ){
           return true;
       }
       if( s.trim().length() < 1 ){
           return true;
       }
       return false;
   }
   
   private static final String logfile = "D:/tomcat.txt";
   
   public static void writeLog(String s){
       Lang.write(logfile,s+"\r\n");
   }
}
