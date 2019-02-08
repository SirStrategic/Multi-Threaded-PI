package com.sirstrategic.multipi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;


public class Threads_BBP extends Thread{

    private int threadNumber = -1;
    //threadNumber starts at 0 and goes up, -1 denotes number not assigned
    private static int numberOfThreads = 0;
    private static boolean[] threadsDone = null;
    private static BigDecimal PI = new BigDecimal(0);
    private static StringBuilder sb = null;
    private static int counter = 0;
    private static int l = 0;

    public Threads_BBP(int threadNumber)throws EssentialsNotSetException {
        this.threadNumber = threadNumber;
        if(numberOfThreads == -1){
            throw new EssentialsNotSetException();
        }
    }

    @Override
    public void run(){

        int k = 0;
        BigDecimal TempVar = new BigDecimal("0");
        MathContext mc = new MathContext(l, RoundingMode.HALF_UP);
        while((k = countUp()) <= l){

            TempVar = (new BigDecimal("4")).divide(eightByAndAdd(k, 1), mc);
            TempVar = TempVar.subtract((new BigDecimal("2")).divide(eightByAndAdd(k, 4), mc));
            TempVar = TempVar.subtract((new BigDecimal("1")).divide(eightByAndAdd(k, 5), mc));
            TempVar = TempVar.subtract((new BigDecimal("1")).divide(eightByAndAdd(k, 6), mc));

            TempVar = TempVar.multiply((new BigDecimal("1").divide(((new BigDecimal(16)).pow(k)), mc)));
            piAdd(TempVar);
        }

        System.out.println(Thread.currentThread().getName() + " is done");
        threadsDone[threadNumber] = true;

    }

    public static BigDecimal eightByAndAdd(int k, int a){
        String K = Integer.toString(k);
        String A = Integer.toString(a);
        BigDecimal eight = new BigDecimal("8");
        BigDecimal eightPlus = new BigDecimal("0");
        eight = eight.multiply(new BigDecimal(K));
        eightPlus = eight.add(new BigDecimal(A));
        return eightPlus;
    }

    public static BigDecimal getPI(){
        return PI;
    }

    private static synchronized int countUp(){
        return counter++;
    }

    private static synchronized void piAdd(BigDecimal x){
        PI = PI.add(x);
    }

    public static void setEssentials(int numberOfThreads, int digits_precision) {
        //THIS METHOD MUST BE RUN BEFORE ANY THREADS ARE STARTED
        Threads_BBP.numberOfThreads = numberOfThreads - 1;
        threadsDone = new boolean[numberOfThreads];
        l = digits_precision;
    }

    public static boolean[] getThreadsDone() {
        return threadsDone;
    }

}
