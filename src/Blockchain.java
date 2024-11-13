import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Bloco> blocos;
    private double quantidadeTotalMoedas = 10000;

    public Blockchain() {
        // Inicializando a lista de blocos
        blocos = new ArrayList<>();

        // Bloco Gênesis
        Bloco genesisBlock = new Bloco("0", null);
        blocos.add(genesisBlock);
    }

    public void adicionarBloco(Bloco bloco) {
        blocos.add(bloco);
    }

    public double conferirSaldo(String idConta) {
        double saldo = 0;

        // Percorrer todos os blocos da blockchain
        for (Bloco bloco : blocos) {
            // Verificar cada transação no bloco
            for (Transacao transacao : bloco.getTransacoes()) {
                if (transacao.getDestinatario().getIdConta().equals(idConta)) {
                    saldo += transacao.getValor();
                }

                if (transacao.getRemetente().getIdConta().equals(idConta)) {
                    saldo -= transacao.getValor();
                }
            }
        }

        return saldo;
    }

    public void criarBloco(List<Transacao> transacoes) {
        String hashBlocoAnterior = blocos.get(blocos.size() - 1).calcularHash();
        Bloco novoBloco = new Bloco(String.valueOf(blocos.size()), hashBlocoAnterior);

        for (Transacao transacao : transacoes) {
            novoBloco.adicionarTransacao(transacao);
        }

        novoBloco.calcularHash();
        adicionarBloco(novoBloco);
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }
}
