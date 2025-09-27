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
        view.addExcluirListener(e -> deletarLivro());
        view.addEditarListener(e -> editarLivro());
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

    public void deletarLivro(){
        int idLivroSelect = view.getLivroIdSelect();

        if (idLivroSelect == -1){
            JOptionPane.showMessageDialog(view, "Por favor, selecione um livro para excluir.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir este livro?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION){
            model.DeletarLivro(idLivroSelect);
            carregarLivrosTabela();
            JOptionPane.showMessageDialog(view, "Livro excluído com sucesso!");
        }
    }

    public void editarLivro(){
        int idLivroSelect = view.getLivroIdSelect();

        if (idLivroSelect == -1){
            JOptionPane.showMessageDialog(view, "Por favor, selecione um livro para excluir.", "Nenhum livro selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Livro livroPraEditar = model.BuscarPorId(idLivroSelect);
        TelaCadastroLivro telaEdicao = new TelaCadastroLivro(view);
        telaEdicao.setTitle("Editar Livro");

        telaEdicao.setDadosEditLivro(livroPraEditar);
        telaEdicao.setVisible(true);
        if (telaEdicao.isConfirmar()){
            String novoTitulo = telaEdicao.getTitulo();
            String novoAutor = telaEdicao.getAutor();
            int novoAno = telaEdicao.getDataPublicacao();
            boolean novoLivre = telaEdicao.isLivre();

            livroPraEditar.setTitulo(novoTitulo);
            livroPraEditar.setAutor(novoAutor);
            livroPraEditar.setDataPublicacao(novoAno);
            livroPraEditar.setLivre(novoLivre);

            model.EditarLivro(livroPraEditar);
            carregarLivrosTabela();
        }

    }
}
