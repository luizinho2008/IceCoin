import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        // Criar contas
        Conta contaA = new Conta(5000);  // Conta A começa com 5000 moedas
        Conta contaB = new Conta(3000);  // Conta B começa com 3000 moedas

        // Criar transações
        Transacao transacao1 = new Transacao(contaA, contaB, 1000);  // Conta A envia 1000 para Conta B

        // Adicionar transações ao bloco
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(transacao1);

        // Criar e adicionar novo bloco à blockchain
        blockchain.criarBloco(transacoes);

        // Conferir saldo das contas
        double saldoContaA = blockchain.conferirSaldo(contaA.getIdConta());
        double saldoContaB = blockchain.conferirSaldo(contaB.getIdConta());

        System.out.println("Saldo da Conta A: " + saldoContaA);
        System.out.println("Saldo da Conta B: " + saldoContaB);

        // Exibir informações do bloco após criá-lo
        System.out.println("------------ Block information ------------------");
        Bloco ultimoBloco = blockchain.getBlocos().get(blockchain.getBlocos().size() - 1);
        ultimoBloco.exibirInformacoesBloco();

    }
}

/*
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
        primaryStage.setTitle("Exchange de Criptmoedas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
    
*/