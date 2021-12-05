package com.uni.controller;

import classe.Student;
import com.uni.model.Student;
import com.uni.repository.StudentFileRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StudentFileController {
    private final StudentFileRepository studentFileRepository;

    public StudentFileController(){
        studentFileRepository = new StudentFileRepository();
    }

    public StudentFileController(String fileName) throws IOException {
        studentFileRepository = new StudentFileRepository(fileName);
    }

    /**
     *
     * @param outputStream
     * @param obj
     * @throws IOException
     */
    public void controller_writeObject(ObjectOutputStream outputStream, Student obj) throws IOException {
        studentFileRepository.writeObject(outputStream, obj);
    }

    /**
     *
     * @param inputStream
     * @return der Student
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object controller_readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return studentFileRepository.readObject(inputStream);
    }

    /**
     *
     * @param id
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void controller_getOne(String id) throws IOException, ClassNotFoundException {
        studentFileRepository.getOne(id);
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Student> controller_findAll() throws IOException, ClassNotFoundException {
        return studentFileRepository.getAll();
    }

    /**
     *
     * @param firstname
     * @param lastname
     * @param Id
     * @throws IOException
     */
    public void controller_save(String firstname, String lastname, Long Id) throws IOException {
        studentFileRepository.create(new Student());
    }

    /**
     *
     * @param Id
     * @throws IOException
     */
    public void controller_delete(Long Id) throws IOException {
        Student studentToDelete = null;
        for (Student student: studentFileRepository.getRepositoryList()) {
            if (student.getFirstName() == Id){
                studentToDelete = new Student(student.getFirstName(), student.getLastName(), Id);
            }
        }
        studentFileRepository.delete(studentToDelete);
    }

    /**
     *
     * @param entity
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void controller_update(Student entity) throws IOException, ClassNotFoundException {
        studentFileRepository.update(entity);
    }
}