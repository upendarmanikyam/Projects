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

    // Constructor initializing the StudentDao implementation
    public StudentServiceImpl() {
        this.studentDao = new StudentDaoImpl();
    }

    @Override
    public Student login(String name, String email, String password) {
        // Find an existing student by email
        Student existingStudent = studentDao.findByEmail(email);
        if (existingStudent != null) {
            // Validate the provided name and password
            if (existingStudent.getName().equals(name) && existingStudent.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + existingStudent.getName() + ".");
                return existingStudent; // Successful login
            } else {
                System.out.println("Invalid credentials: Incorrect name or password. Please try again.");
                return null; // Failed login
            }
        } else {
            // Create a new student if no existing account is found
            return createStudent(name, email, password);
        }
    }

    @Override
    public Student createStudent(String name, String email, String password) {
        // Check if a student with the same email already exists
        Student existingStudent = studentDao.findByEmail(email);
        if (existingStudent != null) {
            System.out.println("A student with this email already exists. Please log in.");
            return null; // Email conflict
        }
        // Create a new student object and set its properties
        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setEmail(email);
        newStudent.setPassword(password);
        
        // Save the new student to the database
        studentDao.createStudent(newStudent);
        System.out.println("Successfully registered new student: " + newStudent.getName() + ".");
        return newStudent; // Return the newly created student
    }

    @Override
    public List<Student> getAllStudents() {
        // Retrieve and return all students from the database
        return studentDao.getAllStudents();
    }

    @Override
    public Student getStudent(long student_Id) {
        // Retrieve a student by their ID
        return studentDao.getStudent(student_Id);
    }

    @Override
    public List<Student> getStudentsByCourse_Id(long course_Id) {
        // Retrieve students enrolled in a specific course
        return studentDao.getStudentsByCourse_Id(course_Id);
    }

    @Override
    public Student updateStudent(long student_Id, Student updatedStudent) {
        // Update an existing student's information
        return studentDao.updateStudent(student_Id, updatedStudent);
    }

    @Override
    public String deleteStudent(long student_Id) {
        // Delete a student by their ID and return the result
        return studentDao.deleteStudent(student_Id);
    }

    @Override
    public void submitExam(Student student, Exam exam, int score) {
        // Create a new Results object to store exam results
        Results result = new Results();
        result.setStudent(student);
        result.setExam(exam);
        result.setScore(score);
        
        // Save the exam results using ResultsService
        ResultsService resultsService = new ResultsServiceImpl(); 
        resultsService.saveResults(result);
    }
    
    @Override
    public List<Results> viewResults(Student student) {
        // Placeholder for viewing results; to be implemented
        return null;
    }

    @Override
    public void registerForExam(long student_Id, long course_Id, Exam exam) {
        // Retrieve the student by ID
        Student student = studentDao.getStudent(student_Id); 
        if (student != null) {
            // Register the student for the exam
            studentDao.registerForExam(student_Id, course_Id, exam);
            System.out.println("Student registered for the exam successfully.");
        } else {
            System.out.println("Student not found. Registration failed."); // Handle case where student is not found
        }
    }
}
