package trabFinal;

public abstract class Ingresso implements Vendavel{
    protected static int totalIngressosVendidos;
    protected Evento evento;

    public Ingresso(Evento evento) {
        this.evento = evento;
        totalIngressosVendidos++;
    }

    public Evento getEvento() {
        return evento;
    }
}