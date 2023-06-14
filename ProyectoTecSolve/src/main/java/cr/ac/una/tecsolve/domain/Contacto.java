
package cr.ac.una.tecsolve.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;



/**
 *
 * @author Maizeth Cisneros
 */
@Entity
@Table(name = "tbcontacto")
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numero_whatsapp;
    private int numero_telefono;
    private String facebook;
    private String correo;
    private String instagram;

    public Contacto() {
    }

    public Contacto(int id, int numeroWhatsapp, int numeroTelefono, String facebook, String correo, String instagram) {
        this.id = id;
        this.numero_whatsapp = numeroWhatsapp;
        this.numero_telefono = numeroTelefono;
        this.facebook = facebook;
        this.correo = correo;
        this.instagram = instagram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumeroWhatsapp() {
        return numero_whatsapp;
    }

    public void setNumeroWhatsapp(int numeroWhatsapp) {
        this.numero_whatsapp = numeroWhatsapp;
    }

    public int getNumeroTelefono() {
        return numero_telefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numero_telefono = numeroTelefono;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    

    
}