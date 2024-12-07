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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerExibeBlockchain {

    @FXML
    private Text blocos;

    @FXML
    private Text carteira_menuBar;

    @FXML
    private Text contato_menuBar;

    private Stage stage;
    private Scene scene;

    private Connection connectToDatabase() throws SQLException {
        String url = MySQL.getUrl();
        String user = MySQL.getUser();
        String password = MySQL.getPassword();
    
        return DriverManager.getConnection(url, user, password);
    }

    @FXML
    private void initialize() {
        String sql = "SELECT * FROM blockchain";
        StringBuilder builder = new StringBuilder();

        try (Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id_bloco");
                String hba = rs.getString("hash_bloco_anterior");
                String hb = rs.getString("hash_bloco");
                String valor = rs.getString("valor");
                
                // Verifica se o ID do bloco é 1
                if (id == 1) {
                    builder.append("Bloco Gênese")
                    .append("\nHash do bloco anterior: ").append(hba)
                        .append("\nHash do bloco: ").append(hb)
                        .append("\n-------------------\n");
                } else {
                    builder.append("Bloco ").append(id)
                        .append("\nHash do bloco anterior: ").append(hba)
                        .append("\nHash do bloco: ").append(hb)
                        .append("\nValor: IC$ ").append(valor)
                        .append("\n-------------------\n");
                }
            }

            blocos.setText(builder.toString());
        } catch (SQLException e) {
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

}