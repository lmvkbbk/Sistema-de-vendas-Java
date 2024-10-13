import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RemocaoCliente {

    // Metodo para remover cliente por ID
    public void removerPorId(int id) {
        String sql = "DELETE FROM Cliente WHERE id = ?";
        executarRemocao(sql, id);
    }

    // Metodo para remover cliente por Nome
    public void removerPorNome(String nome) {
        String sql = "DELETE FROM Cliente WHERE nome = ?";
        executarRemocao(sql, nome);
    }

    // Metodo para remover cliente por CPF
    public void removerPorCpf(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        executarRemocao(sql, cpf);
    }

    // Metodo para remover cliente por E-mail
    public void removerPorEmail(String email) {
        String sql = "DELETE FROM Cliente WHERE email = ?";
        executarRemocao(sql, email);
    }

    // Metodo para remover cliente por Data de Nascimento
    public void removerPorDataNascimento(Date dataNascimento) {
        String sql = "DELETE FROM Cliente WHERE data_nascimento = ?";
        executarRemocao(sql, dataNascimento);
    }

    // Metodo para remover cliente por Telefone
    public void removerPorTelefone(String telefone) {
        String sql = "DELETE FROM Cliente WHERE telefone = ?";
        executarRemocao(sql, telefone);
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
            } else if (valor instanceof Date) {
                statement.setDate(1, (Date) valor);
            }

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o critério fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu de Remoção
    public static void menuRemocao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opções de Remoção:");
        System.out.println("1. Remover por ID");
        System.out.println("2. Remover por Nome");
        System.out.println("3. Remover por CPF");
        System.out.println("4. Remover por E-mail");
        System.out.println("5. Remover por Data de Nascimento");
        System.out.println("6. Remover por Telefone");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        RemocaoCliente remocao = new RemocaoCliente();

        switch (opcao) {
            case 1:
                System.out.print("Digite o ID do cliente: ");
                int id = scanner.nextInt();
                remocao.removerPorId(id);
                break;
            case 2:
                System.out.print("Digite o Nome do cliente: ");
                String nome = scanner.nextLine();
                remocao.removerPorNome(nome);
                break;
            case 3:
                System.out.print("Digite o CPF do cliente: ");
                String cpf = scanner.nextLine();
                remocao.removerPorCpf(cpf);
                break;
            case 4:
                System.out.print("Digite o E-mail do cliente: ");
                String email = scanner.nextLine();
                remocao.removerPorEmail(email);
                break;
            case 5:
                System.out.print("Digite a Data de Nascimento (yyyy-mm-dd) do cliente: ");
                String dataStr = scanner.nextLine();
                Date dataNascimento = Date.valueOf(dataStr);
                remocao.removerPorDataNascimento(dataNascimento);
                break;
            case 6:
                System.out.print("Digite o Telefone do cliente: ");
                String telefone = scanner.nextLine();
                remocao.removerPorTelefone(telefone);
                break;
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}
