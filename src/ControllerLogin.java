import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLogin {

    @FXML
    private PasswordField senhaInput;

    @FXML
    private TextField usuarioInput;

    @FXML
    void redirecionarBanco(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("carteira.fxml"));
            Parent novaTela = loader.load();
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void redirecionarCadastro(ActionEvent event) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logar(ActionEvent event) {
        if (!ControllerCadastro.usuarios.containsKey(usuarioInput.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("IceCoin informa");
            alert.setHeaderText(null);
            alert.setContentText("Usuário não cadastrado!");
            alert.showAndWait();
        } else {
            if (ControllerCadastro.usuarios.get(usuarioInput.getText()).equals(senhaInput.getText())) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("IceCoin informa");
                alert.setHeaderText(null);
                alert.setContentText("Logado no sistema com sucesso!");
                alert.showAndWait();

                Sessao.setNomeUsuario(usuarioInput.getText());
                redirecionarBanco(event);

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("IceCoin informa");
                alert.setHeaderText(null);
                alert.setContentText("Senha incorreta!");
                alert.showAndWait();
            }
        }
    }
}
