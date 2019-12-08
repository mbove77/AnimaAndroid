package com.bove.martin.anima.model;

/**
 * Created by Martín Bove on 07/12/2019.
 * E-mail: mbove77@gmail.com
 */
public class Slider {
    private int id;
    private String image_url;
    private String titulo_principal;
    private String subtitulo_principal;
    private String descripcion_principal;
    private String titulo_secundario;
    private String subtitulo_secundario;
    private String descripcion_secundario;
    private String created_at;
    private String updated_at;

    public Slider() {
    }

    public Slider(int id) {
        this.id = id;
    }

    public Slider(int id, String image_url, String titulo_principal, String subtitulo_principal, String descripcion_principal, String titulo_secundario, String subtitulo_secundario, String descripcion_secundario, String created_at, String updated_at) {
        this.id = id;
        this.image_url = image_url;
        this.titulo_principal = titulo_principal;
        this.subtitulo_principal = subtitulo_principal;
        this.descripcion_principal = descripcion_principal;
        this.titulo_secundario = titulo_secundario;
        this.subtitulo_secundario = subtitulo_secundario;
        this.descripcion_secundario = descripcion_secundario;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitulo_principal() {
        return titulo_principal;
    }

    public void setTitulo_principal(String titulo_principal) {
        this.titulo_principal = titulo_principal;
    }

    public String getSubtitulo_principal() {
        return subtitulo_principal;
    }

    public void setSubtitulo_principal(String subtitulo_principal) {
        this.subtitulo_principal = subtitulo_principal;
    }

    public String getDescripcion_principal() {
        return descripcion_principal;
    }

    public void setDescripcion_principal(String descripcion_principal) {
        this.descripcion_principal = descripcion_principal;
    }

    public String getTitulo_secundario() {
        return titulo_secundario;
    }

    public void setTitulo_secundario(String titulo_secundario) {
        this.titulo_secundario = titulo_secundario;
    }

    public String getSubtitulo_secundario() {
        return subtitulo_secundario;
    }

    public void setSubtitulo_secundario(String subtitulo_secundario) {
        this.subtitulo_secundario = subtitulo_secundario;
    }

    public String getDescripcion_secundario() {
        return descripcion_secundario;
    }

    public void setDescripcion_secundario(String descripcion_secundario) {
        this.descripcion_secundario = descripcion_secundario;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

}
