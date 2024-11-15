import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerCarteira {

    @FXML
    private Text contato_menuBar;

    @FXML
    private Label nome;

    @FXML
    private Label saldo;

    @FXML
    private Text simulacao_menuBar;

    private Stage stage;
    private Scene scene;
    
    @FXML
    public void initialize() {
        nome.setText(Sessao.getNomeUsuario() + "!");
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
    void irParaSimulacao(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("simulacao.fxml")); // Carrega a próxima tela da carteira.
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}