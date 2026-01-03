package pe.com.relari.backendtrainingapi.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class:</b> StudentDetailResponse.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailResponse {

  @Schema(
          description = "students",
          implementation = StudentResponse[].class
  )
  private List<StudentResponse> students;
  
}
