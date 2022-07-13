package projectserver;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * class Account from Server Side
 */
public class Accounts implements Serializable{
    /// State of person
    private String state;
    //   Name of Person
    private String name;
    // Password of person
    private String password;
    /// Email of person
    private String Email;
    /// Phone of person
    private String phoneNumber;
    ///  Friend of person
    private ArrayList<String> friends;
    ///  request of person
    private ArrayList<String> request;
    /// block list of person
    private ArrayList<String> blocklist;

    /**
     * @param name of person
     * @param password password of person
     * @param Email email of person
     * @param phoneNumber phone number of person
     */
    public Accounts(String name, String password, String Email, String phoneNumber) {
        request = new ArrayList<>();
        friends = new ArrayList<>();
        blocklist = new ArrayList<>();
        this.name = name;
        this.password = password;
        this.Email = Email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return name of person
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return password of person
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password of person
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email of person
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email of person
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     *
     * @return phone number of person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber phone number of person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return friend of person
     */
    public ArrayList<String> getFriends() {
        return friends;
    }

    /**
     * @param friends of person
     */
    public void setFriends(String friends) {
        this.friends.add(friends);
    }

    /**
     * @return request of person
     */
    public ArrayList<String> getRequest() {
        return request;
    }

    /**
     * @param request of person
     */
    public void setRequest(String request) {
        this.request.add(request);
    }

    /**
     * @return bock list of person
     */
    public ArrayList<String> getBlocklist() {
        return blocklist;
    }

    /**
     * @param blocklist of person
     */
    public void setBlocklist(ArrayList<String> blocklist) {
        this.blocklist = blocklist;
    }

    /**
     * @return state of person
     */
    public String getState() {
        return state;
    }

    /**
     * @param state of person
     */
    public void setState(String state) {
        this.state = state;
    }
    
}
