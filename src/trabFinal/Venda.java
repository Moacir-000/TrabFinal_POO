package trabFinal;

import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {

    public void realizarVenda(Cliente cli, Ingresso ing) {

        String registro = montarRegistro(cli, ing);
        ArquivoUtil.salvarVenda(registro);
    }

    public void realizarVenda(Cliente cli, ArrayList<Ingresso> ingressos) {

        for (Ingresso ing : ingressos) {
            String registro = montarRegistro(cli, ing);
            ArquivoUtil.salvarVenda(registro);
        }
    }

    private String montarRegistro(Cliente cli, Ingresso ing) {

        Evento evento = ing.getEvento();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "Data/Hora: " + agora.format(formatter)
                + " | Cliente: " + cli.nome
                + " | Evento: " + evento.getNome()
                + " | Local: " + evento.getLocal()
                + " | Data do evento: " + evento.getData().format(fmt)
                + " | Ingresso: " + ing.getClass().getSimpleName()
                + " | Valor: R$ " + String.format("%.2f", ing.calcularPreco());
    }
}