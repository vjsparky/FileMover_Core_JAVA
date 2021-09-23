package sftpProgram;

import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Timer;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;

import sftpProgram.DBConnection.*;
import sftpProgram.Repository.*;
import sftpProgram.entities.*;


public class SftpConnectionMain extends TimerTask {

//	static Logger log = (Logger) LogManager.getLogger(SftpConnectionMain.class);
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Application Will run for 5 mins and File transfer scheduled for every 1 mins");
		Timer time = new Timer(); // Instantiate Timer Object
		SftpConnectionMain st = new SftpConnectionMain();
		time.schedule(st, 0, 100000); // Create Repetitively task for every 1 secs

		for (int i = 0; i <= 5; i++) {
			System.out.println("Application Running");
			Thread.sleep(100000);
			if (i == 5) {
				System.out.println("Application Terminates");
				System.exit(0);
			}
		}

	}

	public void run() {
		boolean check = SftpRepos.CheckFile(SftpEntities.localInputFilepath);
//		boolean checkdir =SftpRepos.CheckFile(SftpEntities.localdir);

//		if (check && checkdir)
		if (check) {
			SftpRepos obj1 = new SftpRepos();
			String[] FileList = obj1.FileList(SftpEntities.localInputFilepath);
			for (String InputFile : FileList) {
				// for getting file pass paramater [targe,localdir]
				ArrayList<String> transferData = obj1.FileTransfer(SftpEntities.Key, InputFile,SftpEntities.remotelocation);
				UpdateDB.updateDBStatus(transferData.get(0), transferData.get(1), transferData.get(2));
				System.out.println("File Transfer is completed : " + InputFile);
			}
			System.out.println("Successfully updated database and transfered file to aws server of batch : "+FileList.length);
			SftpRepos.DeleteFiles(SftpEntities.localInputFilepath);
		} else {
//			log.error("Incorrect File Path or Directory....Please Check");
//			log.error("Shutdown Completed....");
			System.out.print("Can't make Connection");
		}

	}
	
}