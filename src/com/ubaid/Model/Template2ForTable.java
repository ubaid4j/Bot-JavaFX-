package com.ubaid.Model;

import javafx.beans.property.SimpleStringProperty;

public class Template2ForTable
{
	private final SimpleStringProperty temp;
	private final SimpleStringProperty stat;
	
	public Template2ForTable(String template2, String status2)
	{
		this.temp = new SimpleStringProperty(template2);
		this.stat = new SimpleStringProperty(status2);
	}

	/**
	 * @return the template
	 */
	public String getTemp() {
		return temp.get();
	}

	/**
	 * @return the status
	 */
	public String getStat() {
		return stat.get();
	}

	
}
