package com.backend.tarefas.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String titulo;
    private String descricao;
    private Status status;
    private Prioridade prioridade;
    private String categoria;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    
    public Tarefa() {
        this.status = Status.PENDENTE;
        this.prioridade = Prioridade.MEDIA;
        this.dataCriacao = LocalDateTime.now();
    }
    
    public Tarefa(Long id, String titulo, String descricao, Prioridade prioridade, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = Status.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) {
        this.status = status;
        if (status == Status.CONCLUIDA && dataConclusao == null) {