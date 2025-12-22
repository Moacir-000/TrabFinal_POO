public class IngressoComum extends Ingresso{
    private double preco;

    public IngressoComum(String codigo, Evento evento, double preco) {
        super(codigo, evento);
        this.preco = preco;
    }

    @Override
    public double calcularPreco(){
        return this.preco;
    }
}