public class Pessoa {
    private String nome;
    private int idade;
    private Double salario;

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Double getSalario() {
        return this.salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return String.format("\nNome=%s Idade=%d Salario=%.2f\n\n", nome, idade, salario);
    }
}