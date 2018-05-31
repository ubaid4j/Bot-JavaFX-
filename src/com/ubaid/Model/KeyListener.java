package com.ubaid.Model;

import com.ubaid.controller.Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyListener implements EventHandler<KeyEvent>
{

	Controller controller;
	
	public KeyListener(Controller controller)
	{
		this.controller = controller;
	}
	
	
	@Override
	public void handle(KeyEvent event)
	{
		if(event.getSource().equals(controller.getContactedContactsTableController().getTextField()))
		{
			try
			{
				if(event.getCode() == KeyCode.ENTER)
				{
					if(controller.getContactedContactsTableController().getTextField().getText().isEmpty())
					{
						throw new IllegalArgumentException("Your text field is empty, enter a valid email address");
					}
					else if(!(controller.getContactedContactsTableController().getTextField().getText().contains("@")))
					{
						throw new IllegalArgumentException("Your email is invalid, please enter a valid email address");
					}
					else
					{
						controller.getBE().addDataInContactedContactsTable(controller.getContactedContactsTableController().getTextField().getText(), "SELF");
						controller.getModel().setDataFromDataBaseToTable(controller.getContactedContactsTableController().getTable());
						controller.getContactedContactsTableController().getTextField().clear();
						
					}
					
				}
				
			}
			catch(Exception e)
			{
				controller.showPopUp(AlertType.ERROR, "Error", "There is some problem", e.getMessage());
			}
		}
	}

}
