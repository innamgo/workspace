package com.jejuair.cosmicray.core;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;


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
		cmdLine.addArgument(workingdirectory+"\\CARI-6M\\CARI-6M.EXE");

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

		ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setWatchdog(watchdog);
		try {
			executor.execute(cmdLine, resultHandler);
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// some time later the result handler callback was invoked so we
		// can safely request the exit value
		try {
			resultHandler.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
