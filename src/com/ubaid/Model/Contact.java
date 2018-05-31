package com.ubaid.Model;

import javafx.beans.property.SimpleStringProperty;

public class Contact
{
	public final SimpleStringProperty name;
	public final SimpleStringProperty email;
	public final SimpleStringProperty phone;
	public final SimpleStringProperty address;
	
	public Contact(String name, String emailAddress, String phoneNumber, String address)
	{
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(emailAddress);
		this.phone = new SimpleStringProperty(phoneNumber);
		this.address = new SimpleStringProperty(address);
	}

	public String getName()
	{
		return name.get();
	}
	
	
	public String getEmail()
	{
		return email.get();
	}
	
	
	public String getPhone()
	{
		return phone.get();
	}
	
	
	public String getAddress()
	{
		return address.get();
	}
}
