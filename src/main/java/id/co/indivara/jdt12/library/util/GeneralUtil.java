package id.co.indivara.jdt12.library.util;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.var;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class GeneralUtil {

    public static Integer calculatePenalty(Date dueDate){
        LocalDate date = LocalDate.now();
        Date returnDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(returnDate.after(dueDate)){
            int calc = countDaysLate(dueDate, returnDate);
            return 10000 * calc;
        } else {
            return 0;
        }
    }

    public static Integer countDaysLate(Date dueDate, Date returnDate) {
        int diff = Math.toIntExact(returnDate.getTime() - dueDate.getTime());
        return diff / (24 * 60 * 60 * 1000);
    }
}
