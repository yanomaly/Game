package RPC;

import javax.crypto.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGen {
    private static Key key;

    public static void create_key() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, random);
        key = keyGenerator.generateKey();
    }

    public static void show_key() {
        byte b[] = getKey().getEncoded();
        String key_str = "";
        for(byte temp: b) {
            key_str += String.format("%02x", temp);
        }
        System.out.println(key_str);
    }

    public static Key getKey() {return key;}
}
