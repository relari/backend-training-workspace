package pe.com.relari.backendtrainingapi.dao.impl;

import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.model.entity.StudentEntity;
import pe.com.relari.backendtrainingapi.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <b>Class:</b> StudentMapperTest<br/>
 * @author RLR
 * @version 1.0.0
 */

class StudentMapperTest {

  @Test
  @DisplayName("Convertir de StudentEntity a Student.")
  void whenMapperEntityThenReturnStudent() {
    StudentEntity studentEntity = TestUtil.buildStudentEntity();

    Student student = StudentMapper.mapStudent(studentEntity);

    Assertions.assertEquals(studentEntity.getFirstName(), student.getFirstName());
    Assertions.assertEquals(studentEntity.getGender().name(), student.getGender());
    Assertions.assertEquals(studentEntity.getLastName(), student.getLastName());
    Assertions.assertEquals(studentEntity.getDocumentIdentity(), student.getDocumentIdentity());
    Assertions.assertEquals(studentEntity.getStudentCode(), student.getStudentCode());
    Assertions.assertEquals(studentEntity.getDateOfBirth(), student.getDateOfBirth());
  }

  @Test
  @DisplayName("Convertir de Student a StudentEntity.")
  void whenMapperStudentThenReturnEntity() {
    Student student = TestUtil.buildStudent();

    StudentEntity entity = StudentMapper.mapStudentEntity(student);

    Assertions.assertEquals(student.getFirstName(), entity.getFirstName());
    Assertions.assertEquals(student.getGender(), entity.getGender().name());
    Assertions.assertEquals(student.getLastName(), entity.getLastName());
    Assertions.assertEquals(student.getDocumentIdentity(), entity.getDocumentIdentity());
    Assertions.assertEquals(student.getStudentCode(), entity.getStudentCode());
    Assertions.assertEquals(student.getDateOfBirth(), entity.getDateOfBirth());
  }

}
