import java.sql.*;
import java.util.Scanner;

public class ConsultaProduto {

    public void menuPesquisaProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opções de Pesquisa:");
        System.out.println("1. Nome");
        System.out.println("2. ID");
        System.out.println("3. Categoria");
        System.out.println("4. Quantidade");
        System.out.println("5. Custo");
        System.out.println("6. Valor");
        System.out.println("7. Mostrar Todos os Produtos");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        ConsultaProduto consulta = new ConsultaProduto();

        switch (opcao) {
            case 1:
                System.out.print("Digite o nome: ");
                String nome = scanner.nextLine();
                consulta.pesquisarPorNome(nome);
                break;
            case 2:
                System.out.print("Digite o ID: ");
                int id = scanner.nextInt();
                consulta.pesquisarPorId(id);
                break;
            case 3:
                System.out.print("Digite a categoria: ");
                String categoria = scanner.nextLine();
                consulta.pesquisarPorCategoria(categoria);
                break;
            case 4:
                System.out.print("Digite a quantidade: ");
                int quantidade = scanner.nextInt();
                consulta.pesquisarPorQuantidade(quantidade);
                break;
            case 5:
                System.out.print("Digite o custo: ");
                double custo = scanner.nextDouble();
                consulta.pesquisarPorCusto(custo);
                break;
            case 6:
                System.out.print("Digite o valor: ");
                double valor = scanner.nextDouble();
                consulta.pesquisarPorValor(valor);
                break;
            case 7:
                consulta.mostrarTodosProdutos();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void exibirResultados(PreparedStatement statement) throws SQLException {
        try (ResultSet rsProduto = statement.executeQuery()) {
            while (rsProduto.next()) {
                int id = rsProduto.getInt("id");
                String nome = rsProduto.getString("nome");
                String categoria = rsProduto.getString("categoria");
                int quantidade = rsProduto.getInt("quantidade");
                double custo = rsProduto.getDouble("custo");
                double valor = rsProduto.getDouble("valor");

                // Chamar metodo de exibição para cada registro
                mostrarDadosProduto(id, nome, categoria, quantidade, custo, valor);
            }
        }
    }

    public void mostrarTodosProdutos() {
        String sql = "SELECT * FROM Produto";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar mostrar todos produtos: " + ex.getMessage());
        }
    }

    // Métodos de pesquisa por nome
    public void pesquisarPorNome(String nome) {
        String sql = "SELECT * FROM Produto WHERE nome = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pelo nome: "+ nome);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    // Métodos de pesquisa por ID
    public void pesquisarPorId(int id) {
        String sql = "SELECT * FROM Produto WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pelo id: "+ id);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    // Métodos de pesquisa por categoria
    public void pesquisarPorCategoria(String categoria) {
        String sql = "SELECT * FROM Produto WHERE categoria = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoria);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pela categoria: "+ categoria);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    // Métodos de pesquisa por quantidade
    public void pesquisarPorQuantidade(int quantidade) {
        String sql = "SELECT * FROM Produto WHERE quantidade = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantidade);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pela quantidade: "+ quantidade);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    // Métodos de pesquisa por Custo
    public void pesquisarPorCusto(double custo) {
        String sql = "SELECT * FROM Produto WHERE custo = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, custo);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pelo custo: "+ custo);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    // Métodos de pesquisa por valor
    public void pesquisarPorValor(double valor) {
        String sql = "SELECT * FROM Produto WHERE valor = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, valor);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pelo valor: "+ valor);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    private void mostrarDadosProduto(int id, String nome, String categoria, int quantidade, double custo, double valor) {
        System.out.printf("ID: %d, Nome: %s, Categoria: %s, Quantidade: %d, Custo: %.2f, Valor: %.2f%n",
                id, nome, categoria, quantidade, custo, valor);
    }
}
