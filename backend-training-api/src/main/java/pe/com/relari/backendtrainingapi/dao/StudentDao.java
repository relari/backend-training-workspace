package pe.com.relari.backendtrainingapi.dao;

import pe.com.relari.backendtrainingapi.model.business.Student;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * <b>Interface:</b> StudentDao.<br/>
 * @author RLR
 * @version 1.0.0
 */

public interface StudentDao {

  Observable<Student> findAll();

  Single<Student> findById(Integer id);

  Single<Student> findByDocumentIdentity(String documentIdentity);

  Single<Boolean> existsByDocumentIdentity(String documentIdentity);

  Completable save(Student student);
}
