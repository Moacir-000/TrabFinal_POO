package trabFinal;

public abstract class Pessoa {
    public String nome;
    public String cpf;
    public String email;

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    //Sobrecarga de método
    public Pessoa() {
        this.nome = "";
        this.cpf = "";
        this.email = "";
    }

    public abstract String getTipo();
}
