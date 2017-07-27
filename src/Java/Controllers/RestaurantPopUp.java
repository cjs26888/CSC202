package Java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Biggus on 7/5/2017.
 */

public class RestaurantPopUp implements Initializable {
    @FXML
    private Pane pane;

    static Button btn;
    static TextArea resultsArea;
    static ImageView imView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setMinSize(500, 540);

        btn = new Button();
        btn.setOnMouseClicked(e -> switchScene());
        btn.setLayoutY(500);
        btn.setLayoutX(210);
        btn.setPrefWidth(50);
        btn.setPrefHeight(20);
        btn.setText("Back");
        
        resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setLayoutX(15);
        resultsArea.setLayoutY(15);
        resultsArea.setPrefHeight(300);
        resultsArea.setPrefWidth(450);

        imView = new ImageView();
        imView.setLayoutX(150);
        imView.setLayoutY(330);
        imView.setFitWidth(400);
        imView.setFitHeight(160);
        imView.setPickOnBounds(true);
        imView.setPreserveRatio(true);

        pane.getChildren().addAll(imView, resultsArea, btn);
    }

    /**
     * @param iv - The ImageView object from another Controller file
     * @param tArea - the TextArea object ffrom another Controller
     * @return void
     */
    public static void showResults(ImageView iv, TextArea tArea)
    {
        imView = iv;
        resultsArea = tArea;
    }

    /**
     * @return void
     * @throws IOException - if the file cannot be found
     */
    public void switchScene()
    {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Parent RPopUp;
        try
        {
            RPopUp = FXMLLoader.load(this.getClass().getResource("/Resources/View/RestaurantGUI.fxml"));
            Scene scene = new Scene(RPopUp);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}