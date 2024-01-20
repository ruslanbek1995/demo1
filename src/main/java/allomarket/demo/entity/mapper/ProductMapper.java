package allomarket.demo.entity.mapper;

import allomarket.demo.entity.Product;
import allomarket.demo.entity.dto.ProductRequest;

/**
 * <p>Класс маппер для продукции {@link Product} </p>
 * <p>Преобразует обьект типа {@link ProductRequest} в тип {@link Product}</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
public class ProductMapper {

    public static Product mapProductRequestToproduct(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }
}
