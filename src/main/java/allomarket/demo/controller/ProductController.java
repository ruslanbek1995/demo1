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

/**
 * <p>Класс контроллер. Используется для работы с api обьекта {@link Product}.</p>
 * <p>Ипользует аннотации Lombok</p>
 * <p>{@link RequiredArgsConstructor} создаёт конструктор с требуемыми аргументами,</p>
 * <p>{@link FieldDefaults} добавляет ко всем полям окончательный и приватный модификаторы</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    /**
     * <a>Ипользуется для создания продуктов</p>
     *
     * @param request аргуемент типа {@link ProductRequest}
     * @return обьект типа {@link Product}
     */
    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    /**
     * <a>Ипользуется для обновления продукта по ID</p>
     *
     * @param productId аргемент типа {@link Long}
     * @param request аргуемента типа {@link ProductRequest}
     * @return обьект типа {@link Product},
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequest request) {
        return productService.updateProductById(productId, request);
    }

    /**
     * <a>Ипользуется для удаления продуктов по ID</p>
     *
     * @param productId аргуемент типа ({@link Long})</p>
     *
     * @return 200 http статус код при успешном выполнении
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProductById(productId);
    }

    /**
     * <a>Ипользуется для получения продуктов по ID</p>
     * @param productId аргуемент типа  ({@link Long})
     * @return обьект типа {@link Product}
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

}

