package pe.com.relari.controller.mapper;

import pe.com.relari.backendtrainingapi.model.api.AddressResponse;
import pe.com.relari.backendtrainingapi.model.api.StudentRequest;
import pe.com.relari.backendtrainingapi.model.api.StudentDetailResponse;
import pe.com.relari.backendtrainingapi.model.api.StudentResponse;
import pe.com.relari.backendtrainingapi.model.business.Address;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.util.StudentConstant;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * <b>Interface:</b> StudentMapper.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Mapper(componentModel = "spring")
public interface StudentMapper {

  @Named("mapStudent")
  @Mapping(source = "email", target = "address.email")
  @Mapping(source = "phoneNumber", target = "address.phoneNumber")
  @Mapping(target = "studentCode", expression = "java(generateStudentCodeDefault())")
  @Mapping(target = "status", expression = "java(Boolean.TRUE)")
  Student mapStudent(StudentRequest studentRequest);

  default String generateStudentCodeDefault() {
    return StudentConstant.GENERIC_CODE;
  }

  @Mapping(target = "studentCode", ignore = true)
  @Mapping(target = "documentIdentity", ignore = true)
  StudentResponse mapStudentInfoResponse(Student student);

  StudentResponse mapStudentResponse(Student student);

  StudentResponse mapStudentCodeResponse(String studentCode);

  default StudentDetailResponse mapStudentDetailResponse(List<StudentResponse> studentResponses) {
    return new StudentDetailResponse(studentResponses);
  }

  AddressResponse mapAddressResponse(Address address);

  @IterableMapping(qualifiedByName = "mapStudent")
  List<Student> mapStudents(List<StudentRequest> studentRequests);

  @Named("genderDescription")
  default String genderDescription(String genderCode) {
    return genderCode.equals("M") ? "MALE" : "FEMALE";
  }

  @Mapping(source = "email", target = "address.email")
  @Mapping(source = "phoneNumber", target = "address.phoneNumber")
  @Mapping(target = "studentCode", expression = "java(generateStudentCodeDefault())")
  @Mapping(target = "status", expression = "java(Boolean.TRUE)")
  @Mapping(target = "gender", source = "gender", qualifiedByName = "genderDescription")
  Student mapStudent2(StudentRequest studentRequest);

}
