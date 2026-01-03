package pe.com.relari.backendtrainingapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    public static <T> T readDataFromFileJson(String fileName, Class<T> className) {
        try {
            return mapper.readValue(
                    ConvertUtil.class.getClassLoader().getResourceAsStream(fileName), className
            );
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readData(String json, Class<T> className) {
        try {
            return mapper.readValue(json, className);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String convertJsonToString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
