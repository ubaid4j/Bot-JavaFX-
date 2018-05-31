package com.ubaid.view.AnotherTable;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ContactedContactsTableController implements Initializable
{

	@FXML TableColumn<CCT_Partial, String> contact;
	@FXML TableView<CCT_Partial> table;
	@FXML Button delButton;
	@FXML TextField textField;
	@FXML Button addButton;
	@FXML BorderPane borderPane;
	@FXML TableColumn<CCT_Partial, String> fileName;
	@FXML TableColumn<CCT_Partial, String> date;
	@FXML TableColumn<CCT_Partial, String> time;
	@FXML TableColumn<CCT_Partial, String> id;
	
	public TableView<CCT_Partial> getTable()
	{
		return table;
	}
	
	/**
	 * 
	 * @return view in which table lie
	 */
	public BorderPane getBorderPane()
	{
		return borderPane;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		contact.setCellValueFactory(new PropertyValueFactory<CCT_Partial, String>("contact"));
		fileName.setCellValueFactory(new PropertyValueFactory<CCT_Partial, String>("fileName"));
		date.setCellValueFactory(new PropertyValueFactory<CCT_Partial, String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<CCT_Partial, String>("time"));
		id.setCellValueFactory(new PropertyValueFactory<CCT_Partial, String>("id"));

	}


	public Button getAddButton() {
		return addButton;
	}


	public Button getDelButton() {
		return delButton;
	}


	public TextField getTextField() {
		return textField;
	}

}
