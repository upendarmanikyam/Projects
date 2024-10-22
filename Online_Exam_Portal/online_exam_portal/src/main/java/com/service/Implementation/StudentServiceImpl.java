package com.service.Implementation;

import java.util.List;

import com.Dao.Implementation.StudentDaoImpl;
import com.examportal.entity.Exam;
import com.examportal.entity.Results;
import com.examportal.entity.Student;
import com.services.ResultsService;
import com.services.StudentService;

public class StudentServiceImpl implements StudentService {
    private final StudentDaoImpl studentDao;

    public StudentServiceImpl() {
        this.studentDao = new StudentDaoImpl();
    }

    @Override
    public Student login(String name, String email, String password) {
        Student existingStudent = studentDao.findByEmail(email);
        if (existingStudent != null) {
            if (existingStudent.getName().equals(name) && existingStudent.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + existingStudent.getName() + ".");
                return existingStudent; 
            } else {
                System.out.println("Invalid credentials: Incorrect name or password. Please try again.");
                return null;
            }
        } else {
            return createStudent(name, email, password);
        }
    }

    @Override
    public Student createStudent(String name, String email, String password) {
        Student existingStudent = studentDao.findByEmail(email);
        if (existingStudent != null) {
            System.out.println("A student with this email already exists. Please log in.");
            return null; 
        }
        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setEmail(email);
        newStudent.setPassword(password);
        
        studentDao.createStudent(newStudent);
        System.out.println("Successfully registered new student: " + newStudent.getName() + ".");
        return newStudent; 
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public Student getStudent(long student_Id) {
        return studentDao.getStudent(student_Id);
    }

    @Override
    public List<Student> getStudentsByCourse_Id(long course_Id) {
        return studentDao.getStudentsByCourse_Id(course_Id);
    }

    @Override
    public Student updateStudent(long student_Id, Student updatedStudent) {
        return studentDao.updateStudent(student_Id, updatedStudent);
    }

    @Override
    public String deleteStudent(long student_Id) {
        return studentDao.deleteStudent(student_Id);
    }

    @Override
    public void submitExam(Student student, Exam exam, int score) {
        Results result = new Results();
        result.setStudent(student);
        result.setExam(exam);
        result.setScore(score);
        ResultsService resultsService = new ResultsServiceImpl(); 
        resultsService.saveResults(result);
    }
    

    @Override
    public List<Results> viewResults(Student student) {
        return null;
    }
    @Override
    public void registerForExam(long student_Id, long course_Id, Exam exam) {
        Student student = studentDao.getStudent(student_Id); 
        if (student != null) {
            studentDao.registerForExam(student_Id, course_Id, exam);
            System.out.println("Student registered for the exam successfully.");
        } else {
            System.out.println("Student not found. Registration failed.");
        }
    }

}
