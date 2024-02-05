package com.cgi.nl.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> List<T> loadData(Class<T> classs) {
        String content = "[{\n" +

                "    \"recipieName\": \"briyani\",\n" +
                "    \"suitableFor\": 1,\n" +
                "    \"veg\": false,\n" +
                "    \"ingredients\":[{\n" +
                "        \"itemName\":\"rice\"\n" +
                "    },\n" +
                "{\n" +
                " \"itemName\":\"chicken\"   \n" +
                "}\n" +
                "],\n" +
                "    \n" +
                "    \"instructions\": \"cooked with rice chicken and masala\"\n" +
                "}]";

        ObjectMapper mapper=new ObjectMapper();
        mapper.findAndRegisterModules();
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, classs);
        List<T> list = new ArrayList<>();
        try {
            list = objectMapper.readValue(content, type);
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return list;
    }
}
