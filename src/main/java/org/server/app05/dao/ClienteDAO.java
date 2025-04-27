package org.server.app05.dao;

import org.server.app05.DatabaseConfig;
import org.server.app05.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void agregar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, email) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
