//this package contain a class which handle poped up for tables
package com.ubaid.view.AnotherTable;


import com.ubaid.controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

//this class actually extends stage, and is a container for other tables [or anything]
public class AnotherWindow extends Stage
{
	public AnotherWindow(Controller controller)
	{
		
		try
		{
			Scene scene = new Scene(controller.getContactedContactsTableController().getBorderPane());
			setScene(scene);
			setTitle("All Contacted Contacts");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
