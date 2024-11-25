package use_case.logout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import utility.ClientSessionManager;
import utility.ServiceManager;
import utility.SessionManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogoutInteractorTest {
    private static final String TEST_CREDENTIAL = "dummy";
    private LogoutOutputBoundary outputPresenter;
    private SessionManager sessionManagerMock;
    private ClientSessionManager clientSessionManagerMock;
    private ServiceManager serviceManagerMock;

    @BeforeEach
    void setUp() {
        // Mock the output presenter
        outputPresenter = mock(LogoutOutputBoundary.class);

        // Mock the managers
        sessionManagerMock = mock(SessionManager.class);
        clientSessionManagerMock = mock(ClientSessionManager.class);
        serviceManagerMock = mock(ServiceManager.class);
    }

    @Test
    void successfulLogoutTest() {
        LogoutInputData inputData = new LogoutInputData(TEST_CREDENTIAL);

        // Mock static methods of the managers
        try (MockedStatic<SessionManager> sessionManagerStatic = Mockito.mockStatic(SessionManager.class); MockedStatic<ClientSessionManager> clientSessionManagerStatic = Mockito.mockStatic(ClientSessionManager.class); MockedStatic<ServiceManager> serviceManagerStatic = Mockito.mockStatic(ServiceManager.class)) {

            // Setup static method mocks
            sessionManagerStatic.when(SessionManager::Instance).thenReturn(sessionManagerMock);
            clientSessionManagerStatic.when(ClientSessionManager::Instance).thenReturn(clientSessionManagerMock);
            serviceManagerStatic.when(ServiceManager::Instance).thenReturn(serviceManagerMock);

            // Setup response for isValidSession and getCredential
            when(sessionManagerMock.isValidSession(TEST_CREDENTIAL)).thenReturn(true).thenReturn(false);
            when(clientSessionManagerMock.getCredential()).thenReturn(TEST_CREDENTIAL).thenReturn(null);

            // Create the interactor
            LogoutInteractor interactor = new LogoutInteractor(outputPresenter);

            // Verify initial state
            assertTrue(sessionManagerMock.isValidSession(TEST_CREDENTIAL), "Session should be valid before logout");
            assertEquals(TEST_CREDENTIAL, clientSessionManagerMock.getCredential(), "Credential should exist before logout");

            // Execute logout
            interactor.execute(inputData);

            // Verify interactions
            verify(sessionManagerMock).endSession(TEST_CREDENTIAL);
            verify(clientSessionManagerMock).clearCredential();
            verify(outputPresenter).prepareSuccessView();

            // Verify post-logout state
            assertFalse(sessionManagerMock.isValidSession(TEST_CREDENTIAL), "Session should be invalid after logout");
            assertNull(clientSessionManagerMock.getCredential(), "Credential should be null after logout");
        }
    }
}