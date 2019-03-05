package dev.brunoqualhato.guiadacidade.model;

import android.widget.ImageView;

public class MAnuncio {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String whatsapp;
    private ImageView foto;

    public MAnuncio(int id, String nome, String endereco, String telefone, String whatsapp, ImageView foto) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.whatsapp = whatsapp;
        this.foto = foto;
    }

    public MAnuncio(String nome, String endereco, String telefone, String whatsapp, ImageView foto) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.whatsapp = whatsapp;
        this.foto = foto;
    }

    public MAnuncio(String nome, String endereco, String quali) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = quali;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }
}
