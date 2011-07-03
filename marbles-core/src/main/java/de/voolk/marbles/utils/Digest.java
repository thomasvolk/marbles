package de.voolk.marbles.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Digest {
    private String encoding;
    private String algorithm;

    public String getEncoding() {
        if(encoding == null) {
            encoding = "UTF-8";
        }
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getAlgoritm() {
        if(algorithm == null) {
            algorithm = "MD5";
        }
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String digest(String credentials) {
        try {
            MessageDigest md =
                        (MessageDigest) MessageDigest.getInstance(getAlgoritm());
            md.update(credentials.getBytes(getEncoding()));
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for(byte b: bytes) {
            int offset = 0;
            if(b <0) {
                offset = 256;
            }
            String hex = Integer.toHexString(offset + b);
            if(hex.length() == 1) {
                builder.append("0");
            }
            else if(hex.length() < 1 || hex.length() > 2) {
                throw new RuntimeException(
                        String.format("wrong hexcode '%s' for %d", hex, b));
            }
            builder.append(hex);
        }
        return builder.toString();
    }

}
