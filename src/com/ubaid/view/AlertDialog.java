package com.ubaid.view;

//importing required componets
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
* this class pop up a dialog box 
* whenever it will initiated
* @author
*
*/
public class AlertDialog extends Stage {
	 
	//final variables
  private final int WIDTH_DEFAULT = 300;
  public static final int ICON_INFO = 0;
  public static final int ICON_ERROR = 1;    

  //constructor
  public AlertDialog(Stage owner, String msg, int type)
  {
  	//setting attributes
      setResizable(false);
      initModality(Modality.APPLICATION_MODAL);
      initStyle(StageStyle.TRANSPARENT);

      //label, setting font, wrapText, and gap
      Label label = new Label(msg);
      label.setFont(new Font(19));
      label.setWrapText(true);
      label.setGraphicTextGap(20);
      
      //image view, setting size
      ImageView imageView = new ImageView(getImage(type));
      imageView.setFitHeight(30);
      imageView.setFitWidth(30);
      //setting this image in the label
      label.setGraphic(imageView);

      //button
      Button button = new Button("OK");
      
      //action listner on button
      //when click on ok, this will close this pop up
      button.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent arg0) {
              AlertDialog.this.close();
          }

      });

      //border pane, adding stylesheets for styling
      BorderPane borderPane = new BorderPane();
      borderPane.getStylesheets().add(getClass().getResource("style1.css").toExternalForm());        
      //setting label in the center of border pane
      borderPane.setCenter(label);

      //horizonta box, setting alignment, adding button in the horizontal box
      HBox hbox2 = new HBox();
      hbox2.setAlignment(Pos.CENTER);
      hbox2.getChildren().add(button);
      borderPane.setBottom(hbox2);

      // calculate width of string
      final Text text = new Text(msg);
      text.snapshot(null, null);
      // + 20 because there is padding 10 left and right
      int width = (int) text.getLayoutBounds().getWidth() + 40;

      //if width is not equal to defaul width,
      //then setting width equal to default widht
      if (width != WIDTH_DEFAULT)
          width = WIDTH_DEFAULT;

      //height
      int height = 100;

      //making a scene instance
      final Scene scene = new Scene(borderPane, width, height);
      //adding style sheets
      scene.getStylesheets().add(AlertDialog.class.getResource("style1.css").toExternalForm());
      scene.setFill(Color.TRANSPARENT);
      //set scene to this class
      setScene(scene);

      // make sure this stage is centered on top of its owner
      setX(owner.getX() + (owner.getWidth() / 2 - width / 2));
      setY(owner.getY() + (owner.getHeight() / 2 - height / 2));
  }

  //getting image
  private Image getImage(int type)
  {
  	//if type is ICON_ERROR
      if (type == ICON_ERROR)
      {
      	//getting image
      	Image image = new Image("/error.png");
      	return image;
      }
      else
          return new Image(this.getClass().getResourceAsStream("images/info.png"));
  }

}
