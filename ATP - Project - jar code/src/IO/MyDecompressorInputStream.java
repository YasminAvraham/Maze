package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
/**
 * MyDecompressorInputStream class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    /**
     * Constructor to MyDecompressorInputStream
     * @param inStream
     */
    public MyDecompressorInputStream(InputStream inStream){
        this.in=inStream;
    }
    /**
     * This function override read function.
     * read the compress maze and write to inputStream maze's details
     * @param b compress maze
     * @return return 0
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        int indexB=0;
        ArrayList<Byte> temp=new ArrayList<>();
        try{
            while(in.available()>0 ){
                temp.add((byte) in.read());
                //b[indexB]=(byte) in.read();
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        while (indexB<8){
            b[indexB]=temp.get(indexB);
            indexB++;
        }
        int i=8;
        while (i<temp.size()){
            //indexB=b.length-1;
            byte[] array=convertToByte(temp.get(i));
            //int j;
            for(int j=0;j<array.length && indexB<b.length;j++){////////////////////
                b[indexB]=array[j];
                indexB++;
            }
            i++;
        }

        return 0;

    }

    @Override
    public int read() throws IOException {
        return 0;
    }
    /**
     * convert Decimal number to binary number
     * @param num
     * @return
     */
    private byte[] convertToByte(Byte num){
        int newNum=(int)num;
        if(newNum<0){
            newNum+=256;
        }
        byte[] temp=new byte[8];
        for(int i=0;i<8;i++){
            temp[i]=(byte) (newNum%2);
            newNum=newNum/2;
        }
        return temp;
    }
}
