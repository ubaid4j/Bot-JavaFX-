package com.ubaid.Model;


import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.ubaid.Model.Auth.Authentications_U;
import com.ubaid.Model.Templates.Templates;

import javafx.application.Platform;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



public class EmailSender extends DataBase
{
	
	private Vector<Vector<String>> contactedContacts;
	private int fileNumber;
	private LocalDate uploadDate;
	private LocalTime uploadTime;
	private Timer timer1, timer2, timer3;
	private static final String from = "ubaidshan007@gmail.com";
	private BackEnd_1 BE;
	private File folderName;

	public EmailSender(int tempNumber)
	{
		//creating a directory for botFiles
		String username = System.getProperty("user.home");		
	    File file = new File(username + "/botFiles");
	    //for first time, the directory of Original 
	    File originalTemplate = new File(file.getAbsolutePath() + "/Original_Templates");
		Templates temp = new Templates(tempNumber, null, originalTemplate);
		emailSenderMethod(temp, "ubaidshan007@gmail.com");
	}
	
	public EmailSender(int fileNumber, File folderName)
	{
		
		BE = new BackEnd_1();
		setFileNumber(fileNumber);
		setFolderName(folderName);
		timer1 = new Timer();
		timer2 = new Timer();
		timer3 = new Timer();

		Calendar cal = Calendar.getInstance();

		
		try
		{

			//retrieving the time to upload from compaignForContactedContacts
			//and then setting time in the timer1
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT contact, template, dateToUpload, timeToUpload "
					+ "FROM compaignForContactedContacts "
					+ "WHERE fileNumber = '" + getFileNumber() + "' "
					+ "AND sent = false");
	
			while(resultSet.next())
			{
	
				java.sql.Date uploadDate = resultSet.getDate(3);
				Time uploadTime = resultSet.getTime(4);
				Templates templates = new Templates(resultSet.getInt(2) + 1, "Contacted", getFolderName());
				String contact = resultSet.getString(1).trim();
				LocalDate localDate = uploadDate.toLocalDate();
				LocalTime localTime = uploadTime.toLocalTime();
				cal.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
				cal.set(Calendar.MONTH, localDate.getMonthValue() - 1);
				cal.set(Calendar.YEAR, localDate.getYear());
				
				cal.set(Calendar.HOUR_OF_DAY, localTime.getHour());
				cal.set(Calendar.MINUTE, localTime.getMinute());
				cal.set(Calendar.SECOND, localTime.getSecond());
				
				Date date = cal.getTime();
				timer1.schedule(new SendEmail(templates, contact), date);
			
			}
			
			//retrieving the time to upload from compaignForAttemptedContacts
			//and then setting tiem in the timer2
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT contact, template, dateToUpload, timeToUpload "
					+ "FROM compaignForAttemptedContacts "
					+ "WHERE fileNumber = '" + getFileNumber() + "' "
					+ "AND sent = false");
	
			while(resultSet.next())
			{
	
				java.sql.Date uploadDate = resultSet.getDate(3);
				Time uploadTime = resultSet.getTime(4);
				Templates templates = new Templates(resultSet.getInt(2) + 1, "Attemped Contact Email", getFolderName());
				String contact = resultSet.getString(1).trim();
				LocalDate localDate = uploadDate.toLocalDate();
				LocalTime localTime = uploadTime.toLocalTime();
				cal.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
				cal.set(Calendar.MONTH, localDate.getMonthValue() - 1);
				cal.set(Calendar.YEAR, localDate.getYear());
				
				cal.set(Calendar.HOUR_OF_DAY, localTime.getHour());
				cal.set(Calendar.MINUTE, localTime.getMinute());
				cal.set(Calendar.SECOND, localTime.getSecond());
				
				Date date = cal.getTime();
				timer2.schedule(new SendEmail(templates, contact), date);
							
			}
			
			
			//retrieving the time to upload from compaignForAttemptedContactsSMS
			//and then setting tiem in the timer2
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT phoneNumber, template, dateToUpload, timeToUpload "
					+ "FROM `compaignForAttemptedContactsSMS` "
					+ "WHERE fileNumber = '" + getFileNumber() + "' "
					+ "AND sent = false");
	
			while(resultSet.next())
			{
	
				java.sql.Date uploadDate = resultSet.getDate(3);
				Time uploadTime = resultSet.getTime(4);
				Templates templates = new Templates(resultSet.getInt(2) + 1, "Attemped Contacts SMS", getFolderName());
				String contact = resultSet.getString(1).trim();
				LocalDate localDate = uploadDate.toLocalDate();
				LocalTime localTime = uploadTime.toLocalTime();
				cal.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
				cal.set(Calendar.MONTH, localDate.getMonthValue() - 1);
				cal.set(Calendar.YEAR, localDate.getYear());
				
				cal.set(Calendar.HOUR_OF_DAY, localTime.getHour());
				cal.set(Calendar.MINUTE, localTime.getMinute());
				cal.set(Calendar.SECOND, localTime.getSecond());
				
				Date date = cal.getTime();
				timer3.schedule(new SendHangout(templates, contact), date);
			
			}

			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
	
		
		
	}
	
	public void mainMethod_1()
	{
		
	}
	
	public void setFolderName(File folderName)
	{
		this.folderName = folderName;
	}
	
	public File getFolderName()
	{
		return folderName;
	}

	public void setFileNumber(int fileNumber)
	{
		this.fileNumber = fileNumber;
	}
	
	public int getFileNumber()
	{
		return fileNumber;
	}
	
	public LocalTime getUploadTime()
	{
		return uploadTime;
	}
	
	public LocalDate getUploadDate()
	{
		return uploadDate;
	}
	
	public void setUploadTime(LocalTime time)
	{
		uploadTime = time;
	}
	
	public void setUploadDate(LocalDate date)
	{
		uploadDate = date;
	}
	
	public void setContactedContacts(Vector<Vector<String>> data)
	{
		contactedContacts = data;
	}
	
	public Vector<Vector<String>> getContactedContacts()
	{
		return contactedContacts;
	}
	
	//class for sending emails
	private class SendEmail extends TimerTask
	{
		Templates templates = null;
		String contact = null;
		
		public SendEmail(Templates templates, String contact)
		{
			this.templates = templates;
			this.contact = contact;
		}
		public void run()
		{

			
			try 
			{
				//after the succesfull sending the email, the we invoke the following methods
				if(templates.getContactType() == "Contacted")
				{
					//Test
					//System.out.println("Line 226 executed Successfully");
					if(emailSenderMethod(templates, contact));
					{
						System.out.println("Starting Line: 258");
						System.out.println("File Number: " + getFileNumber());
						System.out.println("Contact: " + contact);
						System.out.println("Template: " + templates.toString());
						System.out.println("Contact Type: " + templates.getContactType());
						System.out.println("Email send successfully");			
						System.out.println("Time: " + LocalTime.now());
						System.out.println("******************************************************************************");
						System.out.println("\n\n");
			
						messageSentSuccessFullForContactedContacts(contact, templates);						
					}
				}
				else
				{
					if(emailSenderMethod(templates, contact));
					{
						System.out.println("Starting Line: 258");
						System.out.println("File Number: " + getFileNumber());
						System.out.println("Contact: " + contact);
						System.out.println("Template: " + templates.toString());
						System.out.println("Contact Type: " + templates.getContactType());
						System.out.println("Email send successfully");			
						System.out.println("Time: " + LocalTime.now());
						System.out.println("******************************************************************************");
						System.out.println("\n\n");

						messageSentSuccessFullForAttemptedContacts(contact, templates);
					}
					
				}

			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
	}
	
	//class for sending hangout
	private class SendHangout extends TimerTask
	{
		Templates templates = null;
		String contact = null;
		
		public SendHangout(Templates templates, String contact)
		{
			this.templates = templates;
			this.contact = contact;
		}
		
		public void run()
		{
			//for testing
/**
			System.out.println("File Number: " + getFileNumber());
			System.out.println("Contact: " + contact);
			System.out.println("Template: " + templates.toString());
			System.out.println("Contact Type: " + templates.getContactType());
			System.out.println("Email send successfully");			
			System.out.println("Time: " + LocalTime.now());
			System.out.println("******************************************************************************");
			System.out.println("\n\n");
*/		
			try
			{
				//after the successfull sending the SMS then
				messageSentSuccessFullForAttemptedContactsSMS(contact, templates);
			}
			catch(Exception exp)
			{
				
			}
		}
		
	}
	
	
	/**
	 * this method takes two parameters
	 * this method sends email to the 
	 * given contact
	 * @param temp Templates
	 * @param contact String
	 */
	public Boolean emailSenderMethod(Templates temp, String contact)
	{
	
		String recieverName = null;
		
		try
		{
			recieverName = BE.getNameOfPerson(contact);			
		}
		catch(Exception exp)
		{
			recieverName = "Test_1[succeeded]";
		}
		
		temp.setReceiverName(recieverName);
		
		String to = contact;
		
		//we can retrieve the subject from the template class
		
//		String text = "Hi" + recieverName +  "The purpose of this mail is only for testing";
//		text += " \nThe Template fo this mail is " + temp.toString() + " and the time is " + " " + LocalTime.now().toString();
		
	    try
	    {
				    	
	 	   // Create a default MimeMessage object.
	 	   Message message = new MimeMessage(Authentications_U.getSession());
	 	
	 	   // Set From: header field of the header.
	 	   message.setFrom(new InternetAddress(from));
	 	
	 	   // Set To: header field of the header.
	 	   message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(to));
	 	
	 	   //this snippet of code add reply to functionality
	 	   message.setReplyTo(new Address[]
	 	   {
	 			   new InternetAddress("urehman.bese16seecs@seecs.edu.pk")
		   });
	 	   

	 	   	 	  
	 	   // Set Subject: header field
	 	   message.setSubject(temp.getSubject());
	 	
	 	   // Now set the actual message
//	 	   message.setContent(temp.getTemplate(), "text/html");




	 	   //multipart create a kinda container
	 	   MimeMultipart multipart = new MimeMultipart("related");
	 	  
	 	   
	 	   
	 	   //set new body part
	 	   BodyPart messageBodyPart = new MimeBodyPart();
	 	   //set content
	 	  
	 	   messageBodyPart.setContent(temp.getTemplate(), "text/html");
	 	   
	 	   //add in multi part	 	   
	 	   multipart.addBodyPart(messageBodyPart,0);
	 	   
	   
	 	   int counter=1;
	 	   for(int i = 0; i < temp.getDataSource().size(); i++)
	 	   {
	 		   messageBodyPart = new MimeBodyPart();
	 		   try
	 		   {
	 			   URL uri = new URL(temp.getDataSource().get(i));
	 			   DataSource fDataSource = new FileDataSource(uri.getPath());
	 			   messageBodyPart.setDataHandler(new DataHandler(fDataSource));
	 			   messageBodyPart.setHeader("Content-ID", "<image" + counter++ + ">");
	 			   multipart.addBodyPart(messageBodyPart);
	 		   }
	 		   catch(Exception exp)
	 		   {
	 			   exp.printStackTrace();
	 		   }
	 	   }
	 	   
	 	   
	 	   //now setting new bodypart for image
//	 	   messageBodyPart = new MimeBodyPart();
//	 	   try
//	 	   {
//	 		   
//		 	   DataSource fDataSource = new FileDataSource("C:\\Users\\UbaidurRehman\\Google Drive\\Logo.jpg");
//		 	   messageBodyPart.setDataHandler(new DataHandler(fDataSource));
//
//	 	   }
//	 	   catch(Exception exp)
//	 	   {
//	 		   System.out.println("No Image Souce");
//	 	   }
//	 	   
//	 	   messageBodyPart.setHeader("Content-ID", "<image1>");	 	   
//	 	   multipart.addBodyPart(messageBodyPart);
	 	   
	 	   
	 	   //put every thing together
	 	   message.setContent(multipart);

	 	   
	 	   // Send message
	 	   Transport.send(message);

	 	   //calling method, to show notification for sending message successfull
	 	   Platform.runLater(showSuccessFulNotification(contact));
	 	   
	 	   return true;

	    }
	    catch (MessagingException e)
	    {
	    
	    	e.printStackTrace();
	    	return false;
//	          throw new RuntimeException(e);
	    }	
	}
	

	private class ShowNotification implements Runnable
	{

		String contact = null;
		
		public ShowNotification(String email)
		{
			contact = email;
		}
		
		
		@Override
		public void run()
		{
			try
			{
				TrayNotification trayNotification = new TrayNotification();
				String title = "Email Sent Successfully";
				NotificationType notificationType = NotificationType.SUCCESS;
				trayNotification.setTitle(title);
				trayNotification.setNotificationType(notificationType);
				String message = String.format("Reciever Email-ID: %s", contact);
				trayNotification.setMessage(message);
				trayNotification.setAnimationType(AnimationType.POPUP);
				trayNotification.showAndDismiss(Duration.seconds(10));
				
				
				
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			
		}
		
	}
	
	
	private Runnable showSuccessFulNotification(String email)
	{
		return new ShowNotification(email);
	}
	
	/**
	 * this method send the hangout
	 * @param phoneNumber
	 * @param temp
	 */
	public void sendHangoutMethod(String phoneNumber, Templates temp)
	{
		
	}
	
	
	/**
	 * This method takes two argument
	 * @param contact String
	 * @param temp	Templates
	 * 
	 * This method set the `sent field` on the mySQL server of the 
	 * specified contact and template 
	 */
	public void messageSentSuccessFullForContactedContacts(String contact, Templates temp)
	{
		try
		{
			//Test//
			//System.out.println("Contact: " + contact);
			//System.out.println("Template: " + (temp.getTemplateNumber() - 1));
			//System.out.println("File Number: " + getFileNumber());
			
	       // update compaingforcontactedcontacts set sent = true where fileNumber = 2 and contact = 'ubaidshan007@hotmail.com' and template = 1;
 		   statement = connection.createStatement();
 		   statement.executeUpdate("UPDATE compaignForContactedContacts "
	 		   		+ "SET sent = true "
	 		   		+ "WHERE fileNumber = " + getFileNumber() + " "
	 		   		+ "AND contact = '" + contact + "' "
	   				+ "AND template = " + (temp.getTemplateNumber() - 1));

 		   //updating status of file when all the emails have been sent [both contacted and attempted]
 		   if(BE.getFileStatus(getFileNumber()))
 		   {
 			   statement = connection.createStatement();
 			   statement.executeUpdate("UPDATE file_record "
 			   		+ "SET status_ = true "
 			   		+ "WHERE fileNumber = " + getFileNumber());
 		   }

		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}

	/**
	 * This method takes two argument
	 * @param contact String
	 * @param temp	Templates
	 * 
	 * This method set the `sent field` on the mySQL server of the 
	 * specified contact and template 
	 */	
	public void messageSentSuccessFullForAttemptedContacts(String contact, Templates temp)
	{
		try
		{
	       // update compaingforcontactedcontacts set sent = true where fileNumber = 2 and contact = 'ubaidshan007@hotmail.com' and template = 1;
 		   statement = connection.createStatement();
 		   statement.executeUpdate("UPDATE compaignForAttemptedContacts "
	 		   		+ "SET sent = true "
	 		   		+ "WHERE fileNumber = " + getFileNumber() + " "
	 		   		+ "AND contact = '" + contact + "' "
	   				+ "AND template = " + (temp.getTemplateNumber() - 1));


		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}
	
	/**
	 * This method takes two argument
	 * @param contact String
	 * @param temp	Templates
	 * 
	 * This method set the `sent field` on the mySQL server of the 
	 * specified contact and template 
	 */	

	public void messageSentSuccessFullForAttemptedContactsSMS(String contact, Templates temp)
	{
		try
		{
	       // update compaingforcontactedcontacts set sent = true where fileNumber = 2 and contact = 'ubaidshan007@hotmail.com' and template = 1;
 		   statement = connection.createStatement();
 		   statement.executeUpdate("UPDATE compaignForAttemptedContactsSMS "
	 		   		+ "SET sent = true "
	 		   		+ "WHERE fileNumber = " + getFileNumber() + " "
	 		   		+ "AND phoneNumber = '" + contact + "' "
	   				+ "AND template = " + (temp.getTemplateNumber() - 1));


		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}


	
	
/*		
	public void emailSenderMethodForAttemptedContacts(int tempNumber)
	{
	
		
		for(int i = 0; i < 0; i++)
		{
			
			String recieverName = null;
			String to = null;
			
			//we can retrieve the subject from the template class
			String subject = "Testing";
			String text = "Hi" + recieverName +  "The purpose of this mail is only for testing";
			text += " \nThe tempNumber fo this mail is " + tempNumber + " and the time is " + " " + LocalTime.now().toString();
		    try
		    {
					    	
		 	   // Create a default MimeMessage object.
		 	   Message message = new MimeMessage(Authentications_U.getSession());
		 	
		 	   // Set From: header field of the header.
		 	   message.setFrom(new InternetAddress(from));
		 	
		 	   // Set To: header field of the header.
		 	   message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse(to));
		 	
		 	   // Set Subject: header field
		 	   message.setSubject(subject);
		 	
		 	   // Now set the actual message
		 	   message.setText(text);

		 	   // Send message
		 	   Transport.send(message);

		 	   System.out.println("Sent message successfully....");

		    }
		    catch (MessagingException e)
		    {
		          throw new RuntimeException(e);
		    }

		}
			
	}
*/

}
