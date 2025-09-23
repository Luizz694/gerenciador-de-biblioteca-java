package org.exm.controller;

import org.exm.view.tela.TelaPrincipal;
import org.exm.model.dao.LivroDAO;
import org.exm.model.entity.Livro;

import java.util.List;

public class LivroController {

    private final TelaPrincipal view;
    private final LivroDAO model;

    public LivroController(TelaPrincipal view, LivroDAO model) {
        this.view = view;
        this.model = model;
    }

    private void carregarLivrosTabela(){
        List<Livro> livros = model.ListarLivros();
        view.atualizarTabela(livros);
    }

    public void start(){
        carregarLivrosTabela();
    }

}
