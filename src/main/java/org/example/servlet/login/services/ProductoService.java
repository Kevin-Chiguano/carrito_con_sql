package org.example.servlet.login.services;

import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listar();
    //Metodo para obtener el producto por id
    Optional<Producto> porId(Integer id);


    //implemantamos un metodo para guardar
    void guardar(Producto producto);

    //Implementamos un metodo para eliminar
    void eliminar(Integer id);

    //Implementamos un metodo para listar la categoria
    List<Categoria> listarCategorias();

    //Implementamos un metodo para obtener el id de la categoria
    Optional<Categoria> porIdCategoria(Integer id);

}
