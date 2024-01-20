package allomarket.demo.controller;

import allomarket.demo.entity.Product;
import allomarket.demo.entity.dto.ProductRequest;
import allomarket.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>Тестовый класс. Используется для работы тестов api обьекта {@link Product}.</p>
 * <p>Применяет {@link MockMvc} - для итеграционных тестов</p>
 * <p>Ипользует аннотации Lombok</p>
 * <p>{@link FieldDefaults} добавляет ко всем полям окончательный и приватный модификаторы</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class ProductControllerTest {

    String URL = "/api/products";
    MockMvc mockMvc;
    ObjectMapper mapper;
    ProductRepository productRepository;

    @Autowired
    public ProductControllerTest(ObjectMapper mapper, WebApplicationContext webApplicationContext, ProductRepository productRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @BeforeAll
    void setUp() {
        productRepository.save(Product.builder().name("First").description("First Description").price(99.9).build());
        productRepository.save(Product.builder().name("Second").description("Second Description").price(89.9).build());
        productRepository.save(Product.builder().name("Third").description("Third Description").price(79.9).build());
    }

    @Test
    void createProduct() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(ProductRequest.builder()
                                .name("some_name")
                                .description("some_description")
                                .price(123.0)
                                .build())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateProduct() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put(URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(
                                ProductRequest.builder()
                                        .name("First-changed")
                                        .description("First-description changed")
                                        .price(130.0).build())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + "/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getProduct() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}