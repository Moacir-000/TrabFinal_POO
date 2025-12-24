import java.util.ArrayList;

public class Cliente extends Pessoa {
    private ArrayList<Ingresso> ingressosComprados;

    public ArrayList<Ingresso> getIngressosComprados() {
    return ingressosComprados;
    }
    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        ingressosComprados = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
