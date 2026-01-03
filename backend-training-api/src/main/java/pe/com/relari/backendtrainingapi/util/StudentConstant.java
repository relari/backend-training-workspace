package pe.com.relari.backendtrainingapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

/**
 * <b>Class:</b> StudentConstant.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentConstant {

    public static final String GENERIC_CODE = "GENERIC";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = "16/08/2016";

    //convert String to LocalDate

}
