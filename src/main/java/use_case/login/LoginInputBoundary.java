package use_case.login;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface LoginInputBoundary {
    /**
     * Execute the login process
     *
     * @param data the input data
     * @return CompletableFuture that completes when the login process is done
     */
    void execute(LoginInputData data);
}
