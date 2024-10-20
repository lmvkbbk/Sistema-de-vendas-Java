package models;

import Menus.Administrador;
import database.Conexao;
import java.sql.*;
import java.util.Scanner;

public class Usuario {

    public void mostrarTodosUsuarios() {
        String sql = "SELECT * FROM Usuarios";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar mostrar todos os usuarios: "+ ex.getMessage());
        }
    }


    private void exibirResultados(PreparedStatement statement) throws SQLException {
        try (ResultSet rs = statement.executeQuery()) {
            System.out.printf("%-3s | %-15s | %-11s | %-10s\n", "ID", "Nome", "Senha", "Adm");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                boolean adm = rs.getBoolean("adm");

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
            System.out.println("Ocorreu um erro ao pesquisar pelo id: "+ id);
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

    public void menuPesquisaUsuario(){
        Scanner sc = new Scanner(System.in);
        Administrador administrador = new Administrador();

        System.out.println("Opções de Pesquisa:");
        System.out.println("1. Nome");
        System.out.println("2. ID");
        System.out.println("3. Tipo de Usuario");

        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao){
            case 1:
                System.out.print("Digite o nome: ");
                String nome = sc.nextLine();
                pesquisarPorNome(nome);
                break;
            case 2:
                System.out.print("Digite o Id: ");
                int id = sc.nextInt();
                pesquisarPorId(id);
                break;
            case 3:
                boolean adm;
                while (true) {
                    System.out.print("Digite o tipo de usuario(adm ou fun): ");
                    String admstring = sc.nextLine();

                    if (admstring.equalsIgnoreCase("adm")) {
                        adm = true;
                        break;
                    } else if (admstring.equalsIgnoreCase("fun")) {
                        adm = false;
                        break;
                    } else
                        System.out.println("Opção inválida");
                }
                pesquisarPorAdm(adm);
                break;
            default:
                System.out.println("Opção inválida.");
        }
        administrador.menuAdm();
    }

    public void adicionarUsuarioCompleto(String nome, String senha, boolean adm) {
        String sql = "INSERT INTO Usuario (nome, senha, adm) VALUES (?, ?, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, senha);
            statement.setBoolean(3, adm);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar adicionar um usuario: "+ ex.getMessage());
        }
    }

    public void menuInsercaoUsuario(){
        Scanner sc = new Scanner(System.in);
        Administrador administrador = new Administrador();

        System.out.print("Digite o nome do Usuario: ");
        String nome = sc.nextLine();

        System.out.print("Digite a senha: ");
        String senha = sc.nextLine();

        boolean adm;
        while (true) {
            System.out.print("Digite o tipo do Usuario (adm ou fun): ");
            String admstring = sc.nextLine();

            if (admstring.equalsIgnoreCase("adm")) {
                adm = true;
                break;
            } else if (admstring.equalsIgnoreCase("fun")) {
                adm = false;
                break;
            } else
                System.out.println("Opção inválida");
        }
        adicionarUsuarioCompleto(nome, senha, adm);
        administrador.menuAdm();
    }

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

    public void menuRemoverUser(){
        Scanner sc = new Scanner(System.in);
        Administrador adm = new Administrador();

        System.out.println("Digite o Id do Usuario");
        int id = sc.nextInt();

        removerPorId(id);
        adm.menuAdm();
    }

    public void atualizarNomeUsuario(int id, String novoNome) {
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

    public void atualizarSenhaUsuario(int id, String novaSenha) {
        String sql = "UPDATE Usuarios SET senha = ? WHERE id = ?";
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

    public void atualizarTipoUsuario(int id, boolean novoadm) {
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

    public void menuAtualizacaoUsuario(){
        Scanner sc = new Scanner(System.in);
        Administrador administrador = new Administrador();

        System.out.print("Digite o ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Escolha o campo que deseja atualizar:");
        System.out.println("1. Nome");
        System.out.println("2. Senha");
        System.out.println("3. Tipo de Usuario");

        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                String nome = sc.nextLine();
                atualizarNomeUsuario(id, nome);
                break;
            case 2:
                System.out.print("Digite o nova senha: ");
                String senha = sc.nextLine();
                atualizarSenhaUsuario(id, senha);
                break;
            case 3:
                boolean adm;
                while (true) {
                    System.out.print("Digite o novo tipo do Usuario (adm ou fun): ");
                    String admstring = sc.nextLine();

                    if (admstring.equalsIgnoreCase("adm")) {
                        adm = true;
                        break;
                    } else if (admstring.equalsIgnoreCase("fun")) {
                        adm = false;
                        break;
                    } else
                        System.out.println("Opção inválida");
                }
                atualizarTipoUsuario(id, adm);
                break;
            default:
                System.out.println("Opção inválida.");
        }
        administrador.menuAdm();
    }

}
