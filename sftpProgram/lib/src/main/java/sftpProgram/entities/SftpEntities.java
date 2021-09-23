package sftpProgram.entities;

import sftpProgram.Repository.SftpRepos;

public class SftpEntities {

	
	public static String localInputFilepath=SftpRepos.readingFile("localInputFilepath");
	public static String remotelocation=SftpRepos.readingFile("remotelocation");
	
	public static String target="/home/ec2-user/test.txt";
	public static String localdir="C:\\Users\\vijay\\Documets\\";
	public static String Key=SftpRepos.readingFile("key");
	
	
	
	
	//DB Details
	public static String dbdriver=SftpRepos.readingFile("dbdriver");
	public static String dburl=SftpRepos.readingFile("dburl");
	public static String username=SftpRepos.readingFile("username");
	public static String password =SftpRepos.readingFile("password");
	public static String fileStatus="";
	public static String fileName="";
	public static String date="";
	
	
//	public static String dbdriver="com.mysql.jdbc.Driver";
//	public static String dburl="jdbc:mysql://localhost:3306/vijay?characterEncoding=latin1&useConfigs=maxPerformance";
//	public static String username="root";
//	public static String password ="Sparky@6@21998";
//	public static String fileStatus="";
//	public static String fileName="";
//	public static String date="";
	
	
	
	
	
}
