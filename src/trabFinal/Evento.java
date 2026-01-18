package trabFinal;

import java.time.LocalDate;

public class Evento {
    private String nome;
    private String local;
    private LocalDate data;
    private Administrador adm;

    public Evento(String nome, String local, LocalDate data, Administrador adm){
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.adm = adm;
    }

    public Evento() {
        this.nome = "";
        this.local = "";
        this.data = null;
        this.adm = null;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Administrador getADM() {
        return this.adm;
    }
}