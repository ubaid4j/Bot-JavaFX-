package com.ubaid.view;


import java.io.IOException;


import com.ubaid.controller.Controller;
import com.ubaid.view.AnotherTable.AnotherWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//acct = all contacted contacts table

public class App extends Application
{

	Controller controller;
	Table_1_Controller contactedContactsScroll;
	Table_2_Controller attemptedContactsScroll;
	
	public App()
	{
		controller = new Controller();
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		
		FXMLLoader loader = new FXMLLoader(App.class.getResource("LoginPage.fxml"));
		FXMLLoader nextPageLoader = new FXMLLoader(App.class.getResource("RootPane.fxml"));
		FXMLLoader tableLoader = new FXMLLoader(App.class.getResource("Table.fxml"));
		FXMLLoader tableLoader2 = new FXMLLoader(App.class.getResource("Table2.fxml"));
		FXMLLoader anotherPaneLoader = new FXMLLoader(App.class.getResource("anotherOnePane.fxml"));
		FXMLLoader htmlEditor = new FXMLLoader(App.class.getResource("htmlEditor/HTMLeditor.fxml"));
		FXMLLoader loader_for_acct = new FXMLLoader(AnotherWindow.class.getResource("ContactedContactsTable.fxml"));

		
		controller.setLpLoader(loader);
		controller.setNextPageLoader(nextPageLoader);
		controller.setAnotherPaneLoader(anotherPaneLoader);
		try
		{
			Parent mainPage = loader.load();
			controller.setLpage(mainPage);

			ScrollPane scrollPane = tableLoader.load();
			ScrollPane scrollPane2 = tableLoader2.load();

			controller.setScrollPane(scrollPane);
			controller.setScrollPane2(scrollPane2);
			Parent nextPage = nextPageLoader.load();
			controller.setNextPage(nextPage);
			
			//setting in controller the upload file menu button
			MenuItem uploadFile = ((MenuItem) loader.getNamespace().get("upLoadFileID"));
			uploadFile.setDisable(true);
			controller.setUploadFile(uploadFile);
			MenuItem editTemplate = ((MenuItem) loader.getNamespace().get("editTemplate"));
			controller.setEditTemplate(editTemplate);
			
			//loading [acct]
			loader_for_acct.load();
			//setting controller
			controller.setContactedContactsTableController(loader_for_acct.getController());

			

			//adding in controller root pane for contacted contacts and attempted contacts 
			controller.setBorderPaneForAttemptedContacts((BorderPane) nextPageLoader.getNamespace().get("borderPaneForAC"));
			controller.setBorderPaneForContactedContacts((BorderPane) nextPageLoader.getNamespace().get("borderPaneForCC"));
			controller.setAnotherPaneInRootPane((BorderPane) nextPageLoader.getNamespace().get("anotherPane"));
			controller.setProgressBar((ProgressBar) nextPageLoader.getNamespace().get("progressBar"));
			controller.setHtmlBorderPane((BorderPane) htmlEditor.load());
			controller.setHtmlEditorController(htmlEditor.getController());
			
			//setting controllers of different panes
			controller.setAcTable(tableLoader2.getController());
			controller.setCcTable(tableLoader.getController());
			
			//calling methods
			settingLoginPage();
			addingScrollPaneInInnerPane();
			addingAnotherPaneInRootPane();
			
			
			//calling handle methods
			controller.handleActionOnLogInButton();		
			controller.handleActionOnMenuButton();
			controller.handleForComboBox();
			controller.handleSelectionOnTable();
			controller.handleSentTempMenuItems();
			controller.handleOnAllContactedContactsTables();
			
			Scene scene = new Scene(mainPage);
			primaryStage.setScene(scene);
			primaryStage.show();
			controller.setStage(primaryStage);
			
			
			//creating htmlEditor
			controller.createHtmlEditor();
			
			//setting another window in the controller
			controller.setAnotherWindow(new AnotherWindow(controller));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IllegalArgumentException exp)
		{
			controller.showPopUp(AlertType.ERROR, "ERROR", "Error Occured: Details are following",exp.getMessage());
		}
	}
	
	public void settingLoginPage()
	{
		try
		{
			controller.setLgButton((Button) controller.getLpLoader().getNamespace().get("loginButton"));
			controller.setMenutItemController(controller.getLpLoader().getController());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addingScrollPaneInInnerPane()
	{
		try
		{

			controller.getBorderPaneForContactedContacts().setCenter(controller.getScrollPane());
			controller.getBorderPaneForAttemptedContacts().setCenter(controller.getScrollPane2());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addingAnotherPaneInRootPane()
	{
		try
		{
			controller.getAnotherPaneInRootPane().setCenter(controller.getAnotherPaneLoader().load());
			controller.setAnotherPaneController(controller.getAnotherPaneLoader().getController());
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
}
