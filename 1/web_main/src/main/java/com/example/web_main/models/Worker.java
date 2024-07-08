package com.example.web_main.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Worker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName, SecondName, ThirdName, Old, RankName, Stonks, Branch;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return SecondName;
    }
    public void setSecondName(String SecondName) {
        this.SecondName = SecondName;
    }
    public String getThirdName() {
        return ThirdName;
    }
    public void setThirdName(String ThirdName) {
        this.ThirdName = ThirdName;
    }
    public String getOld() {
        return Old;
    }
    public void setOld(String Old) {
        this.Old = Old;
    }
    public String getRank() {
        return RankName;
    }
    public void setRank(String RankName) {
        this.RankName = RankName;
    }
    public String getStonks() {
        return Stonks;
    }
    public void setStonks(String Stonks) {
        this.Stonks = Stonks;
    }
    public String getBranch() {
        return Branch;
    }
    public void setBranch(String Branch) {
        this.Branch = Branch;
    }
    public Worker(String firstName, String SecondName, String ThirdName, String Old, String RankName, String Stonks, String Branch) {
        this.firstName = firstName;
        this.SecondName = SecondName;
        this.ThirdName = ThirdName;
        this.Old = Old;
        this.RankName = RankName;
        this.Stonks = Stonks;
        this.Branch = Branch;
    }
    public Worker() {
    }
    
}