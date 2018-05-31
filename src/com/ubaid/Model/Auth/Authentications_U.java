package com.ubaid.Model.Auth;

import javax.mail.Session;
import java.util.Properties;
import javax.mail.PasswordAuthentication;

public class Authentications_U 
{
	//vkdtlildyzdnvjas
	private static String username = "ubaidshan007@gmail.com";
	private static String password = "password";

	
	
		
	public static Session getSession()
	{

		String host = "smtp.gmail.com";
	
		Session session = null;
		try
		{
			Properties props = new Properties();
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", host);
		    props.put("mail.smtp.port", "25");
		    
		    //for second computer
//			props.put("mail.smtp.user", username);
//		    props.put("mail.smtp.host", host);
//		    props.put("mail.smtp.port", "465");
//		    props.put("mail.smtp.starttls.enable","true");
//		    props.put("mail.smtp.debug", "true");
//		    props.put("mail.smtp.auth", "true");
//		    props.put("mail.smtp.socketFactory.port", "465");
//		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		    props.put("mail.smtp.socketFactory.fallback", "false");

		    
		    session = Session.getInstance(props,
		            new javax.mail.Authenticator()
		    		{
		            	protected PasswordAuthentication getPasswordAuthentication()
		            	{
		            		return new PasswordAuthentication(username, password);
		            	}
		            });
		    
		    return session;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			getSession();
		}
		
		return session;
		
	}
}
