import java.io.FileWriter;
import java.io.IOException;

public class ArquivoUtil {
    public static void salvarVenda(String txt ) throws IOException {
        FileWriter fw = new FileWriter("VendasdeIngressos.txt", true);
        fw.write(txt + "\n");
        fw.close();
    }
}
