/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spmyleaf;

import java.util.List;
import java.util.Map;
import smile.data.DataFrame;

/**
 *
 * @author Eslam
 */
public interface WuzuffDAO {
    
    DataFrame readFromCSV(String filename) throws Exception;
    DataFrame displayFirstRecs() ;
    DataFrame displayStrucure();
    DataFrame cleanData();
    List<String> JobsForCompany();
    List<String> TopTitles();
    List<String> TopAreas();
    List<String> TopSkills();
    List<Job> toJoblist(DataFrame data);
    List<Structure> toSchemalist(DataFrame data);
    
}
