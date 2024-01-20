package allomarket.demo.entity.mapper;

import allomarket.demo.entity.Product;
import allomarket.demo.entity.dto.ProductRequest;

public class ProductMapper {

    public static Product mapProductRequestToproduct(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }
}
