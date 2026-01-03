package pe.com.relari.backendtrainingbegin.type.single;

import pe.com.relari.backendtrainingbegin.dao.repository.StudentRepository;
import pe.com.relari.backendtrainingbegin.model.domain.Student;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SingleExample {

    public static void main(String[] args) {

        var student = StudentRepository.student01();

        Single.just(student)
                .doOnSuccess(result -> log.info(result.toString()))
                .subscribe();

        var students = StudentRepository.students();
        Single.just(students)
                .doOnSuccess(result -> log.info(result.toString()))
                .subscribe();

        Single.just(new Student())
                .doOnSuccess(result -> log.info(result.toString()))
                .subscribe();

        Student student1 = null;
        Single.just(student1)
                .doOnSuccess(result -> log.info(result.toString()))
                .subscribe();

    }
}
