package org.example.servlet.login.Repositories;

import org.example.servlet.login.models.Producto;
import org.example.servlet.login.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {

    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(
                     "SELECT " +
                             "articulo.idarticulo, " +
                             "articulo.codigo, " +
                             "articulo.nombre, " +
                             "articulo.stock, " +
                             "articulo.descripcion, " +
                             "articulo.imagen, " +
                             "articulo.condicion, " +
                             "articulo.precio, " +
                             "articulo.idcategoria, " +
                             "categoria.nombre AS categoria " +
                             "FROM articulo " +
                             "INNER JOIN categoria ON articulo.idcategoria = categoria.idcategoria;")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement smt = conn.prepareStatement(
                "SELECT articulo.*, categoria.nombre AS categoria " +
                        "FROM articulo " +
                        "INNER JOIN categoria ON articulo.idcategoria = categoria.idcategoria " +
                        "WHERE articulo.idarticulo = ?;")) {

            smt.setInt(1, id);
            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE articulo SET nombre=?, idcategoria=?, descripcion=?, precio=? WHERE idarticulo=?;";
        } else {
            sql = "INSERT INTO articulo(nombre, idcategoria, descripcion, precio, condicion) VALUES(?,?,?,?,1);";
        }
        try (PreparedStatement smt = conn.prepareStatement(sql)) {
            smt.setString(1, producto.getNombre());
            smt.setInt(2, producto.getCategoria().getIdCategoria());
            smt.setString(3, producto.getDescripcion());
            smt.setDouble(4, producto.getPrecio());
            if (producto.getId() != null && producto.getId() > 0) {
                smt.setInt(5, producto.getId());
            }
            smt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM articulo WHERE idarticulo=?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Producto activar(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Producto desactivar(Integer id) throws SQLException {
        return null;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("idarticulo"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen"));
        p.setCondicion(rs.getBoolean("condicion"));
        p.setPrecio(rs.getDouble("precio"));

        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idcategoria"));
        c.setNombre(rs.getString("categoria"));  // Asegurarse de que el alias es "categoria"
        p.setCategoria(c);

        return p;
    }
}
