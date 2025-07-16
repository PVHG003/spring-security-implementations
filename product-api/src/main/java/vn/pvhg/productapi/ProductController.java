package vn.pvhg.productapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final List<Product> listProducts = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> list() {
        if (listProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProducts);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product product) {
        Integer productId = listProducts.size() + 1;
        product.setId(productId);
        listProducts.add(product);

        URI uri = URI.create("/api/products/" + productId);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);

        if (!listProducts.contains(product)) {
            return ResponseEntity.notFound().build();
        }

        int index = listProducts.indexOf(product);
        listProducts.set(index, product);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Product product = new Product();
        product.setId(id);

        if (!listProducts.contains(product)) {
            return ResponseEntity.notFound().build();
        }

        listProducts.remove(product);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Product product = new Product();
        product.setId(id);

        if (!listProducts.contains(product)) {
            return ResponseEntity.notFound().build();
        }

        int index = listProducts.indexOf(product);
        Product product1 = listProducts.get(index);

        return ResponseEntity.ok(product1);
    }
}
