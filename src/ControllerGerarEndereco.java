import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerGerarEndereco {

    @FXML
    private Text contato_menuBar;

    @FXML
    private TextField endereco;

    @FXML
    private Text carteira_menuBar;

    private Stage stage;
    private Scene scene;

    private Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/icecoin_db";
        String user = "root";
        String password = "";
    
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    public void initialize() {
        Conta conta = new Conta(10);
        endereco.setText(String.valueOf(conta.getIdConta()));

        String sql = "INSERT INTO contas(endereco, id_usuario, saldo) VALUES (?, ?, ?)";
        
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, conta.getIdConta());
            stmt.setString(2, String.valueOf(Sessao.getIdUsuario()));
            stmt.setString(3, String.valueOf(conta.getSaldoInicial()));
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaContato(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("contato.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void irParaCarteira(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("carteira.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}