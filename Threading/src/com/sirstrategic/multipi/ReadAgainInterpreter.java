package com.sirstrategic.multipi;

import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.lang.StringBuilder;

public class ReadAgainInterpreter {
    private int lineNumber = 0;
    private String x;
    String path = "doYouWantToSeeThisMessageAgain";

    public boolean findIfReadAgain_boolean(String messageName) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //not static so br keeps count of line
            if ((x = br.readLine()).contains(messageName)) {
                //meaningful name
                boolean ytho = Boolean.parseBoolean(x.substring(messageName.length()));
                return ytho;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    //I know It says Interpreter in the class name :)

    public void setReadAgain(String messageName, boolean assignValue) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(path));
        BufferedWriter bw = null;
        try {
            //for now I will have a LinkedList for the whole file, memory hungry for large aplications but fine for this
            String line = null;
            LinkedList<String> lines = new LinkedList<>();
            while((line = br.readLine()) != null){
                lines.add(line);
            }

            StringBuilder sb = new StringBuilder(messageName);
//           for(int i = 0; i < lines.size(); i++){
//                String s = lines.get(i);
//                if(s.contains(messageName)){
//                    lines.set(i, sb.toString());
//                }
//            }
            lines = this.editLineTF(lines, messageName, assignValue);
            //use file wiping property of buffered writer;
            bw = new BufferedWriter(new FileWriter(path,false));

            for(int i = 0; i < lines.size(); i ++){
                bw.write(lines.get(i));
            }

        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
        }
    }

    private LinkedList<String> editLineTF(LinkedList<String> list, String message, boolean tf){
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).contains(message)){
                String x = (new StringBuilder(message).append(tf)).toString();
                list.set(i, x);
            }
        }
        return list;
    }


    //unused code


    private String readLine_(FileReader fr, String filePath)throws IOException{
        StringBuilder sb = new StringBuilder();
        int c;

        System.out.println('H');
        try{
            fr = new FileReader(filePath);
            while((c = fr.read()) != 10){
                if(c == -1)
                    break;
                sb.append((char)c);
                System.out.println("====" + c);
                Thread.sleep(100);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fr != null){
                fr.close();
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private int countLines(String filePath)throws IOException{
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while(br.readLine() != null)
                counter++;
        }
        return counter;
    }

    private boolean doesContain(String s, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String x = null;
            while ((x = br.readLine()) != null) {
                if (x.contains(s))
                    return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

