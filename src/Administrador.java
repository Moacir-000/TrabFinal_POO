public class Administrador extends Pessoa{
    public Administrador(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }

    @Override
    public String getTipo() {
        return "Administrador";
    }
}
