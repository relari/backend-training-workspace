package pe.com.relari.backendtrainingapi.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class StudentResponse {

  @Schema(
          description = "gender student",
          implementation = String.class,
          example = "M"
  )
  private String gender;

  @Schema(
          description = "first name",
          implementation = String.class,
          example = "Juan"
  )
  private String firstName;

  @Schema(
          description = "middle name",
          example = "Lopez",
          implementation = String.class
  )
  private String documentIdentity;

  @Schema(
          description = "last name",
          example = "Martinez",
          implementation = String.class
  )
  private String lastName;

  @Schema(
          description = "date of birth",
          example = "01/01/2020",
          implementation = String.class
  )
  private String dateOfBirth;

  @Schema(
          description = "student code",
          example = "",
          implementation = String.class
  )
  private String studentCode;
}
