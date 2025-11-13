package com.amazon.backend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.backend.Entity.Cart;
import com.amazon.backend.Entity.CartItem;
import com.amazon.backend.Exceptions.CartItemNotFoundException;
import com.amazon.backend.Exceptions.EmptyCartException;
import com.amazon.backend.Repository.CartItemRepository;
import com.amazon.backend.Repository.CartRepository;
import com.amazon.backend.constants.CartConstants;
import com.amazon.backend.dto.CartDto;
import com.amazon.backend.pojo.AddtoCartData;
import com.amazon.backend.pojo.UpdateCartData;


@Service
public class CartService {

	@Autowired
	private CartRepository  cartRepository;
	
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public void addToCart(AddtoCartData addtoCartData) {
		
		Optional<Cart> cartOptional=cartRepository.findByUserId(addtoCartData.getUserId());
		Cart cart =new Cart();
		if (cartOptional.isEmpty()) {
			cart.setUserId(addtoCartData.getUserId());
			cart = cartRepository.save(cart);
		}else {
			 cart =cartOptional.get();
		}
		CartItem cartItem=new CartItem();
		cartItem.setCartId(cart.getCartId());
		cartItem.setProductId(addtoCartData.getProductId());
		cartItem.setQuantity(addtoCartData.getQuantity());
		cartItemRepository.save(cartItem);
	}
	@Transactional
	public List<CartDto> getCart(int userId) {
		Optional<Cart> cartOptional=cartRepository.findByUserId(userId);
		if(cartOptional.isEmpty()) {
			throw new EmptyCartException(CartConstants.EXCEPTION_EMPTY_CART);
		}
		Cart cart=cartOptional.get();
		 List<Object[]> cartObjects= cartRepository.getCartData(cart.getCartId());
		
		 List<CartDto> cartDataList=new ArrayList<CartDto>();
		for(Object[] row: cartObjects) {
			CartDto tempCartDto=new CartDto();
			tempCartDto.setCartItemId( (Integer) row[0] );
			tempCartDto.setProductId( (Integer) row[1] );
			tempCartDto.setQuantity( (Integer) row[2] );
			tempCartDto.setTitle( (String) row[3] );
			tempCartDto.setDescription( (String) row[4] );
			tempCartDto.setPrice( Double.parseDouble(String.valueOf(row[5])) );
			tempCartDto.setImages( (String) row[6] );
			cartDataList.add(tempCartDto);
		}
		return cartDataList;
	}
	public void updateCart(int cartItemId,UpdateCartData updateCartData) {
		
		Optional<CartItem> cartOptional=cartItemRepository.findById(cartItemId);
		if (cartOptional.isEmpty()) {
			throw new CartItemNotFoundException(CartConstants.EXCEPTION_CART_ITEM_NOT_FOUND,cartItemId);
		}
		CartItem cartItem=cartOptional.get();
		cartItem.setQuantity(updateCartData.getQuantity());
		cartItemRepository.save(cartItem);
	}
	public void deleteCartItem(int cartItemId) {
		Optional<CartItem> cartOptional=cartItemRepository.findById(cartItemId);
		if (cartOptional.isEmpty()) {
			throw new CartItemNotFoundException(CartConstants.EXCEPTION_CART_ITEM_NOT_FOUND,cartItemId);
		}
		CartItem cartItem=cartOptional.get();
		cartItemRepository.delete(cartItem);
	}	
//	@EventListener(ApplicationReadyEvent.class)
//	@Transactional
//	public void runOnStartup() {
//		System.out.println("Running scheduler on startup for testing.....");
//	    sendCartReminders();
//	}
//	@Transactional
//	public void sendCartReminders() {
//		List<Object[]> cartdata=cartRepository.getCartReminders();
//		for(Object[] row: cartdata) {
//			System.out.println( row[0] );
//			
//		}
	
}