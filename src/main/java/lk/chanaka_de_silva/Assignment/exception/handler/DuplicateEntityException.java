package lk.chanaka_de_silva.Assignment.exception.handler;

@SystemErrorResponse(4001)
public class DuplicateEntityException extends SystemException {

    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}

