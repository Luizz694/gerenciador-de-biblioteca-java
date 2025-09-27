package org.exm.view.tela;

import org.exm.model.entity.Livro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame{
    private JTable tabelaLivros;
    private JButton botaoAdicionar, botaoEditar, botaoExcluir;
    private JScrollPane scrollPane; // rolagem para a tabela
    private JPanel painelBotoes;

    private DefaultTableModel modeloTabela;


    public TelaPrincipal() {
        setTitle("Gerenciador de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela


        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Título", "Autor", "Ano", "Livre"}, 0);

        tabelaLivros = new JTable(modeloTabela);
        scrollPane = new JScrollPane(tabelaLivros);

        botaoAdicionar = new JButton("Adicionar");
        botaoEditar = new JButton("Editar");
        botaoExcluir = new JButton("Excluir");

        painelBotoes = new JPanel();
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);

        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
    public void atualizarTabela(List<Livro> livros) {
        modeloTabela.setRowCount(0);

        for (Livro livro : livros) {
            modeloTabela.addRow(new Object[]{
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getDataPublicacao(),
                    livro.getLivre()
            });
        }
    }

    public void addAdicionarListener(ActionListener listener) {
        botaoAdicionar.addActionListener(listener);
    }

    public int getLivroIdSelect(){
        int linha = tabelaLivros.getSelectedRow();
        if (linha == -1) {
            return -1;
        };
        return (int)modeloTabela.getValueAt(linha, 0);
    }

    public void addExcluirListener(ActionListener listener) {
        botaoExcluir.addActionListener(listener);
    }

    public void addEditarListener(ActionListener listener){
        botaoEditar.addActionListener(listener);
    }
}
