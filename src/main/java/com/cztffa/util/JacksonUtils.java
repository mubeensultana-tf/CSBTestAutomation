package com.cztffa.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtils {

	public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
    	log.info("deserializing json file {}", fileName);
    	InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, T);
    }
}
