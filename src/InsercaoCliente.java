import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class InsercaoCliente {
    public static void menuInsercaoCliente() {
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

        scanner.close();
    }

    // Metodo para adicionar um cliente completo
    public static void adicionarClienteCompleto(String nome, String cpf, java.sql.Date dataNascimento, String email, String telefone) {
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
            e.printStackTrace();
        }
    }
}