package com.uni.repository;

import classe.Lehrer;
import repository.CrudRepository;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LehrerFileRepository implements CrudRepository<Lehrer> {
    protected ObjectOutputStream objectOutputStream;
    protected FileOutputStream fileOutputStream;
    protected ObjectInputStream objectInputStream;
    protected FileInputStream fileInputStream;
    protected String fileName;
    protected List<Lehrer> inMemoryList;

    public LehrerFileRepository(){
        super();
        this.fileName = "src//lehrer";
        inMemoryList = new ArrayList<>();
    }

    public LehrerFileRepository(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        inMemoryList = new ArrayList<>();
        this.fileOutputStream = new FileOutputStream(fileName);
        this.objectOutputStream = new ObjectOutputStream(fileOutputStream);
        this.fileInputStream = new FileInputStream(fileName);
        this.objectInputStream = new ObjectInputStream(fileInputStream);
    }

    /**
     * @return eine Liste mit allen Lehrer
     */
    public List<Lehrer> getRepositoryList() {
        return inMemoryList;
    }

    /**
     * @param obj der Lehrer
     */
    public void writeObject(ObjectOutputStream outputStream, Lehrer obj) throws IOException {
        outputStream.writeObject(obj);
    }

    /**
     * @param inputStream
     * @return der Lehrer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Lehrer obj;
        obj = (Lehrer) inputStream.readObject();

        return obj;
    }

    /**
     *
     * @param firstName
     * @return inMemoryList
     */
    @Override
    public Lehrer getOne(String firstName) throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer: inMemoryList) {
                writeObject(objectOutputStream, lehrer);
            }
        }

        List<Lehrer> list = new ArrayList<>();
        while (fileInputStream.available() > 0) {
            Lehrer obj = (Lehrer) objectInputStream.readObject();
            if (obj.getFirstName() == firstName) {
                list.add(obj);
            }

        }
        return list.get(list.size() - 1);
    }

    /**
     *
     * @return eine Liste mit allen Lehrer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Lehrer> getAll() throws IOException, ClassNotFoundException {
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer: inMemoryList) {
                writeObject(objectOutputStream, lehrer);
            }
        }

        return inMemoryList;
    }

    /**
     *
     * @param obj ein Objekt von Typ "Lehrer"
     * @return das neue eingefügte Object
     * @throws IOException falls man nicht in der Datei schreiben kann
     */
    @Override
    public Lehrer create(Lehrer obj) throws IOException {
        inMemoryList.add(obj);
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer : inMemoryList) {
                writeObject(objectOutputStream, lehrer);
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
    public void delete(Lehrer obj) throws IOException {
        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer : inMemoryList) {
                writeObject(objectOutputStream, lehrer);
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
    public Lehrer update(Lehrer obj) throws IOException, ClassNotFoundException {
        Lehrer lehrerToUpdate = this.inMemoryList.stream()
                .filter(lehrer -> lehrer.getFirstName() == obj.getFirstName())
                .findFirst()
                .orElseThrow();

        lehrerToUpdate.setFirstName(obj.getFirstName());
        lehrerToUpdate.setLastName(obj.getLastName());
        lehrerToUpdate.setCourses(obj.getCourses());

        if (fileInputStream.available() <= 0)
        {
            for (Lehrer lehrer : inMemoryList) {
                writeObject(objectOutputStream, lehrer);
            }
        }

        return lehrerToUpdate;
    }

}