package allomarket.demo.exception.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * <p>Класс DTO для показа данных бизнес исключений.</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
@Setter
@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String exceptionName;
    private String message;
}
