package models;

import database.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Venda {

    public void mostrarTodasVendas() {
        String sql = "SELECT * FROM Venda";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar mostrar todas as vendas: "+ ex.getMessage());
        }
    }

    private void exibirResultados(PreparedStatement statement) throws SQLException {
        try (ResultSet rsCliente = statement.executeQuery()) {
            System.out.printf("%-15s | %-15s | %-15s | %-10s | %-20s\n", "idVenda", "dataVenda", "metodoPagamento", "totalVenda", "clienteNome");

            while (rsCliente.next()) {
                int idVenda = rsCliente.getInt("idVenda");
                Date dataVenda = rsCliente.getDate("dataVenda");
                String metodoPagamento = rsCliente.getString("metodoPagamento");
                double totalVenda = rsCliente.getDouble("totalVenda");
                String clienteNome = rsCliente.getString("clienteNome");

                mostrarDadosUsuarios(idVenda, dataVenda, metodoPagamento, totalVenda, clienteNome);
            }
        }
    }

    private void mostrarDadosUsuarios(int idVenda, Date dataVenda, String metodoPagamento, double totalVenda, String clienteNome) {
        System.out.printf("%-15d | %-15s | %-15s | %-10.2f | %-20s\n", idVenda, dataVenda, metodoPagamento, totalVenda, clienteNome);
    }

    public void pesquisaPorId(int idVenda){
        String sql = "SELECT * FROM Venda WHERE idVenda LIKE ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, idVenda);
            exibirResultados(statement);
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao pesquisar pelo id: "+ idVenda);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    public void pesquisaPorData(Date dataVenda){
        String sql = "SELECT * FROM Venda WHERE dataVenda LIKE ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDate(1, dataVenda);
            exibirResultados(statement);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar pela data: "+ dataVenda);
            System.out.println("Erro :"+ ex.getMessage());
        }
    }

    public void menuPesquisaVenda() {
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("1. Pesquisar Venda por ID");
            System.out.println("2. Pesquisar Venda por Data");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID da venda: ");
                    int idVenda = sc.nextInt();
                    pesquisaPorId(idVenda);
                    break;

                case 2:
                    System.out.print("Digite a data da venda (formato YYYY-MM-DD): ");
                    String dataStr = sc.nextLine();
                    Date dataVenda = Date.valueOf(dataStr);
                    pesquisaPorData(dataVenda);
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }while (opcao != 3);
    }


    public void consultaListaItensPorId(int idVenda){
        String sql = "SELECT iv.idVenda, p.nome AS nomeProduto, iv.quantidade, iv.precoUnitario " +
                "FROM ItensVenda iv " +
                "JOIN Produto p ON iv.idProduto = p.id " +
                "WHERE iv.idVenda = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, idVenda);

            try(ResultSet rs = statement.executeQuery()){
                System.out.printf("%-15s | %-15s | %-15s | %-10s\n", "idVenda", "NomeProduto", "Quantidade", "Preço Unitario");
                while (rs.next()){
                    int idVendaResultado = rs.getInt("idVenda");
                    String nomeProduto = rs.getString("nomeProduto");
                    int quantidade = rs.getInt("quantidade");
                    double precoUnitario = rs.getDouble("precoUnitario");
                    System.out.printf("%-15s | %-15s | %-15s | %-10s\n", idVendaResultado, nomeProduto, quantidade, precoUnitario);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar a lista de venda pelo Id: "+ idVenda);
            System.out.println("Erro :"+ ex.getMessage());
        }

    }

    public int adicionarVenda(Date dataVenda, String metodoPagamento, String clienteNome) {
        String sqlVenda = "INSERT INTO Venda (dataVenda, metodoPagamento, totalVenda, clienteNome) VALUES (?, ?, 0, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmtVenda = connection.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

            stmtVenda.setTimestamp(1, new java.sql.Timestamp(dataVenda.getTime()));
            stmtVenda.setString(2, metodoPagamento);
            stmtVenda.setString(3, clienteNome);
            stmtVenda.executeUpdate();
            try (ResultSet rs = stmtVenda.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar venda:"+ ex.getMessage());
        }
        return -1;
    }

    public void removerItemVenda(int idItemVenda) {
        String sqlRemoverItem = "DELETE FROM ItensVenda WHERE idItemVenda = ?";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmtRemoverItem = connection.prepareStatement(sqlRemoverItem)) {

            stmtRemoverItem.setInt(1, idItemVenda);
            int rowsAffected = stmtRemoverItem.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item removido com sucesso.");
            } else {
                System.out.println("Item não encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao remover item da lista venda:"+ ex.getMessage());
        }
    }


    public BigDecimal adicionarItemVenda(int idVenda, int idProduto, int quantidade, BigDecimal precoUnitario) {
        String sqlItensVenda = "INSERT INTO ItensVenda (idProduto, quantidade, precoUnitario, idVenda) VALUES (?, ?, ?, ?)";
        BigDecimal totalVenda = BigDecimal.ZERO;

        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmtItens = connection.prepareStatement(sqlItensVenda)) {

            stmtItens.setInt(1, idProduto);
            stmtItens.setInt(2, quantidade);
            stmtItens.setBigDecimal(3, precoUnitario);
            stmtItens.setInt(4, idVenda);
            stmtItens.executeUpdate();

            totalVenda = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar intem a lista venda:"+ ex.getMessage());
        }

        return totalVenda;
    }

    public void atualizarTotalVenda(int idVenda, BigDecimal totalVenda) {
        String sqlAtualizarTotal = "UPDATE Venda SET totalVenda = ? WHERE idVenda = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmtAtualizarTotal = connection.prepareStatement(sqlAtualizarTotal)) {
            stmtAtualizarTotal.setBigDecimal(1, totalVenda);
            stmtAtualizarTotal.setInt(2, idVenda);
            stmtAtualizarTotal.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar o total da venda:"+ ex.getMessage());
        }
    }

    public BigDecimal buscarPrecoProduto(int idProduto) {
        String sql = "SELECT valor FROM Produto WHERE id = ?";
        BigDecimal precoUnitario = BigDecimal.ZERO;

        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    precoUnitario = rs.getBigDecimal("valor");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar preço do produto selecionado:"+ ex.getMessage());
        }

        return precoUnitario;
    }

    public void editarlistaVendaMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o Id da venda em que deseja editar a lista: ");
        int idVenda = sc.nextInt();
        consultaListaItensPorId(idVenda);
        editarListaVenda(idVenda);
    }

    public void editarListaVenda(int idVenda){
        Scanner sc = new Scanner(System.in);
        Produto produto = new Produto();
        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Adicionar item a lista");
            System.out.println("2. Remover item da lista");
            System.out.println("3. Sair");

            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    produto.mostrarTodosProdutos();
                    System.out.print("Digite o ID do produto: ");
                    int idProduto = sc.nextInt();

                    System.out.print("Digite a quantidade: ");
                    int quantidade = sc.nextInt();

                    BigDecimal precoUnitario = buscarPrecoProduto(idProduto);
                    BigDecimal totalVenda = adicionarItemVenda(idVenda, idProduto, quantidade, precoUnitario);
                    atualizarTotalVenda(idVenda, totalVenda);
                    break;
                case 2:
                    System.out.print("Digite o ID do produto: ");
                    int idItemVenda = sc.nextInt();
                    removerItemVenda(idItemVenda);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }while (opcao != 3);

    }

    public void removerVenda(int idVenda) {
        String sqlRemoverVenda = "DELETE FROM Venda WHERE idVenda = ?";
        String sqlRemoverItens = "DELETE FROM ItensVenda WHERE idVenda = ?";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement stmtRemoverItens = connection.prepareStatement(sqlRemoverItens);
             PreparedStatement stmtRemoverVenda = connection.prepareStatement(sqlRemoverVenda)) {

            // Remover itens da venda primeiro
            stmtRemoverItens.setInt(1, idVenda);
            stmtRemoverItens.executeUpdate();

            // Agora remover a venda
            stmtRemoverVenda.setInt(1, idVenda);
            int rowsAffected = stmtRemoverVenda.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Venda removida com sucesso.");
            } else {
                System.out.println("Venda não encontrada.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao remover venda:"+ ex.getMessage());
        }
    }

    public void removerVendaMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o Id da venda em que deseja editar a lista: ");
        int idVenda = sc.nextInt();
        removerVenda(idVenda);
    }

    public void cadastrarVenda() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a data da venda (AAAA-MM-DD): ");
        String data = sc.nextLine();
        Date dataVenda = Date.valueOf(data);

        System.out.print("Digite o método de pagamento: ");
        String metodoPagamento = sc.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String clienteNome = sc.nextLine();
        int idVenda = adicionarVenda(dataVenda, metodoPagamento, clienteNome);

        if (idVenda > 0) {
            editarListaVenda(idVenda);
        } else {
            System.out.println("Erro ao processar venda.");
        }
    }

    public void gerarRelatorioPorProduto() {
        String sql = "SELECT p.nome AS nomeProduto, " +
                "SUM(iv.quantidade) AS totalItensVendidos, " +
                "SUM(iv.quantidade * iv.precoUnitario) AS totalArrecadado " +
                "FROM ItensVenda iv " +
                "JOIN Produto p ON iv.idProduto = p.id " +
                "GROUP BY p.nome";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Relatório de Vendas por Produto:");
            System.out.printf("%-20s %-20s %-20s%n", "Produto", "Total Itens Vendidos", "Total Arrecadado");
            System.out.println("------------------------------------------------------------");

            while (rs.next()) {
                String nomeProduto = rs.getString("nomeProduto");
                int totalItensVendidos = rs.getInt("totalItensVendidos");
                double totalArrecadado = rs.getDouble("totalArrecadado");

                System.out.printf("%-20s %-20d %-20.2f%n", nomeProduto, totalItensVendidos, totalArrecadado);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao Gerar relatorio: "+ e.getMessage());
        }
    }

    public void gerarRelatorioPorData(String dataInicio, String dataFim) {
        String sql = "SELECT v.idVenda, v.dataVenda, v.metodoPagamento, " +
                "SUM(iv.quantidade * iv.precoUnitario) AS totalVenda " +
                "FROM Venda v " +
                "JOIN ItensVenda iv ON v.idVenda = iv.idVenda " +
                "WHERE v.dataVenda BETWEEN ? AND ? " +
                "GROUP BY v.idVenda, v.dataVenda, v.metodoPagamento";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dataInicio);
            stmt.setString(2, dataFim);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Relatório de Vendas de " + dataInicio + " a " + dataFim + ":");
                System.out.printf("%-10s %-20s %-15s %-10s%n", "ID Venda", "Data Venda", "Método Pagamento", "Total");
                System.out.println("------------------------------------------------------");

                while (rs.next()) {
                    int idVenda = rs.getInt("idVenda");
                    String dataVenda = rs.getString("dataVenda");
                    String metodoPagamento = rs.getString("metodoPagamento");
                    double totalVenda = rs.getDouble("totalVenda");

                    System.out.printf("%-10d %-20s %-15s %-10.2f%n", idVenda, dataVenda, metodoPagamento, totalVenda);
                }

            }

        } catch (SQLException e) {
            System.out.println("Erro ao Gerar relatorio: "+ e.getMessage());
        }
    }

}
