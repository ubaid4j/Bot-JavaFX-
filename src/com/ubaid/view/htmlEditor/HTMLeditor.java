package com.ubaid.view.htmlEditor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.HTMLEditor;

public class HTMLeditor
{
	@FXML HTMLEditor htmlEditor;
	@FXML ComboBox<String> comboBox;
	@FXML Button button;
	@FXML Button openFile;
	
	public HTMLEditor getHtmlEditor()
	{
		return htmlEditor;
	}
	
	public ComboBox<String> getComboBox()
	{
		return comboBox;
	}
	
	public Button getButton()
	{
		return button;
	}
	
	public Button getOpenFile()
	{
		return openFile;
	}
}
