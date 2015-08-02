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
    Scene payrollRecordsScene;

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
        window.setTitle("IPP v0.1: Home");
        window.setScene(homeScene);
        window.show();
    }

    // Get all of the Employees
    public ObservableList<Employee> getEmployee()
    {
        ObservableList<Employee> employees = DBConnector.listEmployee();
        return employees;
    }

    public ObservableList<Payroll> getPayroll(int eid)
    {
        ObservableList<Payroll> payroll = DBConnector.listPayroll(eid);
        return payroll;
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
        btn.setPrefWidth(140);
        btn.setPrefHeight(20);
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

    public TableView<Employee> createEmpTableView()
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

    public TableView<Payroll> createPayrollTableView(int eid)
    {
        TableView<Payroll> table = new TableView<>();

        TableColumn<Payroll, Integer> eIDColumn = new TableColumn<>("Employee ID");
        eIDColumn.setMinWidth(90);
        eIDColumn.setCellValueFactory(new PropertyValueFactory<>("eid"));

        TableColumn<Payroll, Integer> pIDColumn = new TableColumn<>("Week");
        pIDColumn.setMinWidth(90);
        pIDColumn.setCellValueFactory(new PropertyValueFactory<>("pid")); // must match attribute name in Payroll

        TableColumn<Payroll, String> startDate = new TableColumn<>("Start Date");
        startDate.setMinWidth(100);
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Payroll, String> endDate = new TableColumn<>("End Date");
        endDate.setMinWidth(100);
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<Payroll, Double> net = new TableColumn<>("Net Amount");
        net.setMinWidth(100);
        net.setCellValueFactory(new PropertyValueFactory<>("net"));

        table.setEditable(false);
        table.setMaxWidth(490);
        table.setItems(getPayroll(eid));
        table.getColumns().addAll(eIDColumn, pIDColumn, startDate, endDate, net);

        return table;
    }

    // This is the home page template that will be loaded whenever home is clicked.
    public BorderPane homePane()
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        VBox container = new VBox();
        HBox hBoxMainTop = new HBox();
        HBox hBoxMainBot = new HBox();

        Text txtTitle = new Text("Home");
        hBoxMainTop.getChildren().addAll(txtTitle);

        Button viewEmployees = createMainButton("View Employees");
        Button addEmployees = createMainButton("Add Employee");

        container.getChildren().addAll(hBoxMainTop, hBoxMainBot);
        border.setCenter(container);
        border.setLeft(vBoxNav);

        hBoxMainBot.getChildren().addAll(viewEmployees, addEmployees);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);
        hBoxMainBot.setAlignment(Pos.CENTER);
        hBoxMainBot.setSpacing(25);
        container.setSpacing(50);
        container.setAlignment(Pos.CENTER);

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
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        HBox hBoxMainTop = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Button btnBack = createNavButton("Back");
        Button btnSave = createMainButton("Save");
        Button btnCancel = createMainButton("Cancel");
        vBoxNav.getChildren().addAll(btnBack);
        hBoxBot.getChildren().addAll(btnSave, btnCancel);

        Text txtTitle = new Text("Add Employee");
        hBoxMainTop.getChildren().addAll(txtTitle);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

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
        container.getChildren().addAll(hBoxMainTop, form, hBoxBot);

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
        border.setCenter(container);

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
        HBox hBoxBot = new HBox();
        HBox hBoxMainTop = new HBox();
        TableView empTable = createEmpTableView();
        VBox vBoxTable = new VBox();
        vBoxTable.setAlignment(Pos.CENTER);
        vBoxTable.setSpacing(5);
        vBoxTable.setPadding(new Insets(10, 10, 0, 10));
        vBoxTable.getChildren().addAll(empTable);
        VBox container = new VBox();

        Text txtTitle = new Text("Employee Roster");
        hBoxMainTop.getChildren().addAll(txtTitle);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

        Button btnBack = createNavButton("Back");
        Button btnCalcPay = createMainButton("Calculate Payroll");
        Button btnViewEmployeeInfo = createMainButton("View Employee Info");
        Button btnViewPayrollHistory = createMainButton("View Payroll History");

        vBoxNav.getChildren().addAll(btnBack);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        hBoxBot.getChildren().addAll(btnCalcPay, btnViewEmployeeInfo, btnViewPayrollHistory);
        vBoxTable.setAlignment(Pos.CENTER);
        container.getChildren().addAll(hBoxMainTop, vBoxTable, hBoxBot);
        vBoxTable.setSpacing(10);
        hBoxBot.setSpacing(10);
        container.setSpacing(50);
        container.setAlignment(Pos.CENTER);

        border.setCenter(container);
        border.setLeft(vBoxNav);

        btnCalcPay.setOnMouseClicked((e -> {
            System.out.println("Calculate Payment Button Clicked");
            ObservableList<Employee> emp = (empTable.getSelectionModel().getSelectedItems());
            int ID = emp.get(0).getIdNum();
            System.out.println(ID);
            displayCalcPayrollWindow(ID);
        }
        ));

        btnViewEmployeeInfo.setOnMouseClicked((e -> {
            System.out.println("View Employee Info Clicked");
            ObservableList<Employee> emp = (empTable.getSelectionModel().getSelectedItems());
            int ID = emp.get(0).getIdNum();
            System.out.println(ID);
            displayViewEmployeeWindow(ID);
        }
        ));

        btnViewPayrollHistory.setOnMouseClicked((e -> {
            System.out.println("View Employee Info Clicked");
            ObservableList<Employee> payroll = (empTable.getSelectionModel().getSelectedItems());
            int ID = payroll.get(0).getIdNum();
            System.out.println(ID);
            displayPayrollRecordsWindow(ID);
        }
        ));

        return border;
    }

    public BorderPane payrollRecordsPane(int eid)
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxMainTop = new HBox();
        HBox hBoxBot = new HBox();
        TableView table = createPayrollTableView(eid);
        VBox vBoxTable = new VBox();
        vBoxTable.setAlignment(Pos.CENTER);
        vBoxTable.setSpacing(5);
        vBoxTable.setPadding(new Insets(10, 10, 0, 10));
        vBoxTable.getChildren().addAll(table);
        VBox container = new VBox();

        Text txtTitle = new Text("Payroll Records");
        hBoxMainTop.getChildren().addAll(txtTitle);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

        Button btnBack = createNavButton("Back");
        Button btnViewRecord = createMainButton("View Payroll Record");

        vBoxNav.getChildren().addAll(btnBack);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        hBoxBot.getChildren().addAll(btnViewRecord);
        vBoxTable.setAlignment(Pos.CENTER);
        container.getChildren().addAll(hBoxMainTop, vBoxTable, hBoxBot);
        vBoxTable.setSpacing(10);
        hBoxBot.setSpacing(10);
        container.setSpacing(50);
        container.setAlignment(Pos.CENTER);

        border.setCenter(container);
        border.setLeft(vBoxNav);

        btnViewRecord.setOnMouseClicked((e -> {
            System.out.println("View Employee Info Clicked");
            ObservableList<Payroll> payroll = (table.getSelectionModel().getSelectedItems());
            Payroll record = payroll.get(0);
            displayCalcPayrollWindow(record);
        }
        ));

        return border;
    }

    public BorderPane viewEmployeePane(int eid)
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxMainTop = new HBox();
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Text txtTitle = new Text("View Employee Information");
        hBoxMainTop.getChildren().addAll(txtTitle);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

        Button btnBack = createNavButton("Back");
        vBoxNav.getChildren().addAll(btnBack);
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
        container.getChildren().addAll(hBoxMainTop, form, hBoxBot);

        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setCenter(container);

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
        HBox hBoxMainTop = new HBox();
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();

        Text txtTitle = new Text("Edit Employee Information for ");
        Text txtName = new Text(DBConnector.getFname(eid) + " " + DBConnector.getLname(eid));
        hBoxMainTop.getChildren().addAll(txtTitle, txtName);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

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
        container.getChildren().addAll(hBoxMainTop, form, hBoxBot);

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
        HBox hBoxMainTop = new HBox();
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();
        VBox lblColTwo = new VBox();
        VBox txtFieldColTwo = new VBox();

        Text txtTitle = new Text("Calculate Payroll for ");
        Text txtName = new Text(DBConnector.getFname(eid) + " " + DBConnector.getLname(eid));
        hBoxMainTop.getChildren().addAll(txtTitle, txtName);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

        Button btnBack = createNavButton("Back");
        vBoxNav.getChildren().addAll(btnBack);
        Button btnCalcPay = createMainButton("Calculate Payroll");
        Button btnSavePayroll = createMainButton("Save Payroll");
        hBoxBot.setSpacing(10);
        hBoxBot.getChildren().addAll(btnCalcPay, btnSavePayroll);

        TextField eID = createFormTextField(false);
        eID.setText(Integer.toString(eid));
        TextField fName = createFormTextField(false);
        fName.setText(DBConnector.getFname(eid));
        TextField lName = createFormTextField(false);
        lName.setText(DBConnector.getLname(eid));
        TextField hours = createFormTextField(true);
        hours.setText("");
        TextField startDate = createFormTextField(true);
        TextField endDate = createFormTextField(true);
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
        Label startDateL = createFormLabel("Start Date:", startDate);
        Label endDateL = createFormLabel("End Date:", endDate);
        Label wagesL = createFormLabel("Wage:", wages);
        Label grossIncomeL= createFormLabel("Gross Income:", grossIncome);
        Label fedTaxL = createFormLabel("Federal Tax:", fedTax);
        Label stateL = createFormLabel("State Tax:", stateTax);
        Label ssiL = createFormLabel("SSI:", ssi);
        Label medL = createFormLabel("Medicare:", med);
        Label netL = createFormLabel("Net Income:", net);

        lblCol.getChildren().addAll(employeeID, firstName, lastName, wagesL, startDateL, endDateL, hoursL);
        txtFieldCol.getChildren().addAll(eID, fName, lName, wages, startDate, endDate, hours);
        lblColTwo.getChildren().addAll(grossIncomeL, ssiL, medL, fedTaxL, stateL, netL);
        txtFieldColTwo.getChildren().addAll(grossIncome, ssi, med, fedTax, stateTax, net);
        form.getChildren().addAll(lblCol, txtFieldCol, lblColTwo, txtFieldColTwo);
        container.getChildren().addAll(hBoxMainTop, form, hBoxBot);

        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        lblColTwo.setSpacing(10);
        txtFieldColTwo.setSpacing(10);
        form.setSpacing(20);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setCenter(container);

        btnCalcPay.setOnMouseClicked((e -> {
            System.out.println("Calculate Button Clicked");
            Calculations calc = new Calculations((Double.parseDouble(hours.getText())), (Double.parseDouble(wages.getText())), DBConnector.getWithholding(eid));
            calc.setMaritalStatus(calc.isMarried(eid));
            grossIncome.setText(Double.toString(calc.calcGross()));
            fedTax.setText(Double.toString(calc.calcFederalTX()));
            stateTax.setText(Double.toString(calc.calcStateTX()));
            ssi.setText(Double.toString(calc.calcSocial_secu()));
            med.setText(Double.toString(calc.calcMediCare()));
            net.setText(Double.toString(calc.calcNetIn()));
        }
        ));

        btnSavePayroll.setOnMouseClicked((e -> {
            DBConnector.addPayroll(eid, Double.parseDouble(grossIncome.getText()), Double.parseDouble(fedTax.getText()),
                    Double.parseDouble(stateTax.getText()), Double.parseDouble(ssi.getText()),
                    Double.parseDouble(med.getText()), Double.parseDouble(net.getText()), startDate.getText(),
                    endDate.getText(), Double.parseDouble(hours.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Payroll Saved");
            alert.setTitle("Payroll Saved");
            alert.setResizable(false);
            alert.setHeaderText(null);
            alert.showAndWait();
            displayHomeWindow();
        }));

        return border;
    }

    public BorderPane calcPayrollPane(Payroll rec)
    {
        BorderPane border = new BorderPane();
        VBox vBoxNav = createVBoxNav();
        HBox hBoxMainTop = new HBox();
        HBox hBoxBot = new HBox();
        HBox form = new HBox();
        VBox container = new VBox();
        VBox lblCol = new VBox();
        VBox txtFieldCol = new VBox();
        VBox lblColTwo = new VBox();
        VBox txtFieldColTwo = new VBox();

        Text txtTitle = new Text("Payroll Record for ");
        Text txtName = new Text(DBConnector.getFname(rec.getEid()) + " " + DBConnector.getLname(rec.getEid()));
        hBoxMainTop.getChildren().addAll(txtTitle, txtName);
        hBoxMainTop.setAlignment(Pos.TOP_CENTER);

        Button btnBack = createNavButton("Back");
        vBoxNav.getChildren().addAll(btnBack);

        TextField eID = createFormTextField(false);
        eID.setText(Integer.toString(rec.getEid()));
        TextField fName = createFormTextField(false);
        fName.setText(DBConnector.getFname(rec.getEid()));
        TextField lName = createFormTextField(false);
        lName.setText(DBConnector.getLname(rec.getEid()));
        TextField hours = createFormTextField(false);
        hours.setText(Double.toString(DBConnector.getHours(rec.getEid())));
        TextField startDate = createFormTextField(false);
        startDate.setText(DBConnector.getStartDate(rec.getPid()));
        TextField endDate = createFormTextField(false);
        endDate.setText(DBConnector.getEndDate(rec.getPid()));
        TextField wages = createFormTextField(false);
        wages.setText(Double.toString(DBConnector.getWage(rec.getEid())));
        TextField grossIncome = createFormTextField(false);
        grossIncome.setText(Double.toString(DBConnector.getGross(rec.getPid())));
        TextField fedTax = createFormTextField(false);
        fedTax.setText(Double.toString(DBConnector.getFIT(rec.getPid())));
        TextField stateTax = createFormTextField(false);
        stateTax.setText(Double.toString(DBConnector.getSIT(rec.getPid())));
        TextField ssi = createFormTextField(false);
        ssi.setText(Double.toString(DBConnector.getSocialTax(rec.getPid())));
        TextField med = createFormTextField(false);
        med.setText(Double.toString(DBConnector.getMedicare(rec.getPid())));
        TextField net = createFormTextField(false);
        net.setText(Double.toString(DBConnector.getNet(rec.getPid())));

        Label employeeID = createFormLabel("Employee ID:", eID);
        Label firstName = createFormLabel("First Name:", fName);
        Label lastName = createFormLabel("Last Name:", lName);
        Label hoursL = createFormLabel("Hours Worked:", hours);
        Label startDateL = createFormLabel("Start Date:", startDate);
        Label endDateL = createFormLabel("End Date:", endDate);
        Label wagesL = createFormLabel("Wages:", wages);
        Label grossIncomeL= createFormLabel("Gross Income:", grossIncome);
        Label fedTaxL = createFormLabel("Federal Tax:", fedTax);
        Label stateL = createFormLabel("State Tax:", stateTax);
        Label ssiL = createFormLabel("SSI:", ssi);
        Label medL = createFormLabel("Medicare:", med);
        Label netL = createFormLabel("Net Income:", net);

        lblCol.getChildren().addAll(employeeID, firstName, lastName, wagesL, startDateL, endDateL, hoursL);
        txtFieldCol.getChildren().addAll(eID, fName, lName, wages, startDate, endDate, hours);
        lblColTwo.getChildren().addAll(grossIncomeL, ssiL, medL, fedTaxL, stateL, netL);
        txtFieldColTwo.getChildren().addAll(grossIncome, ssi, med, fedTax, stateTax, net);
        form.getChildren().addAll(lblCol, txtFieldCol, lblColTwo, txtFieldColTwo);
        container.getChildren().addAll(hBoxMainTop, form, hBoxBot);

        lblCol.setSpacing(10);
        txtFieldCol.setSpacing(10);
        lblColTwo.setSpacing(10);
        txtFieldColTwo.setSpacing(10);
        form.setSpacing(20);
        hBoxBot.setAlignment(Pos.BASELINE_CENTER);
        form.setAlignment(Pos.CENTER);
        lblCol.setAlignment(Pos.CENTER);
        txtFieldCol.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        border.setLeft(vBoxNav);
        border.setCenter(container);

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
        window.setTitle("IPP v0.1: Home");
        window.setScene(homeScene);
    }

    public void displayAddEmployeeWindow()
    {
        addEmployeesScene = new Scene(addEmployeePane(), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: Add Employees");
        window.setScene(addEmployeesScene);
    }

    public void displayViewEmployeeListWindow()
    {
        viewEmployeeListScene = new Scene(viewEmployeeListPane(), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: Employee Roster");
        window.setScene(viewEmployeeListScene);
    }

    public void displayViewEmployeeWindow(int eid)
    {
        viewEmployeeScene = new Scene(viewEmployeePane(eid), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: View Employee Information");
        window.setScene(viewEmployeeScene);
    }

    public void displayEditEmployeeWindow(int eid)
    {
        editEmployeeScene = new Scene(editEmployeePane(eid), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: Edit Employee Information");
        window.setScene(editEmployeeScene);
    }

    public void displayCalcPayrollWindow(int eid)
    {
        calcPayrollScene = new Scene(calcPayrollPane(eid), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: Calculate Payroll");
        window.setScene(calcPayrollScene);
    }

    public void displayCalcPayrollWindow(Payroll record)
    {
        calcPayrollScene = new Scene(calcPayrollPane(record), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: Calculate Payroll");
        window.setScene(calcPayrollScene);
    }

    public void displayPayrollRecordsWindow(int eid)
    {
        payrollRecordsScene = new Scene(payrollRecordsPane(eid), window.getWidth(), window.getHeight());
        window.setTitle("IPP v0.1: Payroll Records");
        window.setScene(payrollRecordsScene);
    }
}
