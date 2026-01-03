package pe.com.relari.backendtrainingbegin.type.single;

import pe.com.relari.backendtrainingbegin.dao.repository.StudentRepository;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MaybeExample {

    public static void main(String[] args) {

        var student = StudentRepository.student01();

        Maybe.just(student)
                .doOnSuccess(result -> log.info(result.toString()))
                .subscribe();

        var students = StudentRepository.students();
        Maybe.just(students)
                .doOnSuccess(result -> log.info(result.toString()))
                .subscribe();
        
    }
}
