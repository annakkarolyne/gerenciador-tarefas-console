package com.backend.tarefas;

import com.backend.tarefas.model.Tarefa;
import com.backend.tarefas.model.Status;
import com.backend.tarefas.model.Prioridade;
import com.backend.tarefas.service.TarefaService;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final TarefaService tarefaService = new TarefaService();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   GERENCIADOR DE TAREFAS");
        System.out.println("=================================\n");
        
        boolean executando = true;
        
        while (executando) {
            mostrarMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1 -> criarTarefa();
                case 2 -> listarTarefas();
                case 3 -> listarPorPrioridade();
                case 4 -> buscarPorStatus();
                case 5 -> buscarPorCategoria();
                case 6 -> atualizarStatus();
                case 7 -> atualizarTarefa();
                case 8 -> deletarTarefa();
                case 0 -> {
                    System.out.println("\n👋 Encerrando o sistema...");
                    executando = false;
                }
                default -> System.out.println("\n❌ Opção inválida!");
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Criar tarefa");
        System.out.println("2. Listar todas");
        System.out.println("3. Listar por prioridade");
        System.out.println("4. Buscar por status");
        System.out.println("5. Buscar por categoria");
        System.out.println("6. Atualizar status");
        System.out.println("7. Atualizar tarefa");
        System.out.println("8. Deletar tarefa");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void criarTarefa() {
        System.out.println("\n--- CRIAR NOVA TAREFA ---");
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.println("\nPrioridade:");
        System.out.println("1. Baixa");
        System.out.println("2. Média");
        System.out.println("3. Alta");
        System.out.println("4. Urgente");
        System.out.print("Escolha: ");
        int prioridadeOpcao = lerOpcao();
        
        Prioridade prioridade = switch (prioridadeOpcao) {
            case 1 -> Prioridade.BAIXA;
            case 2 -> Prioridade.MEDIA;
            case 3 -> Prioridade.ALTA;
            case 4 -> Prioridade.URGENTE;
            default -> Prioridade.MEDIA;
        };
        
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        
        try {
            tarefaService.criarTarefa(titulo, descricao, prioridade, categoria);
            System.out.println("\n✅ Tarefa criada com sucesso!");
        } catch (Exception e) {
            System.out.println("\n❌ Erro: " + e.getMessage());
        }
    }
    
    private static void listarTarefas() {
        System.out.println("\n--- LISTA DE TAREFAS ---");
        List<Tarefa> tarefas = tarefaService.listarTodas();
        
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }
    
    private static void listarPorPrioridade() {
        System.out.println("\n--- TAREFAS POR PRIORIDADE ---");
        List<Tarefa> tarefas = tarefaService.listarPorPrioridade();
        
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }
    
    private static void buscarPorStatus() {
        System.out.println("\n--- BUSCAR POR STATUS ---");
        System.out.println("1. Pendente");
        System.out.println("2. Em Andamento");
        System.out.println("3. Concluída");
        System.out.println("4. Cancelada");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        Status status = switch (opcao) {
            case 1 -> Status.PENDENTE;
            case 2 -> Status.EM_ANDAMENTO;
            case 3 -> Status.CONCLUIDA;
            case 4 -> Status.CANCELADA;
            default -> null;
        };
        
        if (status == null) {
            System.out.println("\n❌ Status inválido!");
            return;
        }
        
        List<Tarefa> tarefas = tarefaService.buscarPorStatus(status);
        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada com esse status.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }
    
    private static void buscarPorCategoria() {
        System.out.println("\n--- BUSCAR POR CATEGORIA ---");
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        
        List<Tarefa> tarefas = tarefaService.buscarPorCategoria(categoria);
        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada nessa categoria.");
        } else {
            tarefas.forEach(System.out::println);
        }
    }
    
    private static void atualizarStatus() {
        System.out.println("\n--- ATUALIZAR STATUS ---");
        System.out.print("ID da tarefa: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            
            System.out.println("\nNovo status:");
            System.out.println("1. Pendente");
            System.out.println("2. Em Andamento");
            System.out.println("3. Concluída");
            System.out.println("4. Cancelada");
            System.out.print("Escolha: ");
            
            int opcao = lerOpcao();
            Status status = switch (opcao) {
                case 1 -> Status.PENDENTE;
                case 2 -> Status.EM_ANDAMENTO;
                case 3 -> Status.CONCLUIDA;
                case 4 -> Status.CANCELADA;
                default -> null;
            };
            
            if (status == null) {
                System.out.println("\n❌ Status inválido!");
                return;
            }
            
            tarefaService.atualizarStatus(id, status);
            System.out.println("\n✅ Status atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("\n❌ ID inválido!");
        } catch (Exception e) {
            System.out.println("\n❌ Erro: " + e.getMessage());
        }
    }
    
    private static void atualizarTarefa() {
        System.out.println("\n--- ATUALIZAR TAREFA ---");
        System.out.print("ID da tarefa: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            
            System.out.print("Novo título: ");
            String titulo = scanner.nextLine();
            
            System.out.print("Nova descrição: ");
            String descricao = scanner.nextLine();
            
            System.out.println("\nNova prioridade:");
            System.out.println("1. Baixa");
            System.out.println("2. Média");
            System.out.println("3. Alta");
            System.out.println("4. Urgente");
            System.out.print("Escolha: ");
            int prioridadeOpcao = lerOpcao();
            
            Prioridade prioridade = switch (prioridadeOpcao) {
                case 1 -> Prioridade.BAIXA;
                case 2 -> Prioridade.MEDIA;
                case 3 -> Prioridade.ALTA;
                case 4 -> Prioridade.URGENTE;
                default -> Prioridade.MEDIA;
            };
            
            tarefaService.atualizarTarefa(id, titulo, descricao, prioridade);
            System.out.println("\n✅ Tarefa atualizada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("\n❌ ID inválido!");
        } catch (Exception e) {
            System.out.println("\n❌ Erro: " + e.getMessage());
        }
    }
    
    private static void deletarTarefa() {
        System.out.println("\n--- DELETAR TAREFA ---");
        System.out.print("ID da tarefa: ");
        
        try {
            Long id = Long.parseLong(scanner.nextLine());
            tarefaService.deletarTarefa(id);
            System.out.println("\n✅ Tarefa deletada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("\n❌ ID inválido!");
        } catch (Exception e) {
            System.out.println("\n❌ Erro: " + e.getMessage());
        }
    }
}