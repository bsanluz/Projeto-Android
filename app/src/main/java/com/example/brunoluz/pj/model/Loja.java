package com.example.brunoluz.pj.model;

/**
 * Created by Bruno Luz on 01/08/2016.
 */
public class Loja {
    private String nomeLoja;
    private String enderecoLoja;
    private String cidadeLoja;
    private String estadoLoja;
    private String tipoLoja;

    public String getContatoTel() {
        return contatoTel;
    }

    public void setContatoTel(String contatoTel) {
        this.contatoTel = contatoTel;
    }

    private String contatoTel;

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getEnderecoLoja() {
        return enderecoLoja;
    }

    public void setEnderecoLoja(String enderecoLoja) {
        this.enderecoLoja = enderecoLoja;
    }

    public String getCidadeLoja() {
        return cidadeLoja;
    }

    public void setCidadeLoja(String cidadeLoja) {
        this.cidadeLoja = cidadeLoja;
    }

    public String getEstadoLoja() {
        return estadoLoja;
    }

    public void setEstadoLoja(String estadoLoja) {
        this.estadoLoja = estadoLoja;
    }

    public String getTipoLoja() {
        return tipoLoja;
    }

    public void setTipoLoja(String tipoLoja) {
        this.tipoLoja = tipoLoja;
    }

    public Loja(){
    }
}
