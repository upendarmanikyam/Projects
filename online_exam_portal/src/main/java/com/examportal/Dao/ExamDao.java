package com.examportal.Dao;

import java.util.List;
import com.examportal.entity.Exam;

public interface ExamDao {
    void save(Exam exam);
    Exam get(long exam_Id);
    List<Exam> getAll();
    void update(Exam exam);
    void delete(long exam_Id);
}
