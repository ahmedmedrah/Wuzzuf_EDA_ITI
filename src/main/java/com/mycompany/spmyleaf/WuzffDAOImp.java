/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spmyleaf;

import BuilderForHtml.DisplayHtml;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.io.Read;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

/**
 *
 * @author Eslam
 */


public class WuzffDAOImp implements WuzuffDAO{
    
    DataFrame jobs;
    
    
    public WuzffDAOImp() throws Exception {
        jobs = readFromCSV("src/main/resources/Wuzzuf_Jobs.csv");
    }

    @Override
    public DataFrame readFromCSV(String filename) throws Exception {
        // read the csv file
        DataFrame jobs = Read.csv(filename, CSVFormat.DEFAULT.withDelimiter(',')
                .withHeader("Title","Company","Location","Type","Level","YearsExp","Country","Skills")
                .withSkipHeaderRecord(true));
        return jobs;
    }

    @Override
    public DataFrame displayFirstRecs() {
         return jobs.slice(0, 10);
    }

    @Override
    public DataFrame displayStrucure() {
        return jobs.structure();
    }


    @Override
    public DataFrame cleanData() {
        // remove null and duplicate values
        jobs.omitNullRows();
        List<Tuple> data = jobs.stream().distinct().collect(Collectors.toList());
        jobs = DataFrame.of(data);
  
        return jobs;
    }


    @Override
    public List<String> JobsForCompany() {
        // group by company name and count each occurrence
        Map<String, Long> countCompany = jobs.stream()
        .collect(Collectors.groupingBy(t -> String.valueOf (t.getString("Company")), Collectors.counting()));

        // sort the companies by value
        Map<String, Long> sorted = countCompany.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        List<String> row = new ArrayList<>();

        // create a list of companies and its count
        sorted.forEach((k,v)->{
                row.add("["+ k +","+v.toString()+"]");
            });

        return row;
    }

    @Override
    public List<String> TopTitles() {
        // group by job title and count each occurrence
        Map<String, Long> countTitles = jobs.stream()
        .collect(Collectors.groupingBy(t -> String.valueOf (t.getString("Title")), Collectors.counting()));

        // sort the jobs by value
        Map<String, Long> sorted = countTitles.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        List<String> row = new ArrayList<>();

        // create a list of jobs and its count
        sorted.forEach((k,v)->{
            row.add("["+ k +","+v.toString()+"]");
        });

        return row;
    }

    @Override
    public List<String> TopAreas() {
        // group by area and count each occurrence
        Map<String, Long> countLocation = jobs.stream()
        .collect(Collectors.groupingBy(t -> String.valueOf (t.getString("Location")), Collectors.counting()));

        // sort the jobs by value
        Map<String, Long> sorted = countLocation.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        List<String> row = new ArrayList<>();

        // create a list of areas and its count
        sorted.forEach((k,v)->{
            row.add("["+ k +","+v.toString()+"]");
        });

        return row;
    }

    @Override
    public List<String> TopSkills() {
        // get skills
        String[] values = jobs.stringVector("Skills").toStringArray();
        Map<String,Integer> skills =  new HashMap<String, Integer>();
        // count the number of appearance of each skill
        for(String i : values){
            String[] splitted = i.split(",");
            for(String j : splitted){
                String skill = j.trim();
                if(skills.containsKey(skill))
                    skills.put(skill, skills.get(skill)+1);
                else
                    skills.put(skill, 1);
            }
        }

        //sort the skills by the number of appearance
        skills = skills.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        List<String> row = new ArrayList<>();

        // create a list of areas and its count
        skills.forEach((k,v)->{
            row.add("["+ k +","+v.toString()+"]");
        });

    return row;
    }

    @Override
    public List<Job> toJoblist(DataFrame data) {
        
         List<Job> allData = new ArrayList<>();
        for (Tuple t : data.stream().collect(Collectors.toList())) {
            Job wuzzufJob = new Job(
                    t.get("Title").toString(),
                    t.get("Company").toString(),
                    t.get("Location").toString(),
                    t.get("Type").toString(),
                    t.get("Level").toString(),
                    t.get("YearsExp").toString(),
                    t.get("Country").toString(),
                    t.get("Skills").toString());
            allData.add(wuzzufJob);
        }
        return allData;
    }


    @Override
    public List<Structure> toSchemalist(DataFrame data) {
        
        List<Structure> allData = new ArrayList<>();
        for (Tuple t : data.stream().collect(Collectors.toList())) {
            Structure schema = new Structure(
                    t.get("Column").toString(),
                    t.get("Type").toString());
            allData.add(schema);
        }
        return allData;
    }


    
    public List<String> parseJobsList(List<Job> ls){
        StringBuilder innerrow = new StringBuilder();
        List<String> row = new ArrayList<>();
        ls.forEach(j->{
            innerrow.append("["+j.getTitle()+",");
            innerrow.append(j.getCompany()+",");
            innerrow.append(j.getLocation()+",");
            innerrow.append(j.getType()+",");
            innerrow.append(j.getLevel()+",");
            innerrow.append(j.getYearsExp()+",");
            innerrow.append(j.getCountry()+",");
            innerrow.append(j.getSkills()+"]");
            row.add(innerrow.toString());
            innerrow.setLength(0);       
            
        });
    
    
    
    return row ;
    }
    
    public List<String> parseStructureList(List<Structure> ls){
        StringBuilder innerrow = new StringBuilder();
        List<String> row = new ArrayList<>();
        ls.forEach(j->{
            innerrow.append("["+j.getType()+",");
            innerrow.append(j.getColumn()+"]");
          
            row.add(innerrow.toString());
            innerrow.setLength(0);       
            
        });
        
        
    return row ;
    }
    

  public String companyPieChart() throws IOException{

        List<Map.Entry<Object, Long>> cmpns = jobs.stream().collect(Collectors.groupingBy(c -> c.get("Company"), Collectors.counting()))
          .entrySet().stream().sorted(Map.Entry.comparingByValue())
          .collect(Collectors.toList());

        // Create Chart
        PieChart chart =
              new PieChartBuilder().width(1200).height(750).title("Pie chart for companies").build();

        // Customize Chart
        chart.getStyler().setCircular(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);

        for (int i=cmpns.size()-1; i > cmpns.size()-11 ; i--) {
          chart.addSeries(cmpns.get(i).getKey().toString(), cmpns.get(i).getValue());
        }

        String path = "src\\main\\resources\\Sample_pieChart.png";
        BitmapEncoder.saveBitmap(chart,path, BitmapEncoder.BitmapFormat.PNG);



        return DisplayHtml.viewchart(path);
  }
  
  public String titleBarChart() throws IOException{

      List<Map.Entry<Object, Long>> titles = jobs.stream().collect(Collectors.groupingBy(c -> c.get("Title"), Collectors.counting()))
              .entrySet().stream().sorted(Map.Entry.comparingByValue())
              .collect(Collectors.toList());
      List<String> title = new ArrayList<>();
      List<Long> counts = new ArrayList<>();
      for(int i = 1;i<11 ;i++){
          title.add(String.valueOf(titles.get(titles.size()-i).getKey()));
          counts.add(titles.get(titles.size()-i).getValue());
      }
      // Create Chart
      CategoryChart chart =
              new CategoryChartBuilder()
                      .width(1100)
                      .height(650)
                      .title("Title Bar chart")
                      .xAxisTitle("Title")
                      .yAxisTitle("Counts")
                      .build();

      // Customize Chart
      chart.getStyler().setXAxisLabelRotation(60);
      chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
      chart.getStyler().setPlotGridLinesVisible(false);

      chart.addSeries("Jobs",title, counts);




      String path = "src\\main\\resources\\Titles_barChart.png";
      BitmapEncoder.saveBitmap(chart,path, BitmapEncoder.BitmapFormat.PNG);
        

  
        return DisplayHtml.viewchart(path);
  }
  
   public String locationBarChart() throws IOException{
       List<Map.Entry<Object, Long>> locs = jobs.stream().collect(Collectors.groupingBy(c -> c.get("Location"), Collectors.counting()))
               .entrySet().stream().sorted(Map.Entry.comparingByValue())
               .collect(Collectors.toList());
       List<String> loc_names = new ArrayList<>();
       List<Long> counts = new ArrayList<>();
       for(int i = 1;i<11 ;i++){
           loc_names.add(String.valueOf(locs.get(locs.size()-i).getKey()));
           counts.add(locs.get(locs.size()-i).getValue());
       }
       // Create Chart
       CategoryChart chart =
               new CategoryChartBuilder()
                       .width(1100)
                       .height(650)
                       .title("Location Bar chart")
                       .xAxisTitle("Location")
                       .yAxisTitle("Counts")
                       .build();

       // Customize Chart
       chart.getStyler().setXAxisLabelRotation(60);
       chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
       chart.getStyler().setPlotGridLinesVisible(false);

       chart.addSeries("Location",loc_names, counts);




       String path = "src\\main\\resources\\Titles_barChart.png";
       BitmapEncoder.saveBitmap(chart,path, BitmapEncoder.BitmapFormat.PNG);
        return DisplayHtml.viewchart(path);
  }
  
  
  
 

    
    
}
