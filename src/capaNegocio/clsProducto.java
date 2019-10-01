//18-09-2019
package capaNegocio;

import capaDatos.clsJDBC;
import java.sql.*;

public class clsProducto {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs=null;
    
    public ResultSet listarProductos() throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria order by codProducto ";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
    
    public Integer generarCodigoProducto() throws Exception{
        strSQL = "SELECT COALESCE(max(codProducto),0)+1 as codigo from producto" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código del producto");
        }
        return 0;
    }
    
    public void registrarProducto(Integer cod, String nom, String des, Double pre, Integer sto, Boolean vig, Integer codMar, Integer codCat) throws Exception{
        strSQL="insert into Producto values(" + cod + ",'" + nom + "','" + des + "'," + pre + "," + sto + "," + vig + "," + codMar + "," + codCat + ")";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception ("Error al registrar un producto!");
        }
            
    }
    
    public void modificarProducto(Integer cod, String nom, String des, Double pre, Integer sto, Boolean vig, Integer codMar, Integer codCat) throws Exception{
        strSQL="update Producto set nomProducto='" + nom + "', descripcion='" + des + "', precio=" + pre + ", stock=" + sto + ", vigencia=" + vig + ", codMarca=" + codMar + ", codCategoria=" + codCat + " where codProducto=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception ("Error al modificar un producto!");
        }
    }
    
    public void eliminarProducto(Integer cod) throws Exception{
        strSQL="delete from Producto where codProducto=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception ("Error al modificar un producto!");
        }
            
    }
    
    public void darBajaProducto(Integer cod) throws Exception{
        strSQL="update Producto set vigencia=false where codProducto=" + cod;
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception ("Error al modificar un producto!");
        }
            
    }

    public ResultSet buscarProducto(Integer cod) throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where codProducto=" + cod;
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
    
    public ResultSet listarProductosPorCategoria(Integer codCat) throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where P.codCategoria=" + codCat + " order by codProducto ";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
    
    public ResultSet listarProductosPorMarca(Integer codMar) throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where P.codMarca=" + codMar + " order by codProducto ";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
    
    public ResultSet listarProductosMarca_Cat(Integer codMar, Integer codCat) throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where P.codMarca=" + codMar + " and P.codCategoria=" + codCat + " order by codProducto ";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
    
    //select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where nomproducto like 'm%' order by codProducto 
    public ResultSet listarProductosPorNombre(String nom) throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where nomproducto like '" + nom + "%' order by codProducto ";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
   
    public ResultSet listarProductosPorprecio(Double precioMin, Double precioMax) throws Exception{
        strSQL="select P.*,M.nomMarca, C.nomCategoria from producto P inner join Marca M on P.codMarca=M.codMarca inner join Categoria C on P.codCategoria=C.codCategoria where precio>=" + precioMin + " and precio<=" + precioMax +  " order by codProducto ";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Error al consultar productos.") ;
        }
    }
}
