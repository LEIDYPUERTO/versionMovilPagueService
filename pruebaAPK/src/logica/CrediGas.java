package logica;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1



/**
 * CrediGas generated by hbm2java
 */
public class CrediGas  implements java.io.Serializable {


     private CrediGasId id;
     private int valorArticulo;
     private String detalleContratogas;

    public CrediGas() {
    }

	
    public CrediGas(CrediGasId id, int valorArticulo) {
        this.id = id;
        this.valorArticulo = valorArticulo;
    }
    public CrediGas(CrediGasId id, int valorArticulo, String detalleContratogas) {
       this.id = id;
       this.valorArticulo = valorArticulo;
       this.detalleContratogas = detalleContratogas;
    }
   
    public CrediGasId getId() {
        return this.id;
    }
    
    public void setId(CrediGasId id) {
        this.id = id;
    }
    public int getValorArticulo() {
        return this.valorArticulo;
    }
    
    public void setValorArticulo(int valorArticulo) {
        this.valorArticulo = valorArticulo;
    }
    public String getDetalleContratogas() {
        return this.detalleContratogas;
    }
    
    public void setDetalleContratogas(String detalleContratogas) {
        this.detalleContratogas = detalleContratogas;
    }




}


