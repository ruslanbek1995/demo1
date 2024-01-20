package allomarket.demo.service;

import allomarket.demo.entity.Product;
import allomarket.demo.entity.dto.ProductRequest;
import allomarket.demo.exception.NotFoundException;
import allomarket.demo.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * <p>Тестовый класс. Используется для работы тестов бизнес логики обьекта {@link Product}.</p>
 * <p>Применяет {@link Mockito} - для модульных тестов</p>
 * <p>Ипользует аннотации Lombok</p>
 * <p>{@link FieldDefaults} добавляет ко всем полям окончательный и приватный модификаторы</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void createProduct() {
        productService.createProduct(ProductRequest.builder().build());
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void updateProductById() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Product.builder().build()));
        productService.updateProductById(1L, ProductRequest.builder().build());
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void deleteProductById() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Product.builder().build()));
        productService.deleteProductById(1L);
        Mockito.verify(productRepository, Mockito.times(1)).delete(Mockito.any(Product.class));
    }

    @Test
    void getProductById() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(Product.builder().build()));
        productService.getProductById(1L);
        Mockito.verify(productRepository, Mockito.times(1)).findById(Mockito.anyLong());

    }

    @Test
    void getProductByIdThrowsException() {
        Assertions.assertThrows(NotFoundException.class,
                () -> productService.getProductById(1L));

    }

    @Test
    void deleteProductByIdThrowsException() {
        Assertions.assertThrows(NotFoundException.class,
                () -> productService.deleteProductById(1L));

    }

    @Test
    void updateProductByIdThrowsException() {
        Assertions.assertThrows(NotFoundException.class,
                () -> productService.updateProductById(1L, new ProductRequest()));

    }
}