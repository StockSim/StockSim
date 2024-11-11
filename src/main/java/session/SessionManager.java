package session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SessionManager {

    // singleton instance
    private volatile static SessionManager instance;

    // map to store session credentials and associated usernames
    private final Map<String, String> sessions;

    // singleton constructor
    private SessionManager() {
        sessions = new HashMap<>();
    }

    public static synchronized SessionManager Instance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // create a new session and return the unique credential key
    // TODO: consider hashing the credential key
    public String createSession(String username) {
        String credentialKey = UUID.randomUUID().toString();
        sessions.put(credentialKey, username);
        return credentialKey;
    }

    // get the username associated with a session credential
    public Optional<String> getUsername(String credentialKey) {
        return Optional.ofNullable(sessions.get(credentialKey));
    }

    // end a session
    public void endSession(String credentialKey) {
        if (isValidSession(credentialKey)) {
            sessions.remove(credentialKey);
        }
    }

    // check if a session is valid
    public boolean isValidSession(String credentialKey) {
        return sessions.containsKey(credentialKey);
    }
}