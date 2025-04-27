package org.server.app05.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.server.app05.dao.ProductoDAO;
import org.server.app05.model.Producto;

import java.io.IOException;
import java.util.List;

@WebServlet("/productos")
public class ProductoController extends HttpServlet {
    private ProductoDAO productoDAO;

    @Override
    public void init() {
        productoDAO = new ProductoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Listar productos
        List<Producto> productos = productoDAO.listar();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("productos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Agregar producto
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);

        productoDAO.agregar(producto);

        // Redirigir para listar productos
        response.sendRedirect("productos");
    }
}
