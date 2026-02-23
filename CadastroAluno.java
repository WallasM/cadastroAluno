import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CadastroAluno {

    static Scanner sc = new Scanner(System.in);

    // Lista principal
    static ArrayList<Map<String, Object>> lista = new ArrayList<>();

    // Índice: matrícula -> posição na lista
    static Map<String, Integer> indice = new HashMap<>();

    public static void main(String[] args) {

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("1 - Cadastrar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Listar");
            System.out.println("4 - Remover");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) cadastrar();
            else if (opcao == 2) buscar();
            else if (opcao == 3) listar();
            else if (opcao == 4) remover();
            else if (opcao == 5) System.out.println("Saindo...");
            else System.out.println("Opção inválida!");
        }
    }

    
    // CADASTRAR
    
    static void cadastrar() {

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        if (idade < 0) {
            System.out.println("Idade inválida!");
            return;
        }

        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        if (indice.containsKey(matricula)) {
            System.out.println("Matrícula já existe!");
            return;
        }

        Map<String, Object> aluno = new HashMap<>();
        aluno.put("nome", nome);
        aluno.put("idade", idade);
        aluno.put("matricula", matricula);

        lista.add(aluno);
        indice.put(matricula, lista.size() - 1);

        System.out.println("Aluno cadastrado!");
    }

    // -------------------------
    // BUSCAR
    // -------------------------
    static void buscar() {

        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        if (!indice.containsKey(matricula)) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        int pos = indice.get(matricula);
        Map<String, Object> aluno = lista.get(pos);

        System.out.println("Nome: " + aluno.get("nome"));
        System.out.println("Idade: " + aluno.get("idade"));
        System.out.println("Matrícula: " + aluno.get("matricula"));
    }

    // -------------------------
    // LISTAR
    // -------------------------
    static void listar() {

        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (Map<String, Object> aluno : lista) {
            System.out.println("----------------");
            System.out.println("Nome: " + aluno.get("nome"));
            System.out.println("Idade: " + aluno.get("idade"));
            System.out.println("Matrícula: " + aluno.get("matricula"));
        }
    }

    // -------------------------
    // REMOVER
    // -------------------------
    static void remover() {

        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        if (!indice.containsKey(matricula)) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        int pos = indice.get(matricula);

        lista.remove(pos);
        indice.remove(matricula);

        // Atualizar posições no índice
        for (int i = pos; i < lista.size(); i++) {
            String mat = (String) lista.get(i).get("matricula");
            indice.put(mat, i);
        }

        System.out.println("Aluno removido!");
    }
}