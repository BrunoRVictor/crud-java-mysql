package sistema.model;

public class Cliente {
    private String nome;
    private int id;

    public Cliente (String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[- ID: " + id + " | Nome: " + nome + "]";
    }
}
