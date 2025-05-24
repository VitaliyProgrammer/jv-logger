package mate.academy;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.Order;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.AuthenticationServiceImpl;
import mate.academy.service.OrderService;
import mate.academy.service.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        User user;
        try {
            logger.info("Start process of authentication");
            user = authenticationService.login("bob", "1234");
        } catch (AuthenticationException e) {
            logger.error("Authentication is failed! {}", e.getMessage());
            return;
        }
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.completeOrder(user.getUserId());
        logger.info("The order is completed. OrderId {} UserId {},", order.getOrderId(),
                order.getUserId());
    }
}
