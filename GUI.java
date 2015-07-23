/**
 * Created by Antonio on 7/22/2015.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application
{

    public static void main(String[] args)
    {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane border = new BorderPane();
        VBox vbox = new VBox();
        HBox hboxTop = new HBox();
        HBox hboxBot = new HBox();
        HBox main = new HBox();

        Text txtTitle = new Text("Illinois Pay Program");
        vbox.setPrefWidth(200);
        vbox.setStyle("-fx-background-color: steelblue");
        vbox.setPadding(new Insets(15));
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(createNavButton("Home"),
                createNavButton("Back"),
                createNavButton("Save"),
                createNavButton("Cancel"));
        vbox.setSpacing(5);

        txtTitle.setStyle("-fx-font-size: 18");

        border.setCenter(main);
        border.setLeft(vbox);
        border.setTop(hboxTop);
        border.setBottom(hboxBot);

        main.getChildren().addAll(createMainButton("View Employees"),
                createMainButton("Add Employee"),
                createMainButton("Edit Employee"));
        main.setAlignment(Pos.CENTER);
        main.setSpacing(25);

        hboxTop.getChildren().addAll(txtTitle);
        hboxTop.setStyle("-fx-background-color: LightGray");
        hboxTop.setPrefHeight(40);
        hboxTop.setAlignment(Pos.CENTER);

        Scene scene = new Scene(border, 1024, 768);
        primaryStage.setTitle("IPP v0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button createNavButton(String name)
    {
        Button btn = new Button(name);
        btn.setPrefWidth(200);
        btn.setPadding(new Insets(10));
        return btn;
    }

    public Button createMainButton(String name)
    {
        Button btn = new Button(name);
        btn.setPrefWidth(150);
        btn.setPadding(new Insets(10));
        return btn;
    }

    public TextField createTextField(String name)
    {
        TextField txtField = new TextField(name);


        return txtField;
    }
}
