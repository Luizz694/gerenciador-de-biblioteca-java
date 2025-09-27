package org.exm.view.tela;

import org.exm.model.entity.Livro;

import javax.swing.*;
import java.awt.*;



public class TelaCadastroLivro extends JDialog {
    private JTextField Titulo;
    private JTextField Autor;
    private JTextField DataPublicacao;
    private JCheckBox  Livre;
    private JButton Salvar;
    private JButton Cancelar;

    private boolean confirmar = false;

    public TelaCadastroLivro(Frame owner){
        super(owner, "Adicionar Novo Livro", true);
        setSize(400, 250);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Título:"));
        Titulo = new JTextField();
        add(Titulo);

        add(new JLabel("Autor:"));
        Autor = new JTextField();
        add(Autor);

        add(new JLabel("DataPublicacao"));
        DataPublicacao = new JTextField();
        add(DataPublicacao);

        add(new JLabel("Livre"));
        Livre = new JCheckBox();
        Livre.setSelected(true); //Serve pra começar marcado como "ok"
        add(Livre);

        Salvar = new JButton("Salvar");
        Salvar.addActionListener(e ->{
            confirmar = true;
            setVisible(false);
        });
        add(Salvar);

        Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(e->{
            confirmar = true;
            setVisible(false);
        });
        add(Cancelar);
    }

    public String getTitulo(){
        return Titulo.getText();
    }

    public String getAutor(){
        return Autor.getText();
    }

    public int getDataPublicacao() {
        try {
            return Integer.parseInt(DataPublicacao.getText());
            //conversão
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isLivre(){
        return Livre.isSelected();
    }

    public boolean isConfirmar(){
        return confirmar;
    }

    public void setDadosEditLivro(Livro livro){
        Titulo.setText(livro.getTitulo());
        Autor.setText(livro.getAutor());
        DataPublicacao.setText(String.valueOf(livro.getDataPublicacao()));
        Livre.setSelected(livro.getLivre());
    }
}
