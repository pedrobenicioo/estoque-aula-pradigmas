public abstract class Product implements Vendavel {

    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException(
                    "Preço inválido para o produto \"" + nome + "\": " + preco + " (não pode ser negativo).");
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException(
                    "Quantidade inválida para o produto \"" + nome + "\": " + quantidade + " (não pode ser negativa).");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    protected void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    protected void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format("%-20s | Preço: R$ %8.2f | Quantidade: %4d", nome, preco, quantidade);
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Estoque insuficiente para \"" + nome + "\". Disponível: " + quantidade
                            + ", solicitado: " + quantidadeDesejada + ".");
        }
        quantidade -= quantidadeDesejada;
    }

    public void aplicarDesconto(double percentual) {
        double desconto = preco * (percentual / 100.0);
        preco = Math.max(0, preco - desconto);
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        double desconto = preco * (percentual / 100.0);
        if (desconto > descontoMaximo) {
            desconto = descontoMaximo;
        }
        preco = Math.max(0, preco - desconto);
    }
}
