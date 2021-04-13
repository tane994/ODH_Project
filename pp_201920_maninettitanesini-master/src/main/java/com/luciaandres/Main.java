package com.luciaandres;

import com.luciaandres.analysis.Analysis;
import com.luciaandres.analysis.Analyzer;
import com.luciaandres.analysis.JsonAnalysisGenerator;
import com.luciaandres.entities.ReducedActivity;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * This is the main class, the one from where all operations of the program begin.
 * it contains several variables that will be the attributes of the objects created below.
 */
public class Main {


    public static void main(String[] args) throws IOException {
        String fileName = "input.txt"; //name of file in which the number of activities to fetch is written
        List<ReducedActivity> activityList; //will contain a list of activities

        Configuration retriever = new Configuration(fileName); // instance of class Configuration, which retrieves the number in "input.txt"
        int pageNumber = retriever.getPageSize();
        System.out.println(pageNumber);

        URL url = new URL("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + pageNumber + "&activitytype=1023");

        ODHGetter odhGetter = new ODHGetter(url); //instance of odhGetter class, which contains method for fetching and deserializing data
        odhGetter.deserializer(odhGetter.fetchData());
        activityList= odhGetter.createActivityList();
        JsonActivityGenerator activityGenerator = new JsonActivityGenerator(activityList, pageNumber); //the class Json Activity Generator contains methods for...
        activityGenerator.filesGenerator(); //...generating a file for each activity


        Analyzer analyzer = new Analyzer(activityList);
        Analysis analysis = analyzer.analyze();//call to method that performs operations on data
        JsonAnalysisGenerator jsonAnalysisGenerator = new JsonAnalysisGenerator(analysis); //summary of the results
        jsonAnalysisGenerator.analysisFileGenerator(analysis);//generation of json files containing results
        Validator.validate();
    }



}
