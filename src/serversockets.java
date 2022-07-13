package projectserver;
import java.net.*;
import java.io.*;
import org.json.JSONObject;

/**
 * this is the server socket that should have socket
 */
public class serversockets {
    /// data reader
    private DataInputStream reader;
    // data write
    private DataOutputStream writer;
    /// socket of server
    private Socket socket;

    /**
     * @param socket server of socket
     * @throws IOException if problem happened
     */
    public serversockets(Socket socket) throws IOException {
             this.socket = socket;
        try{
             this.reader = new DataInputStream(socket.getInputStream());
             this.writer = new DataOutputStream(socket.getOutputStream());
       }catch(IOException e) {
           e.printStackTrace();
           this.socket.close();
       }
        
    }

    /**
     * @param json of information
     * @param socket server of socket
     */
    public void sendMassage(JSONObject json, Socket socket) {
        
    }
    
    public JSONObject getmassage(JSONObject json) {
        
        
        return json;
    }
}
