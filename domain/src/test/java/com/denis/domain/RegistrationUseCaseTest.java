package com.denis.domain;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Created by denis on 4/15/16.
 */
public class RegistrationUseCaseTest extends ApplicationTestCase {

    /*private RegistrationUseCase registrationUseCase;

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private UserRepository mockUserRepository;

    private static final String FAKE_USER_ID = "";
    private static final String FAKE_USERNAME = "Batman";
    private static final String FAKE_EMAIL = "batman@gmail.com";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationUseCase = new RegistrationUseCase(mockThreadExecutor, mockPostExecutionThread,
                mockUserRepository);
    }

    @Test
    public void buildUseCaseTest(){
        User user = buildFakeUser();
        registrationUseCase.buildUseCaseObservable(user);
    }

    private User buildFakeUser(){
        User user = new User(FAKE_USER_ID);
        user.setUsername(FAKE_USERNAME);
        user.setEmail(FAKE_EMAIL);
        return user;
    }*/
}
