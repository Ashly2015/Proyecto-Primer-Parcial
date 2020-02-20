/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento.empleado;

import java.io.Serializable;

/**
 *
 * @author ranbr
 */
public class MantenimientoDepartamento implements Serializable{

    private String departamento;
    private boolean activo;
    private String id;
   

    public MantenimientoDepartamento() {
        
        departamento = "NN";
        id="NN";

        activo = true;
    }
  
    

    public MantenimientoDepartamento(String id,String departamento, boolean activo) {
        this.id=id;
        this.departamento = departamento;
        this.activo = activo;
    }

  
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
    

    @Override
    public String toString() {
        
        
        
        return "\nId: " + id
                + "\nDepartamento: " + departamento;
             

    }
    
    
    public int getTamañoDepto() {
        return getDepartamento().length() * 2 + 2 + 4 + 1;
    }
    
    
    
}
