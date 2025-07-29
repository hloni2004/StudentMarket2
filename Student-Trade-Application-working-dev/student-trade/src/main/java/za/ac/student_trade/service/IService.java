package za.ac.student_trade.service;

import za.ac.student_trade.domain.Student;

import java.util.List;

public interface IService<T,ID>{
    //create student
    T create(T t);

    //read
    T read (ID id );

    //update
    T update (T t);

//    //find all the students
//    List<T> getAll();
}
