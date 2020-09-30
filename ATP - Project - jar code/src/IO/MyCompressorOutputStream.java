package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
/**
 * MyCompressorOutputStream class
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     * Constructor to MyCompressorOutputStream
     * @param outStream
     */
    public MyCompressorOutputStream(OutputStream outStream){
        this.out=outStream;
    }
    /**
     * This function override write function.
     * Compresses the details of the maze and writes to OutputStream
     * @param b
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        int temp=(b.length-8)/8;
        if((b.length-8)%8!=0){
            temp+=1;
        }
        byte[] send=new byte[temp+8];
        int i=0;
        while(i<8){
            //maze Details
            send[i]=b[i];
            i++;
        }
        int indexSend=8;
        int size=b.length-8;
        for(;i<b.length;i++){
            byte[] tempArr;//=new byte[8];
            int index=0;
            if(size>=8) {
                tempArr=new byte[8];
                while (index < 8&& i<b.length) {
                    tempArr[index] = b[i];
                    index++;
                    i++;
                }
            }else {

                tempArr=new byte[size];
                while (index < size && i<b.length) {
                    tempArr[index] = b[i];
                    index++;
                    i++;
                }
            }
            i--;
            send[indexSend]=convertToByte(tempArr);
            indexSend++;
            size=size-8;
            if(indexSend>=send.length||i>=b.length)
                break;
        }

//        for(int y=0;y<send.length;y++){
//            System.out.print(send[y]+" ");
//        }
        try {
            for(int x=0;x<send.length;x++){
               out.write(send[x]);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * This function override write function.
     *  write int to OutputStream
     * @param i
     * @throws IOException
     */
    @Override
    public void write(int i) throws IOException {
       // out.write(i);
    }

    /**
     * convert binary number to Decimal number
     * @param b
     * @return
     */
    private byte convertToByte(byte[] b){
     byte sol=0;
     int p=0;
     for(int i=0;i<b.length;i++){
         sol+=(byte)(b[i]*(int)Math.pow(2,p));
         p++;
     }
     return sol;
    }

}
