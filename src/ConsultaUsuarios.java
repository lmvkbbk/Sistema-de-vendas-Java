import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsultaUsuarios {

    public void menuPesquisaUsuarios() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Opções de Pesquisa:");
        System.out.println("1. Nome");
        System.out.println("2. ID");
        System.out.println("3. Tipo de Usuario");
        System.out.println("4. Mostrar Todos os Usuarios");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        ConsultaUsuarios consulta = new ConsultaUsuarios();

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
                boolean adm;
                int escolha;

                while (true) {
                    System.out.println("Digite o tipo de Usuario");
                    System.out.println("1. para adm");
                    System.out.println("2 para fun");
                    System.out.print("Escolha: ");
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
                
                consulta.pesquisarPorAdm(adm);
                break;
            case 4:
                consulta.mostrarTodosClientes();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void mostrarTodosClientes() {
        String sql = "SELECT * FROM Usuarios";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar mostrar todos os clientes: "+ ex.getMessage());
        }
    }


    private void exibirResultados(PreparedStatement statement) throws SQLException {
        try (ResultSet rsCliente = statement.executeQuery()) {
            System.out.printf("%-3s | %-15s | %-11s | %-10s\n", "ID", "Nome", "Senha", "Adm");
            while (rsCliente.next()) {
                int id = rsCliente.getInt("id");
                String nome = rsCliente.getString("nome");
                String senha = rsCliente.getString("senha");
                boolean adm = rsCliente.getBoolean("adm");

                mostrarDadosUsuarios(id, nome, senha, adm);
            }
        }
    }


    private void mostrarDadosUsuarios(int id, String nome, String senha, boolean adm) {
        System.out.printf("%-3d | %-15s | %-11s | %-10s\n", id, nome, senha, adm ? "Sim" : "Não");
    }


    public void pesquisarPorNome(String nome) {
        String sql = "SELECT * FROM Usuarios WHERE nome LIKE ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + nome + "%");
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pelo nome: "+ nome);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }


    public void pesquisarPorId(int id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pelo nome: "+ id);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    public void pesquisarPorAdm(boolean adm) {
        String sql = "SELECT * FROM Usuarios WHERE adm = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, adm);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar por adm: " + ex.getMessage());
        }
    }

}
