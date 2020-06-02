package com.abc.dao;

import com.abc.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    void insertStudent(Student student);

    List<Student> selectStudentsBelowAge(int age);

    Integer selectStudentsCount();

}
