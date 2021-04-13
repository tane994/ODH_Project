package com.luciaandres.analysis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonAnalysisGenerator
{
    Analysis analysis;
    private static Logger logger = LogManager.getLogger();

    public JsonAnalysisGenerator(Analysis analysis) {
        this.analysis = analysis;
    }

    public void analysisFileGenerator(Analysis analysis) throws IOException
    {
        File path = new File("src/result/Analysis.json");
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try
        {
            logger.info("Analysis file writing...");
            mapper.writeValue(path, analysis);
            logger.info("Successfully wrote to the file -> Analysis.json");
        }
        catch (IOException e)
        {
            logger.fatal("Caught IO exception.", e);
        }

    }

}
