package com.ubaid.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


public class CSVReader extends DataBase
{

	private static final String delimiter = ",";
	public static final char[] digitArray = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	String fileName = "";
	
		
	public enum Type
	{
		ContactedContacts;
	}
	
	public CSVReader(File file, Type type) throws IllegalArgumentException
	{
		if(type == Type.ContactedContacts)
		{
			BufferedReader br = null;
			FileReader fr = null;
			
			try
			{
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				fileName = file.getName();
				String line = "";
				int index = 0;
				
				
				statement = connection.createStatement();
				String date = LocalDate.now().toString();
				String time = LocalTime.now().toString();
				
				while((line = br.readLine()) != null)
				{
					if(index != 0)
					{
						if(line.split(",").length != 1)
							throw new IllegalArgumentException("Check your CSV File\nThere is error in your CSV File\nFollow the Pattern\n[CSV-50]");
							
							
						line = line.trim();
						line = line.replace(",", "");
						line = line.trim();
						statement.executeUpdate("INSERT INTO contactedContacts(fileName, date_to_upload, time_to_upload, emailAddress) "
								+ "VALUES('" + fileName + "','" + date + "', '" + time + "', '" + line + "')");

					}
					
					index = index + 1;
				}
			}
			catch(IOException exp)
			{
				throw new IllegalArgumentException("There is an error in IO [Line 58, CSV Reader]");
			}
			catch(SQLException exp)
			{
				throw new IllegalArgumentException("There is an error in SQL [Line 62, CSV Reader]");
			}
			catch(Exception exp)
			{
				throw new IllegalArgumentException("There is unknown error [Line 66, CSV Reader]");
			}
			finally
			{
				try
				{
					br.close();
				}
				catch(IOException exp)
				{
					throw new IllegalArgumentException("There is an error in IO [Line 76, CSV Reader]");
				}
			}
			
			System.out.println("Why 80");
		}
	}
	
	
	public CSVReader(File file) throws IllegalArgumentException
	{
		
		BufferedReader br = null;
		FileReader fr;
		
		try
		{
			
			fr = new FileReader(file);
		    br = new BufferedReader(fr);
			fileName = file.getName();
			String line = "";
			
			String[] tempArr;
			int index = 0;
			
			
			int fileNumber = 0;
			try
			{
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT max(fileNumber) from file_record");
				resultSet.next();
				if(resultSet.getObject(1) == null)
				{
					fileNumber = 1;
				}
				else
				{
					fileNumber = resultSet.getInt(1);
					fileNumber = fileNumber + 1;
				}
				
		
				String date = LocalDate.now().toString();
				String time = LocalTime.now().toString();
				
				statement.executeUpdate("INSERT INTO file_record "
						+ "VALUES(" + fileNumber + ", '" + fileName + "', '" + date + "', '" + time +  "', false )");
				
			}
			catch(SQLException exp)
			{
				throw new IllegalArgumentException("There is SQL exception in line 62 of CSVReader");
			}
			
			
			
			
			while ((line = br.readLine()) != null)
			{
				tempArr = line.split(delimiter);
				
				if(index != 0)
				{
					if(tempArr.length != 4)
					{
						statement.executeUpdate("DELETE FROM file_record where fileNumber = " + fileNumber);
						throw new IllegalArgumentException("Check your CSV File\nThere is error in your CSV File\nFollow the Pattern\n[CSV-150]");						
					}
						
					
					try
					{
						String phoneNumber = changePhoneFormat(tempArr[1]);
						
						//address
						String address = "";
						try
						{
							address = tempArr[3];
						}
						catch(IndexOutOfBoundsException exp)
						{
							address = "No Address";
						}
						
						
						statement = connection.createStatement();
						statement.executeUpdate("INSERT INTO contacts(fileNumber, emailAddress, phoneNumber, address) "
									+ "VALUES(" + fileNumber + ",'" + tempArr[2].trim() + "', '" + phoneNumber + "', '" + address + "')");

						
						String name = tempArr[0];
						String[] nameArray = name.split(" ");
						
						String first_name = "";
						String last_name = "";
						String mid_name = "";
						
						if(nameArray.length == 3)
						{
							first_name = nameArray[0];
							mid_name = nameArray[1];
							last_name = nameArray[2];
						}
						else if(nameArray.length == 2)
						{
							first_name = nameArray[0];
							last_name = nameArray[1];
						}
						else
						{
							String s = nameArray[0];
							nameArray = s.split("(?=\\p{Lu})");
						
							if(nameArray.length == 3)
							{
								first_name = nameArray[0];
								mid_name = nameArray[1];
								last_name = nameArray[2];
							}
							else if(nameArray.length == 2)
							{
								first_name = nameArray[0];
								last_name = nameArray[1];
							}
							else
							{
								first_name = nameArray[0];
							}
						}
						
						first_name = first_name.substring(0, 1).toUpperCase() + first_name.substring(1).toLowerCase();
						if(!mid_name.isEmpty())
							mid_name = mid_name.substring(0, 1).toUpperCase() + mid_name.substring(1).toLowerCase();
						if(!last_name.isEmpty())
							last_name = last_name.substring(0, 1).toUpperCase() + last_name.substring(1).toLowerCase();
						
						resultSet = statement.executeQuery("SELECT max(id) from contacts");
						resultSet.next();
						
						int id = resultSet.getInt(1);
						
						statement = connection.createStatement();
						statement.executeUpdate("INSERT INTO contacts_name "
								+ "VALUES('" + first_name + "', '" + mid_name + "', '" + last_name + "', " + id +  ")");
						
						
					}
					catch(SQLException exp)
					{
						throw new IllegalArgumentException("There is SQL Exception at Line 233 in CSV Reader");
					}
				}
				
				index += 1;
				
			}

//			br.close();

		} 
		catch (IOException ex)
		{
			throw new IllegalArgumentException("This exception raised due to some error in IO [Line 246 CSV Reader]");
		}
		catch(Exception exp)
		{
			throw new IllegalArgumentException("This exception raised due to some unkonwn error. [Line 250 CSV Reader]");
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(IOException ex)
			{
				throw new IllegalArgumentException("This exception raised due to error in IO [Line 260 CSV Reader]");
			}
		}

	}
	
	
	private String changePhoneFormat(String number)
	{
		char[] numberArray = number.toCharArray();
		String number1 = "";
		for(int i = 0; i < numberArray.length; i++)
		{
			for(int j = 0; j < digitArray.length; j++)
			{
				if(numberArray[i] == digitArray[j])
				{
					number1 += numberArray[i];
					break;
				}
			}
		}
		
		if(number1.length() != 10)
			number1 = number1.substring(1);
		
		return number1;
	}


}