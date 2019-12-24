package fscut.manager.demo.controller;


import fscut.manager.demo.entity.Product;
import fscut.manager.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("create")
    public ResponseEntity<Product> create(String productName) {
        Product product = productService.save(productName);
        return ResponseEntity.ok(product);
    }

    @GetMapping("delete")
    public ResponseEntity<String> delete(Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("delete successfully");
    }

}
