package com.youzan.ad.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by baimugudu on 2019/4/2
 */
public class CommonUtils {

    public static <K,V> V getorCreate(K key, Map<K,V> map,
                                   Supplier<V> supplier){
        return map.computeIfAbsent(key,
               k -> supplier.get() );

    }


    public static String stringConcat(String... args){

        StringBuilder sb = new StringBuilder();
        for(String arg:args){
            sb.append(arg);
            sb.append("-");
        }

       return  sb.deleteCharAt(sb.length()-1).toString();


    }

    public static Date parseStringDate(String dataString){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US
        );

        try {
            Date date = simpleDateFormat.parse(dataString);
            return DateUtils.addHours(date,-8);
        } catch (ParseException e) {

            e.printStackTrace();
            return null;
        }


    }
 }
