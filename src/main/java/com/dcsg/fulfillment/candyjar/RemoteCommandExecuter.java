package com.dcsg.fulfillment.candyjar;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RemoteCommandExecuter{
 
 private String username;
 private String password;
 private String host;
 private int port;
 
 public RemoteCommandExecuter(String username, String password, String host, int port) {
	 this.username = username;
	 this.password = password;
	 this.host = host;
	 this.port = port;
 }
    
 public List<String> executeCommand(String command)
 {
	 
     List<String> result = new ArrayList<String>();
     try
     {
         JSch jsch = new JSch();

         Session session = jsch.getSession(username, host, port);
         session.setConfig("StrictHostKeyChecking", "no");
         session.setPassword(password);
         session.connect();

         ChannelExec channelExec = (ChannelExec)session.openChannel("exec");

         InputStream in = channelExec.getInputStream();

         channelExec.setCommand(command);

         channelExec.connect();

         BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         String line;
         
         while ((line = reader.readLine()) != null)
         {
             result.add(line);
         }

         channelExec.disconnect();
         session.disconnect();
     }
     catch(Exception e)
     {
         System.err.println("Error: " + e);
     }
     return result;
 }
 
}