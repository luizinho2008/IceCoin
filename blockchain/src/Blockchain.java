import java.util.Scanner;
import java.util.HashMap;
import java.util.Locale;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blockchain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        HashMap<String, Pessoa> pessoas = new HashMap<>();
        String previousHash = "";

        int cont = 1;

        while (cont <= 3) {
            Pessoa p = new Pessoa();

            System.out.print(String.format("Digite o nome da pessoa %d: ", cont));
            p.setNome(sc.next());

            System.out.print(String.format("Digite a idade da pessoa %d: ", cont));
            while (!sc.hasNextInt()) {
                sc.next();
            }
            p.setIdade(sc.nextInt());

            System.out.print(String.format("Digite o salário da pessoa %d: ", cont));
            while (!sc.hasNextDouble()) {
                sc.next();
            }
            p.setSalario(sc.nextDouble());

            String originalString = (cont == 1) ? p.getNome() : previousHash;

            String algoritmo = "SHA-256";
            String hash = gerarHash(originalString, algoritmo);

            pessoas.put(hash, p);
            previousHash = hash;

            cont++;

            System.out.println();
        }

        System.out.println("Conjunto de pessoas (Chave | Características)\n\n" + pessoas);

        sc.close();
    }

    public static String gerarHash(String input, String algoritmo) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algoritmo);
            byte[] encodedHash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro: Algoritmo de hash não encontrado", e);
        }
    }
}