package com.lmq.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;


public class FileUtilPath {

    private static final String pertiestPath = "properties";

    public static String getPropertiesPath(String fileName){
        try {
           return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+pertiestPath+ File.separator+fileName).getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
