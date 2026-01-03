package pe.com.relari.backendtrainingapi.service;

import pe.com.relari.backendtrainingapi.model.business.Student;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * <b>Interface:</b> StudentService.<br/>
 * @author RLR
 * @version 1.0.0
 */

public interface StudentService {

  Observable<Student> findAll();

  Single<Student> findById(Integer id);

  Completable save(Student student);
}
