package pe.com.relari.backendtrainingapi.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class:</b> AddressResponse.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

  @Schema(
          description = "email",
          implementation = String.class,
          example = "email@mail.com"
  )
  private String email;

  @Schema(
          description = "phone number",
          implementation = String.class,
          example = "999999999"
  )
  private String phoneNumber;

}
