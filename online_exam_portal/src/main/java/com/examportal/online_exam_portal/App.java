package com.examportal.online_exam_portal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.services.*;
import com.service.Implementation.*;
import com.examportal.entity.*;

public class App {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final StudentService studentService = new StudentServiceImpl();
    private static final InstructorService instructorService = new InstructorServiceImpl();
    private static final ExamService examService = new ExamServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final QuestionService questionService = new QuestionServiceImpl();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                showMainMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        studentLogin(scanner);
                        break;
                    case 2:
                        instructorLogin(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to the Online Exam Portal!");
        System.out.println("1. Student Login");
        System.out.println("2. Instructor Login");
        System.out.println("3. Exit");
    }

    // Student Login
    private static void studentLogin(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required. Please try again.");
            return;
        }

        Student student = studentService.login(name, email, password);
        if (student != null) {
            System.out.println("Welcome " + student.getName() + "!");
            System.out.println("Dear student, your ID is: " + student.getStudent_Id());
            studentMenu(scanner, student);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

   //instructorLogin
    private static void instructorLogin(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your department: ");
        String department = scanner.nextLine();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required. Please try again.");
            return;
        }

        Instructor instructor = instructorService.login(name, department, email, password);
        if (instructor != null) {
            System.out.println("Welcome " + instructor.getName() + "!");
            System.out.println("Dear Instructor, your ID is: " + instructor.getInstructor_Id());
            instructorMenu(scanner, instructor);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    // Student Menu
    private static void studentMenu(Scanner scanner, Student student) {
        while (true) {
            System.out.println("Student Menu");
            System.out.println("1. Register for Exam");
            System.out.println("2. Take Exam");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    registerForExam(scanner, student);
                    break;
                case 2:
                    takeExam(scanner, student);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


	// Register for an Exam
    private static void registerForExam(Scanner scanner, Student student) {
        System.out.println("Available Courses:");
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            System.out.println(course.getCourse_Id() + ": " + course.getCourseName());
        }
        System.out.print("Enter Course_Id to register for exam: ");
        long course_Id = scanner.nextLong();

        Exam exam = examService.getExamBy_Id(course_Id);
        if (exam != null) {
            studentService.registerForExam(student.getStudent_Id(), course_Id, exam);
            System.out.println("You have successfully registered for the exam!");
        } else {
            System.out.println("Invalid Course_Id or no exams available.");
        }
    }
    // take exam
    private static void takeExam(Scanner scanner, Student student) {
        System.out.print("Enter the Exam_Id you want to take: ");
        long exam_Id = scanner.nextLong();

        Exam exam = examService.getExamBy_Id(exam_Id);
        if (exam != null) {
            if (exam.getQuestions() != null && !exam.getQuestions().isEmpty()) {
                System.out.println("Starting the exam: " + exam.getSubject());
                int score = 0; 
                for (Question question : exam.getQuestions()) {
                    System.out.println("Question Type: " + question.getQuestionType());
                    System.out.println("Q: " + question.getQuestionContent());
                    System.out.print("Enter your answer: ");
                    String answer = scanner.next(); 

                    if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                        score++; 
                    }
                }

                studentService.submitExam(student, exam, score);
                System.out.println("You have completed the exam. Your score is: " + score + "/" + exam.getQuestions().size());
            } else {
                System.out.println("No questions available for this exam.");
            }
        } else {
            System.out.println("Invalid Exam_Id.");
        }
    }


    // instructorMenu
    private static void instructorMenu(Scanner scanner, Instructor instructor) {
        while (true) {
            System.out.println("Instructor Menu");
            System.out.println("1. Create Course");
            System.out.println("2. Create Exam");
            System.out.println("3. Add Questions");
            System.out.println("4. View All Courses");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createCourse(scanner, instructor);
                    break;
                case 2:
                    createExam(scanner, instructor);
                    break;
                case 3:
                    addQuestions(scanner, instructor);
                    break;
                case 4:
                    viewAllCourses();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
 // create course
    private static void createCourse(Scanner scanner, Instructor instructor) {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        Course course = new Course(courseName);
        courseService.createCourse(course, instructor.getInstructor_Id());
        System.out.println("Course created successfully!");
    }

    // create exam
    private static void createExam(Scanner scanner, Instructor instructor) {
        System.out.print("Enter Course_Id for the exam: ");
        long course_Id = scanner.nextLong();
        scanner.nextLine(); 
        System.out.print("Enter Exam Subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter Exam Date (YYYY-MM-DD): ");
        LocalDate examDate = LocalDate.parse(scanner.nextLine());

        Exam exam = new Exam(subject, examDate);
        exam.setInstructor(instructor);
        exam.setCourse(courseService.getCourseBy_Id(course_Id));
        examService.saveExam(exam);
        System.out.println("Exam created successfully!");
    }
    // add question
    private static void addQuestions(Scanner scanner, Instructor instructor) {
        System.out.print("Enter Exam ID to add questions to: ");
        long examId = scanner.nextLong();
        scanner.nextLine();
        Exam exam = examService.getExamBy_Id(examId);

        if (exam != null) {
            Question question = new Question();
            System.out.print("Enter Question Content: ");
            String content = scanner.nextLine();
            question.setQuestionContent(content);

            System.out.print("Enter Question Type (MCQ/TrueFalse): ");
            String type = scanner.nextLine();
            question.setQuestionType(type);

            Category category = new Category();
            category.setCategoryName(type.equalsIgnoreCase("MCQ") ? "Multiple Choice" : "True/False");
            question.setCategory(category);

            question.setExam(exam);
            question.setCourse(exam.getCourse());

            if (type.equalsIgnoreCase("MCQ")) {
                addMCQOptions(scanner, question);
            } else if (type.equalsIgnoreCase("TrueFalse")) {
                addTrueFalseAnswer(scanner, question);
            }

            // Save the question along with its options/answers
            questionService.saveQuestion(question);
            System.out.println("Question added successfully!");
        } else {
            System.out.println("Invalid Exam ID.");
        }
    }

    // Add MCQ options
    private static void addMCQOptions(Scanner scanner, Question question) {
        System.out.print("Enter number of options: ");
        int numberOfOptions = scanner.nextInt();
        scanner.nextLine();
        List<Answer> answers = new ArrayList<>(); 

        for (int i = 0; i < numberOfOptions; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            String option = scanner.nextLine();
            Answer answer = new Answer(option);
            answer.setQuestion(question); 
            answers.add(answer); 
        }
        question.setAnswers(answers); 
        System.out.print("Enter the correct answer: ");
        String correctAnswer = scanner.nextLine();
        question.setCorrectAnswer(correctAnswer);
    }

    // Add True/False answer
    private static void addTrueFalseAnswer(Scanner scanner, Question question) {
        System.out.print("Enter correct answer (True/False): ");
        String correctAnswer = scanner.nextLine();
        question.setCorrectAnswer(correctAnswer); 

        List<Answer> answers = new ArrayList<>();
        Answer answerTrue = new Answer("True");
        answerTrue.setQuestion(question);
        answers.add(answerTrue);

        Answer answerFalse = new Answer("False");
        answerFalse.setQuestion(question);
        answers.add(answerFalse);

        question.setAnswers(answers); 
    } 

    private static void viewAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : courses) {
                System.out.println("Course ID: " + course.getCourse_Id() + ", Name: " + course.getCourseName());
            }
        }
    }

}
