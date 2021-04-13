package com.luciaandres;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.luciaandres.entities.ReducedActivity;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class JsonActivityGenerator {
    private final Logger logger = getLogger(ODHGetter.class);
    List<ReducedActivity> activityList;
    int num;


    /*
          Here we initialize a list containing the reduced activities, and the number of them.

          @param activityList is the list containing the already filtered out reduced activities
          @param num is the number of reduced activities contained in said list
     */
    public JsonActivityGenerator(List<ReducedActivity> activityList, int num) {
        this.activityList = activityList;
        this.num = num;
    }

    /*
          * This method is in charge of writing a json file for each reduced activity.
          * First the method clearFiles is called,
          * to clear the folder in which the files will be put.
          * Then we iterate through the list of reduced activities. In the scope of the loop
          * we create the json files, naming the "Activity"
          * plus the ID of the given activity. Each file will contain the attributes of each
          * ReducedActivity object.
          * The UTF 8 characters are used to address a converting issue, which otherwise
          * @throws an IOException
     */
    public void filesGenerator() throws IOException {


        clearFiles();
        logger.info("Activity file writing...");
        for (ReducedActivity activity : activityList){

            try {

                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                String fileName = "Activity" + activity.getId();
                File file = new File("src/result/" + fileName + ".json");
                file.getParentFile().mkdirs();
                String value = mapper.writeValueAsString(activity);// Gets the info to write as a string
                FileOutputStream writer = new FileOutputStream(file);
                writer.write(value.getBytes(StandardCharsets.UTF_8)); // Gets the strings characters as UTF-8 bytes
                // NOTE: This is necessary to take in account special characters
                // 	    such as German characters
                logger.debug("Successfully wrote to the file -> " + activity.getId()+".json");

            }
            catch (IOException exception){
            logger.fatal("Caught IO exception.");}
        }

    }

    public void clearFiles() {

            File folder = new File("result/");
            File[] files = folder.listFiles() != null ? folder.listFiles() : new File[0];

            for (File file : files) {
                if (!file.isDirectory())
                    file.delete();
            }
    }


}
