package trabFinal;

public class IngressoComum extends Ingresso{
    private double preco;

    public IngressoComum(Evento evento, double preco) {
        super(evento);
        this.preco = preco;
    }

    @Override
    public double calcularPreco(){
        return this.preco;
    }
}