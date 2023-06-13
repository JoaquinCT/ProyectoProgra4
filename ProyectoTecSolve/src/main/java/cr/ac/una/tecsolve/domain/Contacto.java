
package cr.ac.una.tecsolve.domain;

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
@Table(name = "tbContactos")
public class Contacto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numeroWhatsapp;
    private int numeroTelefono;
    private String facebook;
    private String correo;
    private String instagram;
    private boolean status=true;

    public Contacto() {
    }

    public Contacto(int id, int numeroWhatsapp, int numeroTelefono, String facebook, String correo, String instagram) {
        this.id = id;
        this.numeroWhatsapp = numeroWhatsapp;
        this.numeroTelefono = numeroTelefono;
        this.facebook = facebook;
        this.correo = correo;
        this.instagram = instagram;
        this.status = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumeroWhatsapp() {
        return numeroWhatsapp;
    }

    public void setNumeroWhatsapp(int numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
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
        public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}