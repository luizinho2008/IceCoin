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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerSeusHashs {

    @FXML
    private ScrollPane scrollPane;

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
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        try (Connection conn = connectToDatabase()) {
            String query = "SELECT * FROM contas WHERE id_usuario = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Sessao.getIdUsuario());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String endereco = rs.getString("endereco");
                String saldo = rs.getString("saldo");
                javafx.scene.control.TextField textField = new javafx.scene.control.TextField(endereco + " - IC$ " + saldo);
                textField.setEditable(false);
                textField.setPrefWidth(650);
                textField.setStyle("-fx-font-size: 20px; -fx-pref-height: 30px; -fx-font-weight: bold; -fx-text-fill: black;");
                vbox.getChildren().add(textField);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            vbox.getChildren().add(new Text("Erro ao carregar dados!"));
        }
        scrollPane.setContent(vbox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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
}