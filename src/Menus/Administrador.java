package Menus;

import models.*;
import java.util.Scanner;

public class Administrador {

    public void UsuariosMenu(){
        Scanner sc = new Scanner(System.in);
        Usuario user = new Usuario();

        user.mostrarTodosUsuarios();

        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Cadastrar Usuario");
            System.out.println("2. Editar Usuario");
            System.out.println("3. Remover Usuario");
            System.out.println("4. Pesquisa Usuario");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    user.menuInsercaoUsuario();
                    break;
                case 2:
                    user.menuAtualizacaoUsuario();
                    break;
                case 3:
                    user.menuRemoverUser();
                    break;
                case 4:
                    user.menuPesquisaUsuario();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }while(opcao != 5);

    }

    public void clientesMenu(){
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();

        cliente.mostrarTodosClientes();

        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Editar Cliente");
            System.out.println("3. Remover Cliente");
            System.out.println("4. Pesquisa Cliente");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cliente.menuInsercaoCliente();
                    break;
                case 2:
                    cliente.menuAtualizacaoCliente();
                    break;
                case 3:
                    cliente.menuRemocaoCliente();
                    break;
                case 4:
                    cliente.menuPesquisaCliente();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 5);
    }

    public void produtosMenu(){
        Scanner sc = new Scanner(System.in);
        Produto produto = new Produto();

        produto.mostrarTodosProdutos();

        int opcao;
        do {
            System.out.println("Opções: ");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Editar Produto");
            System.out.println("3. Remover Produto");
            System.out.println("4. Pesquisar Produto");
            System.out.println("5. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    produto.menuInsercaoProduto();
                    break;
                case 2:
                    produto.menuAtualizacaoProduto();
                    break;
                case 3:
                    produto.menuRemocaoProduto();
                    break;
                case 4:
                    produto.menuPesquisaProduto();
                    break;
                case 5:
                    menuAdm();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 5);
    }

    public void relatorioMenu(){
        Scanner sc = new Scanner(System.in);
        Venda venda = new Venda();

        int opcao;

        do {
            System.out.println("Opções: ");
            System.out.println("1. Gerar relatorio de vendas de um periodo");
            System.out.println("2. Gerar relatorio da venda de produto");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            switch (opcao){
                case 1:
                    System.out.println("Digite a data inicial (yyyy-mm-dd) ou (yyyy-mm-dd HH:MM:SS):");
                    String dataInicio = sc.nextLine();
                    System.out.println("Digite a data final (yyyy-mm-dd) ou (yyyy-mm-dd HH:MM:SS):");
                    String dataFim = sc.nextLine();
                    venda.gerarRelatorioPorData(dataInicio,dataFim);
                    break;
                case 2:
                    venda.gerarRelatorioPorProduto();
                    break;
                case 3:
                    break;
                default:
            }
        }while(opcao !=3);
    }

    public void menuAdm(){
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Opções: ");
            System.out.println("1. Clientes");
            System.out.println("2. Produtos");
            System.out.println("3. Vendas");
            System.out.println("4. Relatorios");
            System.out.println("5. Usuarios");
            System.out.println("6. Sair");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    clientesMenu();
                    break;
                case 2:
                    produtosMenu();
                    break;
                case 3:

                    break;
                case 4:
                    relatorioMenu();
                    break;
                case 5:
                    UsuariosMenu();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

        } while (opcao != 6);
    }
}
