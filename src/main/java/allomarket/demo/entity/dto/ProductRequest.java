package allomarket.demo.entity.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
