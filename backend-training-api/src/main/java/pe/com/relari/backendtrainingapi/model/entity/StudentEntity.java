package pe.com.relari.backendtrainingapi.model.entity;

import javax.persistence.*;

import pe.com.relari.backendtrainingapi.util.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * <b>Class:</b> StudentEntity.<br/>
 * @author RLR
 * @version 1.0.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Student")
public class StudentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false, length = 1)
  private GenderEnum gender;

  @Column(name = "firstName", nullable = false, length = 20)
  private String firstName;

  @Column(name = "documentIdentity", nullable = false, length = 20)
  private String documentIdentity;

  @Column(name = "lastName", nullable = false, length = 20)
  private String lastName;

  @Column(name = "dateOfBirth", nullable = false, length = 10, columnDefinition = "DATE")
  private LocalDate dateOfBirth;

  @Column(name = "studentCode", nullable = false, length = 50)
  private String studentCode;

  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @Column(name = "phoneNumber", nullable = false, length = 9)
  private String phoneNumber;

  @Column(name = "status", nullable = false)
  private Boolean status;

}
