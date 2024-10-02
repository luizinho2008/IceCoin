import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Controller3 {

    static HashMap<String, String> usuarios = new HashMap<>();

    @FXML
    private TextField cSenhaInput;

    @FXML
    private TextField senhaInput;

    @FXML
    private TextField usuarioInput;

    @FXML
    void redirecionarLogin(ActionEvent event) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cadastrar(ActionEvent event) {
        if (usuarios.containsKey(usuarioInput.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("IceCoin informa");
            alert.setHeaderText(null);
            alert.setContentText("Esse usuário já tinha sido cadastrado!");
            alert.showAndWait();
        }
        else if (!senhaInput.getText().equals(cSenhaInput.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("IceCoin informa");
            alert.setHeaderText(null);
            alert.setContentText("As senhas não condizem!");
            alert.showAndWait();
        }
        else if (usuarioInput.getText().isEmpty() ||
        senhaInput.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("IceCoin informa");
            alert.setHeaderText(null);
            alert.setContentText("Há campos vazios!");
            alert.showAndWait();
        }
        else {
            usuarios.put(usuarioInput.getText(), senhaInput.getText());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("IceCoin informa");
            alert.setHeaderText(null);
            alert.setContentText("Usuário cadastrado com sucesso!");
            alert.showAndWait();
            redirecionarLogin(event);
        }
    }
}