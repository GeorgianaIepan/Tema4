package com.uni.repository;

import classe.Student;
import repository.CrudRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepository implements CrudRepository<Student> {
    protected ObjectOutputStream objectOutputStream;
    protected FileOutputStream fileOutputStream;
    protected ObjectInputStream objectInputStream;
    protected FileInputStream fileInputStream;
    protected String fileName;
    protected List<Student> inMemoryList;

    public StudentFileRepository(){
        super();
        this.fileName = "src//student";
        inMemoryList = new ArrayList<>();
    }

    public StudentFileRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        inMemoryList = new ArrayList<>();
        this.fileOutputStream = new FileOutputStream(fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.fileInputStream = new FileInputStream(fileName);
        this.objectInputStream = new ObjectInputStream(fileInputStream);
    }

    /**
     * @return Liste mit Studenten
     */
    public List<Student> getRepositoryList() {
        return inMemoryList;
    }

    /**
     * @param outputStream ein "ObjectOutputStream"
     * @param obj der Student, der man in der Datei schreiben will
     * @throws IOException
     */
    public void writeObject(ObjectOutputStream outputStream, Student obj) throws IOException {
        outputStream.writeObject(obj);
    }

    /**
     * @param inputStream ein "ObjectInputStream"
     * @return der Student, der aus der Datei gelesen wurde
     * @throws IOException  falls der Student nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls der Student nicht aus der Datei gelesen wurde
     */
    public Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Student obj;
        obj = (Student) inputStream.readObject();

        return obj;
    }

    /**
     *
     * @param id das Id eines Objektes aus der Liste "repoList"
     * @return das Objekt aus der Liste "repoList"
     * @throws IOException falls das Object nicht aus der Datei gelesen wurde
     * @throws ClassNotFoundException falls das Object nicht aus der Datei gelesen wurde
     */
    @Override
    public Student getOne(String id) throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Student student : inMemoryList) {
                writeObject(objectOutputStream, student);
            }
        }

        List<Student> list = new ArrayList<>();
        while (fileInputStream.available() > 0) {
            Student obj = (Student) objectInputStream.readObject();
            if (obj.getStudentId() == Integer.parseInt(id)) {
                list.add(obj);
            }

        }
        return list.get(list.size() - 1);
    }

    /**
     *
     * @return eine Liste mit allen Studenten
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Student> getAll() throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Student student : inMemoryList) {
                writeObject(objectOutputStream, student);
            }
        }

        return inMemoryList;
    }

    /**
     *
     * @param obj
     * @return das neue eingefügte Object
     * @throws IOException
     */
    @Override
    public Student create(Student obj) throws IOException {
        inMemoryList.add(obj);
        if (fileInputStream.available() <= 0)
        {
            for (Student student : inMemoryList) {
                writeObject(objectOutputStream, student);
            }
        }
        return obj;
    }

    /**
     *
     * @param obj
     * @throws IOException
     */
    @Override
    public void delete(Student obj) throws IOException {
        if (fileInputStream.available() <= 0)
        {
            for (Student student : inMemoryList) {
                writeObject(objectOutputStream, student);
            }
        }
        inMemoryList.remove(obj);
    }

    /**
     *
     * @param obj
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Student update(Student obj) throws IOException, ClassNotFoundException {
        Student studentToUpdate = this.inMemoryList.stream()
                .filter(student -> student.getStudentId() == obj.getStudentId())
                .findFirst()
                .orElseThrow();

        studentToUpdate.setFirstName(obj.getFirstName());
        studentToUpdate.setLastName(obj.getLastName());
        studentToUpdate.setTotalCredits(obj.getTotalCredits());
        studentToUpdate.setEnrolledCourse(obj.getEnrolledCourse());

        if (fileInputStream.available() <= 0)
        {
            for (Student student : inMemoryList) {
                writeObject(objectOutputStream, student);
            }
        }

        return obj;
    }
}