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
public class MantenimientoPuesto implements Serializable{

    private String puesto;
    private boolean activo;
    private int id;
   

    public MantenimientoPuesto() {
        
        puesto = "NN";
        id=0;

        activo = true;
    }
  
    

    public MantenimientoPuesto(int id,String puesto, boolean activo) {
        this.id=id;
        this.puesto = puesto;
        this.activo = activo;
    }

  
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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
                + "\nPuesto: " + puesto;
             

    }
    
    
    public int getTamañoPuesto() {
        return getPuesto().length() * 2 + 2 + 4 + 1;
    }
    
    
    
}

