package com.backend.tarefas.dao;

import com.backend.tarefas.model.Tarefa;
import com.backend.tarefas.model.Status;
import com.backend.tarefas.model.Prioridade;
import java.util.List;
import java.util.Optional;

public interface TarefaDAO {
    void salvar(Tarefa tarefa);
    Optional<Tarefa> buscarPorId(Long id);
    List<Tarefa> listarTodas();
    void atualizar(Tarefa tarefa);
    void deletar(Long id);
    List<Tarefa> buscarPorStatus(Status status);
    List<Tarefa> buscarPorPrioridade(Prioridade prioridade);
    List<Tarefa> buscarPorCategoria(String categoria);
}