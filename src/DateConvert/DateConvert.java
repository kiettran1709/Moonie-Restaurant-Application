/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateConvert;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 *
 * @author Asus
 */
public class DateConvert {
    
// Sử dụng LocalDate để convert ra String, Date... và ngược lại.

    
/* Chuyển từ java.util.Date sang kiểu LocalDate(không giờ, không vùng) 
 @Truyền vào tham số là lớp util.Date (lấy từ JDateChooser
    */
public static LocalDate DatetoLocal(java.util.Date date){
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
}    
   

/*
Chuyển từ java.sql.đate sang kiểu LocalDate
@Truyền vào tham số sql.Date
*/
public static LocalDate sqlDatetoLocal(java.sql.Date date){
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
}

/*
Chuyển từ LocalDate sang sql.Date
@Truyền vào tham số là LocalDate
*/
public static java.sql.Date LocaltoSqlDate(LocalDate date){
    java.sql.Date sqlDate = Date.valueOf(date);
    return sqlDate;
}

/*
Chuyển từ LocalDate sang util.Date
@Truyền vào tham số là LocalDate
*/
public static java.util.Date LocaltoDate(LocalDate date){
    java.util.Date utilDate= Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return utilDate;
}

/*
Chuyển từ LocalDate sang String
@Truyền vào tham số là LocalDate
Fill lên các textfield
*/

public static String toString(LocalDate date,String...pattern){
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    if(pattern.length>0){
        formatDate.applyPattern(pattern[0]);
        return formatDate.format(date);
    }
    if(date == null){
        date = LocalDate.now();
    }
    return formatDate.format(date);
    
}

/*
Chuyển từ String sang LocalDate
@Truyền vào tham số là chuỗi

*/
 public static LocalDate toLocalDate(String date,String...pattern){
     DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     if(pattern != null){
         format = DateTimeFormatter.ofPattern(pattern[0]);
     }
     return (LocalDate) LocalDate.parse(date,format);
 }
    
}
