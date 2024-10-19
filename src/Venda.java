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

}
