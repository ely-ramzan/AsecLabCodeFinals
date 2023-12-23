package com.example.labfinalsa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class FileHandling {
    private static final String FILE_NAME = "PersonInformation.ser";

    static ArrayList<PersonInformation> personInformationArrayList = new ArrayList<>();


    public static void addToArraylist(PersonInformation personInformation){
        personInformationArrayList.add(personInformation);
        writeDataToFile();
    }
    public static void writeDataToFile(){

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(personInformationArrayList);
            System.out.println("data added");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void readDataFromFile(){


        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            Object object;
            if(!((object = ois.readObject()).equals(null))){
                if(object instanceof ArrayList){
                    personInformationArrayList = (ArrayList<PersonInformation>) object;
                } else {
                    throw new RuntimeException("Unexpected object type in file");
                }
            }
            System.out.println("data added");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
