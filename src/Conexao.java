import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://autorack.proxy.rlwy.net:59922/railway";
    private static final String USUARIO = "root";
    private static final String SENHA = "HkjhOVwAeinrRhVdYzjfnJYhsDMoCunN";

    //metodo de conexão ao anco de dados SQL
    public static Connection getConnection() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return conexao;
    }
}
