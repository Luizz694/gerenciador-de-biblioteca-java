package org.exm;

import org.exm.controller.LivroController;
import org.exm.model.dao.LivroDAO;
import org.exm.view.tela.TelaPrincipal;

import javax.swing.*;


public class Main {
    static void main() {
        SwingUtilities.invokeLater(() -> {


            LivroDAO livroDAO = new LivroDAO();
            TelaPrincipal telaPrincipal = new TelaPrincipal();

            LivroController livroController = new LivroController(telaPrincipal, livroDAO);

            livroController.start();

            telaPrincipal.setVisible(true);
        });
    }
}
