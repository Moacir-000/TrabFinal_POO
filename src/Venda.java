import java.util.ArrayList;

public class Venda {
    public void realizarVenda(Cliente cli, Ingresso ing) {
        cli.getIngressosComprados().add(ing);
    }
    public void realizarVenda(Cliente cli, ArrayList<Ingresso> ing){
        cli.getIngressosComprados().addAll(ing);
    }
}
