package allomarket.demo.entity.dto;

import allomarket.demo.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * <p>Pojo DTO класс для продукции {@link Product} </p>
 * <p>Используется для переноса данных со стороны клиента</p>
 * <p>Ипользует аннотации Lombok для генерации дефолотных конструкций (конструкторы, геттеры/сеттеры...)</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String name;
    String description;
    double price;

}
