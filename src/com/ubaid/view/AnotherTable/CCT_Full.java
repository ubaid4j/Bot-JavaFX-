package com.ubaid.view.AnotherTable;

import javafx.beans.property.SimpleStringProperty;


//this class represents the contact
public class CCT_Full
{
	final SimpleStringProperty contact;

	public CCT_Full(String contact, String time, String date, String fileName)
	{
		this.contact = new SimpleStringProperty(contact);
	}
	
	
}
