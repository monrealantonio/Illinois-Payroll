/**
 * Created by Antonio on 7/22/2015.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application
{
    Stage window;
    Scene homeScene = new Scene(createHomePane(), 1024, 768);
    Scene addEmployeesScene;
    Scene viewEmployeeListScene;
    Scene viewEmployeeScene;
    Scene editEmployeeScene;
    Scene calcPayrollScene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
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
    public HBox createForm(){

        Label employeeID = new Label("Employee ID:");
        employeeID.setAlignment(Pos.BASELINE_LEFT);
        TextField employID = new TextField("0000000");
        employID.setPrefHeight(35);

        Label firstName = new Label("First Name:");
        TextField fName = new TextField ();
        fName.setPrefHeight(35);

        Label lastName = new Label("Last Name:");
        TextField lName = new TextField ();
        lName.setPrefHeight(35);

        Label DOB = new Label("Date of Birth:");
        TextField dateB = new TextField ("MM/DD/YYYY");
        dateB.setPrefHeight(35);

        Label socialSecurity = new Label("SSN:");
        TextField ssn = new TextField ("***-**-****");
        ssn.setPrefHeight(35);

        Label withold = new Label("Withold Amount:");
        TextField wth = new TextField ();
        wth.setPrefHeight(35);

        Label maritalStatus = new Label("Marital Status:");
        TextField mStatus = new TextField ();
        mStatus.setPrefHeight(35);

        Label wageR = new Label("Wage Rate:");
        TextField wage = new TextField ();
        wage.setPrefHeight(35);

        VBox holder = new VBox();
        holder.setAlignment(Pos.CENTER);
        holder.getChildren().addAll(employeeID, employID, firstName, fName, lastName, lName, DOB, dateB, socialSecurity, ssn, withold, wth, maritalStatus,mStatus,wageR,wage);

        HBox form = new HBox();
        form.getChildren().addAll(holder);


        return form;
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
        homeOnMouseClick(home);

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

    public VBox createTableView()
    {
        TableView table = new TableView();
        Label lbl = new Label("Employee List");

        TableColumn EID = new TableColumn("Employee ID");
        TableColumn fName = new TableColumn("First Name");
        TableColumn lName = new TableColumn("Last Name");
        table.getColumns().addAll(EID, fName, lName);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(lbl, table);

        return vbox;
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
            System.out.println("View Employee List Clicked");
            displayViewEmployeeListWindow();
        }
        ));

        addEmployees.setOnMouseClicked((e -> {
            System.out.println("Add Employees Clicked");
            displayAddEmployeeWindow();
        }
        ));

        editEmployees.setOnMouseClicked((e -> {
            System.out.println("Edit Employees Clicked");
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
        homeOnMouseClick(btnCancel);

        HBox form = createForm();
        form.setPrefWidth(400);
        form.setAlignment(Pos.CENTER);
        border.setCenter(form);

        return border;
    }

    public BorderPane createViewEmployeeListPane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle();
        HBox hBoxBot = new HBox();
        VBox vBoxTable = createTableView();

        Button btnBack = createNavButton("Back");
        Button btnCancel = createNavButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack, btnCancel);
        Button btnViewEmployeeInfo = createMainButton("View Employee Info");
        hBoxBot.setAlignment(Pos.CENTER_RIGHT);
        hBoxBot.getChildren().addAll(btnViewEmployeeInfo);

        border.setCenter(vBoxTable);
        border.setBottom(hBoxBot);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        homeOnMouseClick(btnCancel);

        btnViewEmployeeInfo.setOnMouseClicked((e -> {
            System.out.println("Employee Info Button Clicked");
            displayViewEmployeeWindow();
        }
        ));

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

        homeOnMouseClick(btnCancel);

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

        homeOnMouseClick(btnCancel);

        return border;
    }

    public BorderPane createCalcPayrollPane()
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

        homeOnMouseClick(btnCancel);

        return border;
    }

    public void homeOnMouseClick(Button btn)
    {
        btn.setOnMouseClicked((e -> {
            System.out.println("Home Button Clicked");
            displayHomeWindow();
        }
        ));
    }



    public void displayHomeWindow()
    {
        homeScene = new Scene(createHomePane(), window.getWidth(), window.getHeight());
        window.setScene(homeScene);
    }

    public void displayAddEmployeeWindow()
    {
        addEmployeesScene = new Scene(createAddEmployeePane(), window.getWidth(), window.getHeight());
        window.setScene(addEmployeesScene);
    }

    public void displayViewEmployeeListWindow()
    {
        viewEmployeeListScene = new Scene(createViewEmployeeListPane(), window.getWidth(), window.getHeight());
        window.setScene(viewEmployeeListScene);
    }

    public void displayViewEmployeeWindow()
    {
        viewEmployeeScene = new Scene(createViewEmployeePane(), window.getWidth(), window.getHeight());
        window.setScene(viewEmployeeScene);
    }

    public void displayEditEmployeeWindow()
    {
        editEmployeeScene = new Scene(createEditEmployeePane(), window.getWidth(), window.getHeight());
        window.setScene(editEmployeeScene);
    }

    public void displayCalcPayrollWindow()
    {
        calcPayrollScene = new Scene(createCalcPayrollPane(), window.getWidth(), window.getHeight());
        window.setScene(calcPayrollScene);
    }
}