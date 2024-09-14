
// Classe para representar a blockchain

import java.util.ArrayList;
import java.util.List;

class Blockchain {
    private List<Bloco> blocos;

    // Construtor da Blockchain
    public Blockchain() {
        blocos = new ArrayList<>();
        // Adiciona o bloco gênese
        blocos.add(criarBlocoGênese());
    }

    // Método para criar o bloco gênese
    private Bloco criarBlocoGênese() {
        return new Bloco("Bloco Gênese", "0");
    }

    // Método para adicionar um novo bloco à blockchain
    public void adicionarBloco(String dados) {
        String hashAnterior = blocos.get(blocos.size() - 1).hash;
        blocos.add(new Bloco(dados, hashAnterior));
    }

    // Método para verificar a integridade da blockchain
    public boolean verificarIntegridade() {
        Bloco blocoAtual;
        Bloco blocoAnterior;
        for (int i = 1; i < blocos.size(); i++) {
            blocoAtual = blocos.get(i);
            blocoAnterior = blocos.get(i - 1);

            // Verifica se o hash do bloco atual corresponde ao hash calculado
            if (!blocoAtual.hash.equals(blocoAtual.calcularHash())) {
                return false;
            }

            // Verifica se o hash do bloco anterior está correto
            if (!blocoAtual.hashAnterior.equals(blocoAnterior.hash)) {
                return false;
            }
        }
        return true;
    }

    // Método para exibir a blockchain
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
