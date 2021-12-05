package repository;

import classe.Kurs;

import repository.CrudRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KursFileRepository implements CrudRepository<Kurs> {
    protected ObjectOutputStream objectOutputStream;
    protected FileOutputStream fileOutputStream;
    protected ObjectInputStream objectInputStream;
    protected FileInputStream fileInputStream;
    protected String fileName;
    protected List<Kurs> inMemoryList;

    public KursFileRepository(){
        super();
        this.fileName = "src//kurs";
        inMemoryList = new ArrayList<>();
    }

    public KursFileRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        inMemoryList = new ArrayList<>();
        this.fileOutputStream = new FileOutputStream(fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.fileInputStream = new FileInputStream(fileName);
        this.objectInputStream = new ObjectInputStream(fileInputStream);
    }

    /**
     * @return eine Liste mit allen Vorlesungen
     */
    public List<Kurs> getRepositoryList() {
        return inMemoryList;
    }

    /**
     * @param outputStream
     * @param obj
     * @throws IOException
     */
    public void writeObject(ObjectOutputStream outputStream, Kurs obj) throws IOException {
        outputStream.writeObject(obj);
    }

    /**
     * @param inputStream
     * @return die Vorlesung
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Kurs obj;
        obj = (Kurs) inputStream.readObject();

        return obj;
    }

    /**
     *
     * @param name
     * @return das Objekt aus der Liste "repoList"
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Kurs getOne(String name) throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Kurs student : inMemoryList) {
                writeObject(objectOutputStream, student);
            }
        }

        List<Kurs> list = new ArrayList<>();
        while (fileInputStream.available() > 0) {
            Kurs obj = (Kurs) objectInputStream.readObject();
            if (obj.getName() == name) {
                list.add(obj);
            }

        }
        return list.get(list.size() - 1);
    }

    /**
     *
     * @return eine Liste mit allen Vorlesungen
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Kurs> getAll() throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Kurs kurs : inMemoryList) {
                writeObject(objectOutputStream, kurs);
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
    public Kurs create(Kurs obj) throws IOException {
        inMemoryList.add(obj);
        if (fileInputStream.available() <= 0)
        {
            for (Kurs kurs : inMemoryList) {
                writeObject(objectOutputStream, kurs);
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
    public void delete(Kurs obj) throws IOException {
        if (fileInputStream.available() <= 0)
        {
            for (Kurs kurs : inMemoryList) {
                writeObject(objectOutputStream, kurs);
            }
        }
        inMemoryList.remove(obj);
    }

    /**
     *
     * @param obj
     * @return das neue aktualisierte Object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Kurs update(Kurs obj) throws IOException, ClassNotFoundException {
        Kurs kursToUpdate = this.inMemoryList.stream()
                .filter(vorlesung -> vorlesung.getName() == obj.getName())
                .findFirst()
                .orElseThrow();

        kursToUpdate.setName(obj.getName());
        kursToUpdate.setTeacher(obj.getTeacher());
        kursToUpdate.setMaxEnrollment(obj.getMaxEnrollment());
        kursToUpdate.setStudentsEnrolled(obj.getStudentsEnrolled());
        kursToUpdate.setCredits(obj.getCredits());

        if (fileInputStream.available() <= 0)
        {
            for (Kurs kurs : inMemoryList) {
                writeObject(objectOutputStream, kurs);
            }
        }

        return kursToUpdate;
    }
}