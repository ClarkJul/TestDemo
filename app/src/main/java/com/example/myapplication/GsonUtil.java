package com.example.myapplication;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    public static Gson getGson(){
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(myExclusionStrategy) //设置排除策略
                .create();
        return gson;
    }
    static ExclusionStrategy myExclusionStrategy = new ExclusionStrategy() {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getName().startsWith(" ");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

    };
}
