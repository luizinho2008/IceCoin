import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerContato {

    @FXML
    private Text simulacao_menuBar;

    private Stage stage;
    private Scene scene;
    private Parent root;

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
