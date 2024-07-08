package com.kami.study.apfivethlab;

import com.kami.study.apfivethlab.model.Employer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MainController implements Initializable {
// Здесь объявляются все элементы пользовательского интерфейса, такие как текстовые поля, кнопки и таблица.
    // Каждый элемент имеет свой собственный идентификатор, который используется для доступа к нему в коде.

    public TextField idField;
    public TextField firstnameField;
    public TextField lastnameField;
    public TextField managerField;
    public TextField salaryField;
    public TextField departmentField;
    public TextField NameDepartmentField;
    public TextField cityField;
    public TextField etcField;
    public Button addButton;
    public Button editButton;
    public Button deleteButton;
    public Button byIdButton;
    public TableView<Employer> table;
    public TableColumn<Employer, Integer> idCol;
    public TableColumn<Employer, String> fNameCol;
    public TableColumn<Employer, String> lNameCol;
    public TableColumn<Employer, String> managerCol;
    public TableColumn<Employer, Integer> salaryCol;
    public TableColumn<Employer, String> departmentCol;
    public TableColumn<Employer, String> cityCol;
    public TableColumn<Employer, Integer> etcCol;
    public TableColumn<Employer, Integer> ectRatioCol;
    public TableColumn<Employer, String> nameDepartmentCol;
    String URL = "jdbc:mysql://localhost/company";
    String user = "root";
    String password = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable(true, 0);
    } // Метод initialize вызывается при загрузке интерфейса.
    // Здесь происходит инициализация таблицы и обновление данных.

    private Connection getConnection() {   // Метод getConnection устанавливает соединение с базой данных MySQL.
        // Если соединение не установлено, будет отображено сообщение об ошибке.

        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, user, password);
            System.out.println("Connection succeed..." );
            return conn;
        } catch (Exception e) {
            Popup.showPopup("Ошибка!", "Невозможно установить соединение c базой данных!");
            return null;
        }
    }

    public ObservableList<Employer> allEmp() {
        // Метод allEmp возвращает список всех сотрудников из базы данных.
        // Если возникает ошибка при выполнении запроса, отображается сообщение об ошибке.

        ObservableList<Employer> empList = FXCollections.observableArrayList();
        String query = "SELECT * FROM employees ";
        String query2 = "SELECT * FROM departments ";
        String query3 = "SELECT * FROM etss ";
        Connection conn = getConnection();
        try {
            assert conn != null;
            Statement st;
            ResultSet rs;
            Statement st2;
            ResultSet rs2;
            Statement st3;
            ResultSet rs3;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Employer employer;

            while (rs.next()) {
                st2 = conn.createStatement();
                rs2 = st2.executeQuery(query2);
                st3 = conn.createStatement();
                rs3 = st3.executeQuery(query3);
                String nameDepartment = "";
                int ETCRatio = 1;
                while(rs2.next()){
                    if(rs.getInt("Department") == rs2.getInt("Department")){
                        nameDepartment = rs2.getString("nameDepartment");
                    }
                }
                while(rs3.next()){
                    if(rs.getInt("ETC") == rs3.getInt("ETC")){
                        ETCRatio = rs3.getInt("ETCRatio");
                    }
                }
                employer = new Employer(rs.getInt("ID"),
                        rs.getString("Firstname"),
                        rs.getString("Lastname"),
                        rs.getString("Manager"),
                        rs.getInt("Salary"),
                        rs.getInt("Department"),
                        nameDepartment, rs.getString("City"),
                        rs.getInt("ETC"), ETCRatio);
                empList.add(employer);
            }
        } catch (Exception e) {
            Popup.showPopup("Ошибка!", "Невозможно обработать запрос! Проверьте соединение с базой данных.");
        }

        return empList;
    }

    // Далее следуют методы для добавления, удаления и редактирования сотрудников в базе данных.
    // Также есть методы для выполнения запросов и обновления таблицы.
    public void add() {
        Connection conn = getConnection();
        boolean key2 = false;
        String query4 = "SELECT * FROM employees WHERE ID=" + idField.getText();
        Statement st4;
        ResultSet rs4;
        try {
            assert conn != null;
            st4 = conn.createStatement();
            rs4 = st4.executeQuery(query4);
            while(rs4.next()){
                key2 = true;
            }
        }catch (Exception e) {
            Popup.showPopup("Ошибка!", "Невозможно обработать запрос! Проверьте соединение с базой данных.");
        }
        if(!key2) {


            String query2 = "SELECT * FROM departments ";
            Statement st2;
            ResultSet rs2;
            boolean key = false;


            ObservableList<Employer> employerList = FXCollections.observableArrayList();

            Statement st;
            ResultSet rs;


            try {
                assert conn != null;
                st2 = conn.createStatement();
                rs2 = st2.executeQuery(query2);
                while (rs2.next()) {
                    if (Integer.parseInt(departmentField.getText()) == rs2.getInt("Department")) {
                        key = true;
                    }
                }
            } catch (Exception e) {
                Popup.showPopup("Ошибка!", "Невозможно обработать запрос! Проверьте соединение с базой данных.");
            }

            if (!key) {
                String query3 = "INSERT INTO departments VALUES("
                        + Integer.parseInt(departmentField.getText()) + ", '"
                        + NameDepartmentField.getText() + "')";
                executeQuery(query3);
            }
            if (Integer.parseInt(etcField.getText()) >= 1 && Integer.parseInt(etcField.getText()) <= 18 && !key2) {
                // Метод add добавляет нового сотрудника в базу данных.
                // Новые данные сотрудника берутся из текстовых полей интерфейса.
                String query = "INSERT INTO employees VALUES("
                        + idField.getText() + ", '"
                        + firstnameField.getText() + "', '"
                        + lastnameField.getText() + "', '"
                        + managerField.getText() + "', "
                        + Integer.parseInt(salaryField.getText()) + ", '"
                        + Integer.parseInt(departmentField.getText()) + "', '"
                        + cityField.getText() + "', '"
                        + Integer.parseInt(etcField.getText()) + "')";
                executeQuery(query);


                updateTable(true, 0);
            } else {
                Popup.showPopup("Ошибка!", "ETC должен быть от 1 до 16");

            }
        }
        else{
            Popup.showPopup("Ошибка!", "Ошибка id должен быть уникальный");
        }
    }

    public void delete() {
        // Метод delete удаляет сотрудника из базы данных по его идентификатору.
        if(!idField.getText().isEmpty()) {
            String query = "DELETE FROM employees WHERE ID=" + Integer.parseInt(idField.getText());
            executeQuery(query);
            updateTable(true, 0);
        }
        else{
            Popup.showPopup("Ошибка!", "Укажите Id");
        }
    }

    public void edit() {
        if(Integer.parseInt(etcField.getText()) >=1 && Integer.parseInt(etcField.getText()) <=18) {
            // Метод edit редактирует данные о сотруднике в базе данных.
            // Измененные данные берутся из текстовых полей интерфейса.
            String query = "UPDATE employees SET " +
                    "Firstname='" + firstnameField.getText() +
                    "', Lastname='" + lastnameField.getText() +
                    "', Manager='" + managerField.getText() +
                    "', Salary='" + Integer.parseInt(salaryField.getText()) +
                    "', Department='" + Integer.parseInt(departmentField.getText()) +
                    "', City='" + cityField.getText() +
                    "', ETC='" + Integer.parseInt(etcField.getText()) +
                    "' WHERE ID=" + Integer.parseInt(idField.getText());
            executeQuery(query);

            String query2 = "SELECT * FROM departments ";
            Connection conn = getConnection();
            Statement st2;
            ResultSet rs2;
            boolean key = false;
            try {
                assert conn != null;
                st2 = conn.createStatement();
                rs2 = st2.executeQuery(query2);
                while (rs2.next()) {
                    if(Integer.parseInt(departmentField.getText()) == rs2.getInt("Department")){
                        key = true;
                    }
                }
            } catch (Exception e) {
                Popup.showPopup("Ошибка!", "Невозможно обработать запрос! Проверьте соединение с базой данных.");
            }
            if(!key){
                String query3 = "INSERT INTO departments VALUES("
                        + Integer.parseInt(departmentField.getText()) + ", '"
                        + NameDepartmentField.getText() + "')";
                executeQuery(query3);
            }
            updateTable(true, 0);
        }

    }

    public ObservableList<Employer> getById(int id) {
        // Метод getById возвращает список сотрудников с заданным идентификатором.
        // Если сотрудник с указанным идентификатором не найден, будет отображено сообщение об ошибке.
        ObservableList<Employer> employerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM employees WHERE ID=" + id;

        Connection conn = getConnection();
        Statement st;
        ResultSet rs;


        try {
            assert conn != null;
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Employer employer;
            if (rs.next()) {

                String query2 = "SELECT * FROM departments WHERE Department=" + rs.getInt("Department");
                String query3 = "SELECT * FROM etss WHERE ETC=" + rs.getInt("ETC");

                Statement st2;
                ResultSet rs2;
                Statement st3;
                ResultSet rs3;

                st2 = conn.createStatement();
                rs2 = st2.executeQuery(query2);
                st3 = conn.createStatement();
                rs3 = st3.executeQuery(query3);

                String nameDepartment = "";
                int ETCRatio = 1;

                while(rs2.next()){
                    nameDepartment = rs2.getString("nameDepartment");
                }
                while(rs3.next()){
                    ETCRatio = rs3.getInt("ETCRatio");
                }

                employer = new Employer(rs.getInt("ID"),
                        rs.getString("Firstname"),
                        rs.getString("Lastname"),
                        rs.getString("Manager"),
                        rs.getInt("Salary"),
                        rs.getInt("Department"),
                        nameDepartment, rs.getString("City"),
                        rs.getInt("ETC"), ETCRatio);
                employerList.add(employer);
                return employerList;
            }
            else{
                Popup.showPopup("Ошибка!", "Не найден элемент с данным id");
            }
        } catch (Exception e) {
            Popup.showPopup("Ошибка!", "Невозможно обработать запрос! Проверьте соединение с базой данных.");
        }
        return null;
    }

    public void executeQuery(String query) {
        // Метод executeQuery выполняет запрос к базе данных.
        // Если запрос не может быть выполнен, будет отображено сообщение об ошибке.

        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            Popup.showPopup("Ошибка!", "Невозможно обработать запрос! Проверьте соединение с базой данных.");
        }
    }

    public void updateTable(boolean getAll, int id) {
        // Метод updateTable обновляет данные в таблице сотрудников.
        // Если getAll равно true, то обновляются все данные, иначе обновляются данные только для сотрудника с указанным идентификатором.
        ObservableList<Employer> list;

        if ((!getAll) && (id>0)) {
            list = getById(id);
        }else{
            list = allEmp();
        }
        // Здесь указывается, какие данные должны отображаться в каждом столбце таблицы.
        // Данные берутся из соответствующих полей объекта Employer.
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        managerCol.setCellValueFactory(new PropertyValueFactory<>("Manager"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("Department"));
        nameDepartmentCol.setCellValueFactory(new PropertyValueFactory<>("nameDepartment"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("City"));
        etcCol.setCellValueFactory(new PropertyValueFactory<>("ETC"));
        ectRatioCol.setCellValueFactory(new PropertyValueFactory<>("ETCRatio"));
        table.setItems(list);
    }

    public void addButtonAction(ActionEvent actionEvent) {
        if(!idField.getText().isEmpty()) {
            add();
        }else {
            Popup.showPopup("Ошибка!", "укажите id для вставки");
        }
    }

    public void editButtonAction(ActionEvent actionEvent) {
        if(!idField.getText().isEmpty()) {
            edit();
        }else {
            Popup.showPopup("Ошибка!", "укажите id для замены");
        }
    }

    public void deleteButtonAction(ActionEvent actionEvent) {
        delete();
    }

    public void byIdButtonAction(ActionEvent actionEvent) {
        if(!idField.getText().isEmpty()) {
            updateTable(false, Integer.parseInt(idField.getText()));
        }else {
            Popup.showPopup("Ошибка!", "укажите id для поиска");
        }
    }

    public void refreshButtonAction(ActionEvent actionEvent){
        updateTable(true, 0);
    }
}