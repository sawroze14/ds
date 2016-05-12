import java.io.IOException;
import java.net.Socket;
 
public class Netrcv {
    private DirectoryTxr transmitter = null;
    Socket clientSocket = null;
    private boolean connectedStatus = false;
    private String ipAddress;
    String srcPath = null;
    String dstPath = "";
    public Netrcv() {
 
    }
 
    public void setIpAddress(String ip) {
        this.ipAddress = ip;
    }
 
    public void setSrcPath(String path) {
        this.srcPath = path;
    }
 
    public void setDstPath(String path) {
        this.dstPath = path;
    }
 
    private void createConnection() {
        Runnable connectRunnable = new Runnable() {
            public void run() {
                while (!connectedStatus) {
                    try {
                        clientSocket = new Socket(ipAddress, 3339);
                        connectedStatus = true;
                        transmitter = new DirectoryTxr(clientSocket, srcPath, dstPath);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
 
            }
        };
        Thread connectionThread = new Thread(connectRunnable);
        connectionThread.start();
    }
 
    public static void main(String[] args) {
        Netrcv main = new Netrcv();
        main.setIpAddress("localHost");
        main.setSrcPath("C:/Users/saw roze/Desktop/ds/file");
        main.setDstPath("C:/Users/saw roze/Desktop/ds/copy");
        main.createConnection();
 
    }
}