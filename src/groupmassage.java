package projectserver;

import java.io.Serializable;

public class groupmassage implements Serializable{ 
   private String groupName;
   private String name;
   private String massage;

    public groupmassage(String groupName, String name, String massage) {
        this.groupName = groupName;
        this.name = name;
        this.massage = massage;
    }
    /*
    get the group name
     */
    public String getGroupName() {
        return groupName;
    }

    /*
    set the group name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /*
    get the name
     */
    public String getName() {
        return name;
    }
    /*
    set the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /*
    return the message
     */
    public String getMassage() {
        return massage;
    }

    /*
    set the massage
     */
    public void setMassage(String massage) {
        this.massage = massage;
    }
    
}
