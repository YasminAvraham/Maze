package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Properties;

/**
 * ServerStrategySolveSearchProblem class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy{
    /**
     * This function override write function
     * connect the input and output socket and close the socket in the end
     * @param inFromClient and outToClient
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        InputStream input = null;
        Properties prop = new Properties();
        File fileCheck = new File("./config.properties");
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Solution solve = new Solution();

            Maze mazeToSolve=(Maze)fromClient.readObject();
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            int hash=(int) mazeToSolve.hashCode();
            String nameOfFile=""+hash+"";
//            byte[] tempByteMaze= mazeToSolve.toByteArrayCompress(mazeToSolve.toByteArray());
//            for(int i=0;i<tempByteMaze.length;i++){
//                nameOfFile=nameOfFile+" "+tempByteMaze[i];
//            }
            File file= new File(tempDirectoryPath,nameOfFile);
            if(file.exists()) {//the file is exist
                FileInputStream fileInput= new FileInputStream(file);
                ObjectInputStream fileReturn=new ObjectInputStream(fileInput);
                solve=(Solution)fileReturn.readObject();
                fileReturn.close();
            }else{
                Server.Configurations.configuration(); //create config file if not exthist
                String typeSearch="Breadth First Search";
                SearchableMaze searchableMaze=new SearchableMaze(mazeToSolve);
                ASearchingAlgorithm typeAlgorithm=new BreadthFirstSearch();
                if (fileCheck.length() != 0) { //if properties file empty, and hasnt been run yet
                    input = new FileInputStream("./config.properties");
                    prop.load(input);
                    typeSearch = prop.getProperty("MazeAlgoType"); //get algorithm type from config file
                }
                if (typeSearch == null)
                    typeSearch="BreadthFirstSearch";
                if (typeSearch.equals("DepthFirstSearch"))
                    typeAlgorithm = new DepthFirstSearch();
                else if (typeSearch.equals("BestFirstSearch"))
                    typeAlgorithm = new BestFirstSearch();
                else
                    typeAlgorithm = new BreadthFirstSearch();
                solve=typeAlgorithm.solve(searchableMaze);

                FileOutputStream fileOutput= new FileOutputStream(file);
                ObjectOutputStream objectReturn= new ObjectOutputStream(fileOutput);
                objectReturn.writeObject(solve);
                objectReturn.flush();
            }
            toClient.writeObject(solve);
            toClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }
