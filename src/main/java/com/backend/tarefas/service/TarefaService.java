package com.backend.tarefas.service;

import com.backend.tarefas.dao.TarefaDAO;
import com.backend.tarefas.dao.TarefaDAOImpl;
import com.backend.tarefas.model.Tarefa;
import com.backend.tarefas.model.Status;
import com.backend.tarefas.model.Prioridade;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TarefaService {
    private final TarefaDAO tarefaDAO;
    
    public TarefaService() {
        this.tarefaDAO = new TarefaDAOImpl();
    }
    
    public void criarTarefa(String titulo, String descricao, Prioridade prioridade, String categoria) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("Título não pode ser vazio!");
        }
        
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new Exception("Descrição não pode ser vazia!");
        }
        
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new Exception("Categoria não pode ser vazia!");
        }
        
        // Criando o objeto tarefa
        Tarefa tarefa = new Tarefa(null, titulo, descricao, prioridade, categoria);
        
        // Salvando através do DAO
        tarefaDAO.salvar(tarefa);
    } // Fecha o método criarTarefa

} // Fecha a classe TarefaService

