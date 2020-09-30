package Client;

import Client.IClientStrategy;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Client class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;
    /**
     * constructor to Client
     * @param IP
     * @param port
     * @param clientStrategy
     */
    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.serverIP = IP;
        this.serverPort = port;
        this.clientStrategy = clientStrategy;
    }

    /**
     * This function open connection between Client and server
     */
    public void communicateWithServer(){
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(theServer.getInputStream(),theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            System.out.println("Client is nooooooooooootttttttttt connected to server!");

            e.printStackTrace();
        }
    }
}
