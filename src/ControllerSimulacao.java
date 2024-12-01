import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerSimulacao {

    @FXML
    private Label converterValor;

    @FXML
    private TextField valorReal;


    private Stage stage;
    private Scene scene;

    @FXML
    private ImageView usuario_menuBar;

    @FXML
    void converter(ActionEvent event) {

        String valor = valorReal.getText();

        try {
            Double convertido = Double.parseDouble(valor);
            
            Double valorMoeda = IceCoin.getValor();

            convertido = convertido / valorMoeda;

            valor = String.valueOf(convertido);

            converterValor.setText(valor);

        } catch (NumberFormatException e) {
            System.out.println("Erro: O texto inserido não é um número válido.");
        }

    }

    @FXML
    void irParaCarteira(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("carteira.fxml")); // Carrega a próxima tela da carteira.
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaContato(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("contato.fxml")); // Carrega a próxima tela da carteira.
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaUsuario(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("usuario.fxml")); 
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

}
