package com.kami.study.apfivethlab.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employer { //Этот класс представляет работодателя с различными свойствами,
    // такими как идентификатор, имя, фамилия, менеджер, зарплата, отдел, город и т.д.
    private final IntegerProperty id; // идентификатор
    private final StringProperty firstname;// имя
    private final StringProperty lastname;// фамилия
    private final StringProperty manager;// менеджер
    private final IntegerProperty salary;// зарплата
    private final SimpleIntegerProperty department;// отдел
    private final StringProperty nameDepartment;
    private final StringProperty city;// город
    private final IntegerProperty etc;// дополнительное свойство (разряд)
    private final IntegerProperty etcRatio;// дополнительное свойство коефициент (разряд)

    public Employer(int id, String firstname, String lastname, String manager, int salary, int department, String nameDepartment, String city, int etc, int etcRatio) {
        this.id = new SimpleIntegerProperty(id); //конструктор
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.manager = new SimpleStringProperty(manager);
        this.salary = new SimpleIntegerProperty(salary);
        this.department = new SimpleIntegerProperty(department);
        this.nameDepartment = new SimpleStringProperty(nameDepartment);
        this.city = new SimpleStringProperty(city);
        this.etc = new SimpleIntegerProperty(etc);
        this.etcRatio = new SimpleIntegerProperty(etcRatio);
    }
    // Методы для получения свойств работодателя
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty managerProperty() {
        return manager;
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }

    public IntegerProperty departmentProperty() {
        return department;
    }

    public StringProperty nameDepartmentProperty() {
        return nameDepartment;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public IntegerProperty etcProperty() {
        return etc;
    }

    public IntegerProperty etcRatioProperty() {
        return etcRatio;
    }

    public int getId() {
        return id.get();
    }


    // Методы для получения значений свойств работодателя

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public int getETC() {
        return etc.get();
    }
    public void setETC(int etc){
        this.etc.set(etc);
    }

    public int getETCRatio() {
        return etcRatio.get();
    }
    public void setEtcRatio(int etcRatio){
        this.etcRatio.set(etcRatio);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getManager() {
        return manager.get();
    }

    public void setManager(String manager) {
        this.manager.set(manager);
    }

    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    public int getDepartment() {
        return department.get();
    }

    public void setDepartment(int department) {
        this.department.set(department);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }
}