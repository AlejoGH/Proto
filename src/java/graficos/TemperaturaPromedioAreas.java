/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 *
 * @author PC1
 */

@Entity
public class TemperaturaPromedioAreas implements Serializable {
    @Id
    private String fecha;
    
    private double promedio;


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
}
