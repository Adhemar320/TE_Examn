<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%
    if (session.getAttribute("listatar") == null){
        ArrayList<Producto> lisaux = new ArrayList<Producto>();
        session.setAttribute("listatar", lisaux);
    
    }
   ArrayList<Producto> lista =(ArrayList<Producto>) session.getAttribute("listatar"); 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h4>SEGUNDO PARCIAL TEM-742</h4>
        <h4>Nombre: Adhemar Luis Yapu Nina</h4>
        <h4>Carnet:13181329</h4>
        <h1>Gestion de productos</h1>
        
        <a href="MainServlet?op=nuevo">Nuevo producto</a>
        <br>
        <br>
            <table cellspacing="0" border="1">
                <tr>
                    <th>ID</th>
                                      
                    <th>Descripcion</th>
                    
                    <th>Cantidad</th>
                    
                    <th>Precio</th>
                    
                    <th>Categoria</th>
                    <th></th>
                    <th></th>
                  
                </tr>
                <%
                   if (lista != null){
                       for(Producto item : lista){
                
                %>
                <tr>
                    <td><%= item.getId() %></td>
                    <td><%= item.getDescripcion() %></td>
                    <td><%= item.getCantidad() %></td>
                    <td><%= item.getPrecio() %></td>
                    <td><%= item.getCategoria() %></td>
                    <td><a href="MainServlet?op=modificar&id=<%= item.getId() %>">Editar</a></td>
                    <td>
                        
                        <a href="MainServlet?op=eliminar&id=<%= item.getId() %>"
                           onclick="return(confirm('¿Está seguro de Eliminar?'))"
                             >Eliminar</a>
                    </td>  
                </tr>
                <%
                        } 
                    } 
                %>
            </table>
        
    </body>
</html>
