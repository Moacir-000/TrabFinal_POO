package trabFinal;

import java.io.*;
import java.util.ArrayList;

public class ArquivoUtil {
    public static void salvarVenda(String texto) {
        File arq = new File("src/trabFinal/dadosVendas.txt");

        try {
            FileOutputStream gravador = new FileOutputStream(arq, true);
            OutputStreamWriter conversor = new OutputStreamWriter(gravador);
            BufferedWriter gravadorBuff = new BufferedWriter(conversor);

            gravadorBuff.write(texto + System.lineSeparator());
            gravadorBuff.flush();

            gravadorBuff.close();
            gravador.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ArrayList<String> listarVendas() {

        ArrayList<String> vendas = new ArrayList<>();
        File arq = new File("src/trabFinal/dadosVendas.txt");

        if (arq.isFile() && arq.canRead()) {
            try {
                FileInputStream leitor = new FileInputStream(arq);
                InputStreamReader conversor = new InputStreamReader(leitor);
                BufferedReader leitorBuff = new BufferedReader(conversor);

                String linha;
                while ((linha = leitorBuff.readLine()) != null) {
                    vendas.add(linha);
                }

                leitorBuff.close();
                leitor.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return vendas;
    }

    public static void salvarEvento(String texto) {
        File arq = new File("src/trabFinal/dadosEventos.txt.txt");

        try {
            FileOutputStream gravador = new FileOutputStream(arq, true);
            OutputStreamWriter conversor = new OutputStreamWriter(gravador);
            BufferedWriter gravadorBuff = new BufferedWriter(conversor);

            gravadorBuff.write(texto + System.lineSeparator());
            gravadorBuff.flush();

            gravadorBuff.close();
            gravador.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static ArrayList<String> listarEventos() {

        ArrayList<String> eventos = new ArrayList<>();
        File arq = new File("src/trabFinal/dadosEventos.txt.txt");

        if (arq.isFile() && arq.canRead()) {
            try {
                FileInputStream leitor = new FileInputStream(arq);
                InputStreamReader conversor = new InputStreamReader(leitor);
                BufferedReader leitorBuff = new BufferedReader(conversor);

                String linha;
                while ((linha = leitorBuff.readLine()) != null) {
                    eventos.add(linha);
                }

                leitorBuff.close();
                leitor.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return eventos;
    }
}