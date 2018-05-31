package com.ubaid.Model.Objects;

import java.sql.Date;
import java.sql.Time;


/**
 * this class includes some fields
 * 1. Tepmplate, its status, 
 * @author UbaidurRehman
 *
 */
public class StatusObject
{
	private Boolean status;
	private Date date;
	private Time time;
	private String template;

	public StatusObject(String template, Date date, Time time, Boolean status)
	{
		super();
		this.status = status;
		this.date = date;
		this.time = time;
		this.template = template;
	}

	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getString() {
		return template;
	}
	public void setString(String string) {
		this.template = string;
	}
}
