package projectserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileIO implements Serializable{

    public FileIO() {
        
    }

    /*
    write accounts
     */
    public void writeAccounts(ArrayList<Accounts> accounts) {
        try (ObjectOutputStream ouput=new ObjectOutputStream(new FileOutputStream("accounts.bin"))) {
              ouput.writeObject(accounts);  
              ouput.flush();
              ouput.close();
        }catch(IOException ex) {
        ex.printStackTrace();
        }
        
    }

    /*
    reads acounts
     */
    public ArrayList<Accounts> readAccount() {
        ArrayList<Accounts> account = new ArrayList<>();
         try{
        FileInputStream inputFile = new FileInputStream("accounts.bin");
        byte[] data = new byte[10000000];
        ObjectInputStream fis = new ObjectInputStream(inputFile);
        ArrayList<Accounts> the_account = (ArrayList<Accounts>)fis.readObject();
        if (!the_account.isEmpty()) {
            return the_account;
        }
        fis.close();
        }catch(Exception e) {
            return account;
        }
         return account;
    }
    /*
    write the groups
     */
    public void writeGroups(ArrayList<Group> group) {
         try (ObjectOutputStream ouput=new ObjectOutputStream(new FileOutputStream("groups.bin"))) {
              ouput.writeObject(group);  
              ouput.flush();
              ouput.close();
        }catch(IOException ex) {
        ex.printStackTrace();
        
        }   
    }
    /*
    read the groups
     */
    public ArrayList<Group> readGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        try{
        FileInputStream inputFile = new FileInputStream("groups.bin");
        byte[] data = new byte[100000];
        ObjectInputStream fis = new ObjectInputStream(inputFile);
        ArrayList<Group> the_account = (ArrayList<Group>)fis.readObject();
        if (!the_account.isEmpty()) {
            return the_account;
        }
        fis.close();
        }catch(Exception e) {
            return groups;
        }
        return groups;
    }
    /*
    writes the server
     */
    public void writeServer(ArrayList<TheServer> server) {
        try (ObjectOutputStream ouput=new ObjectOutputStream(new FileOutputStream("server.bin"))) {
              ouput.writeObject(server);  
              ouput.flush();
              ouput.close();
        }catch(IOException ex) {
        ex.printStackTrace();
        
        }
    }
    /*
    reads the server
     */
    public  ArrayList<TheServer> readServer() {
        ArrayList<TheServer> servers = new ArrayList<>();
        try(FileInputStream inputFile = new FileInputStream("server.bin")){
        byte[] data = new byte[100000];
        ObjectInputStream fis = new ObjectInputStream(inputFile);
        
        ArrayList<TheServer> the_server = (ArrayList<TheServer>)fis.readObject();
        if (!the_server.isEmpty()) {
            return the_server;
        }
        
        
        }catch(Exception e) {
            return servers;
        }
        return servers;
    }
    

     /*
     write the massages
      */
    public void write_massage(ArrayList<massage> massages) {
        try (ObjectOutputStream ouput=new ObjectOutputStream(new FileOutputStream("massage.bin"))) {
              ouput.writeObject(massages);  
              ouput.flush();
              ouput.close();
        }catch(IOException ex) {
        ex.printStackTrace();
        
        }       
    }

    /*
    read the massages
     */
    public ArrayList<massage> read_massage() {
    ArrayList<massage> massages = new ArrayList<>();
     
        try(FileInputStream inputFile = new FileInputStream("massage.bin")){
        byte[] data = new byte[100000];
        ObjectInputStream fis = new ObjectInputStream(inputFile);
        
        ArrayList<massage> the_massage = (ArrayList<massage>)fis.readObject();
        if (!the_massage.isEmpty()) {
            return the_massage;
        }
        
        
        }catch(Exception e) {
            return massages;
        }
        return massages;
        
    }

    /*
    write the group massages
     */
    public void write_groupmassage(ArrayList<groupmassage> groupmassages) {
        try (ObjectOutputStream ouput=new ObjectOutputStream(new FileOutputStream("groupmassage.bin"))) {
              ouput.writeObject(groupmassages);  
              ouput.flush();
              ouput.close();
        }catch(IOException ex) {
        ex.printStackTrace();
        
        }       
    }

    /*
    reads the group massages
     */
    public ArrayList<groupmassage> read_groupmassage() {
        ArrayList<groupmassage> groupmassages = new ArrayList<>();
     
        try(FileInputStream inputFile = new FileInputStream("groupmassage.bin")){
        byte[] data = new byte[100000];
        ObjectInputStream fis = new ObjectInputStream(inputFile);
        
        ArrayList<groupmassage> the_groupmassage = (ArrayList<groupmassage>)fis.readObject();
        if (!the_groupmassage.isEmpty()) {
            return the_groupmassage;
        }
        
        
        }catch(Exception e) {
            return groupmassages;
        }
        return groupmassages;
    }
    
    }
