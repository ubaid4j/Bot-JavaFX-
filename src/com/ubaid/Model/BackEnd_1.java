package com.ubaid.Model;

import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;
import java.util.Vector;
import com.ubaid.Model.Objects.StatusObject;


public class BackEnd_1 extends DataBase
{
	
	Random random = new Random();
	
	public BackEnd_1()
	{
		
	}
	
	public Boolean isCorrect(char[] pass, String userName)
	{
		Boolean flag = false;
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT userName "
					+ "FROM users "
					+ "WHERE userName = '" + userName + "'");
			
			if(resultSet.next())
			{
				flag = isPassKeyCorrect(pass, userName);
			}
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return flag;
	}
	
	//this method determine if the passkey of the player is correct
	public Boolean isPassKeyCorrect(char [] password, String userName)
	{
		Boolean flag = false;
		String passKey= "";
		try
		{
			statement = connection.createStatement();
			for(int i = 0; i < password.length; i++)
			{
				passKey = passKey + password[i];
			}
			
			resultSet = statement.executeQuery("SELECT userName "
					+ "FROM users "
					+ "WHERE passkey = '" + passKey + "'");
			
			
			if(resultSet.next())
			{
				flag = true;
			}
			else
				flag = false;
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return flag;
	}
	
	//this method return the name of all uploaded files
	public Vector<String> getFilesNames()
	{
		Vector<String> fileNames = new Vector<String>();
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT fileName FROM file_record");
			while(resultSet.next())
			{
				String name = resultSet.getString(1);
				fileNames.add(name);
			}
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return fileNames;
	}
	
	
	
	
	//this method returns all the files and their upload date
	public Vector<String> getFiles()
	{
		Vector<String> fileNames = new Vector<String>(); 
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT fileName, upload_date, updoad_time "
					+ "FROM file_record");
			while(resultSet.next())
			{
				String info = resultSet.getString(1);
				info += " : ";
				info += resultSet.getString(2);
				info += " : ";
				info += resultSet.getString(3);
				fileNames.addElement(info);
			}
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return fileNames;
	}
	
	//return the upload Date of the file
	public Date getFileUploadDate(String fileName)
	{
		Date date = null;
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT upload_date FROM file_record where fileName = '" + fileName + "'");
			resultSet.next();
			date = resultSet.getDate(1);
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return date;
	}
	
	//return the upload time of the file
	public Time getFileUploadTime(String fileName)
	{
		Time time = null;
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT updoad_time FROM file_record where fileName = '" + fileName + "'");
			resultSet.next();
			time = resultSet.getTime(1);
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return time;
	}
	
	
	
	//this method return if the following is file is present in the file_record
	public Boolean isFileNamePresent(String fileName)
	{
		Boolean flag = false;

		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT fileName FROM file_record WHERE fileName = '" + fileName + "'");
			
			if(resultSet.next())
				flag = true;

		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return flag;
	}
	
	//return file number taking fileName
	public int getFileNumber(String fileName)
	{
		int fileNumber = 0;
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT fileNumber FROM file_record WHERE fileName = '" + fileName + "'");
			resultSet.next();
			fileNumber = resultSet.getInt(1);
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return fileNumber;
	}
	
	
	//this method return the data of contacted contacts
	public Vector<Vector<String>> getContactedContacts(String fileName)
	{
		Vector<Vector<String>> outerVector = new Vector<Vector<String>>();
		
		try
		{		
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT concat(first_name, ' ', mid_name, ' ', last_name), "
					+ "emailAddress, phoneNumber, address "
					+ "FROM contacts_name inner join contacts using(id) "
					+ "WHERE EXISTS (SELECT * FROM "
					+ "contactedcontacts "
					+ "WHERE contactedcontacts.emailAddress = contacts.emailAddress) "
					+ "AND fileNumber = " + getFileNumber(fileName));
			
			while(resultSet.next())
			{
				Vector<String> innerVector = new Vector<String>();
				for(int i = 1; i <= 4; i++)
				{
					innerVector.addElement(resultSet.getString(i));
				}
				
				outerVector.addElement(innerVector);
			}
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return outerVector;
	}
		
	//this method return the data of attempted contacts
	public Vector<Vector<String>> getAttemptedContacts(String fileName)
	{
		Vector<Vector<String>> outerVector = new Vector<Vector<String>>();
		
		try
		{		
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT concat(first_name, ' ', mid_name, ' ', last_name), "
					+ "emailAddress, phoneNumber, address "
					+ "FROM contacts_name inner join contacts using(id) "
					+ "WHERE NOT EXISTS (SELECT * FROM "
					+ "contactedcontacts "
					+ "WHERE contactedcontacts.emailAddress = contacts.emailAddress) "
					+ "AND fileNumber = " + getFileNumber(fileName));
			
			while(resultSet.next())
			{
				Vector<String> innerVector = new Vector<String>();
				for(int i = 1; i <= 4; i++)
				{
					innerVector.addElement(resultSet.getString(i));
				}
				
				outerVector.addElement(innerVector);
			}
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return outerVector;
	}
	
	
	
	//this method write a compaign for contacted contacts
	public void writeCompaignOnServerForContactedContacts(String fileName, Vector<Vector<String>> data)
	{
		
		
	
		//retrieving fileNumber
		int fileNumber = getFileNumber(fileName);
		
		//Retrieving upload time and date of this file
		Date upLoadDate = getFileUploadDate(fileName);
		Time uploadTime = getFileUploadTime(fileName);
		LocalTime currentTime = uploadTime.toLocalTime();
		LocalDate currentDate = upLoadDate.toLocalDate();
				
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, currentDate.getDayOfMonth());
		cal.set(Calendar.MONTH, currentDate.getMonthValue() - 1);
		cal.set(Calendar.YEAR, currentDate.getYear());
		cal.set(Calendar.HOUR_OF_DAY, currentTime.getHour());
		cal.set(Calendar.MINUTE, currentTime.getMinute());
		cal.set(Calendar.SECOND, currentTime.getSecond());

		
		java.util.Date date2 = cal.getTime();
		Date date = null;
		Time time = null;
			
		try
		{
			statement = connection.createStatement();
			for(int i = 0; i < 16; i++)
			{
				
				if(i == 0)
				{
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}
				else if(i % 2 == 0)
				{
					//for even templates
					cal.add(Calendar.MINUTE, 4);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}
				else if(i % 2 != 0)
				{
					//for odd template
					cal.add(Calendar.MINUTE, 3);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}


				for(int j = 0; j < data.size(); j++)
				{
					statement.executeUpdate("INSERT INTO `compaignForContactedContacts` "
							+ "VALUES(" + fileNumber + ", '" + data.get(j).get(1) + "', " + i + ",'" + date + "','" + time + "',"  + 0 + ")");

				}

			}
			
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}//ending of writeCompaigOnServerForAttemptedContacts
	
	
	//this method write a compaign on server for attemped contacts
	public void writeCompaignOnServerForAttemptedContacts(String fileName, Vector<Vector<String>> data)
	{
		
		
	
		//retrieving fileNumber
		int fileNumber = getFileNumber(fileName);
		
		//Retrieving upload time and date of this file
		Date upLoadDate = getFileUploadDate(fileName);
		Time uploadTime = getFileUploadTime(fileName);
		LocalTime currentTime = uploadTime.toLocalTime();
		LocalDate currentDate = upLoadDate.toLocalDate();
				
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, currentDate.getDayOfMonth());
		cal.set(Calendar.MONTH, currentDate.getMonthValue() - 1);
		cal.set(Calendar.YEAR, currentDate.getYear());
		cal.set(Calendar.HOUR_OF_DAY, currentTime.getHour());
		cal.set(Calendar.MINUTE, currentTime.getMinute());
		cal.set(Calendar.SECOND, currentTime.getSecond());

		
		java.util.Date date2 = cal.getTime();
		Date date = null;
		Time time = null;
			
		try
		{
			statement = connection.createStatement();
			for(int i = 0; i < 8; i++)
			{
				
				if(i == 0)
				{
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}
				else if(i % 2 == 0)
				{
					//for even templates
					cal.add(Calendar.MINUTE, 3);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}
				else if(i % 2 != 0)
				{
					//for odd template
					cal.add(Calendar.MINUTE, 4);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}


				for(int j = 0; j < data.size(); j++)
				{
					statement.executeUpdate("INSERT INTO `compaignForAttemptedContacts` "
							+ "VALUES(" + fileNumber + ", '" + data.get(j).get(1) + "', " + i + ",'" + date + "','" + time + "',"  + 0 + ")");

				}

			}
			
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}//ending of method writecompaignForAttemptedcontacts on server

	//this method write a compaign on server for attemped contacts and for SMS
	public void writeCompaignOnServerForAttemptedContactsSMS(String fileName, Vector<Vector<String>> data)
	{
		
		
	
		//retrieving fileNumber
		int fileNumber = getFileNumber(fileName);
		
		//Retrieving upload time and date of this file
		Date upLoadDate = getFileUploadDate(fileName);
		Time uploadTime = getFileUploadTime(fileName);
		LocalTime currentTime = uploadTime.toLocalTime();
		LocalDate currentDate = upLoadDate.toLocalDate();
				
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, currentDate.getDayOfMonth());
		cal.set(Calendar.MONTH, currentDate.getMonthValue() - 1);
		cal.set(Calendar.YEAR, currentDate.getYear());
		cal.set(Calendar.HOUR_OF_DAY, currentTime.getHour());
		cal.set(Calendar.MINUTE, currentTime.getMinute());
		cal.set(Calendar.SECOND, currentTime.getSecond());

		
		java.util.Date date2 = cal.getTime();
		Date date = null;
		Time time = null;
			
		try
		{
			statement = connection.createStatement();
			for(int i = 0; i < 8; i++)
			{
				
				if(i == 0)
				{	cal.add(Calendar.MINUTE, 1);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}
				else if(i % 2 == 0)
				{
					//for even templates
					cal.add(Calendar.MINUTE, 3);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}
				else if(i % 2 != 0)
				{
					//for odd template
					cal.add(Calendar.MINUTE, 4);
					date2 = cal.getTime();
					date = new Date(date2.getTime());
					time = new Time(date2.getTime());
				}


				for(int j = 0; j < data.size(); j++)
				{
			
					statement.executeUpdate("INSERT INTO `compaignForAttemptedContactsSMS` "
							+ "VALUES(" + fileNumber + ", '" + data.get(j).get(2) + "', " + i + ",'" + date + "','" + time + "',"  + 0 + ")");

				}

			}
			
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
	}//ending of method writecompaignForAttemptedcontacts on server

	
	
	
	
	//this method return the name of person taking its mail address
	public String getNameOfPerson(String email)
	{
		String name = null;
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT concat(first_name, ' ', mid_name, ' ', last_name) "
					+ "FROM contacts_name join contacts using(id) "
					+ "WHERE emailAddress = '" + email + "'");
			resultSet.next();
			name = resultSet.getString(1);
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return name;
	}
	
	
	//this method, return an list of list containing the templates
	//information about the contact
	public Vector<StatusObject> getStatus(String contact, String tableName, String fileName)
	{
		StatusObject statusObject;
		Vector<StatusObject> vector = new Vector<StatusObject>();
		
		try
		{
			int fileNumber = getFileNumber(fileName);
			tableName = tableName.trim();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT template, datetoupload, timetoupload, sent from " + tableName 
				+ " where fileNumber = " + fileNumber + " and contact = \"" + contact + "\"");
			
			while(resultSet.next())
			{
				statusObject = new StatusObject(resultSet.getString(1), resultSet.getDate(2), resultSet.getTime(3), resultSet.getBoolean(4));
				vector.add(statusObject);
			}
		}
		catch(SQLException exception)
		{
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return vector;
	}
	
	private String getContactNumber(String contactEmail, int fileNumber)
	{
		String phoneNumber = null;
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT `phoneNumber` from `contacts` "
					+ "WHERE `fileNumber` = " + fileNumber + " AND emailAddress = '" + contactEmail + "'");
			resultSet.next();
			
			phoneNumber = resultSet.getString(1);
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return phoneNumber;
	}
	
	
	public Vector<StatusObject> getStatusForSMS(String contact, String fileName)
	{
		StatusObject statusObject;
		Vector<StatusObject> vector = new Vector<StatusObject>();
		
		try
		{
			int fileNumber = getFileNumber(fileName);
			String phoneNumber = getContactNumber(contact, fileNumber);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT template, datetoupload, timetoupload, sent from `compaignforattemptedcontactssms`" 
				+ " where fileNumber = " + fileNumber + " and `phoneNumber` = \"" + phoneNumber + "\"");
			
			while(resultSet.next())
			{
				statusObject = new StatusObject(resultSet.getString(1), resultSet.getDate(2), resultSet.getTime(3), resultSet.getBoolean(4));
				vector.add(statusObject);
			}
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return vector;
		
	}
	
	/**
	 * this method takes
	 * @param fileNumber
	 * and 
	 * @return true if emails and msgs have been sent to all the contacts of file 
	 */
	public Boolean getFileStatus(int fileNumber)
	{
		Boolean status = null;
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT count(template) from compaignforcontactedcontacts "
					+ "WHERE fileNumber = " + fileNumber + " AND `sent` = FALSE");
			resultSet.next();
			if(resultSet.getInt(1) == 0)
			{
				resultSet = statement.executeQuery("SELECT count(template) from compaignforattemptedcontacts "
						+ "WHERE fileNumber = " + fileNumber + " AND `sent` = FALSE");
				resultSet.next();
				if(resultSet.getInt(1) == 0)
				{
					resultSet = statement.executeQuery("SELECT count(template) from compaignforattemtedcontactssms "
							+ "WHERe fileNumber = " + fileNumber + " AND `sent` =  FALSE");
					resultSet.next();
					
					if(resultSet.getInt(1) == 0)
						status = true;
					else
						status = false;
				}
				else
					status = false;
			}
			else
				status = false;
			
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return status;
	}
	
	//this method schedule those emails which are sent yet
	public void schedulringNotSentEmails_() throws SQLException
	{
		
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT fileName FROM file_record "
				+ "WHERE status_ = false");
		while(resultSet.next())
		{
			String fileName = resultSet.getString(1);
			int fileNumber = getFileNumber(fileName);
	
			String pathName = System.getProperty("user.home");
			fileName = fileName.substring(0, fileName.length() - 4);
			String path = pathName + "/" + "botFiles" + "/" + fileName;
			File file = new File(path);				
			new EmailSender(fileNumber, file);
		}

	}
	
	/**
	 * 
	 * @return all the contacted contacts in the contacted contacts table
	 */
	public Vector<Vector<String>> getAllContactsInContactedTable()
	{
		
		Vector<String> innerData; 
		Vector<Vector<String>> outerData = new Vector<Vector<String>>(); 
		
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT id, fileName, emailAddress, date_to_upload, time_to_upload FROM contactedContacts");
			while(resultSet.next())
			{
				innerData = new Vector<String>();
				
				for(int i = 0; i < 5; i++)
				{
					innerData.add(resultSet.getString(i + 1));
				}
				
				outerData.add(innerData);
			}
			
			return outerData;
		}
		catch(SQLException exp)
		{
			exp.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param contact which is actually gmail
	 * @param fileName_or_Type name of the file
	 * 
	 * and it add the contact in the contacted contacts table in DB
	 */
	public void addDataInContactedContactsTable(String contact, String fileName_or_Type)
	{
		try
		{
			Calendar calendar = Calendar.getInstance();
			java.util.Date date2 = calendar.getTime();
			Date date = new Date(date2.getTime());
			Time time = new Time(date2.getTime());
			
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO contactedContacts(fileName, date_to_upload, time_to_upload, emailAddress) "
					+ "VALUES('" + fileName_or_Type + "', '" +  date + "', '" + time + "', '" + contact + "')");
					
		}
		catch(SQLException exp)
		{
			throw new IllegalArgumentException("Do not enter duplicate emails address");
		}
	}
	
	/**
	 * this method actually delete the row from the contactedcontact table in the DB using id of the row
	 * @param id
	 */
	public void deleteRowInCCTByID(String id)
	{
		try
		{
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM contactedContacts where id = " + id);
		}
		catch(SQLException exp)
		{
			throw new IllegalArgumentException("Can not be delete");
		}
	}
}
