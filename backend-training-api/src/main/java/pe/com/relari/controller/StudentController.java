package pe.com.relari.controller;

import static pe.com.relari.backendtrainingapi.util.RegexConstant.REGEXP_ONLY_NUMBER;

import pe.com.relari.controller.mapper.StudentMapper;
import pe.com.relari.backendtrainingapi.model.api.AddressResponse;
import pe.com.relari.backendtrainingapi.model.api.StudentRequest;
import pe.com.relari.backendtrainingapi.model.api.StudentDetailResponse;
import pe.com.relari.backendtrainingapi.model.api.StudentResponse;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.service.StudentService;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Class:</b> StudentController.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Tag(name = "Student", description = "Student Controller")
@OpenAPIDefinition(
        info = @Info(
                title = "Student API",
                version = "${application.info.version}",
                description = "${application.info.description}"),
        servers = @Server(url = "/application/v1/students")
)
@RestController
@RequestMapping(path = "${application.api.path}")
@AllArgsConstructor
class StudentController {

  private final StudentService service;

  private final StudentMapper mapper;

  @Operation(
          summary = "Guarda al empleado",
          method = "POST",
          responses = {
                  @ApiResponse(
                          responseCode = "201",
                          description = "Create Successful"
                  ),
                  @ApiResponse(
                          responseCode = "500",
                          description = "Error to Save"
                  )
          })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Completable save(
          @RequestBody @Valid StudentRequest request) {
    return Single.fromCallable(() -> mapper.mapStudent(request))
            .flatMapCompletable(service::save);
  }

  //  private Student mapStudent(StudentRequest request) {
  //    return Student.builder()
  //            .otherStudentDetail(request.getOtherStudentDetail())
  //            .middleName(request.getMiddleName())
  //            .lastName(request.getLastName())
  //            .gender(request.getGender())
  //            .firstName(request.getFirstName())
  //            .dateOfBirth(LocalDate.now())
  //            .build();
  //  }

  @Operation(
          summary = "Obtiene la informacion de un estudiante.",
          method = "GET",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Show Students",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  array = @ArraySchema(
                                          schema = @Schema(implementation = StudentResponse.class)
                                  )
                          )
                  )
  })
  @GetMapping(path = "/{id}")
  public Single<StudentResponse> findById(
          @Parameter(
                  name = "id",
                  description = "Identificador del estudiante",
                  in = ParameterIn.PATH,
                  schema = @Schema(
                          pattern = REGEXP_ONLY_NUMBER,
                          implementation = Integer.class
                  ),
                  example = "1",
                  required = true
          )
          @Pattern(regexp = REGEXP_ONLY_NUMBER)
          @PathVariable(name = "id") Integer id) {
    return service.findById(id)
            .map(mapper::mapStudentResponse);
  }

  @Operation(
          summary = "Obtiene el contacto del estudiante.",
          method = "GET",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Show Students",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  array = @ArraySchema(
                                          schema = @Schema(implementation = AddressResponse.class)
                                  )
                          )
                  )
          })
  @GetMapping(path = "/{id}/address")
  public Single<AddressResponse> getAddressById(
          @Parameter(
                  name = "id",
                  description = "Identificador del estudiante",
                  in = ParameterIn.PATH,
                  schema = @Schema(
                          pattern = REGEXP_ONLY_NUMBER,
                          implementation = Integer.class
                  ),
                  example = "1",
                  required = true
          )
          @Pattern(regexp = REGEXP_ONLY_NUMBER)
          @PathVariable(name = "id") Integer id) {
    return service.findById(id)
            .map(Student::getAddress)
            .map(mapper::mapAddressResponse);
  }

  @Operation(
          summary = "Obtiene el contacto del estudiante.",
          method = "GET",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Show Students",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  array = @ArraySchema(
                                          schema = @Schema(implementation = AddressResponse.class)
                                  )
                          )
                  )
          })
  @GetMapping(path = "/{id}/code")
  public Single<StudentResponse> getStudentCodeById(
          @Parameter(
                  name = "id",
                  description = "Identificador del estudiante",
                  in = ParameterIn.PATH,
                  schema = @Schema(
                          pattern = REGEXP_ONLY_NUMBER,
                          implementation = Integer.class
                  ),
                  example = "1",
                  required = true
          )
          @Pattern(regexp = REGEXP_ONLY_NUMBER)
          @PathVariable(name = "id") Integer id) {
    return service.findById(id)
            .map(Student::getStudentCode)
            .map(mapper::mapStudentCodeResponse);
  }

  @Operation(
          summary = "Obtiene el contacto del estudiante.",
          method = "GET",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Show Students",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  array = @ArraySchema(
                                          schema = @Schema(implementation = AddressResponse.class)
                                  )
                          )
                  )
          })
  @GetMapping(path = "/{id}/info")
  public Single<StudentResponse> getStudentInfoById(
          @Parameter(
                  name = "id",
                  description = "Identificador del estudiante",
                  in = ParameterIn.PATH,
                  schema = @Schema(
                          pattern = REGEXP_ONLY_NUMBER,
                          implementation = Integer.class
                  ),
                  example = "1",
                  required = true
          )
          @Pattern(regexp = REGEXP_ONLY_NUMBER)
          @PathVariable(name = "id") Integer id) {
    return service.findById(id)
            .map(mapper::mapStudentInfoResponse);
  }

  @Operation(
          summary = "Obtiene la lista de estudiantes",
          method = "GET",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Show Students",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  schema = @Schema(implementation = StudentDetailResponse.class)
                          )
                  )
  })
  @GetMapping(path = "/detail")
  public Single<StudentDetailResponse> find() {
    return service.findAll()
            .map(mapper::mapStudentResponse)
            .toList()
            .map(mapper::mapStudentDetailResponse);
  }

  @Operation(
          summary = "Obtiene la lista de estudiantes",
          method = "GET",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Show Students",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  array = @ArraySchema(
                                          schema = @Schema(implementation = StudentResponse.class)
                                  )
                          )
                  )
          })
  @GetMapping
  public Observable<StudentResponse> findAll() {
    return service.findAll()
            .map(mapper::mapStudentResponse);
  }

  //  private StudentResponse mapStudentResponse(Student student) {
  //    return StudentResponse.builder()
  //            .dateOfBirth(student.getDateOfBirth())
  //            .firstName(student.getFirstName())
  //            .gender(student.getGender())
  //            .lastName(student.getLastName())
  //            .middleName(student.getMiddleName())
  //            .otherStudentDetail(student.getOtherStudentDetail())
  //            .build();
  //  }


}
