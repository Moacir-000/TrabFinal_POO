public class IngressoVIP extends Ingresso{
    private double precoBase;
    private double adicionalVIP;

    public IngressoVIP(String codigo, Evento evento, double precoBase, double adicionalVIP) {
        super(codigo, evento);
        this.precoBase = precoBase;
        this.adicionalVIP = adicionalVIP;
    }

    @Override
    public double calcularPreco() {
        return this.precoBase + this.adicionalVIP;
    }
}
