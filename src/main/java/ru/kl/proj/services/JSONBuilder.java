package ru.kl.proj.services;

import java.lang.reflect.Field;

public class JSONBuilder {

    public JSONBuilder (){

    }

    public String buildJSONFrom (Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("declared field: " + fields[i]);
        }
        return null;
    }
}
