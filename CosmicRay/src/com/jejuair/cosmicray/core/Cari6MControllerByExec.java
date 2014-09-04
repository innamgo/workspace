package com.jejuair.cosmicray.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;


public class Cari6MControllerByExec {

	
	
	Cari6MControllerByExec() 
	{
		
	}
	public static void main(String[] args) 
	{
		String workingdirectory = System.getProperty("user.dir");
		System.out.println(workingdirectory);
		
		CommandLine cmdLine = new CommandLine("cmd");
		cmdLine.addArgument("/c");
		cmdLine.addArgument(workingdirectory+"\\CosmicRay\\CARI-6M\\CARI-6M.EXE");
		

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

		ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setWatchdog(watchdog);
		executor.setWorkingDirectory(new File(workingdirectory+"\\CosmicRay\\CARI-6M\\"));
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(stdout);
		executor.setStreamHandler(streamHandler);

        
		try {
			streamHandler.start();
			System.out.println(stdout.toString());
			executor.execute(cmdLine, resultHandler);
			resultHandler.waitFor();
			
			
			
			
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
