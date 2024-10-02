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
    void redirecionarInicio(ActionEvent event) {
        try {
            Parent novaTela = FXMLLoader.load(getClass().getResource("telainicial.fxml"));
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cadastrar() {
        if (senhaInput.getText().equals(cSenhaInput.getText()) && 
            !usuarioInput.getText().isEmpty() && 
            !usuarios.containsKey(usuarioInput.getText())) {
            
            usuarios.put(usuarioInput.getText(), senhaInput.getText());
            System.out.println("Cadastrado com sucesso no IceCoin");
        } else {
            System.out.println("Falha ao cadastrar usuário no IceCoin");
        }
    }
}