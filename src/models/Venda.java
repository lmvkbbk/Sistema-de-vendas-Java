package models;

import database.Conexao;
import java.sql.*;

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

    public void consultaListaItensPorId(int idVenda){
        String sql = "SELECT iv.idVenda, p.nome AS nomeProduto, iv.quantidade, iv.precoUnitario " +
                "FROM ItensVenda iv " +
                "JOIN Produto p ON iv.idProduto = p.id " +
                "WHERE iv.idVenda = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, idVenda);

            try(ResultSet rs = statement.executeQuery()){
                System.out.printf("%-15s | %-15s | %-10s\n", "NomeProduto", "Quantidade", "Preço Unitario");
                while (rs.next()){
                    int idVendaResultado = rs.getInt("idVenda");
                    String nomeProduto = rs.getString("nomeProduto");
                    int quantidade = rs.getInt("quantidade");
                    double precoUnitario = rs.getDouble("precoUnitario");
                    System.out.printf("%-15s | %-15s | %-10s\n", nomeProduto, quantidade, precoUnitario);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao pesquisar a lista de venda pelo Id: "+ idVenda);
            System.out.println("Erro :"+ ex.getMessage());
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

}
