/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spmyleaf;

/**
 *
 * @author Eslam
 */
public class Job {
    
    
    private String Title ;
    private String Company ;
    private String Location ;
    private String Type ;
    private String Level ;
    private String YearsExp ;
    private String Country ;
    private String Skills ;
    private int minExp;

    public Job() {
    }

    public Job(String Title, String Company, String Location, String Type, String Level, String YearsExp, String Country, String Skills) {
        this.Title = Title;
        this.Company = Company;
        this.Location = Location;
        this.Type = Type;
        this.Level = Level;
        this.YearsExp = YearsExp;
        this.Country = Country;
        this.Skills = Skills;
    }

    public int getMinExp() {
        return minExp;
    }

    public void setMinExp(int minExp) {
        this.minExp = minExp;
    }
    
    

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public void setYearsExp(String YearsExp) {
        this.YearsExp = YearsExp;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setSkills(String Skills) {
        this.Skills = Skills;
    }

    public String getTitle() {
        return Title;
    }

    public String getCompany() {
        return Company;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public String getLevel() {
        return Level;
    }

    public String getYearsExp() {
        return YearsExp;
    }

    public String getCountry() {
        return Country;
    }

    public String getSkills() {
        return Skills;
    }
    
    
    
    
}
