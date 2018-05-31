package com.ubaid.Model;

import com.ubaid.controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class TableSelectionModel implements EventHandler<MouseEvent>
{

	Controller controller;
	public TableSelectionModel(Controller controller)
	{
		this.controller = controller;
	}
	
	
	@Override
	public void handle(MouseEvent event)
	{

		Contact contact;
		String fileName = controller.getAnotherPaneController().getComboBox().getSelectionModel().getSelectedItem().split(":")[0];
		if(event.getSource().equals(controller.getAcTable().getTable()))
		{
	
			contact = controller.getAcTable().getTable().getSelectionModel().getSelectedItem();
			String tableName = "compaignForAttemptedContacts";
			controller.getModel().setValueInStatusTable(contact, tableName, fileName, true);
			//scrollpane is actually a border pane type
			
			SplitPane root = controller.getAnotherPaneController().getSplitPane();
			BorderPane smsStatusPane = controller.getAnotherPaneController().getSmsStatusPane();

			if(!root.getItems().contains(smsStatusPane))
				root.getItems().add(smsStatusPane);
			
		}
		else
		{
			//removing scrollpane of sms status from split pane
			BorderPane smsStatusPane = controller.getAnotherPaneController().getSmsStatusPane();
			SplitPane root = controller.getAnotherPaneController().getSplitPane();

			root.getItems().remove(smsStatusPane);
			
			contact = controller.getCcTable().getTable().getSelectionModel().getSelectedItem();
			String tableName = "compaignForContactedContacts";
			controller.getModel().setValueInStatusTable(contact, tableName, fileName, false);

		}
		
		
		
	}
	
}
