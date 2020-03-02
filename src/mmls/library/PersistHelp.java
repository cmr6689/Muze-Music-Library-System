package mmls.library;

import java.io.*;

/**
 * Class to assist in the saving and loading of library object
 */
public class PersistHelp implements Serializable {
    String name;
    public PersistHelp(){
        name = "LibPersist";
    }

    /**
     * Serializes a library at the location specified by the variable "name"
     * @param lib The library to be serialized
     */
    public void serialize(Library lib){

        try{
            FileOutputStream file =  new FileOutputStream(name);
            ObjectOutputStream out =  new ObjectOutputStream(file);
            out.writeObject(lib);
            out.close();
            file.close();

        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the library from a location specified by the variable "name"
     * @return the library that was deserialized
     */
    public  Library deserialize(){
        try {

            FileInputStream file = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(file);


            Library returned = (Library) in.readObject();

            in.close();
            file.close();
            return returned;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    /**
     * gets the name/location of the file
     * @return name/location of the file
     */
    public String getName()
    {
        return name;
    }
}
