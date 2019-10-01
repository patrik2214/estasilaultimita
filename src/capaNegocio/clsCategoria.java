package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;

public class clsCategoria {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs=null;
    
    public Integer generarCodigoCategoria() throws Exception{
        strSQL = "SELECT COALESCE(max(codCategoria),0)+1 as codigo from categoria" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de categoría");
        }
        return 0;
    }
    
    public void registrar(Integer cod, String nom,String des, Boolean vig) throws Exception{
        strSQL="insert into CATEGORIA values(" + cod + ",'" + nom + "','" + des + "'," + vig + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la categoría");
        }
    }
    
    public ResultSet buscarCategoria(Integer cod) throws Exception{
        strSQL = "select * from CATEGORIA where codCategoria=" + cod ;
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    }
    
    public void eliminarCategoria(Integer cod) throws Exception {
        strSQL="delete from CATEGORIA where codCategoria=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la categoría");
        }
    }

    public ResultSet listarCategorias() throws Exception{
        strSQL = "select * from CATEGORIA" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría");
        }
    }
    
    public void modificar(Integer cod, String nom, String des,Boolean vig) throws Exception{
        strSQL="update CATEGORIA set nomCategoria='" + nom + "',descripcion='" + des + "', vigencia=" + vig + " where codCategoria=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la categoría");
        }
    }
    
    public void darBaja(Integer cod) throws Exception{
        strSQL="update CATEGORIA set vigencia=false where codCategoria=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la categoría");
        }
    }
    
    public Integer obtenerCodigoCategoria(String nom) throws Exception{
        strSQL = "select codCategoria from categoria where nomcategoria='" + nom + "'" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            if (rs.next()) return rs.getInt("codCategoria");
        } catch (Exception e) {
            throw new Exception("Error al buscar categoria");
        }
        return 0;
    }
}