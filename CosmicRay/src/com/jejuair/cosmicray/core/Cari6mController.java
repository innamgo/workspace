package com.jejuair.cosmicray.core;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class Cari6mController {
			
			private static Logger logger=LoggerFactory.getLogger(Cari6mController.class);
			//CARI-6M 실행경로+파일명
			private String gsPathCari6mExe="";
			//CARI-6M 실행파일명
			private String gsNameCari6mExe="\\CARI-6M.EXE";
			//CARI-6M 경로
			private String gsPathCari6m;
			//작업 경로
			private String gsWorkingdirectory = System.getProperty("user.dir");
			//프로세스 빌더
			private ProcessBuilder goProcessBuilder=null;
			//프로세스
			private Process goProcess=null;
			
			//CARI6M출력내용 처리를 위한 스트림 변수
			private BufferedReader goBufReader=null;
			
			//CARI6M 에러 출력내용 처리를 위한 스트림 변수
			private BufferedReader goBufErrorReader=null;
			
			//CARI6M키 입력 처리를 위한 스트림 변수
			private BufferedWriter goBufWriter=null;
			
			public Cari6mController()
			{
				gsPathCari6m="D:\\hoon\\stsWorkSpace\\CosmicRay\\CosmicRay\\CARI-6M";
				gsPathCari6mExe=gsPathCari6m+gsNameCari6mExe;
				logger.debug(gsWorkingdirectory);
				
				goProcessBuilder=new ProcessBuilder("cmd","/c",gsPathCari6mExe);
				goProcessBuilder.directory(new File(gsPathCari6m));
				//goProcessBuilder.inheritIO();
				//goProcessBuilder.redirectOutput(Redirect.INHERIT);
				//goProcessBuilder.redirectInput(Redirect.INHERIT);
				try {
					goProcess=goProcessBuilder.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.debug(e.toString());
					e.printStackTrace();
				}
			}
			
			
			public void doCalculating() throws IOException {
			
				
			try {
			 
				int characterCount=0;
				try
				{
					String command="1\r\n";
					
					//프로세스의 출력 스트림 얻기
					goBufReader=new BufferedReader(new InputStreamReader(goProcess.getInputStream()));
					goBufErrorReader=new BufferedReader(new InputStreamReader(goProcess.getErrorStream()));
					goBufWriter=new BufferedWriter(new OutputStreamWriter(goProcess.getOutputStream()));
					
					String line;
				    
					while(true)
					{
				    	if(goBufErrorReader.ready())
				    	{
				    		logger.debug(goBufErrorReader.readLine());
				    	}
//				    	logger.debug("Input 1 enter");
//						char[] menu1={0x01,'\r','\n'};
//						goBufWriter.write(menu1);
				    	if(goBufReader.ready())
				    	{
				    		line=goBufReader.readLine();
				    		logger.debug(line);
				    	}
				    	else
						{
							logger.debug("Input 1 enter");
							char[] menu1={0x01,'\r','\n'};
							goBufWriter.write(menu1);
							goBufWriter.newLine();
							goProcess.getOutputStream().write(KeyEvent.VK_1);
							goProcess.getOutputStream().write(KeyEvent.VK_ENTER);
							
							
						}
						
					}
					
					/*

					while(true)
					{
						byte[] arrBytestream=null;
						Thread.sleep(100);
						logger.debug("----Try to Read Stream----");
						
						characterCount=goProcess.getInputStream().available();
						
						logger.debug("Available Count:"+characterCount);
						if(characterCount > 0)
						{
							arrBytestream=new byte[characterCount];
							goProcess.getInputStream().read(arrBytestream);
							String contents=new String(arrBytestream);
							logger.debug(contents);
							//키보드를 입력하라는 메시지라면 입력한다.
							if(contents.indexOf(RefCari6M.INPUT_TEXT) > 0)
							{
								logger.debug("메뉴 출력이 끝나고 숫자를 입력하라고 한다. 3을 입력하자.");
//								char[] menu1={0x01,'\r','\n'};
//								goBufWriter.write(menu1);
								
								goProcess.getOutputStream().write(KeyEvent.VK_1);
								goProcess.getOutputStream().write(KeyEvent.VK_ENTER);
								
							}
							
							
						}
						else if(characterCount == 0)
						{
							
//							logger.debug("WRITE Command:"+(char)KeyEvent.VK_NUMPAD3+(char)KeyEvent.VK_ENTER);
//							goProcess.getOutputStream().write(KeyEvent.VK_NUMPAD3);
//							goProcess.getOutputStream().write(KeyEvent.VK_ENTER);
							
							Thread.sleep(1000);
						}
						
						
					}
					*/
					
					
					
				}
				catch(IOException e)
				{
					e.printStackTrace();
					System.exit(1);
				}
				logger.debug("try exit");
				goProcess.destroy();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("End");
			}
}
