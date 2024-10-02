import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.security.MessageDigest;
import java.util.Date;

class Blockchain {
    private List<Bloco> blocos;

    public Blockchain() {
        blocos = new ArrayList<>();
        blocos.add(criarBlocoGênese());
    }

    private Bloco criarBlocoGênese() {
        return new Bloco("Bloco Gênese", "0");
    }

    public void adicionarBloco(String dados) {
        String hashAnterior = blocos.get(blocos.size() - 1).hash;
        blocos.add(new Bloco(dados, hashAnterior));
    }

    public boolean verificarIntegridade() {
        Bloco blocoAtual;
        Bloco blocoAnterior;
        for (int i = 1; i < blocos.size(); i++) {
            blocoAtual = blocos.get(i);
            blocoAnterior = blocos.get(i - 1);

            if (!blocoAtual.hash.equals(blocoAtual.calcularHash(blocoAtual.dados))) {
                return false;
            }

            if (!blocoAtual.hashAnterior.equals(blocoAnterior.hash)) {
                return false;
            }
        }
        return true;
    }

    public void exibirBlockchain() {
        for (Bloco bloco : blocos) {
            System.out.println("Dados: " + bloco.dados);
            System.out.println("Hash: " + bloco.hash);
            System.out.println("Hash Anterior: " + bloco.hashAnterior);
            System.out.println("Timestamp: " + bloco.timestamp);
            System.out.println();
        }
    }
}