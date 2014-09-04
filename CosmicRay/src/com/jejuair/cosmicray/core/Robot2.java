package com.jejuair.cosmicray.core;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;
 
public class Robot2{
 
    static int keyInput[] = 
    {
      KeyEvent.VK_J, KeyEvent.VK_A, KeyEvent.VK_V, KeyEvent.VK_A, KeyEvent.VK_SPACE,
      KeyEvent.VK_P, KeyEvent.VK_R, KeyEvent.VK_O, KeyEvent.VK_G, KeyEvent.VK_R,
      KeyEvent.VK_A, KeyEvent.VK_M, KeyEvent.VK_M, KeyEvent.VK_I, KeyEvent.VK_N,
      KeyEvent.VK_G, KeyEvent.VK_SPACE, KeyEvent.VK_F, KeyEvent.VK_O, KeyEvent.VK_R,
      KeyEvent.VK_U, KeyEvent.VK_M, KeyEvent.VK_S, KeyEvent.VK_SPACE, KeyEvent.VK_PERIOD,
      KeyEvent.VK_C, KeyEvent.VK_O, KeyEvent.VK_M 
    };

 
    public static void main(String[] args) throws AWTException,IOException {
 
    try {
//		ProcessBuilder cari=new ProcessBuilder("C:\\cari6mex\\CARI-6M.EXE");
		ProcessBuilder cari=new ProcessBuilder("cmd","/c","D:\\hoon\\stsWorkSpace\\CosmicRay\\CosmicRay\\CARI-6M\\CARI-6M.EXE");
//		ProcessBuilder cari=new ProcessBuilder("cmd");
		//cari.getOutputStream().write('1');
//		Map env=cari.environment();
//		env.put("CLASSPATH", "C:\\cari6mex\\");
		cari.directory(new File("D:\\hoon\\stsWorkSpace\\CosmicRay\\CosmicRay\\CARI-6M\\"));
//		cari.directory(new File("C:\\cari6mex\\"));
		cari.redirectErrorStream(true);
		File ErrorLog=new File("ErrorLog.txt");
		File InputLog=new File("InputLog.txt");
		File OutputLog=new File("OutLog.txt");
		FileWriter fw = new FileWriter(InputLog);
		cari.redirectError(ErrorLog);
		cari.redirectInput(InputLog);
		//cari.redirectInput(Redirect.PIPE);
		cari.redirectOutput(OutputLog);
		Process cariP=cari.start();
		//fw.write("1\r\n");
		String ls_str=null;
		int character=0;
		try
		{
			BufferedReader inputStream=new BufferedReader(new InputStreamReader(cariP.getInputStream()));
			//InputStreamReader is=new InputStreamReader(cariP.getInputStream());
			BufferedWriter outputStream=new BufferedWriter(new OutputStreamWriter(cariP.getOutputStream()));
//			String command="D:\\hoon\\stsWorkSpace\\CosmicRay\\CosmicRay\\CARI-6M\\CARI-6M.EXE\r\n";
			//OutputStream out=cariP.getOutputStream();
//			out.write(command.getBytes());
			
			String lsLine="";
//			while((character=is.read()) != -1)
//			{
//				System.out.print((char)character);
//			}
//			inputStream.read();
			//fw.write("1\r\n");
//			fw.close();
			while(true)
			{
				//Thread.sleep(10);
				System.out.println("Wait Val:"+cariP.waitFor());
				//if(inputStream.ready())
					//character=inputStream.read();
				byte[] buffer = new byte[cariP.getInputStream().available()];
				cariP.getInputStream().read(buffer);
				String response = new String(buffer);
				if(response.length() > 0)
				{
					//System.out.print((char)character);
					System.out.print("!!!");
					//lsLine=lsLine+(char)character;
				}
				else
				{
					BufferedReader br = new BufferedReader(new FileReader("OutLog.txt"));
					while(true) {
			            String line = br.readLine();
			            if (line==null) break;
			            System.out.println(line);
			            lsLine=line;
			        }
					br.close();
					//fw.write("1\r\n");
					if(lsLine.indexOf("<ENTER") > 0)
					{
						System.out.println("Input 1 enter");
						outputStream.write("1\r\n");
						fw.write("1\r\n");
						//for(int cnt=0;cnt < 256;cnt++)
						//cariP.getOutputStream().write(49);
						//cariP.getOutputStream().write(13);
						
					}
					lsLine="";
					//System.out.println("ggggg");
					Thread.sleep(1000);
				}
				
				
			}
			
			
			
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("try exit");
		cariP.destroy();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //Robot robot = new Robot();
 
//    for (int i = 0; i < keyInput.length; i++)
//    {
// 
//      robot.keyPress(keyInput[i]);
//      robot.delay(100);
// 
//    }
    System.out.println("End");
}
}