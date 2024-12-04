public class Transacao {
    private String remetente;
    private String destinatario;
    private double valor;

    public Transacao(String remetente, String destinatario, double valor) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valor = valor;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public double getValor() {
        return valor;
    }
}
