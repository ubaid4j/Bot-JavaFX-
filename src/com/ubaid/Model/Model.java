package com.ubaid.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;
import com.ubaid.Model.Objects.StatusObject;
import com.ubaid.Model.Templates.CreateTemplateFiles_;
import com.ubaid.controller.Controller;
import com.ubaid.view.AnotherTable.CCT_Partial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;


public class Model
{
	protected Controller controller;
	
	public Model(Controller controller)
	{
		this.controller = controller;
		
		//creating a directory for botFiles
		String username = System.getProperty("user.home");		
	    File file = new File(username + "/botFiles");
	    //if this file does not exists
	    if(!file.exists())
	    	file.mkdir();
	    
	    //for first time, the directory of Original 
	    File originalTemplate = new File(file.getAbsolutePath() + "/Original_Templates");
	    if(!originalTemplate.exists())
	    {
	    	File imgs = new File(file.getAbsoluteFile() + "/imgs");
	    	imgs.mkdirs();
	    		
	    	
	    	originalTemplate.mkdirs();
	    	try
	    	{
				new CreateTemplateFiles_(controller, originalTemplate);
			}
	    	catch (IOException e)
	    	{
				e.printStackTrace();
			}
	    }
	}
	
	public void insertingNextPage()
	{
		BorderPane borderPane = (BorderPane) controller.getLpage();
		borderPane.setCenter(null);
		borderPane.setCenter((BorderPane) controller.getNextPage());
		controller.getUploadFile().setDisable(false);
		controller.getUploadFile().setStyle("-fx-opacity: 1");
	}
	
	
	/**
	 * this method open a dialog to 
	 * select a file and after 
	 * selecting a file
	 * this method set the 
	 * contents in the table 
	 * and combobox
	 */
	
	
	public void OpenFile() 
	{
		//instance of fileChooser
		final FileChooser fileChooser = new FileChooser();
		//extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		//setting title
		fileChooser.setTitle("Select CSV File");
		//retreiving the path to desktop
		String userNames = System.getProperty("user.home");
		userNames += "\\Desktop";
		//setting initial path
		fileChooser.setInitialDirectory(new File(userNames));
		//opening file
		File file = fileChooser.showOpenDialog(controller.getStage());
		if(file != null) 
		{
			//file is already present
			if(!(controller.getBE().isFileNamePresent(file.getName())))
			{
				//this class read data from the csv file
				//and upload this on the server
				new CSVReader(file);
				//storing the retrieved data from the server
				Vector<Vector<String>> cc_data = controller.getBE().getContactedContacts(file.getName());
				Vector<Vector<String>> ac_data = controller.getBE().getAttemptedContacts(file.getName());				
				
				//now setting these stored data in the table
				setValuesInTable(cc_data, ac_data);
				
				//now setting names of file in the combobox
				setItemsInComboBox();
				int length = controller.getAnotherPaneController().getComboBox().getItems().size();
				controller.getAnotherPaneController().getComboBox().getSelectionModel().select(length - 1);

				//this class write the store data (Vector<Vector<String>>) in the file
				//and add the templates[1 to 16] in the file
				WriteHanlder writeHanlder = new WriteHanlder(controller, file.getName(), cc_data, ac_data);

				//now writing compaing
				controller.getBE().writeCompaignOnServerForContactedContacts(file.getName(), cc_data);
				controller.getBE().writeCompaignOnServerForAttemptedContacts(file.getName(), ac_data);
				controller.getBE().writeCompaignOnServerForAttemptedContactsSMS(file.getName(), ac_data);
				//doing emails
				new EmailSender(controller.getBE().getFileNumber(file.getName()), writeHanlder.getFolder());

			}
			else
			{
				controller.showPopUp(AlertType.ERROR, "Error", "The following is the detail", "The File is already present");
			}
		}
	}

	//this method sets the files names
	//from server into the combo box
	public void setItemsInComboBox()
	{
		Vector<String> fileName = controller.getBE().getFiles();
		try
		{
//			System.out.println(controller.getAnotherPaneController() == null);

			controller.getAnotherPaneController().getComboBox().getItems().clear();
			for(int i = 0; i < fileName.size(); i++)
			{
				controller.getAnotherPaneController().getComboBox().getItems().add(fileName.get(i));
			}

		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		

	}
	
	//get array of contacted contacts
	@SuppressWarnings("unchecked")
	public Vector<Vector<String>> getContactedContantsArray(String fileName)
	{
		Vector<Vector<String>> data = null;
		
		try
		{
			fileName = fileName.substring(0, fileName.length() - 4);
			String path = System.getProperty("user.home");
			path = path + "/botFiles/" + fileName;
			fileName = fileName + "Contacted" + ".ser";
			fileName = path + "/" + fileName;
			File file = new File(fileName);
	
			if(!file.exists())
			{
				return null;
			}
			
			FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			data = (Vector<Vector<String>>) objectInputStream.readObject();
			objectInputStream.close();
		}
		catch(FileNotFoundException exp)
		{
			exp.printStackTrace();
		}
		catch(IOException exp2)
		{
			exp2.printStackTrace();
		}
		catch(ClassNotFoundException exp3)
		{
			exp3.printStackTrace();
		}
		
		return data;
	}
	
	
	
	
	//get the array of attempted contacts 
	@SuppressWarnings("unchecked")
	public Vector<Vector<String>> getAttemptedContantsArray(String fileName)
	{
		Vector<Vector<String>> data = null;
		try
		{
			fileName = fileName.substring(0, fileName.length() - 4);
			String path = System.getProperty("user.home");
			path = path + "/botFiles/" + fileName;
			fileName = fileName + "Attempted" + ".ser";
			fileName = path + "/" + fileName;

			File file = new File(fileName);

			if(!file.exists())
			{
				return null;
			}

			
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (Vector<Vector<String>>) ois.readObject();
			ois.close();
		}
		catch(FileNotFoundException exp)
		{
			exp.printStackTrace();
		}
		catch(IOException exp2)
		{
			exp2.printStackTrace();
		}
		catch(ClassNotFoundException exp3)
		{
			exp3.printStackTrace();
		}
		
		return data;
	}

	//this method sets the values in the table
	public void setValuesInTable(Vector<Vector<String>> cc_data, Vector<Vector<String>> ac_data)
	{
		ObservableList<Contact> contacts = FXCollections.observableArrayList();
		ObservableList<Contact> contacts2 = FXCollections.observableArrayList();

		Contact contact;
		for(int i = 0; i < cc_data.size(); i++)
		{
			contact = new Contact(cc_data.get(i).get(0), cc_data.get(i).get(1), cc_data.get(i).get(2), cc_data.get(i).get(3));
			contacts.add(contact);
		}
		
		
		for(int i = 0; i < ac_data.size(); i++)
		{
			contact = new Contact(ac_data.get(i).get(0), ac_data.get(i).get(1), ac_data.get(i).get(2), ac_data.get(i).get(3));
			contacts2.add(contact);
		}
		
		controller.getCcTable().getTable().setItems(contacts);
		controller.getAcTable().getTable().setItems(contacts2);
		
	}
	
	/**
	 * this method sets values in the 
	 * status table
	 * @param contact
	 * @param tableName
	 * @param fileName
	 * @param tableType
	 */
	public void setValueInStatusTable(Contact contact, String tableName, String fileName, Boolean tableType)
	{
		Vector<StatusObject> data = controller.getBE().getStatus(contact.getEmail().trim(), tableName, fileName);
		
		ObservableList<TemplateForTable> templateForTables = FXCollections.observableArrayList();
		TemplateForTable templateForTable;
		Template2ForTable template2ForTable;
		for(int i = 0; i < data.size(); i++)
		{
			
			if(data.get(i).getStatus() == false)
			{
				String msg = "Email will be send: " + data.get(i).getDate() + " " + data.get(i).getTime();
				templateForTable = new TemplateForTable(data.get(i).getString(), msg);
				

			}
			else
			{
				String msg = "Message Successfull sent";
				templateForTable = new TemplateForTable(data.get(i).getString(), msg);				
			}
			templateForTables.add(templateForTable);
		}
		
		if(tableType)
		{
			Vector<StatusObject> dataSMS = controller.getBE().getStatusForSMS(contact.getEmail().trim(), fileName);
			ObservableList<Template2ForTable> templateForTablesSMS = FXCollections.observableArrayList();
			for(int i = 0; i < dataSMS.size(); i++)
			{
				if(dataSMS.get(i).getStatus() == false)
				{
					String msg = "Message will be send: " + dataSMS.get(i).getDate() + " " + dataSMS.get(i).getTime();
					template2ForTable = new Template2ForTable(dataSMS.get(i).getString(), msg);					
				}
				else
				{
					String msg = "Message Successfull sent";
					template2ForTable = new Template2ForTable(dataSMS.get(i).getString(), msg);					
				}
				
				templateForTablesSMS.add(template2ForTable);
			}

			controller.getAnotherPaneController().getTable2().setItems(templateForTablesSMS);


		}
		
		controller.getAnotherPaneController().getTable().setItems(templateForTables);

	}
	
	//this method insert the data from datebase to another table
	public void setDataFromDataBaseToTable(TableView<CCT_Partial> table)
	{
		Vector<Vector<String>> data = controller.getBE().getAllContactsInContactedTable();
		
		ObservableList<CCT_Partial> arrayList = FXCollections.observableArrayList();
		
		for(int i = 0; i < data.size(); i++)
		{
			CCT_Partial cct = new CCT_Partial(data.get(i).get(0), data.get(i).get(1), data.get(i).get(2), data.get(i).get(3), data.get(i).get(4));
			arrayList.add(cct);
		}
		
		controller.getContactedContactsTableController().getTable().setItems(arrayList);
	}
}
