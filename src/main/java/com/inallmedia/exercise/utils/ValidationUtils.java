package com.inallmedia.exercise.utils;

public  class ValidationUtils {

    public static boolean validateParameters(Integer initialRange, Integer finalRange){
        return initialRange >= 0 && finalRange >= 0 && initialRange <= finalRange && initialRange < finalRange;
    }
}
