package com.sirstrategic.multipi;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.OutputStream;

public class ExtraInfoLiteReader {

    public static void extraInfoBytePrint() throws IOException{
        FileInputStream in = null;
        OutputStream out = null;
        try{
            in = new FileInputStream("Extra_Info");
            out = System.out;
            System.out.println();
            byte c;
            //yes I am using a byte stream....
            while((c = (byte) in.read()) != -1){
                out.write(c);
            }

        }catch(IOException e){
            e.printStackTrace();
        } finally{
            if (in != null) {
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }



}
