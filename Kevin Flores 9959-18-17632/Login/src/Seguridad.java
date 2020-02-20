
import javax.swing.JOptionPane;

public class Seguridad {
   String strRespuesta;
    public void ValidarUsuarios(String Usuario[],String strUsuario, String strContraseña, int Intentos){
        boolean blEncontrado=false;
        
        for(int i=0; i < Usuario.length;i++){  
        if ((Usuario[i].equalsIgnoreCase(strUsuario)&& Usuario[i+1].equals(strContraseña))){
             strRespuesta="Bienvenido "+strUsuario;
             blEncontrado=true;
             JOptionPane.showMessageDialog(null, strRespuesta, "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
             Intentos=0;
             break;
            }
        }if (blEncontrado==false){
            strRespuesta= "Usuraio o contraseña no válidos van: "+ Intentos+ " intentos fallidos.";
               JOptionPane.showMessageDialog(null, strRespuesta, "Inicio de sesión", JOptionPane.ERROR_MESSAGE);
            }
         if(Intentos> 2){
            JOptionPane.showMessageDialog(null, "3 intentros fallidos.", "Inicio de sesión", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            }   
    }
    
}
