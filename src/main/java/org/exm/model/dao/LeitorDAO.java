package org.exm.model.dao;

import org.exm.model.entity.Leitor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorDAO {
    public void SalvarLeitor(Leitor leitor){
        String sql = "INSERT into leitor(Nome, Email) VALUES (?, ?) ";

        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, leitor.getNome());
            pstmt.setString(2, leitor.getEmail());

            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o leitor/usuário: "+ e);
        }
    }

    public List<Leitor> ListarLeitores() throws SQLException {
        String sql = "SELECT * FROM leitor";

        List<Leitor> leitores = new ArrayList<>();
        try (Connection conn = ConexaoFactory.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet aux = pstmt.executeQuery()) {

            while (aux.next()){
                int idLeitor = aux.getInt("idLeitor");
                String Nome = aux.getString("Nome");
                String Email = aux.getString("Email");

                Leitor leitor = new Leitor(idLeitor, Nome, Email);

                leitores.add(leitor);
            }

        }catch (SQLException e){
            throw new RuntimeException("Erro ao procurar leitores");
        }

        return leitores;
    }
}
