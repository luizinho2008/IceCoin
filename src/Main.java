// Blockchain minhaBlockchain = new Blockchain();

        // minhaBlockchain.adicionarBloco("Primeiro bloco de dados");
        // minhaBlockchain.adicionarBloco("Segundo bloco de dados");
        // minhaBlockchain.adicionarBloco("Terceiro bloco de dados");
        // minhaBlockchain.adicionarBloco("Quarto bloco de dados");

        // System.out.println("Blockchain:");
        // minhaBlockchain.exibirBlockchain();

        // System.out.println("Integridade da Blockchain: " + minhaBlockchain.verificarIntegridade());

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("telainicial.fxml"));
        primaryStage.setTitle("Exchange de Criptmoedas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}