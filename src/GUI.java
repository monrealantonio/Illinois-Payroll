import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application
{
    Stage window;
    Scene homeScene = new Scene(homePane(), 1024, 768);
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
        window.setMinHeight(768);
        window.setMinWidth(1024);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setWidth(primaryScreenBounds.getWidth());
        window.setHeight(primaryScreenBounds.getHeight());
        window.setTitle("IPP v0.1");
        window.setScene(homeScene);
        window.show();
    }

    // Get all of the Employees
    public ObservableList<Employee> getEmployee()
    {
        ObservableList<Employee> employees = DBConnector.listEmployee();
        return employees;
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

    public Label createFormLabel(String name, TextField txtField)
    {
        Label lbl = new Label(name);
        lbl.setPrefHeight(txtField.getPrefHeight());
        lbl.setPadding(new Insets(10, 10, 10, 10));
        return lbl;
    }

    public TextField createFormTextField(boolean isEditable)
    {
        TextField txtField = new TextField();
        txtField.setPrefHeight(35);
        txtField.setPadding(new Insets(10, 0, 10, 10));
        if(!isEditable)
            txtField.setEditable(false);
        return txtField;
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

    public HBox createHBoxTitle(String title)
    {
        HBox hBoxTop = new HBox();
        Text txtTitle = new Text(title);

        txtTitle.setStyle("-fx-font-size: 18");
        hBoxTop.getChildren().addAll(txtTitle);
        hBoxTop.setStyle("-fx-background-color: LightGray");
        hBoxTop.setPrefHeight(40);
        hBoxTop.setAlignment(Pos.CENTER);

        return hBoxTop;
    }

    public TableView<Employee> createTableView()
    {
        TableView<Employee> table = new TableView<>();

        TableColumn<Employee, Integer> eidColumn = new TableColumn<>("Employee ID");
        eidColumn.setMinWidth(50);
        eidColumn.setCellValueFactory(new PropertyValueFactory<>("idNum")); // must match attribute name in Employee

        TableColumn<Employee, String> firstName = new TableColumn<>("First Name");
        firstName.setMinWidth(200);
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> lastName = new TableColumn<>("Last Name");
        lastName.setMinWidth(200);
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table.setEditable(false);
        table.setMaxWidth(490);
        table.setItems(getEmployee());
        table.getColumns().addAll(eidColumn, firstName, lastName);

        return table;
    }

    // This is the home page template that will be loaded whenever home is clicked.
    public BorderPane homePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle("Home");
        HBox hBoxMainBot = new HBox();

        Button viewEmployees = createMainButton("View Employees");
        Button addEmployees = createMainButton("Add Employee");

        border.setCenter(hBoxMainBot);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        hBoxMainBot.getChildren().addAll(viewEmployees, addEmployees);
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
        return border;
    }

    public BorderPane addEmployeePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle("Employee Information");
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Button btnBack = createNavButton("Back");
        Button btnSave = createMainButton("Save");
        Button btnCancel = createMainButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack);
        hBoxBot.getChildren().addAll(btnSave, btnCancel);

        TextField fName = createFormTextField(true);
        TextField lName = createFormTextField(true);
        TextField dateB = createFormTextField(true);
        TextField ssn = createFormTextField(true);
        TextField wth = createFormTextField(true);
        TextField mStatus = createFormTextField(true);
        TextField wage = createFormTextField(true);

        Label firstName = createFormLabel("First Name:", fName);
        Label lastName = createFormLabel("Last Name:", fName);
        Label DOB = createFormLabel("Date of Birth:", dateB);
        Label socialSecurity = createFormLabel("SSN:", ssn);
        Label withhold = createFormLabel("Withhold Amount:", wth);
        Label maritalStatus = createFormLabel("Marital Status:", mStatus);
        Label wageR = createFormLabel("Wage Rate:", wage);

        lblCol.getChildren().addAll(firstName, lastName, DOB, socialSecurity, withhold, maritalStatus, wageR);
        txtFieldCol.getChildren().addAll(fName, lName, dateB, ssn, wth, mStatus, wage);
        form.getChildren().addAll(lblCol, txtFieldCol);
        container.getChildren().addAll(form, hBoxBot);

        hBoxBot.setSpacing(10);
        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);
        border.setCenter(container);
        homeOnMouseClick(btnCancel);

        btnSave.setOnMouseClicked((e -> {
            DBConnector.addEmployee(fName.getText(), lName.getText(), dateB.getText(), null, null, mStatus.getText(), Integer.parseInt(wth.getText()), Integer.parseInt(wage.getText()), ssn.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved");
            alert.setTitle("Employee Saved");
            alert.setResizable(false);
            alert.setHeaderText(null);
            alert.showAndWait();
            displayHomeWindow();
        }));

        return border;
    }

    public BorderPane viewEmployeeListPane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle("Employee List");
        HBox hBoxBot = new HBox();
        TableView table = createTableView();
        VBox vBoxTable = new VBox();
        vBoxTable.setAlignment(Pos.CENTER);
        vBoxTable.setSpacing(5);
        vBoxTable.setPadding(new Insets(10, 10, 0, 10));
        vBoxTable.getChildren().addAll(table);

        VBox container = new VBox();

        Button btnBack = createNavButton("Back");
        Button btnCalcPay = createMainButton("Calculate Payroll");
        Button btnViewEmployeeInfo = createMainButton("View Employee Info");

        vBoxNav.getChildren().addAll(btnBack);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        hBoxBot.getChildren().addAll(btnCalcPay, btnViewEmployeeInfo);
        vBoxTable.setAlignment(Pos.CENTER);
        container.getChildren().addAll(vBoxTable, hBoxBot);
        vBoxTable.setSpacing(10);
        hBoxBot.setSpacing(10);
        container.setSpacing(50);
        container.setAlignment(Pos.CENTER);

        border.setCenter(container);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);

        btnCalcPay.setOnMouseClicked((e -> {
            System.out.println("Calculate Payment Button Clicked");
            ObservableList<Employee> emp = (table.getSelectionModel().getSelectedItems());
            int ID = emp.get(0).getIdNum();
            System.out.println(ID);
            displayCalcPayrollWindow(ID);
        }
        ));

        btnViewEmployeeInfo.setOnMouseClicked((e -> {
            System.out.println("View Employee Info Clicked");
            ObservableList<Employee> emp = (table.getSelectionModel().getSelectedItems());
            int ID = emp.get(0).getIdNum();
            System.out.println(ID);
            displayViewEmployeeWindow(ID);
        }
        ));

        return border;
    }

    public BorderPane viewEmployeePane(int eid)
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle("Employee Information");
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Button btnBack = createNavButton("Back");
        Button btnCancel = createNavButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack, btnCancel);
        Button btnCalcPay = createMainButton("Calculate Payroll");
        Button btnEditEmployee = createMainButton("Edit Employee");
        hBoxBot.setSpacing(10);
        hBoxBot.getChildren().addAll(btnCalcPay, btnEditEmployee);

        TextField eID = createFormTextField(false);
        eID.setText(Integer.toString(eid));
        TextField fName = createFormTextField(false);
        fName.setText(DBConnector.getFname(eid));
        TextField lName = createFormTextField(false);
        lName.setText(DBConnector.getLname(eid));
        TextField dateB = createFormTextField(false);
        dateB.setText(DBConnector.getBdate(eid));
        TextField ssn = createFormTextField(false);
        ssn.setText(DBConnector.getSSN(eid));
        TextField wth = createFormTextField(false);
        wth.setText(Integer.toString(DBConnector.getWithholding(eid)));
        TextField mStatus = createFormTextField(false);
        mStatus.setText(DBConnector.getMarital(eid));
        TextField wage = createFormTextField(false);
        wage.setText(Double.toString(DBConnector.getWage(eid)));

        Label employeeID = createFormLabel("Employee ID:", eID);
        Label firstName = createFormLabel("First Name:", fName);
        Label lastName = createFormLabel("Last Name:", fName);
        Label DOB = createFormLabel("Date of Birth:", dateB);
        Label socialSecurity = createFormLabel("SSN:", ssn);
        Label withhold = createFormLabel("Withhold Amount:", wth);
        Label maritalStatus = createFormLabel("Marital Status:", mStatus);
        Label wageR = createFormLabel("Wage Rate:", wage);

        lblCol.getChildren().addAll(employeeID, firstName, lastName, DOB, socialSecurity, withhold, maritalStatus, wageR);
        txtFieldCol.getChildren().addAll(eID, fName, lName, dateB, ssn, wth, mStatus, wage);
        form.getChildren().addAll(lblCol, txtFieldCol);
        container.getChildren().addAll(form, hBoxBot);

        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);
        border.setCenter(container);

        homeOnMouseClick(btnCancel);
        btnCalcPay.setOnMouseClicked((e -> {
            System.out.println("Calculate Payroll Button Clicked");
            displayCalcPayrollWindow(eid);
        }
        ));

        btnEditEmployee.setOnMouseClicked((e -> {
            System.out.println("Edit Employees Clicked");
            displayEditEmployeeWindow(eid);
        }
        ));

        return border;
    }

    public BorderPane editEmployeePane(int eid)
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle("Employee Information");
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Button btnBack = createNavButton("Back");
        Button btnSave = createMainButton("Save");
        Button btnCancel = createMainButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack);
        hBoxBot.getChildren().addAll(btnSave, btnCancel);

        TextField empID = createFormTextField(false);
        empID.setText(Integer.toString(eid));
        TextField fName = createFormTextField(true);
        fName.setText(DBConnector.getFname(eid));
        TextField lName = createFormTextField(true);
        lName.setText(DBConnector.getLname(eid));
        TextField dateB = createFormTextField(true);
        dateB.setText(DBConnector.getBdate(eid));
        TextField ssn = createFormTextField(true);
        ssn.setText(DBConnector.getSSN(eid));
        TextField wth = createFormTextField(true);
        wth.setText(Integer.toString(DBConnector.getWithholding(eid)));
        TextField mStatus = createFormTextField(true);
        mStatus.setText(DBConnector.getMarital(eid));
        TextField wage = createFormTextField(true);
        wage.setText(Double.toString(DBConnector.getWage(eid)));

        Label lblEmpID = createFormLabel("Employee ID:", empID);
        Label firstName = createFormLabel("First Name:", fName);
        Label lastName = createFormLabel("Last Name:", fName);
        Label DOB = createFormLabel("Date of Birth:", dateB);
        Label socialSecurity = createFormLabel("SSN:", ssn);
        Label withhold = createFormLabel("Withhold Amount:", wth);
        Label maritalStatus = createFormLabel("Marital Status:", mStatus);
        Label wageR = createFormLabel("Wage Rate:", wage);

        lblCol.getChildren().addAll(lblEmpID, firstName, lastName, DOB, socialSecurity, withhold, maritalStatus, wageR);
        txtFieldCol.getChildren().addAll(empID, fName, lName, dateB, ssn, wth, mStatus, wage);
        form.getChildren().addAll(lblCol, txtFieldCol);
        container.getChildren().addAll(form, hBoxBot);

        hBoxBot.setSpacing(10);
        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);
        border.setCenter(container);
        homeOnMouseClick(btnCancel);

        btnSave.setOnMouseClicked((e -> {
            DBConnector.editEmployee(eid, fName.getText(), lName.getText(), dateB.getText(), null, null, mStatus.getText(), Integer.parseInt(wth.getText()), Double.parseDouble(wage.getText()), ssn.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved");
            alert.setTitle("Employee Saved");
            alert.setResizable(false);
            alert.setHeaderText(null);
            alert.showAndWait();
            displayHomeWindow();
        }));

        return border;
    }

    public BorderPane calcPayrollPane(int eid)
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxTop = createHBoxTitle("Employee Information");
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Button btnBack = createNavButton("Back");
        Button btnCancel = createNavButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack, btnCancel);
        Button btnCalcPay = createMainButton("Calculate Payroll");
        Button btnEditEmployee = createMainButton("Edit Employee");
        hBoxBot.setSpacing(10);
        hBoxBot.getChildren().addAll(btnCalcPay, btnEditEmployee);

        TextField eID = createFormTextField(false);
        eID.setText(Integer.toString(eid));
        TextField fName = createFormTextField(false);
        fName.setText(DBConnector.getFname(eid));
        TextField lName = createFormTextField(false);
        lName.setText(DBConnector.getLname(eid));
        TextField hours = createFormTextField(true);
        hours.setText("");
        TextField wages = createFormTextField(false);
        wages.setText(Double.toString(DBConnector.getWage(eid)));
        TextField grossIncome = createFormTextField(false);
        TextField fedTax = createFormTextField(false);
        TextField stateTax = createFormTextField(false);
        TextField ssi = createFormTextField(false);
        TextField med = createFormTextField(false);
        TextField net = createFormTextField(false);

        Label employeeID = createFormLabel("Employee ID:", eID);
        Label firstName = createFormLabel("First Name:", fName);
        Label lastName = createFormLabel("Last Name:", lName);
        Label hoursL = createFormLabel("Hours Worked:", hours);
        Label wagesL = createFormLabel("Wages:", wages);
        Label grossIncomeL= createFormLabel("Gross Income:", grossIncome);
        Label fedTaxL = createFormLabel("Federal Tax:", fedTax);
        Label stateL = createFormLabel("State Tax:", stateTax);
        Label ssiL = createFormLabel("SSI:", ssi);
        Label medL = createFormLabel("Medicare:", med);
        Label netL = createFormLabel("Net Income:", net);

        lblCol.getChildren().addAll(employeeID, firstName, lastName,hoursL, wagesL, grossIncomeL, fedTaxL, stateL, ssiL, medL, netL);
        txtFieldCol.getChildren().addAll(eID, fName, lName,hours, wages, grossIncome, fedTax, stateTax, ssi, med, net);
        form.getChildren().addAll(lblCol, txtFieldCol);
        container.getChildren().addAll(form, hBoxBot);

        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setTop(hBoxTop);
        border.setCenter(container);

        btnCalcPay.setOnMouseClicked((e -> {
            System.out.println("Calculate Button Clicked");
            Calculations calc = new Calculations((Double.parseDouble(hours.getText())),(Double.parseDouble(wages.getText())),DBConnector.getWithholding(eid));
            calc.setMaritalStatus(calc.isMarried(eid));
            grossIncome.setText(Double.toString(calc.calcGross()));
            fedTax.setText(Double.toString(calc.calcFederalTX()));
            stateTax.setText(Double.toString(calc.calcStateTX()));
            ssi.setText(Double.toString(calc.calcSocial_secu()));
            med.setText(Double.toString(calc.calcMediCare()));
            net.setText(Double.toString(calc.calcNetIn()));
        }
        ));

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
        homeScene = new Scene(homePane(), window.getWidth(), window.getHeight());
        window.setScene(homeScene);
    }

    public void displayAddEmployeeWindow()
    {
        addEmployeesScene = new Scene(addEmployeePane(), window.getWidth(), window.getHeight());
        window.setScene(addEmployeesScene);
    }

    public void displayViewEmployeeListWindow()
    {
        viewEmployeeListScene = new Scene(viewEmployeeListPane(), window.getWidth(), window.getHeight());
        window.setScene(viewEmployeeListScene);
    }

    public void displayViewEmployeeWindow(int eid)
    {
        viewEmployeeScene = new Scene(viewEmployeePane(eid), window.getWidth(), window.getHeight());
        window.setScene(viewEmployeeScene);
    }

    public void displayEditEmployeeWindow(int eid)
    {
        editEmployeeScene = new Scene(editEmployeePane(eid), window.getWidth(), window.getHeight());
        window.setScene(editEmployeeScene);
    }

    public void displayCalcPayrollWindow(int eid)
    {
        calcPayrollScene = new Scene(calcPayrollPane(eid), window.getWidth(), window.getHeight());
        window.setScene(calcPayrollScene);
    }
}
