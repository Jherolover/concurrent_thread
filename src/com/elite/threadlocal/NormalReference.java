package com.elite.threadlocal;

import java.io.IOException;

public class NormalReference {
    public static void main(String[] args) throws IOException {

        StrongReference s = new StrongReference();
        s = null;
        System.gc();
        System.in.read();
    }
}
