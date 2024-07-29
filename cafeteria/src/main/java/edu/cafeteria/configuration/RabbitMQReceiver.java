package edu.cafeteria.configuration;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.cafeteria.Repos.OrderRepository;
import edu.cafeteria.Services.EmailService;
import edu.cafeteria.Services.OrderService;
import edu.cafeteria.Services.UserService;
import edu.cafeteria.converter.OrderConverter;
import edu.cafeteria.model.Order;

@Component
public class RabbitMQReceiver {

	 @Autowired
	    private OrderService orderService;
	    @Autowired
	    private OrderConverter orderConverter;
	    @Autowired
	    private OrderRepository orderRepository;
	    @Autowired
	    private UserService userService; 
	    @Autowired
	    private EmailService emailService; 
	
    public void receiveMessage(Long message) {
    	
    Order o =	orderService.getOrderById(message);
    	
        System.out.println("Received message: " + message);
    }
}

