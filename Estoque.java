import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        validarIndice(indice);
        produtos.get(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0.0;
        for (Product p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public void listarProdutos() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("[" + i + "] " + produtos.get(i).getDescricao());
        }
    }

    public Product getProduto(int indice) {
        validarIndice(indice);
        return produtos.get(indice);
    }

    public int quantidadeDeProdutos() {
        return produtos.size();
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= produtos.size()) {
            throw new IndexOutOfBoundsException("Índice de produto inválido: " + indice);
        }
    }
}
