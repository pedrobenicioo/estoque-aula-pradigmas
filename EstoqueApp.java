import java.util.Locale;

public class EstoqueApp {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Estoque estoque = new Estoque();

        System.out.println("===== CADASTRO DE PRODUTOS =====");

        try {
            Product comum1 = new ProdutoComum("Caderno Universitário", 25.90, 50);
            Product comum2 = new ProdutoComum("Caneta Esferográfica", 2.50, 200);

            Product perecivel1 = new ProdutoPerecivel("Iogurte Natural", 6.00, 40, 2);
            Product perecivel2 = new ProdutoPerecivel("Queijo Minas", 18.00, 15, 10);

            estoque.adicionarProduto(comum1);
            estoque.adicionarProduto(comum2);
            estoque.adicionarProduto(perecivel1);
            estoque.adicionarProduto(perecivel2);

            System.out.println("Produtos cadastrados com sucesso:");
            estoque.listarProdutos();

        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }

        System.out.println();
        System.out.println("===== TENTATIVA DE CADASTRO INVÁLIDO =====");
        try {
            Product invalido = new ProdutoComum("Produto Fantasma", 10.0, -5);
            estoque.adicionarProduto(invalido);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Falha esperada capturada -> " + e.getMessage());
        }

        System.out.println();
        System.out.println("===== VENDAS =====");

        try {
            System.out.println("Vendendo 10 unidades do produto [0]...");
            estoque.venderProduto(0, 10);
            System.out.println("Venda realizada com sucesso!");
            System.out.println("Situação atual -> " + estoque.getProduto(0).getDescricao());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Erro na venda: " + e.getMessage());
        }

        System.out.println();

        try {
            System.out.println("Tentando vender 1000 unidades do produto [1] (Caneta Esferográfica)...");
            estoque.venderProduto(1, 1000);
            System.out.println("Venda realizada com sucesso!");
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Falha esperada capturada -> " + e.getMessage());
        }

        System.out.println();
        System.out.println("===== DEMONSTRAÇÃO DA ORDEM DOS CATCHES =====");
        try {
            Product testeInvalido = new ProdutoComum("Produto Teste", 5.0, -1);
            estoque.adicionarProduto(testeInvalido);
            estoque.venderProduto(1, 1000);
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Produto indisponível capturado -> " + e.getMessage());
        } catch (EstoqueException e) {
            System.out.println("Erro genérico de estoque capturado -> " + e.getMessage());
        }

        System.out.println();
        System.out.println("===== SOBRECARGA aplicarDesconto() =====");
        Product queijo = estoque.getProduto(3);
        System.out.println("Antes do desconto -> " + queijo.getDescricao());
        queijo.aplicarDesconto(10);
        System.out.println("Depois de 10% de desconto -> " + queijo.getDescricao());
        queijo.aplicarDesconto(50, 2.0);
        System.out.println("Depois de 50% de desconto (máx. R$2,00) -> " + queijo.getDescricao());

        System.out.println();
        System.out.println("===== ESTADO FINAL DO ESTOQUE =====");
        estoque.listarProdutos();

        System.out.println();
        System.out.printf("VALOR TOTAL DO ESTOQUE: R$ %.2f%n", estoque.calcularValorTotalEstoque());
    }
}
