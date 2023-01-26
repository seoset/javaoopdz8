package repository;

import machine.BarsVendingMachine;
import machine.ChipsVendingMachine;
import machine.DrinksVendingMachine;
import machine.VendingMachine;
import product.*;

import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineRepository {
    private final Map<String, VendingMachine> vendingMachines;
    private final Map<VendingMachine, Map<String, List<Product>>> reservedProducts = new HashMap<>();
    private final Map<VendingMachine, List<Product>> products = new HashMap<>();

    public VendingMachineRepository() {
        this.vendingMachines = new HashMap<>();
        vendingMachines.put("bars", new BarsVendingMachine());
        vendingMachines.put("chips", new ChipsVendingMachine());
        vendingMachines.put("drinks", new DrinksVendingMachine());

        List<Product> p = new ArrayList<>(2);
        p.add(new ChocolateBar());
        p.add(new CaramelBar());
        products.put(vendingMachines.get("bars"), p);
        p = new ArrayList<>(2);
        p.add(new CheeseChips());
        p.add(new OnionChips());
        products.put(vendingMachines.get("chips"), p);
        p = new ArrayList<>(2);
        p.add(new CocaCola());
        p.add(new Sprite());
        products.put(vendingMachines.get("drinks"), p);
    }

    public VendingMachine get(String key) {
        return vendingMachines.get(key);
    }

    public List<Product> getProducts(VendingMachine vm) {
        return products.get(vm);
    }

    public void reserveProducts(VendingMachine vm, String key, List<Product> products) {
        Set<String> set = products.stream().map(Product::getName).collect(Collectors.toSet());
        this.products.get(vm).removeIf(product -> set.contains(product.getName()));
        reservedProducts.computeIfAbsent(vm, m -> new HashMap<>()).put(key, products);
    }

    public List<Product> getReservedProducts(VendingMachine vm, String key) {
        return reservedProducts.get(vm).get(key);
    }
}
