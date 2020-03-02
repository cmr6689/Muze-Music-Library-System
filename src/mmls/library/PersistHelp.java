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

        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

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
    public String getName()
    {
        return name;
    }
}
