package org.example.app06.dao;

import org.example.app06.model.Producto;
import java.util.List;

public interface ProductoDAO {
    List<Producto> listarTodos();
    void agregar(Producto producto);
    void eliminar(int id);
}
