package gestioneUtenza;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Questa classe e' una classe Java che si occupa di crittografare le password.
 */
public  class CryptoPw {
	private static String ALGO="SHA-256" ;
    private static final Charset UTF_8=StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT="%-20s:%s";

    /**
     * @precondition input!=null
     * @postcondition result!=null   
     * @param input input inserito
     * @return sb stringBuilder
     */
    public static byte[] digest(byte[] input) {
        MessageDigest md;
        
        try {
            md=MessageDigest.getInstance(ALGO);
        } catch(NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result=md.digest(input);
        return result;
    }

    /**
     * @precondition input!=null
     * @postcondition sb.toString()!=null   
     * @param bytes
     * @return sb stringBuilder
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb=new StringBuilder();
        for (byte b:bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * @precondition pw!=null AND pwStored!=null
     * @postcondition
     * @param pw password
     * @param pwStored password salvata
     * @return true if pass==pwStored, false altrimenti  
     */
    public static boolean isEqual(String pw, String pwStored) {
    	String pass=CryptoPw.crypt(pw);
    	return pass.equals(pwStored);
    }
    
    /**
     * @precondition str!=null
     * @postcondition
     * @param str stringa da criptare
     * @return bytesToHex(CryptoPw.digest(str.getBytes(UTF_8)))!=null
     */
    public static String crypt(String str) {
    	byte[] shaInBytes=CryptoPw.digest(str.getBytes(UTF_8));
    	return bytesToHex(shaInBytes);
    }
}