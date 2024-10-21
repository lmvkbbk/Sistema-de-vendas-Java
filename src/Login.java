import database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private boolean autenticacao = false;
    private boolean tipoUsuario;

    public boolean getAutenticacao(){
        return autenticacao;
    }

    public boolean getTipoUsuario(){
        return tipoUsuario;
    }

    public void login(String nome, String senha) {
        String sql = "SELECT adm FROM Usuarios WHERE nome = ? AND senha = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.setString(2, senha);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                this.tipoUsuario = rs.getBoolean("adm");
                this.autenticacao =  true;
                System.out.println("Login bem-sucedido.");
            } else {
                System.out.println("Login falhou. Verifique suas credenciais.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao autenticar usuário: " + ex.getMessage());
        }
    }

    public void menuLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome de Usuario: ");
        String nome = scanner.nextLine();

        System.out.print("Digite sua Senha: ");
        String senha = scanner.nextLine();

        login(nome, senha);
        if (this.autenticacao) {
            System.out.println("Bem-vindo!");
        } else {
            System.out.println("Não foi possível acessar a conta. Tente novamente.");
        }
    }
}