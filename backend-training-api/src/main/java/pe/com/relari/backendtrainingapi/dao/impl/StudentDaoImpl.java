package pe.com.relari.backendtrainingapi.dao.impl;

import pe.com.relari.backendtrainingapi.dao.StudentDao;
import pe.com.relari.backendtrainingapi.dao.repository.StudentRepository;
import pe.com.relari.backendtrainingapi.model.business.Student;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <b>Class:</b> StudentDaoImpl.<br/>
 * <b>Inject:</b> {@link StudentDao}
 * @author RLR
 * @version 1.0.0
 */
@Slf4j
@Component
@AllArgsConstructor
class StudentDaoImpl implements StudentDao {

  private final StudentRepository repository;

  @Override
  public Observable<Student> findAll() {
    return Observable.fromCallable(repository::findAll)
            .subscribeOn(Schedulers.io())
            .flatMapIterable(studentEntities -> studentEntities)
            .map(StudentMapper::mapStudent)
            .doOnSubscribe(disposable -> log.debug("Listing the students with their data."))
            .doOnNext(student -> log.trace(student.toString()))
            .doOnComplete(() -> log.info("The list of students has been completed."));
  }

  @Override
  public Single<Student> findById(Integer id) {
    return Single.fromCallable(() -> repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado.")))
            .subscribeOn(Schedulers.io())
            .map(StudentMapper::mapStudent)
            .doOnSubscribe(disposable -> log.debug("Searching for the id to the student."))
            .doOnError(throwable -> log.error("An error occurred while searching for the student by ID."))
            .doOnSuccess(student -> log.info("The student was successfully obtained by id."));
  }

  @Override
  public Single<Student> findByDocumentIdentity(String documentIdentity) {
    return Single.fromCallable(() -> repository.findByDocumentIdentity(documentIdentity))
            .subscribeOn(Schedulers.io())
            .map(StudentMapper::mapStudent)
            .doOnSubscribe(disposable -> log.debug("Looking for the student's identity document."))
            .doOnError(throwable -> log.error("An error occurred when searching for the student by identity document."))
            .doOnSuccess(student -> log.info("The student was obtained correctly by identity document."));
  }

  @Override
  public Single<Boolean> existsByDocumentIdentity(String documentIdentity) {
    return Single.fromCallable(() -> repository.existsByDocumentIdentity(documentIdentity))
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(disposable -> log.debug("Validating if the student exists by the identity document."))
            .doOnSuccess(student -> log.info("The student was correctly validated by the identity document."));
  }

//  private Student mapStudent(StudentEntity entity) {
//    return Student.builder()
//            .dateOfBirth(entity.getDateOfBirth())
//            .firstName(entity.getFirstName())
//            .gender(entity.getGender())
//            .lastName(entity.getLastName())
//            .middleName(entity.getMiddleName())
//            .otherStudentDetail(entity.getOtherStudentDetail())
//            .build();
//  }

  @Override
  public Completable save(Student student) {
    return Single.fromCallable(() -> StudentMapper.mapStudentEntity(student))
            .map(repository::save)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(disposable -> log.debug("Registering the student."))
            .ignoreElement()
            .doOnComplete(() -> log.info("The student was saved correctly."));
  }

//  private StudentEntity mapStudentEntity(Student student) {
//    return StudentEntity.builder()
//            .dateOfBirth(student.getDateOfBirth())
//            .firstName(student.getFirstName())
//            .gender(student.getGender())
//            .lastName(student.getLastName())
//            .middleName(student.getMiddleName())
//            .otherStudentDetail(student.getOtherStudentDetail())
//            .build();
//  }
}
