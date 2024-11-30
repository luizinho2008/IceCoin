import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerCotacao {

    @FXML
    private Text contato_menuBar;

    @FXML
    private Text simulacao_menuBar;

    @FXML
    private Text valorReais;

    @FXML
    public void initialize() {
        valorReais.setText("R$ " + IceCoin.getValor());
    }

    @FXML
    void irParaContato(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("contato.fxml"));
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
    void irParaSimulacao(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("simulacao.fxml"));
            Parent novaTela = loader.load();
            Scene novaCena = new Scene(novaTela);
            Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();
            palco.setScene(novaCena);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}