package use_case.execute_buy;

import entity.User;

public interface ExecuteBuyDataAccessInterface {
    User getUserWithCredential(String credential) throws ValidationException;

    // TODO: define this exception elsewhere
    class ValidationException extends Exception {
    }
}