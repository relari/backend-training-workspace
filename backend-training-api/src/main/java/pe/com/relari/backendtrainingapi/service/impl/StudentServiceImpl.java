package pe.com.relari.backendtrainingapi.service.impl;

import pe.com.relari.backendtrainingapi.dao.StudentDao;
import pe.com.relari.backendtrainingapi.model.business.Student;
import pe.com.relari.backendtrainingapi.service.StudentService;
import pe.com.relari.backendtrainingapi.util.StudentUtil;
import pe.com.relari.backendtrainingapi.util.StudentConstant;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <b>Class:</b> StudentServiceImpl.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Service
@AllArgsConstructor
class StudentServiceImpl implements StudentService {

  private final StudentDao dao;

  @Override
  public Observable<Student> findAll() {
    return dao.findAll();
  }

  @Override
  public Single<Student> findById(Integer id) {
    return dao.findById(id)
            .flatMap(this::validateStudentCode);
//            .flatMap(student -> {
//              if (GENERIC_CODE.equals(student.getStudentCode())) {
//                return findAndUpdateStudentCode(student);
//              } else {
//                return getStudent(student);
//              }
//            });
  }

  private Single<Student> validateStudentCode(Student student) {
    return Optional.of(student)
            .filter(studentDomain ->
                    StudentConstant.GENERIC_CODE.equals(studentDomain.getStudentCode())
            )
            .map(this::updateAndSaveStudentCode)
            .orElseGet(() -> getStudent(student));
  }

  private Single<Student> updateAndSaveStudentCode(Student student) {
    return Single.fromCallable(() -> student.mutate()
            .studentCode(StudentUtil.buildStudentCode(student.getId()))
            .build()
    ).flatMap(studentDomain -> dao.save(studentDomain)
            .andThen(getStudent(studentDomain))
    );
  }

  private Single<Student> getStudent(Student student) {
    return Single.fromCallable(() -> student);
  }

  @Override
  public Completable save(Student student) {
    return dao.existsByDocumentIdentity(student.getDocumentIdentity())
            .filter(Boolean.FALSE::equals)
            .flatMapCompletable(isRegister -> dao.save(student));
  }

}
