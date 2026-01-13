import java.io.*;
import java.util.ArrayList;

public class ArquivoUtil {
    // =========================
    // ESCRITA
    // =========================
    public static void salvarVenda(String texto) {

        File arq = new File("src/dadosVendas.txt");

        try {
            FileOutputStream gravador = new FileOutputStream(arq, true);
            OutputStreamWriter conversor = new OutputStreamWriter(gravador);
            BufferedWriter gravadorBuff = new BufferedWriter(conversor);

            gravadorBuff.write(texto);
            gravadorBuff.newLine();

            gravadorBuff.close();
            gravador.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // =========================
    // LEITURA
    // =========================
    public static ArrayList<String> listarVendas() {

        ArrayList<String> vendas = new ArrayList<>();
        File arq = new File("src/dadosVendas.txt");

        if (arq.isFile() && arq.canRead()) {
            try {
                FileInputStream leitor = new FileInputStream(arq);
                InputStreamReader conversor = new InputStreamReader(leitor);
                BufferedReader leitorBuff = new BufferedReader(conversor);

                String linha = leitorBuff.readLine();
                while (linha != null) {
                    vendas.add(linha);
                    linha = leitorBuff.readLine();
                }

                leitorBuff.close();
                leitor.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Arquivo não encontrado: " + arq.getAbsolutePath());
        }

        return vendas;
    }
}
