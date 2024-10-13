import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizacaoCliente {

    // Metodo para atualizar o nome de um cliente pelo ID
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
            e.printStackTrace();
        }
    }

    // Metodo para atualizar o CPF de um cliente pelo ID
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
            e.printStackTrace();
        }
    }

    // Metodo para atualizar a data de nascimento de um cliente pelo ID
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
            e.printStackTrace();
        }
    }

    // Metodo para atualizar o email de um cliente pelo ID
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
            e.printStackTrace();
        }
    }

    // Metodo para atualizar o telefone de um cliente pelo ID
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
            e.printStackTrace();
        }
    }

    public static void menuAtualizacao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o campo que deseja atualizar:");
        System.out.println("1. Nome");
        System.out.println("2. CPF");
        System.out.println("3. Data de Nascimento");
        System.out.println("4. Email");
        System.out.println("5. Telefone");

        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        AtualizacaoCliente atualizacao = new AtualizacaoCliente();

        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                String nome = scanner.nextLine();
                atualizacao.atualizarNome(id, nome);
                break;
            case 2:
                System.out.print("Digite o novo CPF: ");
                String cpf = scanner.nextLine();
                atualizacao.atualizarCpf(id, cpf);
                break;
            case 3:
                System.out.print("Digite a nova data de nascimento (yyyy-mm-dd): ");
                String dataStr = scanner.nextLine();
                Date dataNascimento = Date.valueOf(dataStr);
                atualizacao.atualizarDataNascimento(id, dataNascimento);
                break;
            case 4:
                System.out.print("Digite o novo email: ");
                String email = scanner.nextLine();
                atualizacao.atualizarEmail(id, email);
                break;
            case 5:
                System.out.print("Digite o novo telefone: ");
                String telefone = scanner.nextLine();
                atualizacao.atualizarTelefone(id, telefone);
                break;
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}
