package com.ubaid.Model;

import javafx.beans.property.SimpleStringProperty;

public class TemplateForTable
{
	private final SimpleStringProperty template;
	private final SimpleStringProperty status;
	
	public TemplateForTable(String template, String status)
	{
		this.template = new SimpleStringProperty(template);
		this.status = new SimpleStringProperty(status);
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template.get();
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status.get();
	}

	
}
