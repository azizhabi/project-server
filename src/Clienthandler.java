package projectserver;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import org.json.JSONObject;
public class Clienthandler implements Runnable {
    // name of client
    private String Name;
    // socket of client
    private Socket socket;
    // reader of client
    private DataInputStream reader;
    // write of client
    private DataOutputStream writer;
    // account of client
    private Accounts account;
    // File for saving
    private FileIO file;
    // group of person
    private Group group;
    // message of client
    private massage massages;
    // group message of person
    private groupmassage groupmassages;
    // group of person
    private TheServer theserver;
    // group of person
    private serversockets serversocket;

    // list of accounts
    private  ArrayList<Accounts> accounts;
    // list of groups
    private  ArrayList<Group> groups;
    // list of servers
    private  ArrayList<TheServer> server1;
    // list of massages
    private  ArrayList<massage> the_massages;
    // list of groups massages
    private  ArrayList<groupmassage> the_groupmassage;

    /**
     * @param socket of client
     * @throws IOException if problem happened
     */
    public Clienthandler(Socket socket) throws IOException {
            this.socket = socket;
            this.serversocket = new serversockets(socket);
            file = new FileIO();
            accounts = file.readAccount();
            groups = file.readGroups();
            server1 = file.readServer();
            the_massages = file.read_massage();
            the_groupmassage = file.read_groupmassage();
            try{
             this.reader = new DataInputStream(socket.getInputStream());
             this.writer = new DataOutputStream(socket.getOutputStream());
       }catch(IOException e) {
           e.printStackTrace();
           close();
       }
        
    }

    /**
     * Close everything when error happened
     */
    private void close(){
        try{
        if (reader!= null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
        if (socket != null) {
            socket.close();
        }
        
        }catch (IOException e) {
            e.printStackTrace();
        }   
       
    }

    /**
     * run method of multithread
     */
    @Override
    public void run() {
        String massage;
        int i = 0;
        while(i == 0) {
        try{
        massage = (String) reader.readUTF();
        JSONObject json = new JSONObject(massage);
        switch(json.getString("methode")) {
            case "CreatAccount" :
                CreatAccount(json);
                break;
            case "SendMassage" :
                SendMassage(json);
                break;
            case "GroupMassage" :
                groupMassage(json);
                break;
            case "CreatGroup" :
                creatGroup(json);
                break;
            case "FriendRequest" :
                FriendRequest(json);
                break; 
            case "CheckUser" :
                CheckUser(json);
                break;
            case "CreatServer" :
                creatServer(json);
                break;
            case "AddRole" :
                addRole(json);
                break;
            case "FriendList" :
                friendList(json);
                break;
            case "AnswerRequest" :
                answerRequest(json);
                break;
            case "ShowRequest" :
                showrequest(json);
                break;
            case "getmassage" :
                getmassage(json);
                break;
            case "MyGroups" :
                mygroup(json);
                break;
            case "Showgroupmassage" :
                showgroupmassage(json);
                break;
            case "CheckServerUser" :
                checkserveruser(json);
                break;
            case "UserSatuation" :
                usersatuation(json);
                break;
            case "ShowServerMember" :
                showservermember(json);
                break;
            case "Server" :
                server(json);
                break;
            case "getGroupMassage" :
                getgroupmassage(json);
                break;
            case "ShowGroupMember" :
                showgroupmember(json);
                break;
            case "Showprofile" :
                showprofile(json);
                break;
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
        }
    }

    /**
     * @param json of information
     * set friend list of person
     */
    private void friendList(JSONObject json) {
        int i = 1;
        JSONObject json1 = new JSONObject();
        json1.put("methode", "FriendList");
        for (Accounts account: accounts) {
            if (json.getString("Username").equals(account.getName())) {
                for (String a: account.getFriends()){
                    json1.put("FriendName"+i, a);
                   i++;
                    
                }
            }
        }
        this.Sendprivate(json1);
    }

    /**
     * @param json of information
     * create account for server side
     */
    private void CreatAccount(JSONObject json) {
        Name = json.getString("Username");
        String password = json.getString("password");
        String PhoneNumber = json.getString("phoneNumber");
        String Email = json.getString("Email");
        account = new Accounts(Name,json.getString("password"),json.getString("phoneNumber"),json.getString("Email"));
        accounts.add(account);
        file.writeAccounts(accounts);
        JSONObject json1 = new JSONObject();
        json1.put("methode", "CreatAccount");
        json1.put("Answer", "True");
        Sendprivate(json1);
        
    }

    /**
     * @param json for information
     */
    private void getmassage (JSONObject json) { 
        for (massage mas : the_massages) { 
            if (json.getString("Username").equals(mas.getName()) && json.getString("FriendName").equals(mas.getSended()) || json.getString("FriendName").equals(mas.getName()) && json.getString("Username").equals(mas.getSended())) { 
                    JSONObject json1 = new JSONObject();
                    json1.put("methode", "getmassage");
                    json1.put("FriendName", json.getString("FriendName"));
                    json1.put("massage", mas.getMassages());
                    this.Sendprivate(json1);
                }
            }
        }

    /**
      * @param json of information
     * send message for client
     */
    private void SendMassage(JSONObject json) {
        
               for (Accounts account: accounts) {
                if (json.getString("FriendName").equals(account.getName())){
                    for (String block: account.getBlocklist()){
                    if (json.getString("Username").equals(block)){
                        JSONObject json1 = new JSONObject();
                        json1.put("methode", "privateMassage");
                        json1.put("Username", account.getName());
                        json1.put("Answer","you cannot massage to this account");
                        try{
                            this.writer.writeUTF(json1.toString());
                            return;
                        }catch(IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    }
                   
                            massages =  new massage(json.getString("FriendName"));
                            massages.setMassages(json.getString("massage"));
                            massages.setSended(json.getString("Username"));
                            the_massages.add(massages);
                            this.file.write_massage(the_massages);
                            JSONObject json1 = new JSONObject();
                            json1.put("methode", "privateMassage");
                            json1.put("Answer", "True");
                            break;
                        
               }
            }
        }

    /**
     *
      * @param json1 for information
     * send private message
     */
    private void Sendprivate(JSONObject json1) {
          try{
            this.writer.writeUTF(json1.toString());
            return;
            }catch (IOException e) {
              e.printStackTrace();
              return;
            }
    }

    /**
     * @param json of information
     * @throws IOException if problem happened
     */
    private void groupMassage(JSONObject json) throws IOException {
        int flag = 0;
        for (Group a : groups) {
                if (json.getString("GroupName").equals(a.getName())) {
                    for (String name : a.getMembers()) {
                    
                            for (Accounts as : accounts){
                             if (as.getName().equals(name)) {
                                    groupmassages = new groupmassage(json.getString("GroupName"),json.getString("Username"),json.getString("massage"));
                                    the_groupmassage.add(groupmassages);
                                    this.file.write_groupmassage(the_groupmassage);
                                    flag = 1;
                                    break;
                                    }
                                
                                }
                            }
                        }
                    }
                
        if (flag == 1) {
            JSONObject json1 = new JSONObject();
            json1.put("methode", "GroupMassage");
            json1.put("Answer", "True");
            this.Sendprivate(json1);
            
        }
        else {
            JSONObject json1 = new JSONObject();
            json1.put("methode", "GroupMassage");
            json1.put("Answer", "False");
            this.Sendprivate(json1);
        }
        
    }

    /**
     * send friend request to another client
     * @param json of information
     */
    private void FriendRequest(JSONObject json) {
        int flag = 0;
         System.out.println("alishefa");
        for (Accounts a : accounts) {
            System.out.println("alishefa1");
            if (json.getString("FriendName").equals(a.getName())) {
                 System.out.println("alishefa2");
                a.setRequest(json.getString("Username"));
                 System.out.println("alishefa3");
                this.file.writeAccounts(accounts);
                 System.out.println("alishefa4");
                JSONObject json1 = new JSONObject();
                json1.put("methode", "FriendRequest");
                json1.put("Answer", "True");
                this.Sendprivate(json1); 
                 System.out.println("alishefa5");
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            JSONObject json1 = new JSONObject();
             json1.put("methode", "FriendRequest");
             json1.put("Answer", "False");
             this.Sendprivate(json1);
        
        }
        
    }

    /**
     * check if user valid
     * @param json of information
     */
    private void CheckUser(JSONObject json) {
        JSONObject json1 = new JSONObject();
        for (Accounts a : accounts) {
            if (json.getString("Username").equals(a.getName())&&json.getString("password").equals(a.getPassword())) {
                json1.put("methode", "CheckUser");
                json1.put("Answer", "True");
                Sendprivate(json1);
                return;
            }
        }
           json1.put("methode", "CheckUser");
           json1.put("Answer", "False");
           Sendprivate(json1);
           return;
                
    }


    /**
     * create a group for client
     * @param json of information
     */
    private void creatGroup(JSONObject json) {
        JSONObject json1 = new JSONObject();
            for (TheServer a : server1 ) {
                if (json.getString("ServerName").equals(a.getServerName())) {
                        group = new Group(json.getString("GroupName"),json.getString("GroupCreaters"));
                        a.setGroups(group);
                        groups.add(group);
                        this.file.writeGroups(groups);
                        json1.put("methode", "CreatGroup");
                        json1.put("Answer", "True");
                        Sendprivate(json1);
                        return;
                        }
                }
            
        json1.put("methode", "CreatGroup");
        json1.put("Answer", "False");
        this.Sendprivate(json1);      
        return;    
    }

    /**
     * create server for client
     * @param json information
     */

    private void creatServer(JSONObject json) {
        theserver = new TheServer(json.getString("ServerName"),json.getString("ServerCreater"));
        server1.add(theserver);
        this.file.writeServer(server1);
        JSONObject json1 = new JSONObject();
        json1.put("methode", "CreatServer");
        json1.put("Answer", "True");
        this.Sendprivate(json1);
        return;
    }

    /**
     * give role to client
     * @param json of information
     */
    private void addRole(JSONObject json) {
        JSONObject json1 = new JSONObject();
        for (Accounts s : accounts) {
            if (json.getString("UserName").equals(s.getName())){
              for (TheServer a : server1) {
                   if (a.getServerName().equals("ServerName")){
                     switch (json.getString("Role")) {
                          case "GroupCreater":
                              a.setGroupCreater(json.getString("UserName"));
                              file.writeServer(server1);
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                           case "GroupDeleter" :
                              a.setGroupDeleter(json.getString("UserName"));
                              file.writeServer(server1);
                              
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                          case "UserDeleter" :
                              a.setUserDeleter(json.getString("UserName"));
                              file.writeServer(server1);
                              
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                          case "MassagePiner" :
                              a.setMassagepiner(json.getString("UserName"));
                              file.writeServer(server1);
                              
                              json1.put("methode", "AddRole");
                              json1.put("Answer", "True");
                              Sendprivate(json1);
                              return;
                        }
                   }
                }
            }
        }
        json1.put("methode", "AddRole");
        json1.put("Answer", "False");
        Sendprivate(json1);
    }

    /**
     * show request which is sent by client
     * @param json of information
     */
    private void showrequest (JSONObject json) {
        JSONObject json1 = new JSONObject();
        json1.put("methode", "ShowRequest");
        int i = 1;
        for (Accounts s : accounts) {
         if (json.getString("Username").equals(s.getName())) {
            for (String a : s.getRequest()) {
                json1.put("Username"+i, a);
                i++;
                }
            }
         }
        this.Sendprivate(json1);
    }

    /**
     * response to the request
     * @param json of information
     */
    private void answerRequest(JSONObject json) {
        if (json.getString("Answer").equals("Accept")){
            for (Accounts account: accounts) {
                if (json.getString("Username").equals(account.getName())) {
                    account.setFriends(json.getString("FriendName"));
                    file.writeAccounts(accounts);
                }
                if (json.getString("FriendName").equals(account.getName())) {
                    account.setFriends(json.getString("Username"));
                    file.writeAccounts(accounts);
                }
            }
            JSONObject json1 = new JSONObject();
            json1.put("methode", "AnswerRequest");
            json1.put("Answer", "True");
    }
            JSONObject json1 = new JSONObject();
            json1.put("methode", "AnswerRequest");
            json1.put("Answer", "False");
    }

    /**
     *
     * @return name of client
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name set name of client
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * get a group
     * @param json of information
     */
    private void mygroup(JSONObject json) {
        for (Group group: groups) {
            for (String member : group.getMembers()) {
                if (json.getString("Username").equals(member)) {
                    JSONObject json1 = new JSONObject();
                    json1.put("methode", "MyGroups");
                    json1.put("GroupName", group.getName());
                    this.Sendprivate(json1);
                }
                
            }
            
        }
    }

    /**
     * show the masseges from groups
     * @param json of information
     */
        private void showgroupmassage(JSONObject json) {
        for (groupmassage massages: the_groupmassage) {
            if (json.getString("GroupName").equals(massages.getGroupName())) {
                JSONObject json1 = new JSONObject();
                json1.put("methode", "showgroupmassages");
                json1.put("Sended", massages.getName());
                json1.put("massage", massages.getMassage());
                this.Sendprivate(json1);
            }
            
        }
    }

    /**
     * check server of user
     * @param json of information
     */
    private void checkserveruser(JSONObject json) {
        for (TheServer server: server1) {
            if (json.getString("ServerName").equals(server.getServerName())) {
                for (String serveruser : server.getMembers()) {
                    if (json.getString("ServerUser").equals(serveruser)) {
                        JSONObject json1 = new JSONObject();
                        json1.put("methode", "CheckServerUser");
                        json1.put("Answer", "True");
                        this.Sendprivate(json1);
                        return;
                    }
                }
                
            }
        }
        JSONObject json1 = new JSONObject();
        json1.put("methode", "CheckServerUser");
        json1.put("Answer", "False");
        this.Sendprivate(json1);
    }

    /**
     * show the setuation of the user
     * @param json of information
     */
    private void usersatuation(JSONObject json) {
        for (TheServer server: server1) {
            if (json.getString("ServerName").equals(server.getServerName())){
              for (String admin: server.getAdmins()) {
                 if (json.getString("Username").equals(admin)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "Admins");
                     this.Sendprivate(json1);
                      return;
                 }
             }

                
             for (String deleter: server.getUserDeleter()) {
                 if (json.getString("Username").equals(deleter)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "UserDeleter");
                     this.Sendprivate(json1);
                      return;
                 }
             }
             for (String groupcreater: server.getGroupCreater()) {
                 if (json.getString("Username").equals(groupcreater)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "groupcreater");
                     this.Sendprivate(json1);
                      return;
                 }
             }
             for (String massagepiner: server.getMassagepiner()) {
                 if (json.getString("Username").equals(massagepiner)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "massagepiner");
                     this.Sendprivate(json1);
                      return;
                 }
             }
             for (String groupdeleter: server.getGroupDeleter()) {
                 if (json.getString("Username").equals(groupdeleter)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "groupdeleter");
                     this.Sendprivate(json1);
                      return;
                 }
             }
             for (String userrestricter: server.getUserRestricter()) {
                 if (json.getString("Username").equals(userrestricter)) {
                     JSONObject json1 = new JSONObject();
                     json1.put("methode", "UserSatuation");
                     json1.put("ability", "UserRestricter");
                     this.Sendprivate(json1);
                      return;
                 }
             }
             
        }
    }
       JSONObject json1 = new JSONObject();
       json1.put("methode", "UserSatuation");
       json1.put("ability", "OnlyMember");
       this.Sendprivate(json1);
  }


    /**
     * shoe members in server
     * @param json of information
     */

    private void showservermember(JSONObject json) {
        for (TheServer server: server1) {
            if (json.getString("ServerName").equals(server.getServerName())) {
                for (String member: server.getMembers()) {
                    JSONObject json1 = new JSONObject();
                    json1.put("methode", "ShowServerMember");
                    json1.put("MemberName", member);
                    this.Sendprivate(json1);
                }
                
                break;
            }
        }
    }

    /**
     * server for admin
     * @param json of information
     */
    private void server(JSONObject json) {
        switch (json.getString("job")) {
            case "ShowGroups" :   
               for (TheServer server : server1) {
                    if (json.getString("ServerName").equals(server.getServerName())) {
                        for (Group group : server.getGroups()) {
                            for (String user_name: group.getMembers()) {
                                if (json.getString("Username").equals(user_name)) {
                                    JSONObject json1 = new JSONObject(); 
                                    json1.put("methode", "Server"); 
                                    json1.put("GroupName", group.getName());
                                    this.Sendprivate(json1);
                                    
                                }
                            }
                        }
                    }
                }
                break;
            case "UserDeleter" :
                for (TheServer server : server1) {
                    if (json.getString("ServerName").equals(server.getServerName())) {
                        for (String member : server.getMembers()) {
                            if (json.getString("DeletedName").equals(member)) {
                                server.getMembers().remove(member);
                                JSONObject json1 = new JSONObject();
                                json1.put("methode", "Server");
                                json1.put("Job", "USerDeleter");
                                json1.put("Answer", "True");
                                        this.Sendprivate(json1);
                            }
                        }
                    }
                }
                                JSONObject json1 = new JSONObject();
                                json1.put("methode", "Server");
                                json1.put("Job", "USerDeleter");
                                json1.put("Answer", "False");
                                this.Sendprivate(json1);
                break;
            case "Massagepiner" :
                   for (TheServer server : server1) {
                       if (json.getString("ServerName").equals(server.getServerName())) {
                           for (Group group : server.getGroups()) {
                               if (json.getString("GroupName").equals(group.getName())) {
                                   group.setPinmassages(json.getString("pinMassage"));
                                   JSONObject json4 = new JSONObject();
                                   json4.put("methode", "Server");
                                   json4.put("Answer", "True");
                                   this.Sendprivate(json4);
                                }
                           }
                       }
                   }
                   JSONObject json4 = new JSONObject();
                    json4.put("methode", "Server");
                    json4.put("Answer", "False");
                    this.Sendprivate(json4);
                break;
            case "GroupDeleter" :
                for (TheServer server : server1) {
                    if (json.getString("ServerName").equals(server.getServerName())) {
                        for (Group group : server.getGroups()) {
                            if (json.getString("GroupName").equals(group.getName())) {
                                server.getGroups().remove(group.getName());
                                groups.remove(group.getName());
                                JSONObject json2 = new JSONObject();
                                json2.put("methode", "Server");
                                json2.put("Answer", "True");
                                this.Sendprivate(json2);
                            }
                            
                                
                            
                        }
                    }
                }
                                JSONObject json2 = new JSONObject();
                                json2.put("methode", "Server");
                                json2.put("Answer", "False");
                                this.Sendprivate(json2);
                break;
                
            case "" :
                
                break;
        }
    }

    /**
     * get message from group
     * @param json
     */
    private void getgroupmassage(JSONObject json) {
        for (groupmassage as : the_groupmassage) {
            if (json.getString("GroupName").equals(as.getGroupName())) {
                JSONObject json1 = new JSONObject(); 
                json1.put("methode", "getGroupMassage");
                json1.put("massage", as.getMassage());
                json1.put("Sended", as.getName());
                this.Sendprivate(json1);
                
            }
        }
    }

    /**
     * show member of group
     * @param json of information
     */
    private void showgroupmember(JSONObject json) {
        for (Group a : groups) {
            if (json.getString("GroupName").equals(a.getName())){
            for (String str : a.getMembers()) {
                JSONObject json1 = new JSONObject(); 
                json1.put("methode", "ShowGroupMember");
                json1.put("member", str);
                this.Sendprivate(json1);
            }
            }
        }
    }

    /**
     * show profile of client
     * @param json of information
     */
    private void showprofile(JSONObject json) {
        for (Accounts account : accounts) {
            if (json.getString("Name").equals(account.getName())) {
                JSONObject json1 = new JSONObject();
                json1.put("Name", account.getName());
                json1.put("PhoneNumber", account.getPhoneNumber());
                json1.put("EmailAddress", account.getEmail());
                json1.put("State", account.getState());
                this.Sendprivate(json1);
            }
        }
    }

}
