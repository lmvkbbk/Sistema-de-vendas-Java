import models.*;
import java.util.Scanner;

public class Teste {

    public static void teste() {
        Scanner scanner = new Scanner(System.in);
        Produto produto = new Produto();
        ConsultaUsuarios consultaUsuarios = new ConsultaUsuarios();
        InsercaoProduto insercaoProduto = new InsercaoProduto();
        InsercaoUsuario insercaoUsuario = new InsercaoUsuario();
        AtualizacaoProduto atualizacaoProduto = new AtualizacaoProduto();
        AtualizacaoUsuario atualizacaoUsuarios = new AtualizacaoUsuario();
        RemocaoProduto remocaoProduto = new RemocaoProduto();
        RemocaoUsuario remocaoUsuario = new RemocaoUsuario();

        int opcao;

        do {
            System.out.println("Opções: ");
            System.out.println("1. Consulta models.Produto");
            System.out.println("2. Consulta models.Usuario");
            System.out.println("3. Inserção models.Produto");
            System.out.println("4. Inserção models.Usuario");
            System.out.println("5. Atualização models.Produto");
            System.out.println("6. Atualização models.Usuario");
            System.out.println("7. Remoção models.Produto");
            System.out.println("8. Remoção models.Usuario");
            System.out.println("9. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    produto();
                    break;
                case 2:
                    consultaUsuarios.menuPesquisaUsuarios();
                    break;
                case 3:
                    insercaoProduto.menuInsercaoProduto();
                    break;
                case 4:
                    insercaoUsuario.menuInsercaoUsuario();
                    break;
                case 5:
                    atualizacaoProduto.menuAtualizacaoProduto();
                    break;
                case 6:
                    atualizacaoUsuarios.menuAtualizacaoUsuarios();
                    break;
                case 7:
                    remocaoProduto.menuRemocaoProduto();
                    break;
                case 8:
                    remocaoUsuario.menuRemocaoUsuarios();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 9);
    }
}
