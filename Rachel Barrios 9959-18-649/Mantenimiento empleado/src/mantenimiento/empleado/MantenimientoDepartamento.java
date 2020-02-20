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
    private int id;
   

    public MantenimientoDepartamento() {
        
        departamento = "NN";
        id=0;

        activo = true;
    }
  
    

    public MantenimientoDepartamento(int id,String departamento, boolean activo) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
