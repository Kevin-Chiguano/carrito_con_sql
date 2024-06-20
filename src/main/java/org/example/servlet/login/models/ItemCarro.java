package org.example.servlet.login.models;

import java.util.Objects;

/**
 * La clase ItemCarro representa un elemento individual dentro del carrito de compras.
 * Cada ItemCarro contiene información sobre la cantidad de un producto específico y el propio producto asociado.
 */
public class ItemCarro {
    private int cantidad;        // Cantidad de unidades del producto en el carrito
    private Producto producto;   // Producto asociado a este elemento del carrito

    /**
     * Constructor por defecto de ItemCarro.
     * Inicializa un objeto ItemCarro sin establecer valores iniciales para cantidad y producto.
     */
    public ItemCarro() {
    }

    /**
     * Constructor de ItemCarro que inicializa el objeto con una cantidad y producto específicos.
     *
     * @param cantidad Cantidad de unidades del producto en el carrito.
     * @param producto Producto asociado a este elemento del carrito.
     */
    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    /**
     * Método getter para obtener el producto asociado a este ItemCarro.
     *
     * @return El producto asociado a este ItemCarro.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Método setter para establecer el producto asociado a este ItemCarro.
     *
     * @param producto El producto que se desea asociar a este ItemCarro.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Método getter para obtener la cantidad de unidades del producto en el carrito.
     *
     * @return La cantidad de unidades del producto en el carrito.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método setter para establecer la cantidad de unidades del producto en el carrito.
     *
     * @param cantidad La cantidad de unidades del producto que se desea establecer en el carrito.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método equals para comparar dos objetos ItemCarro basado en la igualdad de sus productos.
     *
     * @param o Objeto a comparar con este ItemCarro.
     * @return true si ambos objetos son iguales (misma instancia o mismo producto), false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return Objects.equals(producto.getId(), itemCarro.producto.getId()) &&
                Objects.equals(producto.getNombre(), itemCarro.producto.getNombre());
    }

    /**
     * Método hashCode para obtener el código hash del objeto ItemCarro.
     *
     * @return Código hash del objeto ItemCarro.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cantidad, producto);
    }

    /**
     * Método para calcular el importe total de este item en el carrito.
     *
     * @return El importe total de este item (cantidad * precio del producto).
     */
    public double getImporte() {
        return cantidad * producto.getPrecio();
    }
}
