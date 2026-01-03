package pe.com.relari.backendtrainingapi.util;

import pe.com.relari.backendtrainingapi.model.business.Address;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.model.entity.StudentEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtil {


    public static Student buildStudent() {
        return Student.builder()
                .firstName("Jose")
                .lastName("Gonzales")
                .documentIdentity("12345678")
                .gender("M")
                .studentCode("20210000")
                .dateOfBirth(LocalDate.now())
                .address(new Address("email@mail.com", "999999999"))
                .build();
    }

    public static StudentEntity buildStudentEntity() {
        return StudentEntity.builder()
                .firstName("Jose")
                .lastName("Gonzales")
                .documentIdentity("12345678")
                .gender(GenderEnum.M)
                .studentCode("20210000")
                .dateOfBirth(LocalDate.now())
                .email("email@mail.com")
                .phoneNumber("999999999")
                .build();
    }
}
