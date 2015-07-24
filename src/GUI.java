/**
 * Created by Antonio on 7/22/2015.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application
{
    Stage window;
    Scene homeScene = new Scene(createHomePane(), 1024, 768);
    Scene addEmployeesScene = new Scene(createAddEmployeePane(), 1024, 768);
    Scene viewEmployeeScene = new Scene(createViewEmployeePane(), 1024, 768);
    Scene editEmployeeScene = new Scene(createEditEmployeePane(), 1024, 768);
    Scene calcPayrollScene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;
        window.setTitle("IPP v0.1");
        window.setScene(homeScene);
        window.show();
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

    public VBox createVBoxNav()
    {
        Button home = createNavButton("Home");
        VBox vBoxNav = new VBox();
        vBoxNav.getChildren().addAll(home);
        vBoxNav.setPrefWidth(200);
        vBoxNav.setStyle("-fx-background-color: steelblue");
        vBoxNav.setPadding(new Insets(15));
        vBoxNav.setAlignment(Pos.TOP_CENTER);
        vBoxNav.setSpacing(5);

        home.setOnMouseClicked((e -> {
            System.out.println("Home Clicked"); // testing lambda functionality
            displayHomeWindow();
        }
        ));

        return vBoxNav;
    }

    public HBox createHBoxTitle()
    {
        HBox hBoxTop = new HBox();
        Text txtTitle = new Text("Illinois Pay Program");

        txtTitle.setStyle("-fx-font-size: 18");
        hBoxTop.getChildren().addAll(txtTitle);
        hBoxTop.setStyle("-fx-background-color: LightGray");
        hBoxTop.setPrefHeight(40);
        hBoxTop.setAlignment(Pos.CENTER);

        return hBoxTop;
    }

    // This is the home page template that will be loaded whenever home is clicked.
    public BorderPane createHomePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle();
        HBox hBoxMainBot = new HBox();

        Button viewEmployees = createMainButton("View Employees");
        Button addEmployees = createMainButton("Add Employee");
        Button editEmployees = createMainButton("Edit Employee");

        border.setCenter(hBoxMainBot);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        hBoxMainBot.getChildren().addAll(viewEmployees, addEmployees, editEmployees);
        hBoxMainBot.setAlignment(Pos.CENTER);
        hBoxMainBot.setSpacing(25);

        viewEmployees.setOnMouseClicked((e -> {
            System.out.println("View Employees Clicked"); // testing lambda functionality
            displayViewEmployeeWindow();
        }
        ));

        addEmployees.setOnMouseClicked((e -> {
            System.out.println("Add Employees Clicked"); // testing lambda functionality
            displayAddEmployeeWindow();
        }
        ));

        editEmployees.setOnMouseClicked((e -> {
            System.out.println("Edit Employees Clicked"); // testing lambda functionality
            displayEditEmployeeWindow();
        }
        ));
        return border;
    }

    public BorderPane createAddEmployeePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle();

        Button btnBack = createNavButton("Back");
        Button btnSave = createNavButton("Save");
        Button btnCancel = createNavButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack, btnSave, btnCancel);

        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        return border;
    }

    public BorderPane createViewEmployeePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle();

        Button btnBack = createNavButton("Back");
        Button btnCancel = createNavButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack, btnCancel);

        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        return border;
    }

    public BorderPane createEditEmployeePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle();

        Button btnBack = createNavButton("Back");
        Button btnSave = createNavButton("Save");
        Button btnCancel = createNavButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack, btnSave, btnCancel);

        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        return border;
    }

    public void displayHomeWindow()
    {
        window.setScene(homeScene);
    }

    public void displayAddEmployeeWindow()
    {
        window.setScene(addEmployeesScene);
    }

    public void displayViewEmployeeWindow()
    {
        window.setScene(viewEmployeeScene);
    }

    public void displayEditEmployeeWindow()
    {
        window.setScene(editEmployeeScene);
    }

    public void displayCalcPayrollWindow()
    {
        window.setScene(calcPayrollScene);
    }
}
