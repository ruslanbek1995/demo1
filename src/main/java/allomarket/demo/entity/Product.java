package allomarket.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Класс продукции со свойствами (id, name, description, price).
 * <p>Ипользует аннотации Lombok для генерации дефолотных конструкций (конструкторы, геттеры/сеттеры...)</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    double price;

}
