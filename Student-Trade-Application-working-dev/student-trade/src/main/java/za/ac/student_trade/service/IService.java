package za.ac.student_trade.service;

import za.ac.student_trade.domain.Student;

import java.util.List;

public interface IService<T,ID>{
    T create(T t);

    T read (ID id );

    T update (T t);

    List<T> getAll();

}
