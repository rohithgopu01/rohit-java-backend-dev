package com.amazon.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.Entity.Cart;
import com.amazon.backend.Entity.CartItem;
import com.amazon.backend.Entity.Order;
import com.amazon.backend.Entity.OrderItem;
import com.amazon.backend.Entity.Product;
import com.amazon.backend.Exceptions.CartIdInvalidException;
import com.amazon.backend.Repository.CartItemRepository;
import com.amazon.backend.Repository.CartRepository;
import com.amazon.backend.Repository.OrderItemRepository;
import com.amazon.backend.Repository.OrderRepository;
import com.amazon.backend.Repository.ProductsRepository;
import com.amazon.backend.constants.OrderConstants;
import com.amazon.backend.enums.OrderStatus;
import com.amazon.backend.pojo.OrderApiData;

@Service
public class OrderService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order createOrder(OrderApiData ApiData) {
		Optional<Cart> cartOptional=cartRepository.findByCartIdAndUserId(ApiData.getCartId(),ApiData.getUserId());
	    if (cartOptional.isEmpty()) {
			throw new CartIdInvalidException(OrderConstants.ORDER_CARTID_IS_INVALID, ApiData.getCartId(), ApiData.getUserId());
		}
	    Cart cart=cartOptional.get();
	    List<CartItem> cartItems=cartItemRepository.findByCartId(ApiData.getCartId());
	    System.out.println(cartItems.size());
	    
	    Double totalprice=0.0;
	
	    for (CartItem cartItem : cartItems) {
	    	System.out.println(cartItem.getProductId());
	    	Optional<Product>  optionalProduct=productsRepository.findById(cartItem.getProductId());
	    	Product product=optionalProduct.get();
	        totalprice = totalprice + ( product.getPrice() * cartItem.getQuantity() );
	        
	    }
	    System.out.println(totalprice);
	    
	    Order order=new Order();
	    order.setUserId(ApiData.getUserId());
	    order.setTotalAmount(totalprice);
	    order.setBillingAddressId(ApiData.getAddressId());
	    order.setShippingAddressId(ApiData.getAddressId());
	    order.setOrderStatus(OrderStatus.Pending);
	    order=orderRepository.save(order);
	    
	    for (CartItem cartItem : cartItems) {
	    	OrderItem orderItem=new OrderItem();
	    	orderItem.setOrderId(order.getOrderId());
	    	orderItem.setProductId(cartItem.getProductId());
	    	Optional<Product>  optionalProduct=productsRepository.findById(cartItem.getProductId());
	    	Product product=optionalProduct.get();
	    	orderItem.setPrice(product.getPrice());
	    	orderItem.setQuantity(cartItem.getQuantity());
	    	orderItemRepository.save(orderItem);
	    }
	    return order;
	}
}
