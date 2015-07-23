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
        Scene scene = new Scene(homePane(), 1024, 768);
        primaryStage.setTitle("IPP v0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main navigation button creation
    public Button createNavButton(String name)
    {
        Button btn = new Button(name);
        btn.setPrefWidth(200);
        btn.setPadding(new Insets(10));
        return btn;
    }

    // Main pane button creation
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

    // This is the home page template that will be loaded whenever home is clicked.
    public BorderPane homePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = new VBox();
        HBox hBoxTop = new HBox();
        HBox hBoxMain = new HBox();
        Text txtTitle = new Text("Illinois Pay Program");

        Button home = createNavButton("Home");
        Button viewEmployees = createMainButton("View Employees");
        Button addEmployees = createMainButton("Add Employee");
        Button editEmployees = createMainButton("Edit Employee");

        vBoxNav.setPrefWidth(200);
        vBoxNav.setStyle("-fx-background-color: steelblue");
        vBoxNav.setPadding(new Insets(15));
        vBoxNav.setAlignment(Pos.TOP_CENTER);
        vBoxNav.getChildren().addAll(home);
        vBoxNav.setSpacing(5);

        txtTitle.setStyle("-fx-font-size: 18");

        border.setCenter(hBoxMain);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        hBoxMain.getChildren().addAll(viewEmployees, addEmployees, editEmployees);
        hBoxMain.setAlignment(Pos.CENTER);
        hBoxMain.setSpacing(25);

        hBoxTop.getChildren().addAll(txtTitle);
        hBoxTop.setStyle("-fx-background-color: LightGray");
        hBoxTop.setPrefHeight(40);
        hBoxTop.setAlignment(Pos.CENTER);

        home.setOnMouseClicked((e ->{
            System.out.println("Home Clicked"); // testing lambda functionality
        }
        ));

        viewEmployees.setOnMouseClicked((e ->{
            System.out.println("View Employees Clicked"); // testing lambda functionality
        }
        ));

        addEmployees.setOnMouseClicked((e ->{
            System.out.println("Add Employees Clicked"); // testing lambda functionality
        }
        ));

        editEmployees.setOnMouseClicked((e -> {
            System.out.println("Edit Employees Clicked"); // testing lambda functionality
        }
        ));
        return border;
    }
}
