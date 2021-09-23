package sftpProgram.Repository;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jcraft.jsch.*;

import sftpProgram.entities.SftpEntities;

public class SftpRepos {
	
	public static boolean CheckFile(String file) {
		File f = new File(file);
		if (f.exists()) {
			return true;
			//log.info("verified the path or file location of : "+f);
		}
			else {
			//log.e
			return false;
		}
	}

	public String[] FileList(String path)
	{
		File dir = new File(path);
		String[] files = dir.list();
        return files;
	}
	public static void DeleteFiles(String path)
	{
		File dir = new File(path);	
		for(File Filenames:dir.listFiles())
		{
			Filenames.delete();
		}
	}
	public static String readingFile(String parameter) 
	{
		String Value="";
		Path filePath = Paths.get("V:\\Softwares", "Property-File.txt");
		try (Stream<String> lines = Files.lines(filePath)) {
			List<String> filteredLines = lines.filter(s -> s.contains(parameter)).collect(Collectors.toList());	      
		     for(String i:filteredLines)
		     {
			  String [] value =i.split("==");
			  Value=value[value.length-1];
		     }
		}catch(IOException e ) {
			System.out.println("Properties File not Found.. Application Shutdowns");
			System.exit(0);}
		return Value;
	}
		
	//for getting file pass paramater [targetdir,localfilename]
	public ArrayList <String> FileTransfer(String Privatekey,String inputFile,String remote)
	{
	    ArrayList<String> data = new ArrayList<String>();
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);  
	    SftpEntities.date=date.toString();
	    
		try {
		JSch jsch = new JSch();
		Session session = null;
		session = jsch.getSession("ec2-user","13.234.232.58",22);
		jsch.addIdentity(Privatekey);
		session.setConfig("StrictHostKeyChecking","no");
		session.connect();
		Channel sftp = session.openChannel("sftp");
		sftp.connect();
		ChannelSftp sftpchannel = (ChannelSftp) sftp;
		sftpchannel.put(SftpEntities.localInputFilepath+"\\"+inputFile,remote);
		//sftpchannel.get(targetdir,localfilename);
		sftpchannel.exit();
		session.disconnect();
		
		SftpEntities.fileStatus="Success";
		File f = new File(inputFile);
		SftpEntities.fileName=f.getName();
		    
	    data.add(SftpEntities.fileName);
	    data.add(SftpEntities.fileStatus);
	    data.add(SftpEntities.date);
	    
		}
		catch(SftpException | JSchException e )
		{
			System.out.print(e);		    
		    
			SftpEntities.fileStatus="Failed";
			File f = new File(inputFile);
			SftpEntities.fileName=f.getName();
			    
		    data.add(SftpEntities.fileName);
		    data.add(SftpEntities.fileStatus);
		    data.add(SftpEntities.date);
		}
		return data;
		
	}
	
}
