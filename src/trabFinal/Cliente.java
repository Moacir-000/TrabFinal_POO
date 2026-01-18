package trabFinal;

public class Cliente extends Pessoa {
    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }

    @Override
    public String getTipo() {
        return "Cliente";
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