package alisa.aniskina.testtaskchat.operations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleOperations {

    public static Date convertStringToDate(String dateString){
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date date = null;

        if (dateString != null) {
            try {
                date = format.parse(dateString);
            } catch (ParseException e) {
                e.getMessage();
            }
        }
        return date;
    }

    public static Integer convertStringToInt(String par){
        int result = -1;

        try {
            result = Integer.parseInt(par);

        } catch (NumberFormatException e) {
            e.getMessage();
        }

        return result;
    }



}
