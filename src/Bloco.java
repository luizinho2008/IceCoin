
// Classe para representar um bloco na blockchain

import java.security.MessageDigest;
import java.util.Date;

class Bloco {
    public String hash;
    public String hashAnterior;
    public String dados;
    public long timestamp;

    // Construtor do Bloco
    public Bloco(String dados, String hashAnterior) {
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.timestamp = new Date().getTime();
        this.hash = calcularHash();
    }

    // Método para calcular o hash do bloco
    public String calcularHash() {
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