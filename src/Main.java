// Classe principal para testar a blockchain
public class Main {
    public static void main(String[] args) {
        Blockchain minhaBlockchain = new Blockchain();

        minhaBlockchain.adicionarBloco("Primeiro bloco de dados");
        minhaBlockchain.adicionarBloco("Segundo bloco de dados");
        minhaBlockchain.adicionarBloco("Terceiro bloco de dados");
        minhaBlockchain.adicionarBloco("Quarto bloco de dados");

        System.out.println("Blockchain:");
        minhaBlockchain.exibirBlockchain();

        System.out.println("Integridade da Blockchain: " + minhaBlockchain.verificarIntegridade());
    }
}
