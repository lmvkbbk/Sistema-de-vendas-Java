import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsultaCliente {

    public static void menuPesquisaCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opções de Pesquisa:");
        System.out.println("1. Nome");
        System.out.println("2. ID");
        System.out.println("3. CPF");
        System.out.println("4. E-mail");
        System.out.println("5. Data de Nascimento");
        System.out.println("6. Telefone");
        System.out.println("7. Mostrar Todos os Clientes");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        ConsultaCliente consulta = new ConsultaCliente();

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
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine();
                consulta.pesquisarPorCpf(cpf);
                break;
            case 4:
                System.out.print("Digite o e-mail: ");
                String email = scanner.nextLine();
                consulta.pesquisarPorEmail(email);
                break;
            case 5:
                System.out.print("Digite a data de nascimento (yyyy-mm-dd): ");
                String dataStr = scanner.nextLine();
                Date dataNascimento = Date.valueOf(dataStr);
                consulta.pesquisarPorDataNascimento(dataNascimento);
                break;
            case 6:
                System.out.print("Digite o telefone: ");
                String telefone = scanner.nextLine();
                consulta.pesquisarPorTelefone(telefone);
                break;
            case 7:
                consulta.mostrarTodosClientes();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Metodo para mostrar todos os clientes
    public void mostrarTodosClientes() {
        String sql = "SELECT * FROM Cliente";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
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

    // Metodo para mostrar os dados de um cliente formatados
    private void mostrarDadosCliente(int id, String nome, String cpf, Date dataNascimento, String email, String telefone) {
        System.out.printf("%-3d | %-15s | %-11s | %-10s | %-30s | %-15s%n", id, nome, cpf, dataNascimento, email, telefone);
    }

    // Metodo para pesquisa por Nome
    public void pesquisarPorNome(String nome) {
        String sql = "SELECT * FROM Cliente WHERE nome LIKE ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome + "%");
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para pesquisa por ID
    public void pesquisarPorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para pesquisa por CPF
    public void pesquisarPorCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpf);
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para pesquisa por E-mail
    public void pesquisarPorEmail(String email) {
        String sql = "SELECT * FROM Cliente WHERE email = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para pesquisa por Data de Nascimento
    public void pesquisarPorDataNascimento(Date dataNascimento) {
        String sql = "SELECT * FROM Cliente WHERE data_nascimento = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, dataNascimento);
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para pesquisa por Telefone
    public void pesquisarPorTelefone(String telefone) {
        String sql = "SELECT * FROM Cliente WHERE telefone = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, telefone);
            exibirResultados(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
