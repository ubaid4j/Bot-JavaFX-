package com.ubaid.view.htmlEditor;

import java.io.File;
import com.ubaid.controller.Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HTMLEditorView extends Stage
{
	//constructor
	public HTMLEditorView(Controller controller)
	{
		super();
		Scene scene = new Scene(controller.getHtmlBorderPane());
		setScene(scene);
		
		//this snippet of code enable the menu item
		setOnHiding(new EventHandler<WindowEvent>()
		{

	         @Override
	         public void handle(WindowEvent event)
	         {
	             Platform.runLater(new Runnable()
	             {

	                 @Override
	                 public void run()
	                 {
	                    controller.getEditTemplate().setDisable(false);
	                    controller.getEditTemplate().setStyle("-fx-opacity: 1");
	                 }
	             });
	         }
	     });
		
		controller.getHtmlEditorController().getButton().setOnAction(controller.getActionModel());
		controller.getHtmlEditorController().getComboBox().setOnAction(controller.getActionModel());
		
		
		setInComboBox(controller);
		OpenPicHandler(controller);
		
		
	}
	//end constructor
	
	//this method sets all the files in the combobox of this html editor
	public void setInComboBox(Controller controller)
	{
		String userPath = System.getProperty("user.home");
		userPath = userPath + "/botFiles/Original_Templates/";
		File file = new File(userPath);		
		File files[] = file.listFiles();
		ObservableList<String> list = FXCollections.observableArrayList();
		for(int i = 0; i < files.length; i++)
		{
			list.add(files[i].getName().substring(0, files[i].getName().length() - 5));
		}
		
		controller.getHtmlEditorController().getComboBox().setItems(list);
	}
	
	public void OpenPicHandler(Controller controller)
	{
		controller.getHtmlEditorController().getOpenFile().setOnAction(controller.getActionModel());
	}
}
