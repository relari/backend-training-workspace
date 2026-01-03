package pe.com.relari.backendtrainingapi.model.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * <b>Class:</b> Student.<br/>
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Student {

  private Integer id;
  private String gender;
  private String firstName;
  private String lastName;
  private String documentIdentity;
  private LocalDate dateOfBirth;
  private String studentCode;
  private Address address;
  private Boolean status;

  public StudentBuilder mutate() {
    return Student.builder()
            .id(id)
            .gender(gender)
            .firstName(firstName)
            .lastName(lastName)
            .documentIdentity(documentIdentity)
            .dateOfBirth(dateOfBirth)
            .studentCode(studentCode)
            .address(address)
            .status(status);
  }

}
