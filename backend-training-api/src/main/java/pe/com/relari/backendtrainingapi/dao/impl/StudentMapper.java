package pe.com.relari.backendtrainingapi.dao.impl;

import pe.com.relari.backendtrainingapi.model.business.Address;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.model.entity.StudentEntity;
import pe.com.relari.backendtrainingapi.util.GenderEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Class:</b> StudentMapper.<br/>
 *
 * @author RLR
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class StudentMapper {

  /**
   * mapStudent.
   * @param entity {@link StudentEntity}
   * @return {@link Student}
   */
  static Student mapStudent(StudentEntity entity) {
    return Student.builder()
        .id(entity.getId())
        .dateOfBirth(entity.getDateOfBirth())
        .firstName(entity.getFirstName())
        .gender(entity.getGender().name())
        .lastName(entity.getLastName())
        .documentIdentity(entity.getDocumentIdentity())
        .studentCode(entity.getStudentCode())
        .address(new Address(entity.getEmail(), entity.getPhoneNumber()))
        .status(entity.getStatus())
        .build();
  }

  /**
   * mapStudentEntity.
   * @param student {@link Student}
   * @return {@link StudentEntity}
   */
  static StudentEntity mapStudentEntity(Student student) {
    return StudentEntity.builder()
        .id(student.getId())
        .dateOfBirth(student.getDateOfBirth())
        .firstName(student.getFirstName())
        .gender(GenderEnum.valueOf(student.getGender()))
        .lastName(student.getLastName())
        .documentIdentity(student.getDocumentIdentity())
        .studentCode(student.getStudentCode())
        .phoneNumber(student.getAddress().getPhoneNumber())
        .email(student.getAddress().getEmail())
        .status(student.getStatus())
        .build();
  }
}