package pe.com.relari.backendtrainingapi.util;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Class:</b> StudentUtil.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentUtil {

    public static String buildStudentCode(Integer id) {
        return LocalDate.now().getYear() + String.format("%04d", id);
    }

    public static LocalDate mapLocalDate(String date) {
        return LocalDate.parse(date, StudentConstant.DATE_TIME_FORMATTER);
    }

}
