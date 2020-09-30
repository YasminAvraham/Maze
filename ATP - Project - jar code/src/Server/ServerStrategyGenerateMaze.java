package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;


public class ServerStrategyGenerateMaze implements IServerStrategy {
    /**
     * This function override write function
     * connect the input and output socket and close the socket in the end
     * @param inFromClient and outToClient
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        InputStream input=null;
        Properties prop = new Properties();
        File fileCheck = new File("./config.properties");
        try{
            ObjectInputStream fromClient=new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient=new ObjectOutputStream(outToClient);
            ByteArrayOutputStream sizeMaze=new ByteArrayOutputStream();
            MyCompressorOutputStream compMazeReturn=new MyCompressorOutputStream(sizeMaze);
            toClient.flush();

            String mazeType = "MyMazeGenerator"; //default
            IMazeGenerator Type = null;
            if (fileCheck.length() != 0) { //if properties file empty, and hasnt been run yet
                input = new FileInputStream("./config.properties");
                // load a properties file
                prop.load(input); //load config file to prop
                mazeType = prop.getProperty("MazeType"); //get algorithm type from config file
                if (mazeType==null)
                    mazeType="MyMazeGenerator";
                if (mazeType.equals("SimpleMazeGenerator"))
                    Type = new SimpleMazeGenerator();
                else
                    Type = new MyMazeGenerator();
            }
            else
            {
                Server.Configurations.configuration();
                Type = new MyMazeGenerator(); //default
            }

            int[] mazeD=(int[])fromClient.readObject();
            Maze mazeReturn=new MyMazeGenerator().generate(mazeD[0],mazeD[1]);
            byte[] mazeComp=mazeReturn.toByteArray();
            compMazeReturn.write(mazeComp);
            toClient.writeObject(sizeMaze.toByteArray());
            toClient.flush();
            compMazeReturn.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        {

        }

    }
}
