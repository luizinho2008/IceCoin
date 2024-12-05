import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Bloco {
    private String hashBlocoAnterior;
    private String hashBloco;
    private String remetente;
    private String destinatario;
    private double quantidade;

    public Bloco(String remetente, String destinatario, double quantidade) {
        this.hashBlocoAnterior = obterUltimoHashBloco();
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.quantidade = quantidade;
        this.hashBloco = calcularHash();
    }

    private String calcularHash() {
        try {
            String dados = hashBlocoAnterior + remetente + destinatario + quantidade;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(dados.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular o hash", e);
        }
    }

    private String obterUltimoHashBloco() {
        String ultimoHash = "0";
        String url = "jdbc:mysql://localhost:3306/icecoin_db";
        String usuario = "root";
        String senha = "";

        String sql = "SELECT hash_bloco FROM blockchain ORDER BY id_bloco DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                ultimoHash = rs.getString("hash_bloco");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ultimoHash;
    }

    public void exibirInformacoesBloco() {
        System.out.println("\nHash do Bloco: " + hashBloco);
        System.out.println("Hash do Bloco Anterior: " + hashBlocoAnterior);
        System.out.println("Remetente: " + remetente);
        System.out.println("Destinatário: " + destinatario);
        System.out.println("Quantidade: " + quantidade);
    }

    public String getHashBloco() {
        return this.hashBloco;
    }

    public String getHashBlocoAnterior() {
        return this.hashBlocoAnterior;
    }

    public String getRemetente() {
        return this.remetente;
    }

    public String getDestinatario() {
        return this.destinatario;
    }

    public double getQuantidade() {
        return this.quantidade;
    }
}