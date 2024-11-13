import java.util.ArrayList;
import java.util.List;

public class Bloco {
    private String idBloco;
    private String hashBlocoAnterior;
    private List<Transacao> transacoes;

    public Bloco(String idBloco, String hashBlocoAnterior) {
        this.idBloco = idBloco;
        this.hashBlocoAnterior = hashBlocoAnterior;
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public String calcularHash() {
        // Exemplo simples de hash: concatenação dos IDs e valores
        StringBuilder hash = new StringBuilder(idBloco);
        for (Transacao transacao : transacoes) {
            hash.append(transacao.getRemetente().getIdConta())
                .append(transacao.getDestinatario().getIdConta())
                .append(transacao.getValor());
        }
        return hash.toString();
    }

    public void exibirInformacoesBloco() {
        System.out.println("ID do Bloco: " + idBloco);
        System.out.println("Hash do Bloco: " + calcularHash());
        System.out.println("Hash do Bloco Anterior: " + hashBlocoAnterior);
        System.out.println("Transações: ");
        for (Transacao transacao : transacoes) {
            System.out.println("De: " + transacao.getRemetente().getIdConta() +
                               " Para: " + transacao.getDestinatario().getIdConta() +
                               " Valor: " + transacao.getValor());
        }
    }

    public String getIdBloco() {
        return idBloco;
    }

    public String getHashBlocoAnterior() {
        return hashBlocoAnterior;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
