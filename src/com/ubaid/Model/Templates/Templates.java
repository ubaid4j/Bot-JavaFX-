package com.ubaid.Model.Templates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Templates
{
	
	private String text = " ";
	private int templateNumber;
	private String contactType = null;
	private String receiverName;
	private File folderName;
	private ArrayList<String> sourceList;
	
	public Templates(int TEMP, String contactType, File folderName)
	{
		setTemplateNumber(TEMP);
		setContactType(contactType);
		setFolderName(folderName);
		if(TEMP == 1)
		{
			text += text + " 1";
		}
		else if(TEMP == 2)
		{
			text += text + " 2";			
		}
		else if(TEMP == 3)
		{
			text += text + " 3";			
		}
		else if(TEMP == 4)
		{
			text += text + " 4";			
		}
		else if(TEMP == 5)
		{
			text += text + " 5";			
		}
		else if(TEMP == 6)
		{
			text += text + " 6";			
		}
		else if(TEMP == 7)
		{
			text += text + " 7";			
		}
		else if(TEMP == 8)
		{
			text += text + " 8";			
		}
		else if(TEMP == 9)
		{
			text += text + " 9";			
		}
		else if(TEMP == 10)
		{
			text += text + " 10";			
		}
		else if(TEMP == 11)
		{
			text += text + " 11";			
		}
		else if(TEMP == 12)
		{
			text += text + " 12";			
		}
		else if(TEMP == 13)
		{
			text += text + " 13";			
		}
		else if(TEMP == 14)
		{
			text += text + " 14";			
		}
		else if(TEMP == 15)
		{
			text += text + " 15";			
		}
		else if(TEMP == 16)
		{
			text += text + " 16";			
		}		

	}
	
	@Override
	public String toString()
	{	
		return text;
	}
	
	public int getTemplateNumber()
	{
		return templateNumber;
	}
	
	public void setTemplateNumber(int templateNumber)
	{
		this.templateNumber = templateNumber;
	}
	
	public void setContactType(String contactType)
	{
		this.contactType = contactType;
	}
	
	public String getContactType()
	{
		return contactType;
	}
	
	public void setReceiverName(String name)
	{
		this.receiverName = name;
	}
	
	protected String getReceiverName()
	{
		return receiverName;
	}
	
	public String getSubject()
	{
		try
		{
			String path = getFolderName().getAbsolutePath() + "/" + "Template_" + getTemplateNumber() + ".html";
			File template = new File(path);
			Document document = Jsoup.parse(template, "UTF-8");

			String subject = "";
			
			Element element = document.selectFirst("h1");
			if(element==null)
				element = document.selectFirst("h2");
			if(element==null)
				element = document.selectFirst("h3");
			if(element==null)
				element = document.selectFirst("h4");
			if(element==null)
				element = document.selectFirst("h5");

			if(element == null)
			{
				subject = "No Subject";
			}
			else
				subject = element.text();
			
			
			return subject;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return null;
	}
	
	public void setFolderName(File folderName)
	{
		this.folderName = folderName;
	}
	
	public File getFolderName()
	{
		return folderName;
	}
	
	private String getModifiedName(String text)
	{
		text = text.toUpperCase();
		
		if(text.contains("USERNAME"))
		{
			text = text.replaceAll("USERNAME", getReceiverName());
			return text;
		}
		
		
		return null;
	}
	
	private Document setUserNameStatement(Document document)
	{
		String statement=null;
		
		try
		{
			Elements p = document.select("p");		
			Elements h1 = document.select("h1");
			Elements h2 = document.select("h2");
			Elements h3 = document.select("h3");
			Elements h4 = document.select("h4");
			Elements h5 = document.select("h5");
			Elements h6 = document.select("h6");
			Elements[] tags = {h1, h2, h3, h4, p, h5, h6};
			
			for(int i = 0; i < tags.length; i++)
			{
				for(int j = 0; j < tags[i].size(); i++)
				{
					if((statement = getModifiedName(tags[i].get(j).text())) != null)
					{
						tags[i].get(j).text(statement);
						return document;
					}
				}
			}

			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return document;
	}
	
	private Document getDocument() throws IOException
	{
		String path = getFolderName().getAbsolutePath() + "/" + "Template_" + getTemplateNumber() + ".html";
		File template = new File(path);
		Document document = Jsoup.parse(template, "UTF-8");
		return document;
	}
	
	public String getTemplate()
	{
		try
		{
			Document document = setUserNameStatement(getDocument());
			
			Elements link = document.select("img");
			
			//in this list, initial path of the file, we will store
			ArrayList<String> list = new ArrayList<String>();
			
			
			int counter=1;
			for(int i = 0; i < link.size(); i++)
			{
				String source = link.get(i).attr("src");
				list.add(source);
				link.get(i).attr("src", "cid:image" + counter++);

			}
					
			setDataSource(list);			
			return document.html();
				
		
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		return null;
	}
	
	public void setDataSource(ArrayList<String> list)
	{
		this.sourceList = list;
	}
	
	public ArrayList<String> getDataSource()
	{
		return sourceList;
	}
}
