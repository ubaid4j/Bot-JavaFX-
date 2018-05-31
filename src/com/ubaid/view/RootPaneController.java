package com.ubaid.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class RootPaneController implements Initializable
{

	@FXML MenuItem all_temp;
	@FXML MenuItem temp_1;
	@FXML MenuItem temp_2;
	@FXML MenuItem temp_3;
	@FXML MenuItem temp_4;
	@FXML MenuItem temp_5;
	@FXML MenuItem temp_6;
	@FXML MenuItem temp_7;
	@FXML MenuItem temp_8;
	@FXML MenuItem temp_9;
	@FXML MenuItem temp_10;
	@FXML MenuItem temp_11;
	@FXML MenuItem temp_12;
	@FXML MenuItem temp_13;
	@FXML MenuItem temp_14;
	@FXML MenuItem temp_15;
	@FXML MenuItem temp_16;
	@FXML MenuItem edit_contacted_contacts;
	@FXML MenuItem uploadCCMenuItem;
	
//	private MenuItem[] menutItems = {all_temp, temp_1, temp_2, temp_3, temp_4, temp_5, temp_6, temp_7, temp_8, temp_9, temp_10, temp_11, temp_12, temp_13, temp_14, temp_15, temp_16};
//
//	public MenuItem[] getMenuItems_sentTemps()
//	{
//		return menutItems;
//	}
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}
	
	/**
	 * 
	 * @return button for to opon file
	 */
	public MenuItem getUploadCCMenuItem()
	{
		return uploadCCMenuItem;
	}

	/**
	 * 
	 * @return button for editing the contacts
	 */
	public MenuItem getContactedContactMenuButton()
	{
		return edit_contacted_contacts;
	}


	public MenuItem getAll_temp() {
		return all_temp;
	}





	public MenuItem getTemp_1() {
		return temp_1;
	}





	public MenuItem getTemp_2() {
		return temp_2;
	}





	public MenuItem getTemp_3() {
		return temp_3;
	}





	public MenuItem getTemp_4() {
		return temp_4;
	}





	public MenuItem getTemp_5() {
		return temp_5;
	}





	public MenuItem getTemp_6() {
		return temp_6;
	}





	public MenuItem getTemp_7() {
		return temp_7;
	}





	public MenuItem getTemp_8() {
		return temp_8;
	}





	public MenuItem getTemp_9() {
		return temp_9;
	}





	public MenuItem getTemp_10() {
		return temp_10;
	}





	public MenuItem getTemp_11() {
		return temp_11;
	}





	public MenuItem getTemp_12() {
		return temp_12;
	}





	public MenuItem getTemp_13() {
		return temp_13;
	}





	public MenuItem getTemp_14() {
		return temp_14;
	}





	public MenuItem getTemp_15() {
		return temp_15;
	}





	public MenuItem getTemp_16() {
		return temp_16;
	}






	
}
