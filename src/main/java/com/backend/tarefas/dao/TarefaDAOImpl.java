package com.backend.tarefas.dao;

import com.backend.tarefas.model.Tarefa;
import com.backend.tarefas.model.Status;
import com.backend.tarefas.model.Prioridade;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TarefaDAOImpl implements TarefaDAO {
    private static final String ARQUIVO = "tarefas.dat";
    private Map<Long, Tarefa> tarefas;
    private Long proximoId;
    
    public TarefaDAOImpl() {
        this.tarefas = new HashMap<>();
        this.proximoId = 1L;
        carregarDados();
    }
    
    @Override
    public void salvar(Tarefa tarefa) {
        if (tarefa.getId() == null) {
            tarefa.setId(proximoId++);
        }
        tarefas.put(tarefa.getId(), tarefa);
        salvarDados();
    }
    
    @Override
    public Optional<Tarefa> buscarPorId(Long id) {
        return Optional.ofNullable