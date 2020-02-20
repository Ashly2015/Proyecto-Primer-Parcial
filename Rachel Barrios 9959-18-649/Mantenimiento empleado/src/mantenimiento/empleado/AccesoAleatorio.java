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
    private static int numeroRegistros, numeroRegistrosDepto;
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
    
    public static void crearFileDepartamento(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo");
        }
        flujo = new RandomAccessFile(archivo, "rw");
        numeroRegistrosDepto = (int) Math.ceil(
                (double) flujo.length() / (double) tamañoRegistro);
    }
    
    public static void crearFilePuesto(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo");
        }
        flujo = new RandomAccessFile(archivo, "rw");
        numeroRegistros = (int) Math.ceil(
                (double) flujo.length() / (double) tamañoRegistro);
    }
    
     public static boolean setMantenimientoEmpleado(int i, MantenimientoEmpleado empleado) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            if(empleado.getTamaño() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i*tamañoRegistro);
                flujo.writeUTF(empleado.getId());
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
     
     
     public static boolean setDepartamento(int i, MantenimientoDepartamento departamento) throws IOException {
        if(i >= 0 && i <= getNumeroRegistrosDepto()) {
            if(departamento.getTamañoDepto() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i*tamañoRegistro);
                flujo.writeUTF(departamento.getId());
                flujo.writeUTF(departamento.getDepartamento());
                flujo.writeBoolean(departamento.isActivo());
                return true;
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
        }
        return false;
    }
     
     public static boolean setPuesto(int i, MantenimientoPuesto puesto) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            if(puesto.getTamañoPuesto() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i*tamañoRegistro);
                flujo.writeUTF(puesto.getId());
                flujo.writeUTF(puesto.getPuesto());
                flujo.writeBoolean(puesto.isActivo());
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
     
       private static int buscarRegistroInactivoDepto() throws IOException {
        String nombre;
        for(int i=0; i<getNumeroRegistrosDepto(); i++) 
        {
            flujo.seek(i * tamañoRegistro);
            if(!getDepartamentos(i).isActivo()) 
                return i;
        }
        return -1;        
    }
       
          private static int buscarRegistroInactivoPuesto() throws IOException {
        String nombre;
        for(int i=0; i<getNumeroRegistros(); i++) 
        {
            flujo.seek(i * tamañoRegistro);
            if(!getPuestos(i).isActivo()) 
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
     
     public static boolean eliminarDepartamento(String aEliminar) throws IOException {
        int pos = buscarRegistroDepto(aEliminar);
        if(pos == -1) return false;
        MantenimientoDepartamento departamentoEliminada = getDepartamentos(pos);
        departamentoEliminada.setActivo(false);
        setDepartamento(pos, departamentoEliminada);
        return true;
    }
     
      public static boolean eliminarPuesto(String aEliminar) throws IOException {
        int pos = buscarRegistroPuesto(aEliminar);
        if(pos == -1) return false;
        MantenimientoPuesto puestoEliminada = getPuestos(pos);
        puestoEliminada.setActivo(false);
        setPuesto(pos, puestoEliminada);
        return true;
    }
    
    public static void compactarArchivo(File archivo) throws IOException {
        crearFileEmpleado(archivo); // Abrimos el flujo.
        MantenimientoEmpleado[] listado = new MantenimientoEmpleado[numeroRegistros];
        for(int i=0; i<numeroRegistros; ++i)
            listado[i] = getEmpleado(i);
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.txt");
        crearFileEmpleado(tempo); // Como no existe se crea.
        for(MantenimientoEmpleado p : listado)
            if(p.isActivo())
                añadirPersona(p);
        cerrar();
        
        tempo.renameTo(archivo); // Renombramos.
    }
    
    public static void compactarArchivoDepto(File archivo) throws IOException {
        crearFileDepartamento(archivo); // Abrimos el flujo.
         MantenimientoDepartamento[] listado = new  MantenimientoDepartamento[numeroRegistrosDepto];
        for(int i=0; i<numeroRegistrosDepto; ++i)
            listado[i] = getDepartamentos(i);
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporalDepto.txt");
        crearFileDepartamento(tempo); // Como no existe se crea.
        for( MantenimientoDepartamento p : listado)
            if(p.isActivo())
                añadirDepartamento(p);
        cerrar();
        
        tempo.renameTo(archivo); // Renombramos.
    }
    
     public static void compactarArchivoPuesto(File archivo) throws IOException {
        crearFilePuesto(archivo); // Abrimos el flujo.
         MantenimientoPuesto[] listado = new  MantenimientoPuesto[numeroRegistros];
        for(int i=0; i<numeroRegistros; ++i)
            listado[i] = getPuestos(i);
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporalDepto.txt");
        crearFileDepartamento(tempo); // Como no existe se crea.
        for( MantenimientoPuesto p : listado)
            if(p.isActivo())
                añadirPuesto(p);
        cerrar();
        
        tempo.renameTo(archivo); // Renombramos.
    }
    
    
    
    public static void añadirPersona(MantenimientoEmpleado persona) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if(setMantenimientoEmpleado(inactivo==-1?numeroRegistros:inactivo, persona)) 
            numeroRegistros++;        
    }
    
     public static void añadirDepartamento( MantenimientoDepartamento departamento) throws IOException {
        int inactivo = buscarRegistroInactivoDepto();
        if(setDepartamento(inactivo==-1?numeroRegistrosDepto:inactivo, departamento)) 
            numeroRegistrosDepto++;        
    }
     
      public static void añadirPuesto( MantenimientoPuesto puesto) throws IOException {
        int inactivo = buscarRegistroInactivoPuesto();
        if(setPuesto(inactivo==-1?numeroRegistros:inactivo, puesto)) 
            numeroRegistros++;        
    }

    public static int getNumeroRegistros() {
        return numeroRegistros;
    }
    
    public static int getNumeroRegistrosDepto() {
        return numeroRegistrosDepto;
    }

    public static MantenimientoEmpleado getEmpleado(int i) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            return new MantenimientoEmpleado(flujo.readUTF(),flujo.readUTF(),flujo.readUTF(),flujo.readUTF(),flujo.readUTF(), flujo.readInt(),flujo.readInt(),flujo.readInt(),flujo.readInt(),flujo.readUTF(),flujo.readFloat(), flujo.readBoolean());
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }
    
     public static  MantenimientoDepartamento getDepartamentos(int i) throws IOException {
        if(i >= 0 && i <= getNumeroRegistrosDepto()) {
            flujo.seek(i * tamañoRegistro);
            return new  MantenimientoDepartamento(flujo.readUTF(),flujo.readUTF(), flujo.readBoolean());
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }
     
     public static  MantenimientoPuesto getPuestos(int i) throws IOException {
        if(i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            return new  MantenimientoPuesto(flujo.readUTF(),flujo.readUTF(), flujo.readBoolean());
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
    
    public static int buscarRegistroDepto(String buscado) throws IOException {
         MantenimientoDepartamento p;
        if (buscado == null) {
            return -1;
        }
        for(int i=0; i<getNumeroRegistrosDepto(); i++) {
            flujo.seek(i * tamañoRegistro);
            p = getDepartamentos(i);
            if(p.getDepartamento().equals(buscado) && p.isActivo()) {
                return i;
            }
        }
        return -1;
    }
    
    public static int buscarRegistroPuesto(String buscado) throws IOException {
         MantenimientoPuesto p;
        if (buscado == null) {
            return -1;
        }
        for(int i=0; i<getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            p = getPuestos(i);
            if(p.getPuesto().equals(buscado) && p.isActivo()) {
                return i;
            }
        }
        return -1;
    }
    
}