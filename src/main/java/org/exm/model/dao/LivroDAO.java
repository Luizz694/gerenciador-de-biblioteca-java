package org.exm.model.dao;

import org.exm.model.entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
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

    public List<Livro> ListarLivros() {
        String sql = "SELECT * FROM livros";

        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet aux = pstmt.executeQuery()) {
            while (aux.next()) {

                int id = aux.getInt("id_Livro");
                String titulo = aux.getString("titulo");
                String autor = aux.getString("autor");
                int ano = aux.getInt("dataPublicacao");
                boolean livre = aux.getBoolean("livre");

                Livro livro = new Livro(id, autor, titulo, ano, livre);

                livros.add(livro);
            }

        } catch (SQLException e) {

            throw new RuntimeException("Erro ao listar livros: " + e.getMessage(), e);
        }

        return livros;
    }

    public void DeletarLivro(int id){
        String sql = "DELETE FROM Livros WHERE id_Livro = ?";

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir livro: " + e.getMessage(), e);
        }
    }

    public Livro BuscarPorId(int id){
        String sql = "SELECT * FROM Livros WHERE id_Livro = ?";
        Livro livro = null;

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try(ResultSet aux = pstmt.executeQuery()) {
                if (aux.next()){
                    String titulo = aux.getString("titulo");
                    String autor = aux.getString("autor");
                    int ano = aux.getInt("dataPublicacao");
                    boolean livre = aux.getBoolean("livre");

                    livro = new Livro(id, autor, titulo, ano, livre);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro por ID: " + e.getMessage(), e);
        }
        return livro;
    }

    public void EditarLivro(Livro livro){
        String sql = "UPDATE livros SET titulo = ?, autor = ?, dataPublicacao = ?, livre = ? WHERE id_Livro = ?";

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setInt(3, livro.getDataPublicacao());
            pstmt.setBoolean(4, livro.getLivre());
            pstmt.setInt(5, livro.getId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

