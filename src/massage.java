package projectserver;

import java.io.Serializable;
import java.util.ArrayList;

public class massage implements Serializable{

    private String name;
    private String massages;
    private String sended;
    public massage (String name) {
        this.name = name;
    }

    /**
    return the name
     */
    public String getName() {
        return name;
    }

    /**
    set the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
    return the massage
     */
    public String getMassages() {
        return massages;
    }

    /**
    set the massage
     */
    public void setMassages(String massages) {
        this.massages = massages;
    }

    /**
    return the sended massage
     */
    public String getSended() {
        return sended;
    }

    /**
    set the sended massage
     */
    public void setSended(String sended) {
        this.sended = sended;
    }

}
