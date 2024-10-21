package models;

import database.*;
import java.sql.*;
import java.util.Scanner;

public class Cliente {

    public void mostrarTodosClientes() {
        String sql = "SELECT * FROM Cliente";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro ao mostrar todos Clientes :"+ e.getMessage());
        }
    }

    private void exibirResultados(PreparedStatement statement) throws SQLException {
        try (ResultSet rsCliente = statement.executeQuery()) {
            while (rsCliente.next()) {
                int id = rsCliente.getInt("id");
                String nome = rsCliente.getString("nome");
                String cpf = rsCliente.getString("cpf");
                Date dataNascimento = rsCliente.getDate("data_nascimento");
                String email = rsCliente.getString("email");
                String telefone = rsCliente.getString("telefone");

                // Chamar metodo de exibição para cada registro
                mostrarDadosCliente(id, nome, cpf, dataNascimento, email, telefone);
            }
        }
    }

    private void mostrarDadosCliente(int id, String nome, String cpf, Date dataNascimento, String email, String telefone) {
        System.out.printf("%-3d | %-15s | %-11s | %-10s | %-30s | %-15s%n", id, nome, cpf, dataNascimento, email, telefone);
    }

    public void menuPesquisaCliente() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Opções de Pesquisa:");
            System.out.println("1. Nome");
            System.out.println("2. ID");
            System.out.println("3. CPF");
            System.out.println("4. E-mail");
            System.out.println("5. Data de Nascimento");
            System.out.println("6. Telefone");
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
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
                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    pesquisarPorCpf(cpf);
                    break;
                case 4:
                    System.out.print("Digite o e-mail: ");
                    String email = scanner.nextLine();
                    pesquisarPorEmail(email);
                    break;
                case 5:
                    System.out.print("Digite a data de nascimento (yyyy-mm-dd): ");
                    String dataStr = scanner.nextLine();
                    Date dataNascimento = Date.valueOf(dataStr);
                    pesquisarPorDataNascimento(dataNascimento);
                    break;
                case 6:
                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();
                    pesquisarPorTelefone(telefone);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao != 7);
    }

    public void pesquisarPorNome(String nome) {
        String sql = "SELECT * FROM Cliente WHERE nome LIKE ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome + "%");
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar o Cliente nome: "+ nome);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void pesquisarPorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar o Cliente Id: "+ id);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void pesquisarPorCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar o Cliente cpf: "+ cpf);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void pesquisarPorEmail(String email) {
        String sql = "SELECT * FROM Cliente WHERE email = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar o Cliente email: "+ email);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void pesquisarPorDataNascimento(Date dataNascimento) {
        String sql = "SELECT * FROM Cliente WHERE data_nascimento = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, dataNascimento);
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar o Cliente data: "+ dataNascimento);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void pesquisarPorTelefone(String telefone) {
        String sql = "SELECT * FROM Cliente WHERE telefone = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, telefone);
            exibirResultados(statement);
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo numero: "+ telefone);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void menuInsercaoCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite a data de nascimento (yyyy-mm-dd): ");
        String dataStr = scanner.nextLine();
        Date dataNascimento = Date.valueOf(dataStr);

        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        adicionarClienteCompleto(nome, cpf, dataNascimento, email, telefone);
    }

    public void adicionarClienteCompleto(String nome, String cpf, java.sql.Date dataNascimento, String email, String telefone) {
        String sql = "INSERT INTO Cliente (nome, cpf, data_nascimento, email, telefone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setDate(3, dataNascimento);
            statement.setString(4, email);
            statement.setString(5, telefone);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente adicionado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo numero: "+ telefone);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void menuRemocaoCliente() {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Opções de Remoção:");
            System.out.println("1. Remover por ID");
            System.out.println("2. Remover por Nome");
            System.out.println("3. Remover por CPF");
            System.out.println("4. Remover por E-mail");
            System.out.println("5. Remover por Data de Nascimento");
            System.out.println("6. Remover por Telefone");
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do cliente: ");
                    int id = scanner.nextInt();
                    removerPorId(id);
                    break;
                case 2:
                    System.out.print("Digite o Nome do cliente: ");
                    String nome = scanner.nextLine();
                    removerPorNome(nome);
                    break;
                case 3:
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.nextLine();
                    removerPorCpf(cpf);
                    break;
                case 4:
                    System.out.print("Digite o E-mail do cliente: ");
                    String email = scanner.nextLine();
                    removerPorEmail(email);
                    break;
                case 5:
                    System.out.print("Digite a Data de Nascimento (yyyy-mm-dd) do cliente: ");
                    String dataStr = scanner.nextLine();
                    Date dataNascimento = Date.valueOf(dataStr);
                    removerPorDataNascimento(dataNascimento);
                    break;
                case 6:
                    System.out.print("Digite o Telefone do cliente: ");
                    String telefone = scanner.nextLine();
                    removerPorTelefone(telefone);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao != 7);
    }

    public void removerPorId(int id) {
        String sql = "DELETE FROM Cliente WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo Id: "+ id);
            System.out.println("Erro :"+ e.getMessage());
        }
    }


    public void removerPorNome(String nome) {
        String sql = "DELETE FROM Cliente WHERE nome = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo nome: "+ nome);
            System.out.println("Erro :"+ e.getMessage());
        }
    }


    public void removerPorCpf(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo cpf: "+ cpf);
            System.out.println("Erro :"+ e.getMessage());
        }
    }


    public void removerPorEmail(String email) {
        String sql = "DELETE FROM Cliente WHERE email = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo email: "+ email);
            System.out.println("Erro :"+ e.getMessage());
        }
    }


    public void removerPorDataNascimento(Date dataNascimento) {
        String sql = "DELETE FROM Cliente WHERE data_nascimento = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, dataNascimento);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo data: "+ dataNascimento);
            System.out.println("Erro :"+ e.getMessage());
        }
    }


    public void removerPorTelefone(String telefone) {
        String sql = "DELETE FROM Cliente WHERE telefone = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, telefone);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro em Remover o Cliente pelo numero: "+ telefone);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void menuAtualizacaoCliente() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        int opcao;
        do {
            System.out.println("Escolha o campo que deseja atualizar:");
            System.out.println("1. Nome");
            System.out.println("2. CPF");
            System.out.println("3. Data de Nascimento");
            System.out.println("4. Email");
            System.out.println("5. Telefone");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    String nome = sc.nextLine();
                    atualizarNome(id, nome);
                    break;
                case 2:
                    System.out.print("Digite o novo CPF: ");
                    String cpf = sc.nextLine();
                    atualizarCpf(id, cpf);
                    break;
                case 3:
                    System.out.print("Digite a nova data de nascimento (yyyy-mm-dd): ");
                    String dataStr = sc.nextLine();
                    Date dataNascimento = Date.valueOf(dataStr);
                    atualizarDataNascimento(id, dataNascimento);
                    break;
                case 4:
                    System.out.print("Digite o novo email: ");
                    String email = sc.nextLine();
                    atualizarEmail(id, email);
                    break;
                case 5:
                    System.out.print("Digite o novo telefone: ");
                    String telefone = sc.nextLine();
                    atualizarTelefone(id, telefone);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }while(opcao != 6);
    }

    public void atualizarNome(int id, String novoNome) {
        String sql = "UPDATE Cliente SET nome = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoNome);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Nome atualizado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro em atulizar o nome para: "+ novoNome);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void atualizarCpf(int id, String novoCpf) {
        String sql = "UPDATE Cliente SET cpf = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoCpf);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("CPF atualizado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro em atualizar o cpf para: "+ novoCpf);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void atualizarDataNascimento(int id, Date novaData) {
        String sql = "UPDATE Cliente SET data_nascimento = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, novaData);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data de Nascimento atualizada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro em atualizar data de nascimento para: "+ novaData);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void atualizarEmail(int id, String novoEmail) {
        String sql = "UPDATE Cliente SET email = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoEmail);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Email atualizado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro em atulizar o email para: "+ novoEmail);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

    public void atualizarTelefone(int id, String novoTelefone) {
        String sql = "UPDATE Cliente SET telefone = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoTelefone);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Telefone atualizado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro em atualizar o numero para: "+ novoTelefone);
            System.out.println("Erro :"+ e.getMessage());
        }
    }

}
