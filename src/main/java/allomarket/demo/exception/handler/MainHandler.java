package allomarket.demo.exception.handler;

import allomarket.demo.exception.NotFoundException;
import allomarket.demo.exception.reponse.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Класс обработчик. Используется для обработки исключений в рамках всего приложения.</p>
 *
 * @author haadibolotbekov
 * @see <a href="https://github.com/Khaadikg/demo"> Code github Repo</a>
 */
@RestControllerAdvice
public class MainHandler {

    /**
     * <p>Метод для обработки бизнес исключения {@link NotFoundException}</p>
     *
     * @return обьект типа {@link ExceptionResponse}
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e) { // если сущность (user,product, etc...) не была найдена
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e.getClass().getName(), e.getMessage());
    }

}
