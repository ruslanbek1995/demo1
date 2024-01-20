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

/**
 * <p>Класс сервис. Используется для работы с бизнес логикой обьекта {@link Product}.</p>
 * <p>Имеет поле типа {@link ProductRepository} для доступа к данным</p>
 * <p>Ипользует аннотации Lombok</p>
 * <p>{@link RequiredArgsConstructor} создаёт конструктор с требуемыми аргументами,</p>
 * <p>{@link FieldDefaults} добавляет ко всем полям окончательный и приватный модификаторы</p>
 * <p>{@link Slf4j} генерирует логгер поле</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;

    /**
     * <a>Ипользуется для создания продуктов и его логирования</p>
     *
     * @param request аргуемент типа {@link ProductRequest}
     * @return обьект типа {@link Product}
     */
    public Product createProduct(ProductRequest request) {
        log.info("Product successfully saved!");
        return productRepository.save(ProductMapper.mapProductRequestToproduct(request));
    }

    /**
     * <a>Ипользуется для обновления продукта по ID его логирования</p>
     *
     * @param productId аргемент типа {@link Long}
     * @param request аргуемента типа {@link ProductRequest}
     * @return обьект типа {@link Product},
     */
    public Product updateProductById(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
                    log.warn("Product not found by id = " + productId);
                    return new NotFoundException("Product not found by id = " + productId);
                }
        );
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        log.info("Product successfully updated!");
        return productRepository.save(product);
    }

    /**
     * <a>Ипользуется для удаления продуктов по ID и его логирования</p>
     *
     * @param productId аргуемент типа ({@link Long})</p>
     *
     * @return (NOT_FOUND) 200 статус код при успешном выполнении
     */
    public ResponseEntity<HttpStatus> deleteProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
                    log.warn("Product not found by id = " + productId);
                    return new NotFoundException("Product not found by id = " + productId);
                }
        );
        productRepository.delete(product);
        log.info("Product successfully deleted!");
        return ResponseEntity.ok().build();
    }

    /**
     * <a>Ипользуется для получения продуктов по ID и его логирования</p>
     * @param productId аргуемент типа  ({@link Long})
     * @return обьект типа {@link Product} успешном выополнении
     */
    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
                    log.warn("Product not found by id = " + productId);
                    return new NotFoundException("Product not found by id = " + productId);
                }
        );
        log.info("Product got = " + product.toString());
        return product;
    }

}
