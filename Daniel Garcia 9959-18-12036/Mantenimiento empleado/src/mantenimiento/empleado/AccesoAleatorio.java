/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento.empleado;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ranbr
 */
public class AccesoAleatorio {

    private static RandomAccessFile flujo;
    private static int numeroRegistros;
    private static int tamañoRegistro = 80;

    public static void crearFileEmpleado(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo");
        }
        flujo = new RandomAccessFile(archivo, "rw");
        numeroRegistros = (int) Math.ceil(
                (double) flujo.length() / (double) tamañoRegistro);
    }
    public static void cerrar() throws IOException {
        flujo.close();
    }
    
     public static boolean setMantenimientoEmpleado(int i, MantenimientoEmpleado empleado) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            if(empleado.getTamaño() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i*tamañoRegistro);
                flujo.writeUTF(empleado.getNombre());
                flujo.writeUTF(empleado.getApellido());
                flujo.writeUTF(empleado.getDepartamento());
                flujo.writeUTF(empleado.getPuesto());
                flujo.writeInt(empleado.getDia());
                flujo.writeInt(empleado.getMes());
                flujo.writeInt(empleado.getAño());
                flujo.writeInt(empleado.getTelefono());
                flujo.writeUTF(empleado.getUbicacion());
                flujo.writeFloat(empleado.getSueldo());
                flujo.writeBoolean(empleado.isActivo());
                return true;
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
        }
        return false;
    }
     
     private static int buscarRegistroInactivo() throws IOException {
        String nombre;
        for(int i=0; i<getNumeroRegistros(); i++) 
        {
            flujo.seek(i * tamañoRegistro);
            if(!getEmpleado(i).isActivo()) 
                return i;
        }
        return -1;        
    }
     
     
     public static boolean eliminarEmpleado(String aEliminar) throws IOException {
        int pos = buscarRegistro(aEliminar);
        if(pos == -1) return false;
        MantenimientoEmpleado personaEliminada = getEmpleado(pos);
        personaEliminada.setActivo(false);
        setMantenimientoEmpleado(pos, personaEliminada);
        return true;
    }
    
    public static void compactarArchivo(File archivo) throws IOException {
        crearFileEmpleado(archivo); // Abrimos el flujo.
        MantenimientoEmpleado[] listado = new MantenimientoEmpleado[numeroRegistros];
        for(int i=0; i<numeroRegistros; ++i)
            listado[i] = getEmpleado(i);
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.dat");
        crearFileEmpleado(tempo); // Como no existe se crea.
        for(MantenimientoEmpleado p : listado)
            if(p.isActivo())
                añadirPersona(p);
        cerrar();
        
        tempo.renameTo(archivo); // Renombramos.
    }
    
    public static void añadirPersona(MantenimientoEmpleado persona) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if(setMantenimientoEmpleado(inactivo==-1?numeroRegistros:inactivo, persona)) 
            numeroRegistros++;        
    }

    public static int getNumeroRegistros() {
        return numeroRegistros;
    }

    public static MantenimientoEmpleado getEmpleado(int i) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            return new MantenimientoEmpleado(flujo.readUTF(),flujo.readUTF(),flujo.readUTF(),flujo.readUTF(), flujo.readInt(),flujo.readInt(),flujo.readInt(),flujo.readInt(),flujo.readUTF(),flujo.readFloat(), flujo.readBoolean());
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }

    public static int buscarRegistro(String buscado) throws IOException {
        MantenimientoEmpleado p;
        if (buscado == null) {
            return -1;
        }
        for(int i=0; i<getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            p = getEmpleado(i);
            if(p.getNombre().equals(buscado) && p.isActivo()) {
                return i;
            }
        }
        return -1;
    }
    
}