package lk.chanaka_de_silva.Assignment.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SystemExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handle(SystemException exception) {
        String message = "";
        int systemStatusCode = -1;

        //system response systemStatus
        SystemErrorResponse systemException = exception.getClass().getAnnotation(SystemErrorResponse.class);
        if (systemException != null) {
            systemStatusCode = systemException.value();
            message = systemException.reason();
        }

        if (message.isEmpty()) {
            message = exception.getLocalizedMessage();
        }

        return new ExceptionBody(systemStatusCode, message);
    }

    public class ExceptionBody {

        private int status;
        private String message;

        public ExceptionBody(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}

