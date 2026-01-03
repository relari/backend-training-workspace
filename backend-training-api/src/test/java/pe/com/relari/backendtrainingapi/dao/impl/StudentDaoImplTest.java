package pe.com.relari.backendtrainingapi.dao.impl;

import pe.com.relari.backendtrainingapi.dao.repository.StudentRepository;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.util.TestUtil;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

/**
 * <b>Class:</b> StudentDaoImplTest<br/>
 * @author RLR
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class StudentDaoImplTest {

  @Mock
  private StudentRepository repository;

  @InjectMocks
  private StudentDaoImpl dao;

  @Test
  @DisplayName("Listado a los estudiantes de la base de datos.")
  void whenFindAllThenReturnStudents() {

    var studentEntity = TestUtil.buildStudentEntity();

    Mockito.when(repository.findAll())
            .thenReturn(Collections.singletonList(studentEntity));

    TestObserver<Student> testObserver = dao.findAll().test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValueAt(0, response ->
                    response.getDocumentIdentity().equals(studentEntity.getDocumentIdentity())
            )
            .assertValueAt(0, response ->
                    response.getFirstName().equals(studentEntity.getFirstName())
            )
            .assertValueAt(0, response ->
                    response.getLastName().equals(studentEntity.getLastName())
            )
            .assertValueAt(0, response ->
                    response.getGender().equals(studentEntity.getGender().name())
            )
            .assertValueAt(0, response ->
                    response.getStudentCode().equals(studentEntity.getStudentCode())
            )
            .assertValueAt(0, response ->
                    response.getDateOfBirth().equals(studentEntity.getDateOfBirth())
            );
  }

  @Test
  @DisplayName("Listando los estudiantes pero retorna la lista vacia.")
  void whenFindAllThenReturnEmpty() {

    Mockito.when(repository.findAll())
            .thenReturn(Collections.emptyList());

    TestObserver<Student> testObserver = dao.findAll().test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors().assertNoValues();
  }

  @Test
  @DisplayName("Guardando al estudiante en la base de datos.")
  void whenSaveStudentThenReturnSuccessful() {

    var studentEntity = TestUtil.buildStudentEntity();

    Mockito.when(repository.save(Mockito.any()))
            .thenReturn(studentEntity);

    TestObserver<Void> testObserver = dao.save(TestUtil.buildStudent()).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoValues().assertNoErrors();
  }

  @Test
  @DisplayName("Busca el estudiante por el Id en la base de datos.")
  void whenFindByIdThenReturnStudent() {

    var studentEntity = TestUtil.buildStudentEntity();

    Mockito.when(repository.findById(Mockito.anyInt()))
            .thenReturn(Optional.of(studentEntity));

    var testObserver = dao.findById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getDocumentIdentity().equals(studentEntity.getDocumentIdentity())
            )
            .assertValue(response ->
                    response.getFirstName().equals(studentEntity.getFirstName())
            )
            .assertValue(response ->
                    response.getLastName().equals(studentEntity.getLastName())
            )
            .assertValue(response ->
                    response.getGender().equals(studentEntity.getGender().name())
            )
            .assertValue(response ->
                    response.getStudentCode().equals(studentEntity.getStudentCode())
            )
            .assertValue(response ->
                    response.getDateOfBirth().equals(studentEntity.getDateOfBirth())
            );
  }

  @Test
  @DisplayName("Busca al estudiante pero no lo encuentra en la base de datos.")
  void whenFindByIdThenReturnError() {

    Mockito.when(repository.findById(Mockito.anyInt()))
            .thenReturn(Optional.empty());

    var testObserver = dao.findById(1).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete().assertError(RuntimeException.class);
  }

  @Test
  @DisplayName("Busca al estudiante por el documento de identidad.")
  void whenFindByDocumentIdentityThenReturnStudent() {

    var studentEntity = TestUtil.buildStudentEntity();

    Mockito.when(repository.findByDocumentIdentity(Mockito.anyString()))
            .thenReturn(TestUtil.buildStudentEntity());

    var testObserver = dao.findByDocumentIdentity(studentEntity.getDocumentIdentity()).test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors()
            .assertValue(response ->
                    response.getDocumentIdentity().equals(studentEntity.getDocumentIdentity())
            )
            .assertValue(response ->
                    response.getFirstName().equals(studentEntity.getFirstName())
            )
            .assertValue(response ->
                    response.getLastName().equals(studentEntity.getLastName())
            )
            .assertValue(response ->
                    response.getGender().equals(studentEntity.getGender().name())
            )
            .assertValue(response ->
                    response.getStudentCode().equals(studentEntity.getStudentCode())
            )
            .assertValue(response ->
                    response.getDateOfBirth().equals(studentEntity.getDateOfBirth())
            );
  }
  
  @Test
  @DisplayName("Busca al estudiante por su documento de identidad retornando vacio.")
  void whenFindByDocumentIdentityThenReturnError() {

    Mockito.when(repository.findByDocumentIdentity(Mockito.anyString()))
            .thenReturn(null);

    var testObserver = dao.findByDocumentIdentity("20210000").test();
    testObserver.awaitTerminalEvent();
    testObserver.assertNotComplete().assertError(RuntimeException.class);
  }

  @Test
  @DisplayName("Validando si existe el documento de identidad a la base de datos.")
  void whenExistsByDocumentIdentityThenTrue() {

    Mockito.when(repository.existsByDocumentIdentity(Mockito.anyString()))
            .thenReturn(true);

    var testObserver = dao.existsByDocumentIdentity("12345678").test();
    testObserver.awaitTerminalEvent();
    testObserver.assertComplete().assertNoErrors().assertValue(true);
  }

}
