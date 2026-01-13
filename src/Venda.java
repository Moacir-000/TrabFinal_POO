import java.util.ArrayList;

public class Venda {

    public void realizarVenda(Cliente cli, Ingresso ing) {

        cli.getIngressosComprados().add(ing);

        String registro = montarRegistro(cli, ing);
        ArquivoUtil.salvarVenda(registro);
    }

    public void realizarVenda(Cliente cli, ArrayList<Ingresso> ingressos) {

        cli.getIngressosComprados().addAll(ingressos);

        for (Ingresso ing : ingressos) {
            String registro = montarRegistro(cli, ing);
            ArquivoUtil.salvarVenda(registro);
        }
    }

    private String montarRegistro(Cliente cli, Ingresso ing) {
        return "Cliente: " + cli.nome
                + " | Ingresso: " + ing.getClass().getSimpleName()
                + " | Valor: R$ " + ing.calcularPreco();
    }
}
