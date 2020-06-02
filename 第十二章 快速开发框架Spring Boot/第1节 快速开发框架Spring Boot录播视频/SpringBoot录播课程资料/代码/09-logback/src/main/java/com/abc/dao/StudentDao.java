package com.abc.dao;

import com.abc.bean.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao {
    void insertStudent(Student student);
}
