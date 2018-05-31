package com.ubaid;


import java.sql.SQLException;
import java.util.Vector;


import com.ubaid.Model.BackEnd_1;
import com.ubaid.Model.DataBase;

public class Test extends DataBase
{
	public static void main(String [] args)
	{
		Test test = new Test();
		test.doSomeThing();
	}
	
	public void doSomeThing()
	{
		try
		{
			BackEnd_1 bEnd_1 = new BackEnd_1();
			Vector<Vector<String>> date = bEnd_1.getAttemptedContacts("ubaidContacts.csv");
			for(int i = 0; i < date.size(); i++)
			{
				for(int j = 0; j < date.get(i).size(); j++)
				{
					System.out.print(date.get(i).get(j) + " ");
				}
				System.out.println();
			}
		}
		catch(Exception exp)
		{
			System.out.println("Exception");
		}
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
					+ "AND fileNumber = " + 1);
			
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


}