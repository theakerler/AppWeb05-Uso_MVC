package org.server.app05.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.server.app05.dao.ClienteDAO;
import org.server.app05.model.Cliente;

import java.io.IOException;
import java.util.List;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {
    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.listar();
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("agregar".equals(accion)) {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");

            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setEmail(email);

            clienteDAO.agregar(cliente);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            clienteDAO.eliminar(id);
        }

        response.sendRedirect("clientes");
    }
}
