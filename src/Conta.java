import java.util.UUID;

public class Conta {
    private String idConta;
    private double saldoInicial;

    public Conta(double saldoInicial) {
        this.saldoInicial = saldoInicial;
        this.idConta = gerarIdUnico();
    }

    private String gerarIdUnico() {
        return UUID.randomUUID().toString();
    }

    public String getIdConta() {
        return idConta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }
}
