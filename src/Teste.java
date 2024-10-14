import java.util.Scanner;

public class Teste {
    public static void teste() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Opções: ");
            System.out.println("1. Consulta Produto");
            System.out.println("2. Consulta Cliente");
            System.out.println("3. Inserção Produto");
            System.out.println("4. Inserção Cliente");
            System.out.println("5. Atualização Produto");
            System.out.println("6. Atualização Cliente");
            System.out.println("7. Remoção Produto");
            System.out.println("8. Remoção Cliente");
            System.out.println("9. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    ConsultaProduto.menuPesquisaProduto();
                    break;
                case 2:
                    ConsultaCliente.menuPesquisaCliente();
                    break;
                case 3:
                    InsercaoProduto.menuInsercaoProduto();
                    break;
                case 4:
                    InsercaoCliente.menuInsercaoCliente();
                    break;
                case 5:
                    AtualizacaoProduto.menuAtualizacaoProduto();
                    break;
                case 6:
                    AtualizacaoCliente.menuAtualizacaoCliente();
                    break;
                case 7:
                    RemocaoProduto.menuRemocaoProduto();
                    break;
                case 8:
                    RemocaoCliente.menuRemocaoCliente();
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
