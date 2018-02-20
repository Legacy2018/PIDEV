/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author medma
 */
public class UploadImage {
    private int id_media;
    private int id_annonce;
    private String media_link;

    public UploadImage(int id_media, int id_annonce, String media_link) {
        this.id_media = id_media;
        this.id_annonce = id_annonce;
        this.media_link = media_link;
    }

    public int getId_media() {
        return id_media;
    }

    public void setId_media(int id_media) {
        this.id_media = id_media;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getMedia_link() {
        return media_link;
    }

    public void setMedia_link(String media_link) {
        this.media_link = media_link;
    }

    @Override
    public String toString() {
        return "UploadImage{" + "id_media=" + id_media + ", id_annonce=" + id_annonce + ", media_link=" + media_link + '}';
    }
    
    
}
