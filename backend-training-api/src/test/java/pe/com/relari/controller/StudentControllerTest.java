package pe.com.relari.controller;

import pe.com.relari.controller.mapper.StudentMapper;
import pe.com.relari.backendtrainingapi.model.api.AddressResponse;
import pe.com.relari.backendtrainingapi.model.api.StudentRequest;
import pe.com.relari.backendtrainingapi.model.api.StudentDetailResponse;
import pe.com.relari.backendtrainingapi.model.api.StudentResponse;
import pe.com.relari.backendtrainingapi.service.StudentService;
import pe.com.relari.backendtrainingapi.util.TestUtil;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

/**
 * <b>Class:</b> StudentServiceImplTest<br/>
 * @author RLR
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

  @Mock
  private StudentService service;

  @Mock
  private StudentMapper mapper;

  @InjectMocks
  private StudentController controller;

  @Test
  @DisplayName("Listando todos los estudiantes.")
  void whenFindAllThenReturnStudents() {

    Mockito.when(service.findAll())
        .thenReturn(Observable.just(TestUtil.buildStudent()));

    var studentResponse = buildStudentResponse();

    Mockito.when(mapper.mapStudentResponse(Mockito.any()))
        .thenReturn(studentResponse);

    var testObserver = controller.findAll().test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValueAt(0, response ->
                    response.getDocumentIdentity().equals(studentResponse.getDocumentIdentity())
            )
            .assertValueAt(0, response ->
                    response.getFirstName().equals(studentResponse.getFirstName())
            )
            .assertValueAt(0, response ->
                    response.getLastName().equals(studentResponse.getLastName())
            )
            .assertValueAt(0, response ->
                    response.getGender().equals(studentResponse.getGender())
            )
            .assertValueAt(0, response ->
                    response.getStudentCode().equals(studentResponse.getStudentCode())
            )
            .assertValueAt(0, response ->
                    response.getDateOfBirth().equals(studentResponse.getDateOfBirth())
            );

  }

  @Test
  @DisplayName("Listando los estudiantes con su detalle.")
  void whenFindAllThenReturnStudentDetailResponse() {

    Mockito.when(service.findAll())
            .thenReturn(Observable.just(TestUtil.buildStudent()));

    Mockito.when(mapper.mapStudentResponse(Mockito.any()))
            .thenReturn(buildStudentResponse());

    var studentDetailResponse = buildStudentDetailResponse();

    Mockito.when(mapper.mapStudentDetailResponse(Mockito.anyList()))
            .thenReturn(studentDetailResponse);

    var testObserver = controller.find().test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(detailResponse ->
                    detailResponse.getStudents().equals(studentDetailResponse.getStudents())
            );

  }

  @Test
  @DisplayName("Buscando el estudiante por su ID.")
  void whenFindByIdThenReturnStudentResponse() {

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(Single.just(TestUtil.buildStudent()));

    var studentResponse = buildStudentResponse();

    Mockito.when(mapper.mapStudentResponse(Mockito.any()))
            .thenReturn(studentResponse);

    var testObserver = controller.findById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getDocumentIdentity().equals(studentResponse.getDocumentIdentity())
            )
            .assertValue(response ->
                    response.getFirstName().equals(studentResponse.getFirstName())
            )
            .assertValue(response ->
                    response.getLastName().equals(studentResponse.getLastName())
            )
            .assertValue(response ->
                    response.getGender().equals(studentResponse.getGender())
            )
            .assertValue(response ->
                    response.getStudentCode().equals(studentResponse.getStudentCode())
            )
            .assertValue(response ->
                    response.getDateOfBirth().equals(studentResponse.getDateOfBirth())
            );

  }

  @Test
  @DisplayName("Obteniendo el contacto del estudiante por su Id.")
  void whenGetAddressByIdThenReturnStudentResponse() {

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(Single.just(TestUtil.buildStudent()));

    var addressResponse = buildAddressResponse();

    Mockito.when(mapper.mapAddressResponse(Mockito.any()))
            .thenReturn(addressResponse);

    var testObserver = controller.getAddressById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getEmail().equals(addressResponse.getEmail())
            )
            .assertValue(response ->
                    response.getPhoneNumber().equals(addressResponse.getPhoneNumber())
            );

  }

  @Test
  @DisplayName("Buscando el estudiante por su codigo.")
  void whenGetStudentCodeByIdThenReturnStudentResponse() {

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(Single.just(TestUtil.buildStudent()));

    var studentResponse = buildStudentResponse();

    Mockito.when(mapper.mapStudentCodeResponse(Mockito.any()))
            .thenReturn(buildStudentResponse());

    var testObserver = controller.getStudentCodeById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getStudentCode().equals(studentResponse.getStudentCode())
            );

  }

  @Test
  @DisplayName("Buscando la informacion del estudiante por si Id.")
  void whenGetStudentInfoByIdThenReturnStudentResponse() {

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(Single.just(TestUtil.buildStudent()));

    var studentResponse = buildStudentResponse();

    Mockito.when(mapper.mapStudentInfoResponse(Mockito.any()))
            .thenReturn(studentResponse);

    var testObserver = controller.getStudentInfoById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getFirstName().equals(studentResponse.getFirstName())
            )
            .assertValue(response ->
                    response.getLastName().equals(studentResponse.getLastName())
            )
            .assertValue(response ->
                    response.getGender().equals(studentResponse.getGender())
            )
            .assertValue(response ->
                    response.getDateOfBirth().equals(studentResponse.getDateOfBirth())
            );

  }

  @Test
  @DisplayName("Guardando al estudiante.")
  void whenSaveStudentThenReturnSuccessful() {

    Mockito.when(mapper.mapStudent(Mockito.any()))
            .thenReturn(TestUtil.buildStudent());

    Mockito.when(service.save(Mockito.any()))
        .thenReturn(Completable.complete());

    var testObserver = controller.save(buildStudentRequest()).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors().assertNoValues();
  }

  private StudentRequest buildStudentRequest() {
    return StudentRequest.builder()
        .firstName("Jose")
        .lastName("Gonzales")
        .documentIdentity("12345678")
        .gender("M")
        .dateOfBirth("01/01/2020")
        .build();
  }

  private StudentResponse buildStudentResponse() {
    return StudentResponse.builder()
        .firstName("Jose")
        .lastName("Gonzales")
        .documentIdentity("12345678")
        .gender("M")
        .dateOfBirth("01/01/2020")
        .studentCode("20210000")
        .build();
  }

  private AddressResponse buildAddressResponse() {
    return new AddressResponse("email@mail.com", "999999999");
  }

  private StudentDetailResponse buildStudentDetailResponse() {
    return new StudentDetailResponse(Collections.singletonList(buildStudentResponse()));
  }

}
