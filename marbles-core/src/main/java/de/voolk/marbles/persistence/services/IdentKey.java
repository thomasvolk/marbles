package de.voolk.marbles.persistence.services;

import de.voolk.marbles.utils.Digest;

import java.util.Random;

public class IdentKey {
    private final String login;
    private final String password;
    private final String salt;

    public IdentKey(String login, String password) {
        this.login = login;
        this.password = password;
        salt = String.valueOf(new Random().nextDouble());
    }

    public String toString() {
        return new Digest().digest(login + password + salt);    
    }
}
