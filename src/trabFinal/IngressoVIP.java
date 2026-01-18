package trabFinal;

public class IngressoVIP extends Ingresso{
    private double precoBase;
    private double adicionalVIP;

    public IngressoVIP(Evento evento, double precoBase, double adicionalVIP) {
        super(evento);
        this.precoBase = precoBase;
        this.adicionalVIP = adicionalVIP;
    }

    @Override
    public double calcularPreco() {
        return this.precoBase + this.adicionalVIP;
    }
}