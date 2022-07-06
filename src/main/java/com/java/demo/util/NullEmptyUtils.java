package com.java.demo.util;

import java.util.List;

public class NullEmptyUtils {

    public static boolean isNullorEmpty(List<?> list){
        return isNull(list) || list.isEmpty();
    }

    public static boolean isNull(Object object){
        return object==null;
    }
}
