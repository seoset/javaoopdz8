package controller;

import dto.ProductDTO;
import dto.ResponseDTO;
import machine.VendingMachine;
import product.Product;
import service.VendingMachineService;
import util.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    private final VendingMachineService service = new VendingMachineService();

    public ResponseDTO<Collection<ProductDTO>> getProducts(String key) {
        List<Product> products = service.getProducts(key);
        return new ResponseDTO<>(
                Utils.mapProducts(products)
        );
    }

    public VendingMachine getMachine(String key) {
        return service.getMachine(key);
    }

    public ResponseDTO<String> reserveProducts(String key, Collection<ProductDTO> products) {
        String s = service.reserveProducts(service.getMachine(key), products.stream().map(p->new Product(p.getName(), p.getWeight())).collect(Collectors.toList()));
        return new ResponseDTO<>(s);
    }

    public ResponseDTO<String> reserveProducts(VendingMachine machine, Collection<ProductDTO> products) {
        String s = service.reserveProducts(machine, products.stream().map(p->new Product(p.getName(), p.getWeight())).collect(Collectors.toList()));
        return new ResponseDTO<>(s);
    }

    public ResponseDTO<List<ProductDTO>> getProductsByCode(String key, String code) {
        List<ProductDTO> products = service.getProductsByCode(key, code).stream()
                .map(p -> new ProductDTO(p.getName(), p.getWeight())).collect(Collectors.toList());
        return new ResponseDTO<>(products);
    }

    public static void main(String[] args) {
        MainController controller = new MainController();
        ResponseDTO<Collection<ProductDTO>> barProducts = controller.getProducts("bars");
        System.out.println(barProducts);
        ResponseDTO<String> code = controller.reserveProducts("bars", barProducts.getData());
        System.out.println("Reserved code: " + code);
        System.out.printf("Code: %s, Products: %s%n", code, controller.getProductsByCode("bars", code.getData()));
        barProducts = controller.getProducts("bars");
        System.out.println(barProducts);
    }

}
