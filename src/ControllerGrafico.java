import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerGrafico {

    @FXML
    private Text contato_menuBar;

    @FXML
    private Text simulacao_menuBar;

    @FXML
    private BarChart<String, Number> grafico;

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
        try (Connection connection = connectToDatabase()) {
            String query = "SELECT * FROM historico WHERE id_usuario = ? ORDER BY data_criacao";

            try (var statement = connection.prepareStatement(query)) {
                statement.setInt(1, Sessao.getIdUsuario());

                try (var resultSet = statement.executeQuery()) {
                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    series.setName("Variação de IceCoins");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    int contador = 1;

                    while (resultSet.next()) {
                        String dataString = resultSet.getString("data_criacao");
                        double valor = resultSet.getDouble("valor");

                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime dataHora = LocalDateTime.parse(dataString, dateTimeFormatter);
                        String dataFormatada = dataHora.toLocalDate().format(formatter);

                        String rotulo = dataFormatada + " (" + contador + ")";
                        contador++;

                        series.getData().add(new XYChart.Data<>(rotulo, valor));
                    }

                    grafico.getData().clear();
                    grafico.getData().add(series);

                    grafico.lookupAll(".chart-bar").forEach(node -> node.setStyle("-fx-bar-fill: #1a2d41;"));
                    grafico.lookupAll(".chart-legend-item-text").forEach(node -> node.setStyle("-fx-text-fill: white;"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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