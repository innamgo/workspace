package com.jejuair.cosmicray.core;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
 
public class Cari6mControllerDOSBOX {
			/* 64비트 윈도우의 경우는 DOSBOX로 CARI-6M을 실행해야하며, DOSBOX설정 파일의 autoexec 마지막부분에
				*   mount c "c:\cari6mex"
			*		c:
			*		cd cari6mex
			*		cari-6m
			*   를 입력해 준다.[CPU] 옵션에 cycles=fixed 100000 이상 값으로 해줘야 빠르다.
			*/
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
				//ProcessBuilder cari=new ProcessBuilder("cmd","/c","C:\\cari6mex\\AF-6MB.EXE");
				ProcessBuilder cari=new ProcessBuilder("cmd","/c","C:\\cari6mex\\DOSBox.exe -noconsole");
			//	ProcessBuilder cari=new ProcessBuilder("C:\\Program Files (x86)\\DOSBox-0.74\\DOSBox.exe -noconsole");
			
				cari.directory(new File("C:\\cari6mex\\"));
				Process cariP=cari.start();
				
				int characterCount=0;
				try
				{
					String command="1\r\n";
			
					while(true)
					{
						byte[] stream=null;
						Thread.sleep(100);
						System.out.println("----Try to Read Stream----");
						characterCount=cariP.getInputStream().available();
						System.out.println("Available Count:"+characterCount);
						if(characterCount > 0)
						{
							stream=new byte[characterCount];
							cariP.getInputStream().read(stream);
							for(int cnt=0;cnt < stream.length; cnt++)
							{
								if(stream[cnt]==0x13)
									System.out.println("READ CHAR:");
								System.out.print((char)stream[cnt]);
							}
							
						}
						else if(characterCount == 0)
						{
							
							System.out.println("WRITE Command:"+(char)KeyEvent.VK_1+(char)KeyEvent.VK_ENTER);
							
							//편명 입력
							command="7C7777";
							cariP.getOutputStream().write(KeyEvent.VK_7);
							cariP.getOutputStream().write(KeyEvent.VK_C);
							cariP.getOutputStream().write(KeyEvent.VK_ENTER);
							//cariP.getOutputStream().flush();
							/*
							//운항날짜 입력
							command="03/2013";
							cariP.getOutputStream().write(command.getBytes());
							cariP.getOutputStream().write(KeyEvent.VK_ENTER);
							cariP.getOutputStream().flush();
							
							//운항노트 입력
							command="EMPTY";
							cariP.getOutputStream().write(command.getBytes());
							cariP.getOutputStream().write(KeyEvent.VK_ENTER);
							cariP.getOutputStream().flush();
							*/
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
			System.out.println("End");
			}
}
