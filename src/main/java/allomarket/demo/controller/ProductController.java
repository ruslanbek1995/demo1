package allomarket.demo.controller;

import allomarket.demo.entity.Product;
import allomarket.demo.entity.dto.ProductRequest;
import allomarket.demo.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequest request) {
        return productService.updateProductById(productId, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProductById(productId);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

}

