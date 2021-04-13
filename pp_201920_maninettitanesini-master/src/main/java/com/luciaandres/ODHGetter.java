package com.luciaandres;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luciaandres.entities.Activity;
import com.luciaandres.entities.ReducedActivity;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ODHGetter {


    URL url;
    private final Logger logger = getLogger(ODHGetter.class);



    public ODHGetter(URL url) {
        this.url = url;
    }

    /**
     * This method will establish a connection with the Suth Tirol's open data hub API in order to retrieve
     * the desired amount of activities.
     * @return the status of the connection
     * @throws IOException in case problems with the file are encountered
     */
    public InputStream fetchData() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        int status = connection.getResponseCode();
        logger.info("Retrieving data from ODH API....");
        if (status < 300) {
            connection.getInputStream();
        }
        return status > 299 ? null : connection.getInputStream();
    }

    /**
     *
     * @param dataToBeDeserialized contains the information retrieved frm the api in json format
     * This method will deserialize it.
     * @return a list containing the activities
     */
   public ArrayList<Activity> deserializer(InputStream dataToBeDeserialized) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(dataToBeDeserialized);
        JsonNode itemsNode = node.get("Items");

        logger.info("Deserializing data...");
       return mapper.readValue(itemsNode.traverse(), new TypeReference<ArrayList<Activity>>(){});

   }

    /**
     * @return a list of filtered activities. This method maps the list of unfiltered activities to a new
     * list of type ReducedActivity, which contains only the required fields
     */
    public List<ReducedActivity> createActivityList() throws IOException {
        return deserializer(fetchData()).stream().map((activity -> new ReducedActivity(activity.getId(), activity.getName(), activity.getDesc(), activity.getTypes(), activity.hasGpsTrack(), activity.getRegionId()))).collect(Collectors.toList());

    }
}







