package org.example.servlet.login.services;

import org.example.servlet.login.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ProductoService para operaciones básicas con productos.
 */
public class ProductoServiceImplement implements ProductoService {

    /**
     * Método que retorna una lista estática de productos.
     *
     * @return Lista de productos
     */
    @Override
    public List<Producto> listar() {
        return Arrays.asList(

        );
    }

    /**
     * Método para obtener un producto por su identificador.
     *
     * @param id Identificador del producto
     * @return Optional que contiene el producto si se encuentra, o Optional vacío si no se encuentra
     */
    @Override
    public Optional<Producto> porId(Integer id) {
        return listar().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Integer id) {

    }

}
