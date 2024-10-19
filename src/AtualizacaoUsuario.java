import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizacaoUsuario{

    public void atualizarNome(int id, String novoNome) {
        String sql = "UPDATE Usuarios SET nome = ? WHERE id = ?";
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

    public void atualizarSenha(int id, String novaSenha) {
        String sql = "UPDATE Cliente SET senha = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novaSenha);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Senha atualizada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

    public void atualizarTipo(int id, boolean novoadm) {
        String sql = "UPDATE Usuarios SET adm = ? WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, novoadm);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Tipo atualizado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar atualizar: "+ ex.getMessage());
        }
    }

    public void menuAtualizacaoUsuarios() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o campo que deseja atualizar:");
        System.out.println("1. Nome");
        System.out.println("2. Senha");
        System.out.println("3. Tipo de Usuario");

        System.out.print("Digite o ID do Usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        AtualizacaoUsuario atualizacao = new AtualizacaoUsuario();

        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                String nome = scanner.nextLine();
                atualizacao.atualizarNome(id, nome);
                break;
            case 2:
                System.out.print("Digite a nova Senha: ");
                String senha = scanner.nextLine();
                atualizacao.atualizarSenha(id, senha);
                break;
            case 3:
                boolean adm;
                while (true) {
                    System.out.println("Digite o tipo do Usuario");
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
                atualizacao.atualizarTipo(id, adm);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
