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

public class Table_1_Controller implements Initializable
{

	@FXML
	private ScrollPane contactedContactsScroll; 
	
	@FXML
	private TableView<Contact> table;
	
	@FXML
	private TableColumn<Contact, String> name;
	
	@FXML
	private TableColumn<Contact, String> email;
	
	@FXML 
	private TableColumn<Contact, String> phoneNumber;
	
	@FXML
	private TableColumn<Contact, String> address;
	
	private TableView<Contact> tableView;
	
//	final ObservableList<Contact> data = FXCollections.observableArrayList();
	
	public Table_1_Controller()
	{

	}
	
//	private ObservableList<Contact> getContacts()
//	{
//		ObservableList<Contact> data = FXCollections.observableArrayList();
//		Contact contact = new Contact("Ubaid", "urehman.bese16seecs@seecs.edu.pk", "03075034375", "Jand");
//		data.add(contact);
//
//		return data;	
//	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		name.setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
		email.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));
		phoneNumber.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
//		getContacts();
//		table.setItems(getContacts());
		setTableView(table);
		
	}
	
	public void setTableView(TableView<Contact> table)
	{
		tableView = table;
	}
	
	public TableView<Contact> getTable()
	{
		return tableView;
	}

}
