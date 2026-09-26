public class ProdutoPerecivel extends Product {

    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double valor = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            valor *= 0.8;
        }
        return valor;
    }

    @Override
    public String getDescricao() {
        String situacao = diasParaVencer <= 3 ? " [PRÓXIMO DO VENCIMENTO - 20% desconto]" : "";
        return super.getDescricao() + String.format(" | Vence em: %2d dia(s)", diasParaVencer) + situacao;
    }
}
