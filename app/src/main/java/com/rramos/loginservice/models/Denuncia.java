package com.rramos.loginservice.models;

/**
 * Created by RODRIGO on 15/11/2017.
 */

public class Denuncia {

    private Integer id;
    private String titulo;
    private String comentario;
    private String latitud;
    private String longitud;
    private String imagen;
    private Integer usuarios_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(Integer usuarios_id) {
        this.usuarios_id = usuarios_id;
    }

    public Denuncia(Integer id, String titulo, String comentario, String latitud, String longitud, String imagen, Integer usuarios_id) {
        this.id = id;
        this.titulo = titulo;
        this.comentario = comentario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
        this.usuarios_id = usuarios_id;
    }

    public Denuncia() {
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", comentario='" + comentario + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", imagen='" + imagen + '\'' +
                ", usuarios_id=" + usuarios_id +
                '}';
    }
}
