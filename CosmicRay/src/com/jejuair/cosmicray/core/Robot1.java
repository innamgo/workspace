package com.jejuair.cosmicray.core;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
 
public class Robot1{
 

 
    public static void main(String[] args) throws AWTException,IOException {
 
    try {
//		ProcessBuilder cari=new ProcessBuilder("C:\\cari6mex\\CARI-6M.EXE");
		ProcessBuilder cari=new ProcessBuilder("cmd","/c","D:\\hoon\\stsWorkSpace\\CosmicRay\\CosmicRay\\CARI-6M\\CARI-6M.EXE");
//		ProcessBuilder cari=new ProcessBuilder("cmd");
		//cari.getOutputStream().write('1');
//		Map env=cari.environment();
//		env.put("CLASSPATH", "C:\\cari6mex\\");
		cari.directory(new File("D:\\hoon\\stsWorkSpace\\CosmicRay\\CosmicRay\\CARI-6M\\"));
		cari.inheritIO();
		//cari.redirectInput(Redirect.INHERIT);
		//cari.redirectOutput(Redirect.INHERIT);
//		cari.directory(new File("C:\\cari6mex\\"));
		Process cariP=cari.start();
		
		String ls_str="";
		String str2="";
		try
		{
			BufferedReader inputStream=new BufferedReader(new InputStreamReader(cariP.getInputStream()));
			BufferedWriter outputStream=new BufferedWriter(new OutputStreamWriter(cariP.getOutputStream()));
			
			while(true)
			{
				//System.out.println("Wait Val:"+cariP.waitFor());
				byte[] buffer = new byte[cariP.getInputStream().available()];
				cariP.getInputStream().read(buffer);
				
				String response = new String(buffer);
				if(response.length() > 0)
				{
					
					//ls_str=inputStream.readLine();
					//int temp=inputStream.read();
					System.out.println("LINE!:"+response);
					str2=response;
				}
				else
				{
//					System.out.println("Input key 1 enter");
//					char[] menu1={0x31,'\r','\n'};
//					outputStream.write(menu1);
					if( str2.indexOf("<ENTER") > 0)
					{
						System.out.println("Input key 1 enter");
//						cariP.getOutputStream().write(49);
//						cariP.getOutputStream().write(10);
//						cariP.getOutputStream().write(13);
						//cariP.getOutputStream().write(10);
						//char[] menu1={0x31,0x0D,0x0A};
						char[] menu1={0x31,0x0A,0x0D};
						outputStream.write(menu1);
						outputStream.flush();
					}
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