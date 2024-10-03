package com.service.Implementation;

import java.util.List;

import com.Dao.Implementation.InstructorDaoImpl;
import com.examportal.entity.Exam;
import com.examportal.entity.Instructor;
import com.services.InstructorService;

public class InstructorServiceImpl implements InstructorService {
    private final InstructorDaoImpl instructorDao;

    public InstructorServiceImpl() {
        this.instructorDao = new InstructorDaoImpl();
    }

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorDao.save(instructor);
    }

    @Override
    public Instructor getInstructorBy_Id(long instructor_Id) {
        return instructorDao.get(instructor_Id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorDao.getAllInstructors();
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        instructorDao.update(instructor);
    }

    @Override
    public void deleteInstructor(long instructor_Id) {
        instructorDao.delete(instructor_Id);
    }
    @Override
    public Instructor login(String name, String email, String password, String department) {
        Instructor existingInstructor = instructorDao.findByEmail(email);
        
        if (existingInstructor != null) {
            if (existingInstructor.getName().equals(name) && existingInstructor.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + existingInstructor.getName() + ".");
                return existingInstructor; 
            } else {
                System.out.println("Invalid credentials: Incorrect name or password. Please try again.");
                return null; 
            }
        } else {
            return createInstructor(name, email, password, department);
        }
    }


    @Override
    public Instructor createInstructor(String name, String email, String password, String department) {
        Instructor existingInstructor = instructorDao.findByEmail(email);

        if (existingInstructor != null) {
            System.out.println("An instructor with this email already exists. Please log in.");
            return null; 
        }
        Instructor newInstructor = new Instructor();
        newInstructor.setName(name);
        newInstructor.setEmail(email);
        newInstructor.setPassword(password);
        newInstructor.setDepartment(department); 

        instructorDao.createInstructor(newInstructor); 
        System.out.println("Successfully registered new instructor: " + newInstructor.getName() + ".");
        return newInstructor;
    }

    @Override
    public Exam conductExam(long instructor_Id, long course_Id, List<Long> question_Id) {
        return null; 
    }
}
