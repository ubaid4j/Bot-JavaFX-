package com.ubaid.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.ubaid.Model.Templates.InsertNewPicInTemplate;
import com.ubaid.controller.Controller;
import com.ubaid.view.AnotherTable.CCT_Partial;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;


public class ActionModel implements EventHandler<ActionEvent>
{

	private Controller controller;
	
	public ActionModel(Controller controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void handle(ActionEvent event)
	{

		
		if(event.getSource().equals(controller.getLgButton()))
		{
			controller.getModel().insertingNextPage();
			controller.getModel().setItemsInComboBox();
			scheldulingNotSentEmails();
		}
		else if(event.getSource().equals(controller.getUploadFile()))
		{
			try
			{
				controller.getModel().OpenFile();				
			}
			catch(Exception exp)
			{
				controller.showPopUp(AlertType.ERROR, "Error", "Details are following:", exp.getMessage());
			}
		}
		else if(event.getSource().equals(controller.getAnotherPaneController().getComboBox()))
		{
			try
			{
				String selectedItem = controller.getAnotherPaneController().getComboBox().getSelectionModel().getSelectedItem();
				String[] nameArray = null;
				Vector<Vector<String>> contactedContacts = null;
				Vector<Vector<String>> attemptedContacts = null;
				if(!(selectedItem == null))
				{
					nameArray = selectedItem.split(":");
					contactedContacts = controller.getModel().getContactedContantsArray(nameArray[0].trim());
					attemptedContacts = controller.getModel().getAttemptedContantsArray(nameArray[0].trim());

				}
//				String[] nameArray = controller.getAnotherPaneController().getComboBox().getSelectionModel().getSelectedItem().split(":");
				if(!(contactedContacts == null || attemptedContacts == null))
					controller.getModel().setValuesInTable(contactedContacts, attemptedContacts);

			}
			catch(Exception exp)
			{
//				System.out.println("Exception at Line 39");
				exp.printStackTrace();
			}
		}
		else if(event.getSource().equals(controller.getEditTemplate()))
		{
			controller.getHtmlEditor().show();
			controller.getEditTemplate().setDisable(true);
		}
		else if(event.getSource().equals(controller.getHtmlEditorController().getButton()))
		{
//			System.out.println(controller.getHtmlEditorController().getHtmlEditor().getHtmlText());
			String path = System.getProperty("user.home");
			path = path + "/botFiles/Original_Templates/";
			String fileName = controller.getHtmlEditorController().getComboBox().getSelectionModel().getSelectedItem();
			path = path + fileName + ".html";			
			File file = new File(path);
			if(file.exists())
			{
				try
				{
					PrintWriter printWriter = new PrintWriter(file);
					printWriter.write(controller.getHtmlEditorController().getHtmlEditor().getHtmlText());
					printWriter.flush();
					printWriter.close();
				}
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				System.out.println("Select a File");
		}
		else if(event.getSource().equals(controller.getHtmlEditorController().getComboBox()))
		{
			String path = System.getProperty("user.home");
			path = path + "/botFiles/Original_Templates/";
			String fileName = controller.getHtmlEditorController().getComboBox().getSelectionModel().getSelectedItem();
			path = path + fileName + ".html";			
			File file = new File(path);
			
			try
			{
				Document doc = Jsoup.parse(file, "UTF-8");
				controller.getHtmlEditorController().getHtmlEditor().setHtmlText(doc.html());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		else if(event.getSource().equals(controller.getHtmlEditorController().getOpenFile()))
		{
			//instance of fileChooser
			final FileChooser fileChooser = new FileChooser();
			//extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
			fileChooser.getExtensionFilters().add(extFilter);
			//setting title
			fileChooser.setTitle("Select Image File");
			//retreiving the path to desktop
			String userNames = System.getProperty("user.home");
			userNames += "\\botFiles\\imgs";
			//setting initial path
			fileChooser.setInitialDirectory(new File(userNames));
			//opening file
			File file = fileChooser.showOpenDialog(controller.getStage());
			if(file != null)
				new InsertNewPicInTemplate(controller, file.getAbsolutePath());

		}
		else if(event.getSource().equals(controller.getMenutItemController().getAll_temp()))
		{
			for(int i = 0; i < 16; i++)
			{
				new EmailSender(i + 1);
			}
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_1()))
		{
			new EmailSender(1);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_2()))
		{
			new EmailSender(2);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_3()))
		{
			new EmailSender(3);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_4()))
		{
			new EmailSender(4);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_5()))
		{
			new EmailSender(5);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_6()))
		{
			new EmailSender(6);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_7()))
		{
			new EmailSender(7);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_8()))
		{
			new EmailSender(8);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_9()))
		{
			new EmailSender(9);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_10()))
		{
			new EmailSender(10);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_11()))
		{
			new EmailSender(11);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_12()))
		{
			new EmailSender(12);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_13()))
		{
			new EmailSender(13);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_14()))
		{
			new EmailSender(14);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_15()))
		{
			new EmailSender(15);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getTemp_16()))
		{
			new EmailSender(16);
		}
		else if(event.getSource().equals(controller.getMenutItemController().getContactedContactMenuButton()))
		{
			controller.getAnotherWindow().show();
			controller.getModel().setDataFromDataBaseToTable(controller.getContactedContactsTableController().getTable());
		}
		else if(event.getSource().equals(controller.getContactedContactsTableController().getAddButton()))
		{
			
			if(controller.getContactedContactsTableController().getTextField().getText().isEmpty())
			{
				controller.showPopUp(AlertType.ERROR, "ERROR", "Error in Email Field", "Enter a valid email address in the Email field");
			}
			else if (!(controller.getContactedContactsTableController().getTextField().getText().contains("@")))
			{
				controller.showPopUp(AlertType.ERROR, "ERROR", "Error in Email Sytax", "Enter a valid email address in the Email field");
			}
			else
			{
				try
				{
					controller.getBE().addDataInContactedContactsTable(controller.getContactedContactsTableController().getTextField().getText(), "SELF");
					controller.getModel().setDataFromDataBaseToTable(controller.getContactedContactsTableController().getTable());
					controller.getContactedContactsTableController().getTextField().clear();
				}
				catch(IllegalArgumentException exp)
				{
					controller.showPopUp(AlertType.ERROR, "Error", "Duplicate Mail Address", exp.getMessage());
					controller.getContactedContactsTableController().getTextField().clear();
				}
			}
				
		}
		else if(event.getSource().equals(controller.getContactedContactsTableController().getDelButton()))
		{
			CCT_Partial cct_Partial = controller.getContactedContactsTableController().getTable().getSelectionModel().getSelectedItem();
			try
			{
				controller.getBE().deleteRowInCCTByID(cct_Partial.getId());
			}
			catch(NullPointerException exp)
			{
				controller.showPopUp(AlertType.ERROR, "Error", "This is Null Pointer Exception", "Select something to eliminate this exception");
			}
			controller.getModel().setDataFromDataBaseToTable(controller.getContactedContactsTableController().getTable());
		}
		else if(event.getSource().equals(controller.getMenutItemController().getUploadCCMenuItem()))
		{
			
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
				try
				{
					new CSVReader(file, CSVReader.Type.ContactedContacts);
					controller.getModel().setDataFromDataBaseToTable(controller.getContactedContactsTableController().getTable());				
					
				}
				catch(Exception exp)
				{
					controller.showPopUp(AlertType.ERROR, "Error", "Following are the details", exp.getMessage());
				}
			}
		}
	}
	
	/**
	 * this method reschedule the not sent emails
	 */
	public void scheldulingNotSentEmails()
	{
		try
		{
			controller.getBE().schedulringNotSentEmails_();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
}
