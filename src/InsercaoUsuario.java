import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsercaoUsuario {

    public void menuInsercaoUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        boolean adm;
        while (true) {
            System.out.println("Digite o tipo de Usuario");
            System.out.println("1. para adm");
            System.out.println("2 para fun");
            System.out.print("Escolha: ");
            int escolha;
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    adm = true;
                    break;
                case 2:
                    adm = false;
                    break;
                default:
                    System.out.println("Escolha inválida. Digite 1 para administrador ou 2 para funcionário.");
                    continue;
            }
            break;
        }
        adicionarClienteCompleto(nome, senha, adm);
    }

    public static void adicionarClienteCompleto(String nome, String senha, boolean adm) {
        String sql = "INSERT INTO Usuario (nome, senha, adm) VALUES (?, ?, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, senha);
            statement.setBoolean(3, adm);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar adicionar um cliente: "+ ex.getMessage());
        }
    }
}