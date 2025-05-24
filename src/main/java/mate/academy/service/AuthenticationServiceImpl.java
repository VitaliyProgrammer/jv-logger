package mate.academy.service;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);

    @Override
    public User login(String login, String password) throws AuthenticationException {
        logger.info("Method of login( ) was called with login! ", login);
        User user = findByLogin(login);
        if (!user.getPassword().equals(password)) {
            logger.warn("Login failed for user! ", user);
            throw new AuthenticationException("Username or password are incorrect");
        }
        logger.info("User " + login + " was successfully logged! login={}, id={}", login,
                user.getUserId());
        return user;
    }

    private User findByLogin(String login) {
        logger.debug("Attempting to find user by login: {}", login);
        User user = new User(login, "1234");
        user.setUserId(2L);
        logger.debug("The user with this login was found!: ", user.getLogin(), user.getUserId());
        return user;
    }
}
