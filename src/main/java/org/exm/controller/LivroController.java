package org.exm.controller;

import org.exm.view.tela.TelaCadastroLivro;
import org.exm.view.tela.TelaPrincipal;
import org.exm.model.dao.LivroDAO;
import org.exm.model.entity.Livro;

import javax.swing.*;
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
        view.addAdicionarListener(e -> adicionarLivros());
        carregarLivrosTabela();
    }

    public void adicionarLivros(){
        TelaCadastroLivro telaCadastro = new TelaCadastroLivro(view);
        telaCadastro.setVisible(true);

        if (telaCadastro.isConfirmar()){
            String titulo = telaCadastro.getTitulo();
            String autor = telaCadastro.getAutor();
            int ano = telaCadastro.getDataPublicacao();
            boolean livre = telaCadastro.isLivre();

            if (titulo.trim().isEmpty() || autor.trim().isEmpty()){
                JOptionPane.showMessageDialog(view, "Título e Autor são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Livro LivroNovo = new Livro(titulo, autor, ano, livre);
            model.SalvarLivro(LivroNovo);
            carregarLivrosTabela();
        }
    }

}
