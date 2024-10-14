import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizacaoProduto {

    // Metodo para atualizar o nome de um produto pelo ID
    public void atualizarNome(int id, String novoNome) {
        String sql = "UPDATE Produto SET nome = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoNome);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Nome atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para atualizar a categoria de um produto pelo ID
    public void atualizarCategoria(int id, String novaCategoria) {
        String sql = "UPDATE Produto SET categoria = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novaCategoria);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Categoria atualizada com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para atualizar a quantidade de um produto pelo ID
    public void atualizarQuantidade(int id, int novaQuantidade) {
        String sql = "UPDATE Produto SET quantidade = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, novaQuantidade);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Quantidade atualizada com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para atualizar o custo de um produto pelo ID
    public void atualizarCusto(int id, double novoCusto) {
        String sql = "UPDATE Produto SET custo = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, novoCusto);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Custo atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para atualizar o valor de um produto pelo ID
    public void atualizarValor(int id, double novoValor) {
        String sql = "UPDATE Produto SET valor = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, novoValor);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Valor atualizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menuAtualizacaoProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o campo que deseja atualizar:");
        System.out.println("1. Nome");
        System.out.println("2. Categoria");
        System.out.println("3. Quantidade");
        System.out.println("4. Custo");
        System.out.println("5. Valor");

        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após o inteiro

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        AtualizacaoProduto atualizacao = new AtualizacaoProduto();

        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                String nome = scanner.nextLine();
                atualizacao.atualizarNome(id, nome);
                break;
            case 2:
                System.out.print("Digite a nova categoria: ");
                String categoria = scanner.nextLine();
                atualizacao.atualizarCategoria(id, categoria);
                break;
            case 3:
                System.out.print("Digite a nova quantidade: ");
                int quantidade = scanner.nextInt();
                atualizacao.atualizarQuantidade(id, quantidade);
                break;
            case 4:
                System.out.print("Digite o novo custo: ");
                double custo = scanner.nextDouble();
                atualizacao.atualizarCusto(id, custo);
                break;
            case 5:
                System.out.print("Digite o novo valor: ");
                double valor = scanner.nextDouble();
                atualizacao.atualizarValor(id, valor);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
