import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsercaoProduto {
    public static void menuInsercaoProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a categoria do produto: ");
        String categoria = scanner.nextLine();

        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o custo do produto: ");
        double custo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite o valor do produto: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        adicionarProdutoCompleto(nome, categoria, quantidade, custo, valor);

    }

    // Metodo para adicionar um produto completo
    public static void adicionarProdutoCompleto(String nome, String categoria, int quantidade, double custo, double valor) {
        String sql = "INSERT INTO Produto (nome, categoria, quantidade, custo, valor) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, categoria);
            statement.setInt(3, quantidade);
            statement.setDouble(4, custo);
            statement.setDouble(5, valor);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Produto adicionado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
