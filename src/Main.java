// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
// import java.security.MessageDigest;
// import java.util.Date;

// class App {
//     public static void main(String[] args) {
//         Blockchain minhaBlockchain = new Blockchain();

//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Digite seu nome: ");
//         String nome = scanner.nextLine();

//         System.out.print("Para quem deseja fazer o pagamento? ");
//         String destinatario = scanner.nextLine();

//         System.out.print("Quantos reais deseja pagar? ");
//         double valor = scanner.nextDouble();

//         System.out.println("\nResumo do Pagamento:");
//         System.out.println("Nome: " + nome);
//         System.out.println("Destinatário: " + destinatario);
//         System.out.println("Valor: R$ " + valor);

//         String dados = nome + destinatario + String.valueOf(valor);
//         minhaBlockchain.adicionarBloco(dados);

//         System.out.println("Blockchain:");
//         minhaBlockchain.exibirBlockchain();
//         System.out.println("Integridade da Blockchain: " + minhaBlockchain.verificarIntegridade());

//         scanner.close();
//     }
// }



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("cadastro2.fxml"));
        primaryStage.setTitle("Exchange de Criptmoedas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}