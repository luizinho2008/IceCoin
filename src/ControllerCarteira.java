import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    @FXML
    private Label saldoicecoin;

    @FXML
    private Label saldoreais;

    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button gerar_endereco;

    @FXML
    private ImageView usuario_menuBar;

    private Connection connectToDatabase() throws SQLException {
        String url = MySQL.getUrl();
        String user = MySQL.getUser();
        String password = MySQL.getPassword();
    
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    public void initialize() {
        nome.setText(Sessao.getNomeUsuario() + "!");

        String query = "SELECT SUM(saldo) FROM contas WHERE id_usuario = ?";

        try (Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, Sessao.getIdUsuario());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double saldo = rs.getDouble(1);

                if (saldo == 0) {
                    saldoicecoin.setText("Não há contas");
                    saldoreais.setText("Não há contas");
                } else {
                    saldoicecoin.setText("IC$ " + String.format(Locale.US, "%.2f", saldo));
                    saldoreais.setText("R$ " + String.format(Locale.US, "%.2f", saldo * IceCoin.getValor()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            saldoicecoin.setText("Erro ao carregar saldo");
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

    @FXML
    void irParaCotacao(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("cotacao.fxml"));
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


    @FXML
    void gerarEnderecos(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("gerarEndereco.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void transferir(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("transferir.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exibeBlockchain(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("exibeBlockchain.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void seusHashs(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("seusHashs.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}