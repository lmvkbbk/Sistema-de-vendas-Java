import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RemocaoUsuario {

    public void removerPorId(int id) {
        String sql = "DELETE FROM Usuarios WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Usuario removido com sucesso!");
            } else {
                System.out.println("Nenhum usuario encontrado com o critério fornecido.");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar remover o Usuario: " + ex.getMessage());
        }
    }

    public void menuRemocaoUsuarios() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        removerPorId(id);
    }
}
