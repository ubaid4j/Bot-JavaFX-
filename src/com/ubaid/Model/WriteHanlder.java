package com.ubaid.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Vector;
import com.ubaid.controller.Controller;


/**
 * this class writes Vector<Vector<String>> in the file
 * @author UbaidurRehman
 *
 */
public class WriteHanlder
{
	
	private File file;
	
	public WriteHanlder(Controller controller, String fileName, Vector<Vector<String>> cc_data, Vector<Vector<String>> ac_data)
	{
		String pathName = System.getProperty("user.home");
		fileName = fileName.substring(0, fileName.length() - 4);
		String path = pathName + "/" + "botFiles" + "/" + fileName;
		File file = new File(path);
		setFolder(file);
		
		file.mkdir();
		
		writeContactedContantsArrayinFile(file.getAbsolutePath(), fileName, cc_data);
		writeAttemptedContactsArrayinFile(file.getAbsolutePath(), fileName, ac_data);
		
		String originalTemplateDirctory = pathName + "/" + "botFiles" + "/" + "Original_Templates";
		File originalTemplateDirec = new File(originalTemplateDirctory);
		
		copyTemplates(originalTemplateDirec, file);
		
	
	}
	
	public void setFolder(File file)
	{

		this.file = file;
	}
	
	/**
	 * 
	 * @return the folder which contain the templates
	 */
	public File getFolder()
	{
		return file;
	}
	
	//writing in file
	public void writeContactedContantsArrayinFile(String path, String fileName, Vector<Vector<String>> data)
	{
		try
		{
			fileName =  path + "/" + fileName + "Contacted" + ".ser";
			FileOutputStream outputStream = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(data);
			objectOutputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
		}
	}
	
	//writing in file
	public void writeAttemptedContactsArrayinFile(String path, String fileName, Vector<Vector<String>> data)
	{
		
		try
		{
			fileName = path + "/" + fileName + "Attempted" + ".ser";
			FileOutputStream fis = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(data);
			oos.close();			
		}
		catch(FileNotFoundException exp1)
		{
			exp1.printStackTrace();
		}
		catch(IOException exp2)
		{
			exp2.printStackTrace();
		}
	}
	
	//copy the templates from the original templates to specific file 
	private void copyTemplates(File sourceFolder, File destinationFolder)
	{
		
		String[] fileNameArray = {"Template_1", "Template_2", "Template_3", "Template_4", "Template_5", "Template_6", "Template_7", "Template_8",
				"Template_9", "Template_10", "Template_11", "Template_12","Template_13", "Template_14", "Template_15", "Template_16"};
		
		try
		{
			int counter = 0;
			for(int i = 0; i <  sourceFolder.listFiles().length; i++)
			{
				File srcFile = new File(sourceFolder.getAbsoluteFile() + "\\" + fileNameArray[i] + ".html");
				
				String dist = destinationFolder.getAbsolutePath() + "/"+ fileNameArray[counter++] + ".html";
				File distFile = new File(dist);
				
				Files.copy(srcFile.toPath(), distFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch(FileNotFoundException exp)
		{
			exp.printStackTrace();
		}
		catch(IOException exp2)
		{
			exp2.printStackTrace();
		}
	}


}
