import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private int userId;

    public boolean autenticar(String email, String cpf) {
        String sql = "SELECT id FROM Cliente WHERE email = ? AND cpf = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Salva o ID do usuário se a autenticação for bem-sucedida
                userId = rs.getInt("id");
                System.out.println("Login bem-sucedido. ID do usuário: " + userId);
                return true;
            } else {
                System.out.println("Login falhou. Verifique suas credenciais.");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao autenticar usuário: " + ex.getMessage());
            return false;
        }
    }

    public int getUserId() {
        return userId;
    }

    public static void menuLogin() {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        boolean autenticado = login.autenticar(email, cpf);
        if (autenticado) {
            int userId = login.getUserId();
            System.out.println("Bem-vindo! Seu ID é: " + userId);
        } else {
            System.out.println("Não foi possível acessar a conta. Tente novamente.");
        }
    }
    // adicionar dps a entrada "adminstrador"
}