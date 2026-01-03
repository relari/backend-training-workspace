package pe.com.relari.backendtrainingapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Class:</b> RegexConstant.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexConstant {

    public static final String REGEXP_ONLY_LETTER = "[a-zA-Z]*";
    public static final String REGEXP_ONLY_NUMBER = "[0-9]*";
    public static final String REGEXP_GENDER = "[M|F]";
    public static final String REGEXP_PHONE_NUMBER = "[0-9]{9}";
    public static final String REGEXP_DATE_OF_BIRTH = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    
}
