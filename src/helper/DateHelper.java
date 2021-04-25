/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author Asus
 */
public class DateHelper {
    static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd");
    public static Date toDate(String date, String...pattern){
        try {
            if(pattern.length > 0){
                DATE_FORMATER.applyPattern(pattern[0]);
            } 
            if(date == null){
                return DateHelper.now();
            }
            return DATE_FORMATER.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String toString(Date date, String...pattern){
        if(pattern.length > 0){
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        if(date == null){
            date = DateHelper.now();
        }
        return DATE_FORMATER.format(date);
    }
    
    public static Date now(){
        return new Date();
    }
    
    public static Date addDays(Date date,int days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return  date;
    }
    
    public static Date add(int days){
        Date now = DateHelper.now();
        now.setTime(now.getTime() + days*24*60*60*1000);
        return  now;
    }
    
}
