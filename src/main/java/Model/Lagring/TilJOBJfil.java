/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Lagring;

import Model.Domene.Artist;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/*class PClassLoader extends ClassLoader {
    private Class objekt;

    @Override
    public Class findClass(String path) {
        byte[] data;
        try {
            data = loadClassData(path);
        } catch (IOException e) {
            System.err.println("Could not read class file. Cause: " +  e.getCause());
            return null;
        }

        objekt = defineClass("", data, 0, data.length);
        return objekt;
    }

    private byte[] loadClassData(String path) throws IOException {
        try(InputStream reader = Files.newInputStream(Paths.get(path))) {
            return reader.readAllBytes();
        }
    }

    public void printClassInfo() {
        if(objekt == null) {
            return;
        }

        System.out.println("Fields:");
        Arrays.asList(objekt.getDeclaredFields()).forEach(System.out::println);
        System.out.println("Methods:");
        Arrays.asList(objekt.getDeclaredMethods()).forEach(System.out::println);
    }
}

class ObjectInputStreamWithPersons extends ObjectInputStream {
    private PClassLoader pLoader;

    ObjectInputStreamWithPersons(FileInputStream in, PClassLoader pLoader) throws IOException {
        super(in);
        this.pLoader = pLoader;
    }

    @Override
    protected Class resolveClass(ObjectStreamClass classStrm) throws ClassNotFoundException {
        return Class.forName(classStrm.getName(), false, pLoader);
    }
}


public class TilJOBJfil  {
    public void tilSystem(String vei) throws Exception {

        PClassLoader classLoader = new PClassLoader();
        Class personClass = classLoader.findClass("Person.class");

        classLoader.printClassInfo();
        Object objekt;

        try (FileInputStream fin = new FileInputStream(vei);
            ObjectInputStreamWithPersons oin = new ObjectInputStreamWithPersons(fin, classLoader)) {
            objekt = oin.readObject();
            System.out.println(objekt);
        } catch(IOException e) {
            System.err.println("Could not read file. Cause: " + e.getCause());
            e.printStackTrace();
            return;
        }

        System.out.println("ID of the person:");
        Method getId = personClass.getMethod("getId");
        getId.setAccessible(true);
        System.out.println(getId.invoke(objekt));

    }
}*/
public class TilJOBJfil{
    public String filepath;
    
    public void lesFil(String filepath){
        TilJOBJfil objectIO = new TilJOBJfil();
 
        //Read object from file
        Artist st = (Artist) objectIO.ReadObjectFromFile(filepath);
        System.out.println(st);
    }
    public Object ReadObjectFromFile(String filepath) {
 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

