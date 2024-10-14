import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RemocaoProduto {

    // Metodo para remover produto por ID
    public void removerPorId(int id) {
        String sql = "DELETE FROM Produto WHERE id = ?";
        executarRemocao(sql, id);
    }

    // Metodo para remover produto por Nome
    public void removerPorNome(String nome) {
        String sql = "DELETE FROM Produto WHERE nome = ?";
        executarRemocao(sql, nome);
    }

    // Metodo para remover produto por Categoria
    public void removerPorCategoria(String categoria) {
        String sql = "DELETE FROM Produto WHERE categoria = ?";
        executarRemocao(sql, categoria);
    }

    // Metodo para remover produto por Custo
    public void removerPorCusto(double custo) {
        String sql = "DELETE FROM Produto WHERE custo = ?";
        executarRemocao(sql, custo);
    }

    // Metodo para remover produto por Valor
    public void removerPorValor(double valor) {
        String sql = "DELETE FROM Produto WHERE valor = ?";
        executarRemocao(sql, valor);
    }

    // Metodo para executar a remoção
    private void executarRemocao(String sql, Object valor) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Configurando o valor dinamicamente
            if (valor instanceof Integer) {
                statement.setInt(1, (Integer) valor);
            } else if (valor instanceof String) {
                statement.setString(1, (String) valor);
            } else if (valor instanceof Double) {
                statement.setDouble(1, (Double) valor);
            }

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu de Remoção
    public static void menuRemocaoProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opções de Remoção:");
        System.out.println("1. Remover por ID");
        System.out.println("2. Remover por Nome");
        System.out.println("3. Remover por Categoria");
        System.out.println("4. Remover por Custo");
        System.out.println("5. Remover por Valor");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        RemocaoProduto remocao = new RemocaoProduto();

        switch (opcao) {
            case 1:
                System.out.print("Digite o ID do produto: ");
                int id = scanner.nextInt();
                remocao.removerPorId(id);
                break;
            case 2:
                System.out.print("Digite o Nome do produto: ");
                String nome = scanner.nextLine();
                remocao.removerPorNome(nome);
                break;
            case 3:
                System.out.print("Digite a Categoria do produto: ");
                String categoria = scanner.nextLine();
                remocao.removerPorCategoria(categoria);
                break;
            case 4:
                System.out.print("Digite o Custo do produto: ");
                double custo = scanner.nextDouble();
                remocao.removerPorCusto(custo);
                break;
            case 5:
                System.out.print("Digite o Valor do produto: ");
                double valor = scanner.nextDouble();
                remocao.removerPorValor(valor);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}

