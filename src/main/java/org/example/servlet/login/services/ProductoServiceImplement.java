package org.example.servlet.login.services;

import org.example.servlet.login.Repositories.CategoriaRepositoryImplement;
import org.example.servlet.login.Repositories.ProductoRepositoryJdbcImpl;
import org.example.servlet.login.Repositories.Repository;
import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ProductoService para operaciones básicas con productos.
 */
public class ProductoServiceImplement implements ProductoService {
    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceImplement(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryImplement(connection);
    }
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
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        return List.of();
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id) {
        return Optional.empty();
    }

}
