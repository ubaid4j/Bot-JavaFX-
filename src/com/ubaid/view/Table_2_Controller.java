package com.ubaid.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.ubaid.Model.Contact;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table_2_Controller implements Initializable
{

	@FXML
	private ScrollPane attemptedContactsScroll;
	
	@FXML
	private TableView<Contact> tableID;
	
	@FXML
	private TableColumn<Contact, String> iName;
	
	@FXML
	private TableColumn<Contact, String> iEmail;
	
	@FXML 
	private TableColumn<Contact, String> iPhoneNumber;
	
	@FXML
	private TableColumn<Contact, String> iAddress;
	
//	private ObservableList<Contact> getContacts()
//	{
//		ObservableList<Contact> data = FXCollections.observableArrayList();
//		Contact contact = new Contact("Ubaid", "urehman.bese16seecs@seecs.edu.pk", "03075034375", "Jand");
//		data.add(contact);
//		return data;
//	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		iName.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
		iEmail.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));
		iPhoneNumber.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));
		iAddress.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
//		tableID.setItems(getContacts());
	}
	
	public TableView<Contact> getTable()
	{
		return tableID;
	}

}
