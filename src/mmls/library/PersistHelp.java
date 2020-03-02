package mmls.library;

import java.io.*;

public class PersistHelp implements Serializable {
    String name;
    public PersistHelp(){
        name = "LibPersist";
    }

    public void serialize(Library lib){

        try{
            FileOutputStream file =  new FileOutputStream(name);
            ObjectOutputStream out =  new ObjectOutputStream(file);
            out.writeObject(lib);
            out.close();
            file.close();
            System.out.println("Saved file with name: " +  name);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public  Library deserialize(){
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Library returned = (Library) in.readObject();
            System.out.println("Reading lib");
            in.close();
            file.close();
            return returned;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public String getName()
    {
        return name;
    }
}
