package de.voolk.marbles.mobile.web;

import de.voolk.marbles.test.Jetty;

public class MobileMarbles {
    private static final String NAME = "marbles";
    private static final String WEBAPP = "src/main/webapp";
    private static final int PORT = 9999;

    public static void main(String[] args) throws Exception {
        System.setProperty("marbles.env", "inttest");
        new Jetty(NAME, WEBAPP, PORT, "marbles-mobile-web").run();
    }
}