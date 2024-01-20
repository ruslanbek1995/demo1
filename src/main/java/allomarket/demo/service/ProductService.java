package allomarket.demo.service;

import allomarket.demo.entity.Product;
import allomarket.demo.entity.dto.ProductRequest;
import allomarket.demo.entity.mapper.ProductMapper;
import allomarket.demo.exception.NotFoundException;
import allomarket.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;

    public Product createProduct(ProductRequest request) {
        log.info("Product successfully saved!");
        return productRepository.save(ProductMapper.mapProductRequestToproduct(request));
    }

    public Product updateProductById(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
                    log.warn("Product not found by id = " + id);
                    return new NotFoundException("Product not found by id = " + id);
                }
        );
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        log.info("Product successfully updated!");
        return productRepository.save(product);
    }

    public ResponseEntity<HttpStatus> deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
                    log.warn("Product not found by id = " + id);
                    return new NotFoundException("Product not found by id = " + id);
                }
        );
        productRepository.delete(product);
        log.info("Product successfully deleted!");
        return ResponseEntity.ok().build();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
                    log.warn("Product not found by id = " + id);
                    return new NotFoundException("Product not found by id = " + id);
                }
        );
        log.info("Product got = " + product.toString());
        return product;
    }

}
