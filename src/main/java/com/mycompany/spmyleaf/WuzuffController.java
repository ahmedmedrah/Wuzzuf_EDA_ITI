/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spmyleaf;

import BuilderForHtml.DisplayHtml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.data.DataFrame;

/**
 *
 * @author Eslam
 */
@RestController
public class WuzuffController  {
    
    WuzffDAOImp dao ;
    
     public WuzuffController() throws Exception {
        this.dao = new WuzffDAOImp();
    }
        
    String [] columns = {"Title","Company","Location","Type","Level","YearsExp","Country","Skills"};
    String [] columns2 = {"column title","Type"};
    String [] columns3 = {"Company","Number of jobs"};
    String [] columns4 = {"Title","Number of apperance"};
    String [] columns5 = {"Areas","Number of appearances"};
    String [] columns6 = {"Skills","Number of appearances"};
    String [] columns7 = {"Min Years of Exp"};

 
    
    @RequestMapping(value = "/show_first_records")
    public String showFirstRecs(){
        DataFrame jobs = dao.displayFirstRecs();
        List<Job> ls = dao.toJoblist(jobs);
        List<String> lst =dao.parseJobsList(ls);
        
        
       return DisplayHtml.displayrows(columns, lst);
    
    
    }
    
    @RequestMapping(value = "/show_structure")
    public String showStructure(){
        
        DataFrame jobs = dao.displayStrucure();
        List<Structure> ls =dao.toSchemalist(jobs);
        List<String> lst =dao.parseStructureList(ls);
        
        
       return DisplayHtml.displayrows(columns2, lst);
    
    
    }
    
    @RequestMapping(value = "/clean_data")
    public String cleanData(){
        DataFrame jobs = dao.cleanData();
        List<Job> ls = dao.toJoblist(jobs);
        List<String> lst =dao.parseJobsList(ls);
        
       return DisplayHtml.displayrows(columns, lst);
    }
    
    @RequestMapping(value = "/show_top_companies")
    public String showTopCompany(){
        List<String> lst = dao.JobsForCompany();
        
       return DisplayHtml.displayrows(columns3, lst);
    }
    
    @RequestMapping(value = "/show_top_titles")
    public String showTopTitels(){
         List<String> lst = dao.TopTitles();
        
       return DisplayHtml.displayrows(columns4, lst);
    }
    @RequestMapping(value = "/show_top_areas")
    public String showTopAreas(){
         List<String> lst = dao.TopAreas();
        
       return DisplayHtml.displayrows(columns5, lst);
    }

    
    @RequestMapping(value = "/show_pie_chart")
    public String showPieChart() throws IOException{
    
         return dao.companyPieChart();
    }
    
    @RequestMapping(value = "/title_bar_chart")
    public String titleBarChart() throws IOException{
    
    return dao.titleBarChart();
    }
    
    @RequestMapping(value = "/location_bar_chart")
    public String locationBarChart() throws IOException{
    
    return dao.locationBarChart();
    }
    
     @RequestMapping(value = "/show_top_skills")
    public String showTopSkills(){
         List<String> lst = dao.TopSkills();
        
       return DisplayHtml.displayrows(columns6, lst);
    }
    
}
