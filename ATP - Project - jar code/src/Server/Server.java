package Server;

import Server.IServerStrategy;


import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;

/**
 * Server class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    /**
     * Constructor to Server class
     * @param port, listeningIntervalMS,serverStrategy
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.serverStrategy = serverStrategy;
    }
    /**
     * This function start the tread
     */
    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }
    /**
     * This function is private and run the socket
     *It's a void function
     */
    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    new Thread(() -> {
                        serverStrategy(clientSocket);
                    }).start();
                } catch (SocketTimeoutException e) {
                    System.out.println(e);
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * this function is strategy of server
     * connect the input and output socket and close the socket in the end
     * @param clientSocket
     */
    private void serverStrategy(Socket clientSocket) {
        try {
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**
     * this function is stop the tread
     * it is a void function
     */
    public void stop() {
        stop = true;
    }
    /**
     * this function is constructor
     */
    public static class Configurations {
        public Configurations() {
        }
        /**
         * This function is static function
         * open a file text and write all the default option that we use in the future
         * @throws IOException
         */
        public static void configuration() throws IOException {
            OutputStream output = null;
            InputStream input = null;
            try {
                input = Server.class.getClassLoader().getResourceAsStream("./Resources/config.properties");//get file
                if (input == null) {//check if file exthist
                    output = new FileOutputStream("./Resources/config.properties");
                    Properties prop = new Properties(); //create new prop file

                    // set the properties value
                    prop.setProperty("MazeAlgorithmType", "BestFirstSearch");
                    prop.setProperty("maxThreads", "5");
                    prop.setProperty("MazeType", "MyMazeGenerator");

                    // save properties to project root folder
                    prop.store(output, null);
                }
            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
