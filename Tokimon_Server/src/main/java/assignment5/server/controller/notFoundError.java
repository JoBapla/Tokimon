/**
 * notFoundError sends out the error messages when a tokimon is not found according to the id thats been given
 *
 * @author  Jovanjot Bapla
 */

package assignment5.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class notFoundError extends RuntimeException{
    public notFoundError(String str) {
        super(str);
    }

}


