package pe.com.relari.backendtrainingapi.model.api;

import pe.com.relari.backendtrainingapi.util.RegexConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class:</b> StudentRequest.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentRequest {

  @Schema(
          description = "gender student",
          implementation = String.class,
          pattern = RegexConstant.REGEXP_GENDER,
          example = "M",
          required = true
  )
  @NotBlank
  @Pattern(regexp = RegexConstant.REGEXP_GENDER)
  private String gender;

  @Schema(
          description = "first name",
          implementation = String.class,
          pattern = RegexConstant.REGEXP_ONLY_LETTER,
          example = "Juan",
          required = true
  )
  @NotBlank
  @Pattern(regexp = RegexConstant.REGEXP_ONLY_LETTER)
  @Size(min = 1, max = 20)
  private String firstName;

  @Schema(
          description = "middle name",
          implementation = String.class,
          pattern = RegexConstant.REGEXP_ONLY_NUMBER,
          example = "Lopez",
          required = true
  )
  @NotBlank
  @Pattern(regexp = RegexConstant.REGEXP_ONLY_NUMBER)
  @Size(min = 1, max = 20)
  private String documentIdentity;

  @Schema(
          description = "last name",
          implementation = String.class,
          pattern = RegexConstant.REGEXP_ONLY_LETTER,
          example = "Martinez",
          required = true
  )
  @NotBlank
  @Pattern(regexp = RegexConstant.REGEXP_ONLY_LETTER)
  @Size(min = 1, max = 20)
  private String lastName;

  @Schema(
          description = "date of birth",
          implementation = String.class,
          pattern = RegexConstant.REGEXP_DATE_OF_BIRTH,
          example = "01/01/2020",
          required = true
  )
  @NotBlank
  @Pattern(regexp = RegexConstant.REGEXP_DATE_OF_BIRTH)
  @Size(min = 10, max = 10)
  private String dateOfBirth;

  @Schema(
          description = "email",
          implementation = String.class,
          example = "email@mail.com",
          required = true
  )
  @Email
  @NotBlank
  @Size(min = 1, max = 50)
  private String email;

  @Schema(
          description = "phone number",
          implementation = String.class,
          pattern = RegexConstant.REGEXP_PHONE_NUMBER,
          example = "999999999",
          required = true
  )
  @NotBlank
  @Pattern(regexp = RegexConstant.REGEXP_PHONE_NUMBER)
  private String phoneNumber;

}
