import java.io.IOException;

import javafx.event.ActionEvent;
// import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller1 {

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
}
