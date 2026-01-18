package trabFinal;

public class Administrador extends Pessoa{
    public Administrador(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }

    public String getTipo() {
        return "Administrador";
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}