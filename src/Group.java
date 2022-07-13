package projectserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Group implements Serializable{
    private String name;
    private String Creater;
    private ArrayList<String> members;
    private ArrayList< String> massages;
    private ArrayList<String> pinmassages;
    public Group (String name, String Creater) {
        
    }

    /**
     * get creater of group
     * @return a creater
     */
    public String getCreater() {
        return Creater;
    }

    /**
     * set creater
     * @param Creater a new creater
     */
    public void setCreater(String Creater) {
        this.Creater = Creater;
    }

    /**
     * get name of group
     * @return name of group
     */
    public String getName() {
        return name;
    }

    /**
     * set name to group
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return array of members
     */
    public ArrayList<String> getMembers() {
        return members;
    }

    /**
     * add members
     * @param members
     */
    public void setMembers(String members) {
        this.members.add(members);
    }

    /**
     * get messages
     * @return message
     */
    public ArrayList<String> getMassages() {
        return massages;
    }

    /**
     * set messages
     * @param massages
     */
    public void setMassages(String massages) {
        this.massages.add(massages);
    }

    /**
     * get the pinned messages
     * @return pinned message
     */
    public ArrayList<String> getPinmassages() {
        return pinmassages;
    }

    /*
    set a pinned message
     */
    public void setPinmassages(String pinmassages) {
        this.pinmassages.add(pinmassages);
    }
    
}
