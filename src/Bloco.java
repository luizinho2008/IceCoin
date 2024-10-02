import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.MessageDigest;
import java.util.Date;

class Bloco {
    public String hash;
    public String hashAnterior;
    public String dados;
    public long timestamp;

    public Bloco(String dados, String hashAnterior) {
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.timestamp = new Date().getTime();
        this.hash = calcularHash(dados);
    }

    public String calcularHash(String dados) {
        String texto = hashAnterior + Long.toString(timestamp) + dados;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}