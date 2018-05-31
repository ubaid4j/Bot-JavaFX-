package com.ubaid.controller;

import java.io.File;
import com.ubaid.Model.ActionModel;
import com.ubaid.Model.BackEnd_1;
import com.ubaid.Model.KeyListener;
import com.ubaid.Model.Model;
import com.ubaid.Model.TableSelectionModel;
import com.ubaid.view.AnotherPane;
import com.ubaid.view.RootPaneController;
import com.ubaid.view.Table_1_Controller;
import com.ubaid.view.Table_2_Controller;
import com.ubaid.view.AnotherTable.AnotherWindow;
import com.ubaid.view.AnotherTable.ContactedContactsTableController;
import com.ubaid.view.htmlEditor.HTMLEditorView;
import com.ubaid.view.htmlEditor.HTMLeditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//acct = all contacted contacts table

public class Controller
{
	private Parent mainPage;
	private FXMLLoader lpLoader;
	private FXMLLoader nextPageLoader, AnotherPaneLoader;
	private Button lgButton;
	private Model model;
	private ActionModel actionModel;
	private Parent nextPage;
	private MenuItem uploadFile, editTemplate;
	private ScrollPane scrollPane, scrollPane2;
	private BorderPane borderPaneForContactedContacts, borderPaneForAttemptedContacts, htmlBorderPane;
	private Stage stage;
	private File file;
	private BackEnd_1 BE;
	private Table_1_Controller ccTable;
	private Table_2_Controller acTable;
	private AnotherPane anotherPaneController;
	private BorderPane anotherPaneInRootPane;
	private TableSelectionModel tableSelectionModel;
	private ProgressBar progressBar;
	private HTMLEditorView htmlEditor;
	private HTMLeditor htmlEditorController;
	private RootPaneController menutItemController;
	private ContactedContactsTableController contactedContactsTableController;
	private AnotherWindow anotherWindow;
	private KeyListener keyListener;
	
	public Controller()
	{
		setModel(new Model(this));
		actionModel = new ActionModel(this);
		setBE(new BackEnd_1());
		tableSelectionModel = new TableSelectionModel(this);
		keyListener = new KeyListener(this);
		
	}
	
	//actionHandlingwhenclicking_on_sent_temp#
	public void handleSentTempMenuItems()
	{
		RootPaneController controller = getMenutItemController();
		
//		
//		for(int i = 0; i < size; i++)
//		{
//			controller.getMenuItems_sentTemps()[i].setOnAction(actionModel);
//		}

		controller.getAll_temp().setOnAction(actionModel);
		controller.getTemp_1().setOnAction(actionModel);
		controller.getTemp_2().setOnAction(actionModel);
		controller.getTemp_3().setOnAction(actionModel);
		controller.getTemp_4().setOnAction(actionModel);
		controller.getTemp_5().setOnAction(actionModel);
		controller.getTemp_6().setOnAction(actionModel);
		controller.getTemp_7().setOnAction(actionModel);
		controller.getTemp_8().setOnAction(actionModel);
		controller.getTemp_9().setOnAction(actionModel);
		controller.getTemp_10().setOnAction(actionModel);
		controller.getTemp_11().setOnAction(actionModel);
		controller.getTemp_12().setOnAction(actionModel);
		controller.getTemp_13().setOnAction(actionModel);
		controller.getTemp_14().setOnAction(actionModel);
		controller.getTemp_15().setOnAction(actionModel);
		controller.getTemp_16().setOnAction(actionModel);

	}
	
	/**
	 * this method handle all the actions 
	 * which are performed on the table [showing all the contacted contacts in it]
	 */
	public void handleOnAllContactedContactsTables()
	{
		getContactedContactsTableController().getAddButton().setOnAction(actionModel);
		getContactedContactsTableController().getTextField().setOnKeyReleased(keyListener);
		getContactedContactsTableController().getDelButton().setOnAction(actionModel);
	}
	
	
	//action handling
	public void handleActionOnLogInButton()
	{
		getLgButton().setOnAction(actionModel);
	}
	
	public void handleActionOnMenuButton()
	{
		getMenutItemController().getContactedContactMenuButton().setOnAction(actionModel);
		getUploadFile().setOnAction(actionModel);
		getMenutItemController().getUploadCCMenuItem().setOnAction(actionModel);
		getEditTemplate().setOnAction(actionModel);
	}
	
	public void handleForComboBox()
	{
		getAnotherPaneController().getComboBox().setOnAction(actionModel);
	}
	
	//selction
	public void handleSelectionOnTable()
	{
		getCcTable().getTable().setOnMouseClicked(tableSelectionModel);
		getAcTable().getTable().setOnMouseClicked(tableSelectionModel);
	}
	
	
	//creating htmlEditor
	public void createHtmlEditor()
	{
		setHtmlEditor(new HTMLEditorView(this));
	}
	/**
	 * @return the lpage
	 */
	public Parent getLpage() {
		return mainPage;
	}

	/**
	 * @param lpage the lpage to set
	 */
	public void setLpage(Parent lpage) {
		this.mainPage = lpage;
	}

	/**
	 * @return the lpLoader
	 */
	public FXMLLoader getLpLoader() {
		return lpLoader;
	}

	/**
	 * @param lpLoader the lpLoader to set
	 */
	public void setLpLoader(FXMLLoader lpLoader) {
		this.lpLoader = lpLoader;
	}

	/**
	 * @return the lgButton
	 */
	public Button getLgButton() {
		return lgButton;
	}

	/**
	 * @param lgButton the lgButton to set
	 */
	public void setLgButton(Button lgButton) {
		this.lgButton = lgButton;
	}

	/**
	 * @return the nextPageLoader
	 */
	public FXMLLoader getNextPageLoader() {
		return nextPageLoader;
	}

	/**
	 * @param nextPageLoader the nextPageLoader to set
	 */
	public void setNextPageLoader(FXMLLoader nextPageLoader) {
		this.nextPageLoader = nextPageLoader;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the nextPage
	 */
	public Parent getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(Parent nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the uploadFile
	 */
	public MenuItem getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFile the uploadFile to set
	 */
	public void setUploadFile(MenuItem uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * @return the scrollPane
	 */
	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * @return the scrollPane2
	 */
	public ScrollPane getScrollPane2() {
		return scrollPane2;
	}

	/**
	 * @param scrollPane2 the scrollPane2 to set
	 */
	public void setScrollPane2(ScrollPane scrollPane2) {
		this.scrollPane2 = scrollPane2;
	}

	/**
	 * @return the borderPaneForContactedContacts
	 */
	public BorderPane getBorderPaneForContactedContacts() {
		return borderPaneForContactedContacts;
	}

	/**
	 * @param borderPaneForContactedContacts the borderPaneForContactedContacts to set
	 */
	public void setBorderPaneForContactedContacts(BorderPane borderPaneForContactedContacts) {
		this.borderPaneForContactedContacts = borderPaneForContactedContacts;
	}

	/**
	 * @return the borderPaneForAttemptedContacts
	 */
	public BorderPane getBorderPaneForAttemptedContacts() {
		return borderPaneForAttemptedContacts;
	}

	/**
	 * @param borderPaneForAttemptedContacts the borderPaneForAttemptedContacts to set
	 */
	public void setBorderPaneForAttemptedContacts(BorderPane borderPaneForAttemptedContacts) {
		this.borderPaneForAttemptedContacts = borderPaneForAttemptedContacts;
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the bE
	 */
	public BackEnd_1 getBE() {
		return BE;
	}

	/**
	 * @param bE the bE to set
	 */
	public void setBE(BackEnd_1 bE) {
		BE = bE;
	}

	/**
	 * @return the ccTable
	 */
	public Table_1_Controller getCcTable() {
		return ccTable;
	}

	/**
	 * @param ccTable the ccTable to set
	 */
	public void setCcTable(Table_1_Controller ccTable) {
		this.ccTable = ccTable;
	}

	/**
	 * @return the acTable
	 */
	public Table_2_Controller getAcTable() {
		return acTable;
	}

	/**
	 * @param acTable the acTable to set
	 */
	public void setAcTable(Table_2_Controller acTable) {
		this.acTable = acTable;
	}

	/**
	 * @return the anotherPaneController
	 */
	public AnotherPane getAnotherPaneController() {
		return anotherPaneController;
	}

	/**
	 * @param anotherPaneController the anotherPaneController to set
	 */
	public void setAnotherPaneController(AnotherPane anotherPaneController) {
		this.anotherPaneController = anotherPaneController;
	}

	/**
	 * @return the anotherPaneInRootPane
	 */
	public BorderPane getAnotherPaneInRootPane() {
		return anotherPaneInRootPane;
	}

	/**
	 * @param anotherPaneInRootPane the anotherPaneInRootPane to set
	 */
	public void setAnotherPaneInRootPane(BorderPane anotherPaneInRootPane) {
		this.anotherPaneInRootPane = anotherPaneInRootPane;
	}

	/**
	 * @return the anotherPaneLoader
	 */
	public FXMLLoader getAnotherPaneLoader() {
		return AnotherPaneLoader;
	}

	/**
	 * @param anotherPaneLoader the anotherPaneLoader to set
	 */
	public void setAnotherPaneLoader(FXMLLoader anotherPaneLoader) {
		AnotherPaneLoader = anotherPaneLoader;
	}

	/**
	 * @return the progressBar
	 */
	public ProgressBar getProgressBar() {
		return progressBar;
	}

	/**
	 * @param progressBar the progressBar to set
	 */
	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	/**
	 * @return the editTemplate
	 */
	public MenuItem getEditTemplate() {
		return editTemplate;
	}

	/**
	 * @param editTemplate the editTemplate to set
	 */
	public void setEditTemplate(MenuItem editTemplate) {
		this.editTemplate = editTemplate;
	}

	/**
	 * @return the htmlBorderPane
	 */
	public BorderPane getHtmlBorderPane() {
		return htmlBorderPane;
	}

	/**
	 * @param htmlBorderPane the htmlBorderPane to set
	 */
	public void setHtmlBorderPane(BorderPane htmlBorderPane) {
		this.htmlBorderPane = htmlBorderPane;
	}

	/**
	 * @return the htmlEditor
	 */
	public HTMLEditorView getHtmlEditor() {
		return htmlEditor;
	}

	/**
	 * @param htmlEditor the htmlEditor to set
	 */
	public void setHtmlEditor(HTMLEditorView htmlEditor) {
		this.htmlEditor = htmlEditor;
	}

	/**
	 * @return the htmlEditorController
	 */
	public HTMLeditor getHtmlEditorController() {
		return htmlEditorController;
	}

	/**
	 * @param htmlEditorController the htmlEditorController to set
	 */
	public void setHtmlEditorController(HTMLeditor htmlEditorController) {
		this.htmlEditorController = htmlEditorController;
	}

	public ActionModel getActionModel()
	{
		return actionModel;
	}

	/**
	 * @return the menutItemController
	 */
	public RootPaneController getMenutItemController() {
		return menutItemController;
	}

	/**
	 * @param menutItemController the menutItemController to set
	 */
	public void setMenutItemController(RootPaneController menutItemController) {
		this.menutItemController = menutItemController;
	}

	/**
	 * @return the contactedContactsTableController
	 */
	public ContactedContactsTableController getContactedContactsTableController() {
		return contactedContactsTableController;
	}

	/**
	 * @param contactedContactsTableController the contactedContactsTableController to set
	 */
	public void setContactedContactsTableController(ContactedContactsTableController contactedContactsTableController) {
		this.contactedContactsTableController = contactedContactsTableController;
	}
	
	/**
	 * 
	 * @param type
	 * @param title
	 * @param header
	 * @param body
	 */
	public void showPopUp(AlertType type, String title, String header, String body)
	{
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);
		alert.showAndWait();
		
	}

	/**
	 * @return the anotherWindow
	 */
	public AnotherWindow getAnotherWindow() {
		return anotherWindow;
	}

	/**
	 * @param anotherWindow the anotherWindow to set
	 */
	public void setAnotherWindow(AnotherWindow anotherWindow) {
		this.anotherWindow = anotherWindow;
	}
}
