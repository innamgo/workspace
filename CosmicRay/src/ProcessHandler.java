import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessHandler extends Thread {

InputStream inpStr;
String strType;

public ProcessHandler(InputStream inpStr, String strType) {
this.inpStr = inpStr;
this.strType = strType;
}

public void run() {
try {
InputStreamReader inpStrd = new InputStreamReader(inpStr);
BufferedReader buffRd = new BufferedReader(inpStrd);
String line = null;
while((line = buffRd.readLine()) != null) {
System.out.println(strType + " —>"  + line);
}
buffRd.close();

} catch(Exception e) {
System.out.println(e);
}

}
public static void main(String args[])throws Exception {

/* For windows setting to cmd.exe */
String command[] = {"cmd.exe","/c","cmd"};

/* executing the command with environments set. */
Process pro = Runtime.getRuntime().exec("cmd");

/* handling the streams so that dead lock situation never occurs.  */
ProcessHandler inputStream =
new ProcessHandler(pro.getInputStream(),"INPUT");
ProcessHandler errorStream =
new ProcessHandler(pro.getErrorStream(),"ERROR");

/* start the stream threads */
inputStream.start();
errorStream.start();

}
}