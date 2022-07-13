package projectserver;

import java.io.Serializable;
import java.util.ArrayList;

public class TheServer implements Serializable {
    // name of server
    private String ServerName;
    // name of server creater
    private String ServerCreater;
    // list of members of server
    private ArrayList<String> members;
    // list of group creaters
    private ArrayList<String> GroupCreater;
    // list of group deleters
    private ArrayList<String> GroupDeleter;
    // list of user deleters
    private ArrayList<String> UserDeleter;
    // list of user restricter
    private ArrayList<String> UserRestricter;
    // list of masseges
    private ArrayList<String> ShowMassages;
    // list of massage pinners
    private ArrayList<String> Massagepiner;
    // list of admins
    private ArrayList<String> Admins;
    // list of groups of servers
    private ArrayList<Group> groups;

    public TheServer(String ServerName, String ServerCreater) {
        this.ServerName = ServerName;
        this.ServerCreater = ServerCreater;
    }

    /**
    return members
     */
    public ArrayList<String> getMembers() {
        return members;
    }
    /**
    set members
     */
    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    /**
    return admins
     */
    public ArrayList<String> getAdmins() {
        return Admins;
    }

    /**
    set admins
     */
    public void setAdmins(String Admins) {
        this.Admins.add(Admins);
    }
    /**
    return groups
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
    set groups
     */
    public void setGroups(Group groups) {
        this.groups.add(groups);
    }

    /**
    return creater of server
     */
    public String getServerCreater() {
        return ServerCreater;
    }

      /**
       set server creater
     */
    public void setServerCreater(String ServerCreater) {
        this.ServerCreater = ServerCreater;
    }

    /**
    return server name
     */
    public String getServerName() {
        return ServerName;
    }
    /**
    set server name
     */
    public void setServerName(String ServerName) {
        this.ServerName = ServerName;
    }
    /**
    returns creater of group
     */
    public ArrayList<String> getGroupCreater() {
        return GroupCreater;
    }

    /**
    set creater of group
     */
    public void setGroupCreater(String GroupCreater) {
        this.GroupCreater.add(GroupCreater);
    }

    /**
    return deleter of group
     */
    public ArrayList<String> getGroupDeleter() {
        return GroupDeleter;
    }

    /**
    set deleter of group
     */
    public void setGroupDeleter(String GroupDeleter) {
        this.GroupDeleter.add(GroupDeleter);
    }

    /**
    returns deleter of a user
     */
    public ArrayList<String> getUserDeleter() {
        return UserDeleter;
    }

    /**
    set deleter of user
    */
    public void setUserDeleter(String UserDeleter) {
        this.UserDeleter.add(UserDeleter);
    }

    /**
    returns user restricter
     */
    public ArrayList<String> getUserRestricter() {
        return UserRestricter;
    }

    /**
    sets user restricter
     */
    public void setUserRestricter(String UserRestricter) {
        this.UserRestricter.add(UserRestricter);
    }

    /**
    show massages
     */
    public ArrayList<String> getShowMassages() {
        return ShowMassages;
    }
    /**
    set massages
     */
    public void setShowMassages(String ShowMassages) {
        this.ShowMassages.add(ShowMassages);
    }

    /**
    returns list of massage piners
     */
    public ArrayList<String> getMassagepiner() {
        return Massagepiner;
    }

    /**
    set massage piners
     */
    public void setMassagepiner(String Massagepiner) {
        this.Massagepiner.add(Massagepiner);
    }

}
