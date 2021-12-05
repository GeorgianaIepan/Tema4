package controller;

import classe.Kurs;
import repository.KursFileRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class KursFileController {
    private final KursFileRepository kursFileRepository;

    public KursFileController(){
        kursFileRepository = new KursFileRepository();
    }

    public KursFileController(String fileName) throws IOException {
        kursFileRepository = new KursFileRepository(fileName);
    }

    /**
     * @param outputStream
     * @param obj
     * @throws IOException
     */
    public void controller_writeObject(ObjectOutputStream outputStream, Kurs obj) throws IOException {
        kursFileRepository.writeObject(outputStream, obj);
    }

    /**
     * @param inputStream
     * @return die Vorlesung
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object controller_readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return kursFileRepository.readObject(inputStream);
    }

    /**
     *
     * @param name
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void controller_getOne(String name) throws IOException, ClassNotFoundException {
        kursFileRepository.getOne(name);
    }

    /**
     *
     * @return eine Liste
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Kurs> controller_getAll() throws IOException, ClassNotFoundException {
        return kursFileRepository.getAll();
    }

    /**
     *
     * @param Name
     * @param teacher
     * @param kurs
     * @param MaxEnrollment
     * @param Credits
     * @throws IOException
     */
    public void controller_create(String Name, String teacher, long kurs, int MaxEnrollment, int Credits) throws IOException {
        kursFileRepository.create(new Kurs();
    }

    /**
     *
     * @param name
     * @throws IOException
     */
    public void controller_delete(String name) throws IOException {
        Kurs vorlesungToDelete = null;
        for (Kurs kurs: kursFileRepository.getRepositoryList()) {
            if (kurs.getName() == name){
                vorlesungToDelete = new Kurs();
            }
        }
        kursFileRepository.delete(vorlesungToDelete);
    }

    /**
     *
     * @param obj
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void controller_update(Kurs obj) throws IOException, ClassNotFoundException {
        kursFileRepository.update(obj);
    }
}