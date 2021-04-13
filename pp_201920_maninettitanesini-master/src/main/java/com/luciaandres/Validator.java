package com.luciaandres;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Validator {
    private static Logger logger = LogManager.getLogger(Validator.class);

    public static void validate() {
        if (isValid("analysis_schema.json", "src/result/Analysis.json")) {
            logger.info("Validating data...");
            logger.debug("Analysis.json is valid.");
        } else {
            logger.error("Analysis.json is not valid.");
        }

        for (File file : new File("src/result/").listFiles()) {
            if (file.isFile()) {
                if (file.getName().contains("Activity")) {
                    String fileName = file.getName();
                    if (isValid("activity_schema.json", "src/result/" + fileName)) {
                        logger.debug(fileName + " is valid.");
                    } else {
                        logger.error(fileName + " is not valid.");
                    }
                }
            }
        }
    }

    private static boolean isValid (String schemaPath, String jsonPath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            JsonNode schemaNode = mapper.readTree(classLoader.getResourceAsStream(schemaPath));
            JsonNode validNode = mapper.readTree(new FileReader(jsonPath));

            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaNode);

            return schema.validate(validNode).size() == 0;
        } catch (IOException e) {
            logger.error(e);
            return false;
        }
    }
}