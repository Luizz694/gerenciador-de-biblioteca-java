package org.exm.model.entity;

public class Leitor {
    private int idLeitor;
    private String Email;
    private String Nome;

    public Leitor(){

    }

    public Leitor(String Nome, String Email){
        this.Nome = Nome;
        this.Email = Email;
    }

    public Leitor(int idLeitor, String Nome, String Email){
        this.idLeitor = idLeitor;
        this.Email = Email;
        this.Nome = Nome;
    }

    public int getIdLeitor() {
        return idLeitor;
    }

    public String getEmail(){
        return Email;
    }

    public String getNome(){
        return Nome;
    }

    public void setIdLeitor(int idLeitor){
        this.idLeitor = idLeitor;
    }

    public void setEmail(String Email){
        this.Email = Email;
    }

    public void setNome(String Nome){
        this.Nome = Nome;
    }
}
