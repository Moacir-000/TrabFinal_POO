import java.util.ArrayList;

public class Evento {
    private String nome;
    private String local;
    private String data;
    private ArrayList<Ingresso> ingressos;

    public Evento(String nome, String local, String data){
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.ingressos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
