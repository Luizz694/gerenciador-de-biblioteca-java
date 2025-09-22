package org.exm.model.entity;

public class Livro {
    private int id_Livro;
    private String autor;
    private String titulo;
    private int dataPublicacao;
    private boolean livre;

    public Livro(){

    }

    //Um construtor sem id pro banco gerar e outro com id
    public Livro(String autor, String titulo, int dataPublicacao, boolean livre){
        this.autor = autor;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.livre = livre;
    }

    public Livro(int id_Livro, String autor, String titulo, int dataPublicacao, boolean livre){
        this.id_Livro = id_Livro;
        this.autor = autor;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.livre = livre;
    }

    public int getId() {
        return id_Livro;
    }
    public String getAutor() {
        return autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getDataPublicacao(){
        return dataPublicacao;
    }
    public boolean getLivre(){
        return livre;
    }


    public void setId(int id_Livro){
        this.id_Livro = id_Livro;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setLivre(boolean livre){
        this.livre = livre;
    }
    public void setDataPublicacao(int dataPublicacao){
        this.dataPublicacao = dataPublicacao;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
}

