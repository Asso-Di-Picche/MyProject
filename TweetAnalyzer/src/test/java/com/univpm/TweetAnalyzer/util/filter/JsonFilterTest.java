package com.univpm.TweetAnalyzer.util.filter;

import ch.qos.logback.core.util.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonFilterTest {

    private static Map<String, String> filter1 = new LinkedHashMap<String, String>();

    @Test
    @DisplayName("Correttezza filtraggio json")
    void jsonTest(){

        String json1 = null;

        try {



        }catch (Exception e){

            e.printStackTrace();
        }

        filter1.put("#", "Prada");


    }
}