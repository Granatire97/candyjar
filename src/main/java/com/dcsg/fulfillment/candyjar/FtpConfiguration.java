package com.dcsg.fulfillment.candyjar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
 
public class FtpConfiguration {
    
	Session ftpSession;
	
	public FtpConfiguration () {
		ftpSession = initSession("dkda2331.dcsg.com", "user", "pass".toCharArray());
		ArrayList<String> fileNames = initChannelSftpFiles(ftpSession, "../../apps/syncatc/log");
		for(String fileName : fileNames)
			initChannelSftp(ftpSession, "../../apps/syncatc/log", fileName, "ftpFiles", fileName);
		
		
	}
	
	private String getTimestampForLog()
	{
		String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
		return "[" +  timeStamp + "] ";
	}

	private Session initSession(String server, String username, char[] password){
		Session session = null;
		try
		{
			JSch jsch=new JSch();
			session = jsch.getSession(username, server, 22);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(new String(password));
			session.connect(30000);
			return session;
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println(getTimestampForLog() + "ERROR initializing UNIX session");
			return null;
		}
	}

	//Uses SFTP to locate desired files
	private ArrayList<String> initChannelSftpFiles(Session session, String remotePath)
	{
		ArrayList<String> filesListed = new ArrayList<String>();
	    boolean fileFound = false;
		try
		{
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel;
			channelSftp.cd(remotePath);
			
			Vector list = null;
		    ChannelSftp.LsEntry lsEntry = null;
		    SftpATTRS attrs = null;
		    String nextName = null;

	    	//List and loop through each file on server
	        list = channelSftp.ls("SyncATC.network.activity.*.log");
			if (list.isEmpty()) 
			{
	            System.out.println(getTimestampForLog() + "File found status: " + fileFound + "ERROR No files were found.");
	        }
	        else 
	        {
	            lsEntry = (ChannelSftp.LsEntry) list.firstElement();
	            for (Object sftpFile : list)
	            {	            	
	                lsEntry = (ChannelSftp.LsEntry) sftpFile;
	                nextName = lsEntry.getFilename();
	                attrs = lsEntry.getAttrs();
	                filesListed.add(nextName);
	                
	            }
	        }
			channel.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(getTimestampForLog() + "ERROR selecting file(s)");
		}
		return filesListed;
	}
	
	//Transfers remote file from remote path to local path with local filename using SFTP
	private void initChannelSftp(Session session, String remotePath, String remoteFilename, String localPath, String localFilename)
	{
		try
		{
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp)channel;
			byte[] buffer = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(channelSftp.get(remotePath + "/" + remoteFilename));
			File newFile = new File(localPath + "/" + localFilename);
			OutputStream os = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int readCount;
			while((readCount = bis.read(buffer)) > 0) 
			{
				bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();
			channel.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(getTimestampForLog() + "ERROR transferring file");
		}
	}
	
	
}