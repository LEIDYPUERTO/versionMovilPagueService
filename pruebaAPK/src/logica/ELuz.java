package logica;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1



/**
 * ELuz generated by hbm2java
 */
public class ELuz  implements java.io.Serializable {


     private ELuzId id;
     private String fechaPagoLuz;
     private double valorLuz;
     private String detallerLuz;

    public ELuz() {
    }

	
    public ELuz(ELuzId id, String fechaPagoLuz, double valorLuz) {
        this.id = id;
        this.fechaPagoLuz = fechaPagoLuz;
        this.valorLuz = valorLuz;
    }
    public ELuz(ELuzId id, String fechaPagoLuz, double valorLuz, String detallerLuz) {
       this.id = id;
       this.fechaPagoLuz = fechaPagoLuz;
       this.valorLuz = valorLuz;
       this.detallerLuz = detallerLuz;
    }
   
    public ELuzId getId() {
        return this.id;
    }
    
    public void setId(ELuzId id) {
        this.id = id;
    }
    public String getFechaPagoLuz() {
        return this.fechaPagoLuz;
    }
    
    public void setFechaPagoLuz(String fechaPagoLuz) {
        this.fechaPagoLuz = fechaPagoLuz;
    }
    public double getValorLuz() {
        return this.valorLuz;
    }
    
    public void setValorLuz(double valorLuz) {
        this.valorLuz = valorLuz;
    }
    public String getDetallerLuz() {
        return this.detallerLuz;
    }
    
    public void setDetallerLuz(String detallerLuz) {
        this.detallerLuz = detallerLuz;
    }




}


