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
public class MantenimientoDepartamento implements Serializable {

    private String departamento;
    private boolean activo;
    private String id;

    public MantenimientoDepartamento() {

        departamento = "NN";
        id = "NN";

        activo = true;
    }

//Constructor
    public MantenimientoDepartamento(String id, String departamento, boolean activo) {
        this.id = id;
        this.departamento = departamento;
        this.activo = activo;
    }
//Constructor

    MantenimientoDepartamento(String departamento, boolean b) {
        this.departamento = departamento;
        this.activo = activo;
    }

//  Getters y Setters
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

//Sobrecarga de metodo sobre el padre 
    @Override
    public String toString() {

//        Impresion de registro
        return "\nId: " + id
                + "\nDepartamento: " + departamento;

    }

    public int getTamañoDepto() {
        return getDepartamento().length() * 2 + 2 + 4 + 1;
    }

}
