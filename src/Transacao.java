public class Transacao {
    private Conta remetente;
    private Conta destinatario;
    private double valor;

    public Transacao(Conta remetente, Conta destinatario, double valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
    }

    public Conta getRemetente() {
        return remetente;
    }

    public Conta getDestinatario() {
        return destinatario;
    }

    public double getValor() {
        return valor;
    }
}
