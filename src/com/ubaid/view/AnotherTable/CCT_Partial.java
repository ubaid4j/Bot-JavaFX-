package com.ubaid.view.AnotherTable;

import javafx.beans.property.SimpleStringProperty;

public class CCT_Partial 
{
	final SimpleStringProperty contact;
	final SimpleStringProperty time;
	final SimpleStringProperty date;
	final SimpleStringProperty fileName;
	final SimpleStringProperty id;

	
	public CCT_Partial(String id, String fileName, String contact, String date, String time)
	{
		this.id = new SimpleStringProperty(id);
		this.contact = new SimpleStringProperty(contact);
		this.time = new SimpleStringProperty(time);
		this.date = new SimpleStringProperty(date);
		this.fileName = new SimpleStringProperty(fileName);

	}
	
	public String getId()
	{
		return id.get();
	}
	
	public String getContact()
	{
		return contact.get();
	}

	public String getTime() {
		return time.get();
	}

	public String getDate() {
		return date.get();
	}

	public String getFileName() {
		return fileName.get();
	}
	
}
