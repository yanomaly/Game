package RPC;

import javax.crypto.Mac;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMAC {
    public static void createHMAC() throws NoSuchAlgorithmException, InvalidKeyException {
        Mac code = Mac.getInstance("HmacSHA256");
        code.init(KeyGen.getKey());
        byte b[] = code.doFinal(Integer.toString(Game.getChose()).getBytes(StandardCharsets.UTF_8));
        String key_str = "";
        for(byte temp: b) {key_str += String.format("%02x", temp);}
        System.out.println("HMAC: " + key_str);
    }
}
