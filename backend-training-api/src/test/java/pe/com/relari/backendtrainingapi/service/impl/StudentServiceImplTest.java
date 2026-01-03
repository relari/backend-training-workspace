package pe.com.relari.backendtrainingapi.service.impl;

import pe.com.relari.backendtrainingapi.dao.StudentDao;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.util.StudentConstant;
import pe.com.relari.backendtrainingapi.util.TestUtil;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <b>Class:</b> StudentServiceImplTest<br/>
 * @author RLR
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

  @Mock
  private StudentDao dao;

  @InjectMocks
  private StudentServiceImpl service;

  @Test
  @DisplayName("Listando todos los estudiantes.")
  void whenFindAllThenReturnStudents() {

    var student = TestUtil.buildStudent();

    Mockito.when(dao.findAll())
            .thenReturn(Observable.just(TestUtil.buildStudent()));

    TestObserver<Student> testObserver = service.findAll().test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValueAt(0, response ->
                    response.getDocumentIdentity().equals(student.getDocumentIdentity())
            )
            .assertValueAt(0, response ->
                    response.getFirstName().equals(student.getFirstName())
            )
            .assertValueAt(0, response ->
                    response.getLastName().equals(student.getLastName())
            )
            .assertValueAt(0, response ->
                    response.getGender().equals(student.getGender())
            )
            .assertValueAt(0, response ->
                    response.getStudentCode().equals(student.getStudentCode())
            )
            .assertValueAt(0, response ->
                    response.getDateOfBirth().equals(student.getDateOfBirth())
            );
  }

  @Test
  @DisplayName("Busca al estudiante por su Id.")
  void whenFindByIdThenReturnStudent() {

    var student = TestUtil.buildStudent();

    Mockito.when(dao.findById(Mockito.anyInt()))
            .thenReturn(Single.just(student));

    TestObserver<Student> testObserver = service.findById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getDocumentIdentity().equals(student.getDocumentIdentity())
            )
            .assertValue(response ->
                    response.getFirstName().equals(student.getFirstName())
            )
            .assertValue(response ->
                    response.getLastName().equals(student.getLastName())
            )
            .assertValue(response ->
                    response.getGender().equals(student.getGender())
            )
            .assertValue(response ->
                    response.getStudentCode().equals(student.getStudentCode())
            )
            .assertValue(response ->
                    response.getDateOfBirth().equals(student.getDateOfBirth())
            );
  }

  @Test
  @DisplayName("Busca al estudiante y actualiza su codigo.")
  void whenFindByIdAndUpdateStudentCodeThenReturnStudent() {

    var student = TestUtil.buildStudent()
            .mutate()
            .studentCode(StudentConstant.GENERIC_CODE)
            .build();

    Mockito.when(dao.findById(Mockito.anyInt()))
            .thenReturn(Single.just(student));

    Mockito.when(dao.save(Mockito.any()))
            .thenReturn(Completable.complete());

    TestObserver<Student> testObserver = service.findById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getDocumentIdentity().equals(student.getDocumentIdentity())
            )
            .assertValue(response ->
                    response.getFirstName().equals(student.getFirstName())
            )
            .assertValue(response ->
                    response.getLastName().equals(student.getLastName())
            )
            .assertValue(response ->
                    response.getGender().equals(student.getGender())
            )
            .assertValue(response ->
                    response.getDateOfBirth().equals(student.getDateOfBirth())
            );
  }

  @Test
  @DisplayName("Guarda al estudiante de manera satisfactoria.")
  void whenSaveStudentThenReturnSuccessful() {

    Mockito.when(dao.existsByDocumentIdentity(Mockito.anyString()))
            .thenReturn(Single.just(Boolean.FALSE));

    Mockito.when(dao.save(Mockito.any()))
            .thenReturn(Completable.complete());

    TestObserver<Void> testObserver = service.save(TestUtil.buildStudent()).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoValues().assertNoErrors();
  }

}
