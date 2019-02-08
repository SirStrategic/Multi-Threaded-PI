package com.sirstrategic.multipi;

public class EssentialsNotSetException extends Exception {
    public EssentialsNotSetException() {
        super("Essentials not set, call Threads_BBP.setEssentials(numberOfThreads, digits_precision) Method");
    }
}
