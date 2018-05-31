package com.ubaid.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.ubaid.Model.Template2ForTable;
import com.ubaid.Model.TemplateForTable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class AnotherPane implements Initializable
{
	@FXML ComboBox<String> comboBox;
	@FXML BorderPane pane;
	@FXML TableView<TemplateForTable> tableView;
	@FXML TableColumn<TemplateForTable, String> template;
	@FXML TableColumn<TemplateForTable, String> status;
	
	@FXML TableView<Template2ForTable> tableView2;
	@FXML TableColumn<Template2ForTable, String> temp;
	@FXML TableColumn<Template2ForTable, String> stat;
	
	@FXML BorderPane smsStatusScrollPane;
	@FXML SplitPane splitPane;
	
	public SplitPane getSplitPane()
	{
		return splitPane;
	}
	
	public BorderPane getSmsStatusPane()
	{
		return smsStatusScrollPane;
	}
	
	public ComboBox<String> getComboBox()
	{
		return comboBox;
	}
	
	public BorderPane getBorderPane()
	{
		return pane;
	}
	
	public TableView<TemplateForTable> getTable()
	{
		return tableView;
	}

	public TableView<Template2ForTable> getTable2()
	{
		return tableView2;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		temp.setCellValueFactory(new PropertyValueFactory<Template2ForTable, String>("temp"));
		stat.setCellValueFactory(new PropertyValueFactory<Template2ForTable, String>("stat"));

		
		template.setCellValueFactory(new PropertyValueFactory<TemplateForTable, String>("template"));
		status.setCellValueFactory(new PropertyValueFactory<TemplateForTable, String>("status"));
				
	}
	
}
