package data_access;

import entity.User;
import session.SessionManager;
import use_case.execute_buy.ExecuteBuyDataAccessInterface;

/**
 * A class that implements the ExecuteBuyDataAccessInterface interface
 * This class is used to get the user with the given credential
 */
public class InMemoryUserDataAccessObject implements ExecuteBuyDataAccessInterface {
    @Override
    public User getUserWithCredential(String credential) throws ValidationException {
        SessionManager sessionManager = SessionManager.Instance();
        String username = sessionManager.getUsername(credential).orElseThrow(ValidationException::new);
        return getUserWithUsername(username);
    }

    // TODO: implement this method, it should read from an in-memory database
    private User getUserWithUsername(String username) {
        return new User(username, "0");
    }
}