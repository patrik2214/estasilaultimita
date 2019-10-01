//25/09/2019
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;

public class clsCliente {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs=null;

    public ResultSet listarTipoClientes() throws Exception{
        strSQL = "select * from TIPO_CLIENTE" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    }    
    
    public ResultSet listarClientes() throws Exception{
        strSQL = "select * from CLIENTE C inner join TIPO_CLIENTE T on C.codTipo=T.codTipo" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    } 
    
    
      public Integer generarCodigoCliente() throws Exception{
        strSQL = "SELECT COALESCE(max(codCliente),0)+1 as codigo from cliente" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código ");
        }
        return 0;
    }
      
      public void registrar(Integer cod, String nom,String dni,String ruc,String nombres,String telefono,String correo,String direccion, Boolean vig, Integer codTipo) throws Exception{
        strSQL="insert into cliente values("+cod+",'"+ nom+"' ,'"+dni+"','"+ruc+"','"+nombres+"','"+telefono+"','"+correo+"','"+direccion+"',"+vig+"  , "+codTipo+")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar");
        }
    }
}
