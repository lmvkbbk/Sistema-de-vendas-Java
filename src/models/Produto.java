package models;

import database.*;
import java.sql.*;
import java.util.Scanner;

public class Produto {



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

    private void mostrarDadosProduto(int id, String nome, String categoria, int quantidade, double custo, double valor) {
        System.out.printf("ID: %d, Nome: %s, Categoria: %s, Quantidade: %d, Custo: %.2f, Valor: %.2f%n",
                id, nome, categoria, quantidade, custo, valor);
    }

    public void menuPesquisaProduto() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Opções de Pesquisa:");
            System.out.println("1. Nome");
            System.out.println("2. ID");
            System.out.println("3. Categoria");
            System.out.println("4. Quantidade");
            System.out.println("5. Custo");
            System.out.println("6. Valor");
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            opcao  = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    pesquisarPorNome(nome);
                    break;
                case 2:
                    System.out.print("Digite o ID: ");
                    int id = scanner.nextInt();
                    pesquisarPorId(id);
                    break;
                case 3:
                    System.out.print("Digite a categoria: ");
                    String categoria = scanner.nextLine();
                    pesquisarPorCategoria(categoria);
                    break;
                case 4:
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();
                    pesquisarPorQuantidade(quantidade);
                    break;
                case 5:
                    System.out.print("Digite o custo: ");
                    double custo = scanner.nextDouble();
                    pesquisarPorCusto(custo);
                    break;
                case 6:
                    System.out.print("Digite o valor: ");
                    double valor = scanner.nextDouble();
                    pesquisarPorValor(valor);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao != 7);
    }

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

    public void menuInsercaoProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o custo do produto: ");
        double custo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o valor do produto: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        adicionarProdutoCompleto(nome, categoria, quantidade, custo, valor);
    }

    public static void adicionarProdutoCompleto(String nome, String categoria, int quantidade, double custo, double valor) {
        String sql = "INSERT INTO Produto (nome, categoria, quantidade, custo, valor) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, categoria);
            statement.setInt(3, quantidade);
            statement.setDouble(4, custo);
            statement.setDouble(5, valor);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Produto adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar adicionar um produto: " + ex.getMessage());
        }
    }

    public void removerPorId(int id) {
        String sql = "DELETE FROM Produto WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o critério fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao ao tentar remover o produto: " + ex.getMessage());
        }
    }

    public void removerPorNome(String nome) {
        String sql = "DELETE FROM Produto WHERE nome = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,nome);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o critério fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao ao tentar remover o produto: " + ex.getMessage());
        }
    }

    public void removerPorCategoria(String categoria) {
        String sql = "DELETE FROM Produto WHERE categoria = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,categoria);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o critério fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao ao tentar remover o produto: " + ex.getMessage());
        }
    }

    public void removerPorCusto(double custo) {
        String sql = "DELETE FROM Produto WHERE custo = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, custo);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o critério fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao ao tentar remover o produto: " + ex.getMessage());
        }
    }

    public void removerPorValor(double valor) {
        String sql = "DELETE FROM Produto WHERE valor = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, valor);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o critério fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao ao tentar remover o produto: " + ex.getMessage());
        }
    }

    public void menuRemocaoProduto() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Opções de Remoção:");
            System.out.println("1. Remover por ID");
            System.out.println("2. Remover por Nome");
            System.out.println("3. Remover por Categoria");
            System.out.println("4. Remover por Custo");
            System.out.println("5. Remover por Valor");
            System.out.println("6. Sair");


            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do produto: ");
                    int id = scanner.nextInt();
                    removerPorId(id);
                    break;
                case 2:
                    System.out.print("Digite o Nome do produto: ");
                    String nome = scanner.nextLine();
                    removerPorNome(nome);
                    break;
                case 3:
                    System.out.print("Digite a Categoria do produto: ");
                    String categoria = scanner.nextLine();
                    removerPorCategoria(categoria);
                    break;
                case 4:
                    System.out.print("Digite o Custo do produto: ");
                    double custo = scanner.nextDouble();
                    removerPorCusto(custo);
                    break;
                case 5:
                    System.out.print("Digite o Valor do produto: ");
                    double valor = scanner.nextDouble();
                    removerPorValor(valor);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao != 6);
    }

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
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

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
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

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
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

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
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

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
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

    public void menuAtualizacaoProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int opcao;
        do {
            System.out.println("Escolha o campo que deseja atualizar:");
            System.out.println("1. Nome");
            System.out.println("2. Categoria");
            System.out.println("3. Quantidade");
            System.out.println("4. Custo");
            System.out.println("5. Valor");
            System.out.println("6. Sair");


            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    String nome = scanner.nextLine();
                    atualizarNome(id, nome);
                    break;
                case 2:
                    System.out.print("Digite a nova categoria: ");
                    String categoria = scanner.nextLine();
                    atualizarCategoria(id, categoria);
                    break;
                case 3:
                    System.out.print("Digite a nova quantidade: ");
                    int quantidade = scanner.nextInt();
                    atualizarQuantidade(id, quantidade);
                    break;
                case 4:
                    System.out.print("Digite o novo custo: ");
                    double custo = scanner.nextDouble();
                    atualizarCusto(id, custo);
                    break;
                case 5:
                    System.out.print("Digite o novo valor: ");
                    double valor = scanner.nextDouble();
                    atualizarValor(id, valor);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao != 6);
    }
}
