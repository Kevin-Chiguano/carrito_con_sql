package org.example.servlet.login.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public Carro(List<ItemCarro> items) {
        this.items = items;
    }

    public void addItem(ItemCarro itemCarro) {
        // Verifica si el item ya está en el carrito
        if (items.contains(itemCarro)) {
            // Si está presente, incrementa la cantidad
            Optional<ItemCarro> optionalItemCarro = items.stream().filter(i -> i.equals(itemCarro)).findAny();
            if (optionalItemCarro.isPresent()) {
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        } else {
            // Si no está presente, añade el nuevo item al carrito
            this.items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public double getTotal() {
        // Calcula el total sumando los importes de todos los items en el carrito
        return items.stream().mapToDouble(ItemCarro::getImporte).sum();
    }
}
