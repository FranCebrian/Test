/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividad6;
import java.util.*;
import java.io.*;


/**
 *
 * @author Francisco José Cebrián Duarte
 */

public class fichero {
    final private String FICHERO ="45911628V.txt"; 
    private ArrayList<producto> datos= new ArrayList<>();
    
    public fichero () {
        //Pasar datos del fichero al arrayList
            try{
            DataInputStream ios=new DataInputStream(new FileInputStream(FICHERO));
            producto aux=new producto();
            aux.setCodigoProducto(ios.readUTF());
            while (aux.getCodigoProducto()!=null){
                   aux.setNombre(ios.readUTF());
                   aux.setCantidad(ios.readUTF());
                   aux.setDescripcion(ios.readUTF());
                   datos.add(aux);
                   aux=new producto();
                   aux.setCodigoProducto(ios.readUTF());
            }
            ios.close();
        }
        catch(Exception ex){
        }
    }
    //Guardar
        public int guardar() {
        int respuesta=0;
        try{
            DataOutputStream oos= new DataOutputStream (new FileOutputStream(FICHERO));
            for (int i =0; i<datos.size(); i++){
                oos.writeUTF(datos.get(i).getCodigoProducto()); 
                oos.writeUTF(datos.get(i).getNombre()); 
                oos.writeUTF(datos.get(i).getCantidad());
                oos.writeUTF(datos.get(i).getDescripcion());
            }
            oos.close();
            respuesta=0;
        }
        catch (Exception ex){
            respuesta=1;
        }     
        return respuesta;
    }
    //Insertar
        public void insertar (String codigoProducto, String nombre, String cantidad, String descripcion){
        producto aux=new producto();
        aux.setCodigoProducto(codigoProducto);
        aux.setNombre(nombre);
        aux.setCantidad(cantidad);
        aux.setDescripcion(descripcion);
        datos.add(aux);
        guardar();
    }
        
        //Mostrar
       public String listado(){
        String texto="";
        for (int i =0; i<datos.size(); i++){
            texto= texto + " Código Producto:" + datos.get(i).getCodigoProducto()+System.lineSeparator(); 
            texto= texto + " Nombre:" + datos.get(i).getNombre()+System.lineSeparator();
            texto= texto + " Cantidad:" + datos.get(i).getCantidad()+System.lineSeparator();
            texto= texto + " Descripción:" + datos.get(i).getDescripcion()+System.lineSeparator()+System.lineSeparator();
        }
        return texto;
    }
    
       //Modificar
           public int modificar (String codigoProducto, String nombre, String cantidad, String descripcion){
        int modificado=-1;
        for (int i =0; i<datos.size(); i++){
            if (datos.get(i).getCodigoProducto().equals(codigoProducto)){
                modificado=i;
                break;
            }
        }
        if (modificado>=0){
            datos.get(modificado).setNombre(nombre);
            datos.get(modificado).setCantidad(cantidad);
            datos.get(modificado).setDescripcion(descripcion);
            guardar();
        }
        return modificado;
    }
           
      public int borrar (String codigoProducto){
         int borrado=-1;
        for (int i =0; i<datos.size(); i++){
            if (datos.get(i).getCodigoProducto().equals(codigoProducto)){
                borrado=i;
                break;
            }
        }
        if (borrado>=0){
            datos.remove(borrado);
            guardar();
        }
        return borrado;
    }
      
public String buscar(String txtCodigoProducto) {
    try {
        DataInputStream ios = new DataInputStream(new FileInputStream(FICHERO));
        producto aux = new producto();

        while (aux.getCodigoProducto() != null) {
            aux.setCodigoProducto(ios.readUTF());
            aux.setNombre(ios.readUTF());
            aux.setCantidad(ios.readUTF());
            aux.setDescripcion(ios.readUTF());

            if (aux.getCodigoProducto().equals(txtCodigoProducto)) {
                String resultado = "";
                resultado += "Código Producto: " + aux.getCodigoProducto() + "\n";
                resultado += "Nombre: " + aux.getNombre() + "\n";
                resultado += "Cantidad: " + aux.getCantidad() + "\n";
                resultado += "Descripción: " + aux.getDescripcion() + "\n";
                ios.close();
                return resultado;
            }
        }

        ios.close();
        return "No se ha encontrado ningún producto con esa ID.";
    } catch (Exception ex) {
        return "Ha ocurrido un error al buscar el producto.";
    }
}
          /*String cadena ="";
          try {
              File fichero = new File (FICHERO);
              DataInputStream io = new DataInputStream (new FileInputStream(FICHERO));
              producto aux = new producto();
              aux.setCodigoProducto(io.readUTF());
              while (aux.getCodigoProducto()!=null) {
                  if (aux.getCodigoProducto().equals (txtCodigoProducto)) {
                      aux.setCodigoProducto(io.readUTF());
                      aux.setNombre(io.readUTF());
                      aux.setCantidad(io.readUTF());
                      aux.setDescripcion(io.readUTF());
                      cadena = "Código producto: " + aux.getCodigoProducto() + " Nombre: " + aux.getNombre() +
                              " Cantidad: " + aux.getCantidad() + " Descripción: " + aux.getDescripcion() + System.lineSeparator();
                  }
              }
              io.close();
           }
          catch (Exception ex){
      }
          return cadena;*/
      }
