package com.service.Implementation;

import java.util.List;

import com.Dao.Implementation.InstructorDaoImpl;
import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;
import com.services.InstructorService;

public class InstructorServiceImpl implements InstructorService {
    // DAO instance for interacting with Instructor data
    private final InstructorDaoImpl instructorDao;

    // Constructor initializing the InstructorDao implementation
    public InstructorServiceImpl() {
        this.instructorDao = new InstructorDaoImpl();
    }

    @Override
    public void saveInstructor(Instructor instructor) {
        // Save the provided instructor to the database
        instructorDao.save(instructor);
    }

    @Override
    public Instructor getInstructorBy_Id(long instructor_Id) {
        // Retrieve and return the instructor by their ID
        return instructorDao.get(instructor_Id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        // Retrieve and return a list of all instructors from the database
        return instructorDao.getAllInstructors();
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        // Update the provided instructor details in the database
        instructorDao.update(instructor);
    }

    @Override
    public void deleteInstructor(long instructor_Id) {
        // Delete the instructor by their ID from the database
        instructorDao.delete(instructor_Id);
    }

    @Override
    public Instructor login(String name, String email, String password, String department) {
        // Attempt to find an instructor by email
        Instructor existingInstructor = instructorDao.findByEmail(email);
        
        if (existingInstructor != null) {
            // Check if the provided name and password match the existing instructor's details
            if (existingInstructor.getName().equals(name) && existingInstructor.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + existingInstructor.getName() + ".");
                return existingInstructor; 
            } else {
                System.out.println("Invalid credentials: Incorrect name or password. Please try again.");
                return null; 
            }
        } else {
            // If instructor does not exist, create a new one
            return createInstructor(name, email, password, department);
        }
    }

    @Override
    public Instructor createInstructor(String name, String email, String password, String department) {
        // Check if an instructor with the given email already exists
        Instructor existingInstructor = instructorDao.findByEmail(email);

        if (existingInstructor != null) {
            System.out.println("An instructor with this email already exists. Please log in.");
            return null; 
        }
        
        // Create a new instructor object and set its properties
        Instructor newInstructor = new Instructor();
        newInstructor.setName(name);
        newInstructor.setEmail(email);
        newInstructor.setPassword(password);
        newInstructor.setDepartment(department); 

        // Save the new instructor to the database
        instructorDao.createInstructor(newInstructor); 
        System.out.println("Successfully registered new instructor: " + newInstructor.getName() + ".");
        return newInstructor;
    }

    @Override
    public Exam conductExam(long instructor_Id, long course_Id, List<Long> question_Id) {
        // Logic for conducting an exam will be implemented here
        return null; 
    }
}
