import java.util.ArrayList;

public class Cliente extends Pessoa {
    private ArrayList<Ingresso> ingressosComprados;

    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
        ingressosComprados = new ArrayList<>();
    }

    public ArrayList<Ingresso> getIngressosComprados() {
        return ingressosComprados;
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }
}
