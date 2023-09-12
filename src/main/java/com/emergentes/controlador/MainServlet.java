
package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String op =request.getParameter("op");
        Producto objtar = new Producto();
         int id, pos;
         HttpSession ses = request.getSession();
         ArrayList<Producto> lista =(ArrayList<Producto>)ses.getAttribute("listatar");
        
        switch(op){
            case "nuevo":
                
                request.setAttribute("miobjtar",objtar);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                break;
            case "modificar":
                id = Integer.parseInt(request.getParameter("id"));
                
                pos = buscarPorIndice(request, id);
                
                objtar = lista.get(pos);
                request.setAttribute("miobjtar",objtar);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                break;
            case "eliminar":
                 id = Integer.parseInt(request.getParameter("id"));
                 
                 pos = buscarPorIndice(request, id);
                 if (pos >= 0){
                     lista.remove(pos);
                 }
                 request.setAttribute("listatar", lista);
                 response.sendRedirect("index.jsp");
                break;
            default: 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("listatar");
        Producto objtar =  new Producto();
        objtar.setId(id);
        objtar.setDescripcion(request.getParameter("descripcion"));
       // Para el atributo cantidad (int)
         int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        objtar.setCantidad(cantidad);

         // Para el atributo precio (double)
        double precio = Double.parseDouble(request.getParameter("precio"));
        objtar.setPrecio(precio);

        objtar.setCategoria(request.getParameter("categoria"));
        
        if (id == 0){
            int idnuevo = obtenerId(request);
            objtar.setId(idnuevo);
            lista.add(objtar);
        }
        else{
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objtar);
            
        }
        request.setAttribute("listatar", lista);
        response.sendRedirect("index.jsp");
        
    }

    public int buscarPorIndice(HttpServletRequest request,int id){
     HttpSession ses = request.getSession();
     ArrayList<Producto> lista =(ArrayList<Producto>) ses.getAttribute("listatar");
     
     int pos = -1;
     
     if (lista != null){
         for(Producto ele : lista){
             ++pos;
             if(ele.getId() ==id){
                 break;
             }
         }
     }
     return pos;
    }
      public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listatar");
        int idn = 0;
        for (Producto ele : lista){
            idn = ele.getId();
        }
        return idn + 1;
    }


}
