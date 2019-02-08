package com.sirstrategic.multipi;

import java.io.IOException;
import java.lang.StringBuilder;
import java.util.Scanner;

/*
 *
 * Source code written by SirStrategic
 *
 */

public class Application {

    private static Threads_BBP[] Threads = null;

    public static void main(String[] args) throws java.lang.ArithmeticException, EssentialsNotSetException,
            IOException, InterruptedException {

        int digits = 0;
        int numberOfThreads = 0;
        String readAgain = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Sir Strategic's Multi-Threaded PI program (Formula BBP)");
        System.out.println("Digits / Precision (1 or more): ");
        digits = sc.nextInt();
        System.out.println("Number of Threads (1 or more): ");
        numberOfThreads = sc.nextInt();
        sc = null;

        Threads_BBP.setEssentials(numberOfThreads, digits);

        Threads = new Threads_BBP[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            Threads[i] = new Threads_BBP(i);
            Threads[i].start();
        }

        boolean done = false;
        while (!done) {
            int numberDone = 0;
            for (int i = 0; i < numberOfThreads; i++) {
                if ((Threads_BBP.getThreadsDone())[i] == true)
                    numberDone++;
            }
            if (numberDone == numberOfThreads) {
                StringBuilder sb = new StringBuilder(Threads_BBP.getPI().toString());
                //System.out.println("BigDecimal PI:" + PI.toString());

                System.out.println("\n" + sb.insert(digits + 2, " █(All Digits from this point are extra precision, they may or may not be correct:)█ "));
                done = true;
            }

        }


       sc = new Scanner(System.in);
        //sc.nextLine();

        if (new ReadAgainInterpreter().findIfReadAgain_boolean("ExtraInfo?:")) {
            ExtraInfoLiteReader.extraInfoBytePrint();
            //the line bellow had to be moved into the file so that it would print,nor does test1 below, why does it not print?
            System.out.println("Do you want to see this message again? (Answer true,false,yes,no,y,n):");
            ReadAgainInterpreter rai = new ReadAgainInterpreter();
            readAgain = sc.nextLine();
            System.out.println("test1");
            rai.setReadAgain("ExtraInfo?:", yesNo(readAgain));
        }

        sc.close();
    }


    private static boolean yesNo(String input) {
        input = input.toLowerCase();
        //if statement shorter code over switch statment
        if (input.equals("true") || input.equals("yes") || input.equals("y")) {
            return true;
        } else if (input.equals("false") || input.equals("no") || input.equals("n")) {
            return false;
        } else {
            System.out.println("I'll take that as a yes");
            return true;
        }
    }
}


