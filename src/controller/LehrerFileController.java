package controller;

import classe.Kurs;
import classe.Lehrer;

import com.uni.repository.LehrerFileRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LehrerFileController {
    private final LehrerFileRepository lehrerFileRepository;

    public LehrerFileController(){
        lehrerFileRepository = new LehrerFileRepository();
    }

    public LehrerFileController(String fileName) throws IOException {
        lehrerFileRepository = new LehrerFileRepository(fileName);
    }

    /**
     * @param outputStream
     * @param obj
     * @throws IOException
     */
    public void controller_writeObject(ObjectOutputStream outputStream, Lehrer obj) throws IOException {
        lehrerFileRepository.writeObject(outputStream, obj);
    }

    /**
     * @param inputStream
     * @return der Lehrer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object controller_readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return lehrerFileRepository.readObject(inputStream);
    }

    /**
     * @param name
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void controller_getOne(String name) throws IOException, ClassNotFoundException {
        lehrerFileRepository.getOne(name);
    }

    /**
     *
     * @return eine Liste
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Lehrer> controller_getAll() throws IOException, ClassNotFoundException {
        return lehrerFileRepository.getAll();
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param kursList
     * @throws IOException
     */
    ArrayList<Kurs> kursList = null;
    public void controller_save(String firstName, String lastName, List kursList) throws IOException {
        lehrerFileRepository.create(new Lehrer(firstName, lastName, kursList));
    }

    /**
     *
     * @param firstName
     * @throws IOException
     */
    public void controller_delete(String firstName) throws IOException {
        Lehrer lehrerToDelete = null;
        for (Lehrer lehrer: lehrerFileRepository.getRepositoryList()) {
            if (lehrer.getFirstName() == firstName){
                lehrerToDelete = new Lehrer(lehrer.getFirstName(), lehrer.getLastName(), kursList);
            }
        }
        lehrerFileRepository.delete(lehrerToDelete);
    }

    /**
     *
     * @param obj
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void controller_update(Lehrer obj) throws IOException, ClassNotFoundException {
        lehrerFileRepository.update(obj);
    }
}