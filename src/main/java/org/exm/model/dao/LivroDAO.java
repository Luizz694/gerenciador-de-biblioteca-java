package org.exm.model.dao;

import org.exm.model.entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    /**
     * Salva um novo livro no banco de dados.
     * @param livro O objeto Livro a ser salvo vindo do Controller.
     */
    public void SalvarLivro(Livro livro){
        String sql = "INSERT into livros(titulo, autor, dataPublicacao, livre) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setInt(3, livro.getDataPublicacao());
            pstmt.setBoolean(4, livro.getLivre());

            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar livro: "+ e);
        }
    }

    /**
     * Retorna uma lista com todos os livros do banco de dados.
     * @return Uma lista de objetos Livro.
     */
    public List<Livro> ListarLivros() {
        String sql = "SELECT * FROM livros";

        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                int id = rs.getInt("id_Livro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano = rs.getInt("dataPublicacao");
                boolean livre = rs.getBoolean("livre");

                Livro livro = new Livro(id, autor, titulo, ano, livre);

                livros.add(livro);
            }

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao listar livros: " + e.getMessage(), e);
        }

        return livros;
    }
}

