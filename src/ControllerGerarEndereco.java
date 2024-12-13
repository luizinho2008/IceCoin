import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String url = MySQL.getUrl();
        String user = MySQL.getUser();
        String password = MySQL.getPassword();
    
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    public void initialize() {
        String sqlVerificaUsuario = "SELECT COUNT(*) FROM contas";
        String sqlInserirConta = "INSERT INTO contas(endereco, id_usuario, saldo) VALUES (?, ?, ?)";
        String sqlInserirBlocoGenese = "INSERT INTO blockchain(hash_bloco_anterior, hash_bloco, remetente, destinatario, valor) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmtVerificaUsuario = conn.prepareStatement(sqlVerificaUsuario)) {

            ResultSet rs = stmtVerificaUsuario.executeQuery();

            int usuarioCount = 0;
            if (rs.next()) {
                usuarioCount = rs.getInt(1);
            }

            double saldoInicial = (usuarioCount == 0) ? 20000 : 0;
            Conta conta = new Conta(saldoInicial);
            endereco.setText(String.valueOf(conta.getIdConta()));

            try (PreparedStatement stmtConta = conn.prepareStatement(sqlInserirConta)) {
                stmtConta.setString(1, conta.getIdConta());
                stmtConta.setString(2, String.valueOf(Sessao.getIdUsuario()));
                stmtConta.setDouble(3, saldoInicial);

                stmtConta.executeUpdate();
            }

            if(saldoInicial == 20000) {
                try (PreparedStatement stmtBloco = conn.prepareStatement(sqlInserirBlocoGenese)) {
                    stmtBloco.setString(1, "0");
                    stmtBloco.setString(2, "0");
                    stmtBloco.setString(3, null);
                    stmtBloco.setString(4, null);
                    stmtBloco.setDouble(5, saldoInicial);
    
                    stmtBloco.executeUpdate();
                }
            }

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