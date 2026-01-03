package pe.com.relari.backendtrainingapi.dao.repository;

import pe.com.relari.backendtrainingapi.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Interface:</b> StudentRepository.<br/>
 * @author RLR
 * @version 1.0.0
 */

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    boolean existsByDocumentIdentity(String documentIdentity);
    StudentEntity findByDocumentIdentity(String documentIdentity);
}
