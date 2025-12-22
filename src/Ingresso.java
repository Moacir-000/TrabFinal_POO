public abstract class Ingresso implements Vendavel{
    protected static int totalIngressosVendidos;
    protected String codigo;
    protected Evento evento;

    public Ingresso(String codigo, Evento evento) {
        this.codigo = codigo;
        this.evento = evento;
        totalIngressosVendidos++;
    }
}
