package com.tdtu.library.service.impl;

import com.tdtu.library.model.Cart;
import com.tdtu.library.model.Customer;
import com.tdtu.library.model.Item;
import com.tdtu.library.model.Product;
import com.tdtu.library.repository.CartRepository;
import com.tdtu.library.repository.ItemRepository;
import com.tdtu.library.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private CartRepository cartRepo;

    @Override
    public Cart addItem2Cart(Product product, int quantity, Customer customer) {
        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
        }
        Set<Item> cartItems = cart.getItems();
        Item item = findItem(cartItems, product.getId());
        if (cartItems == null) {
            cartItems = new HashSet<>();
            if (item == null) {
                item = new Item();
                item.setProduct(product);
                item.setTotalPrice(quantity * product.getPrice());
                item.setQuantity(quantity);
                item.setCart(cart);
                cartItems.add(item);
                itemRepo.save(item);
            }
        } else {
            if (item == null) {
                item = new Item();
                item.setProduct(product);
                item.setTotalPrice(quantity * product.getPrice());
                item.setQuantity(quantity);
                item.setCart(cart);
                cartItems.add(item);
                itemRepo.save(item);
            } else {
                item.setQuantity(item.getQuantity() + quantity);
                item.setTotalPrice(item.getTotalPrice() + (quantity * product.getPrice()));
                itemRepo.save(item);
            }
        }
        cart.setItems(cartItems);
        int totalItem = totalItems(cart.getItems());
        double totalPrice = totalPrices(cart.getItems());
        cart.setTotalItems(totalItem);
        cart.setTotalPrices(totalPrice);
        cart.setCustomer(customer);
        System.out.println("Enter here");
        return cartRepo.save(cart);
    }

    @Override
    public Cart updateItemInCart(Product product, int quantity, Customer customer) {
        Cart cart = customer.getCart();
        Set<Item> cartItems = cart.getItems();
        Item item = findItem(cartItems, product.getId());
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());
        itemRepo.save(item);

        int totalItem = totalItems(cartItems);
        double totalPrice = totalPrices(cartItems);
        cart.setTotalItems(totalItem);
        cart.setTotalPrices(totalPrice);
        return cartRepo.save(cart);
    }

    @Override
    public Cart deleteItemFromCart(Product product, Customer customer) {
        Cart cart = customer.getCart();
        Set<Item> cartItems = cart.getItems();
        Item item = findItem(cartItems, product.getId());
        cartItems.remove(item);
        itemRepo.delete(item);

        int totalItem = totalItems(cartItems);
        double totalPrice = totalPrices(cartItems);
        cart.setItems(cartItems);
        cart.setTotalItems(totalItem);
        cart.setTotalPrices(totalPrice);
        return cartRepo.save(cart);
    }

    // Check item already in cart or not
    private Item findItem(Set<Item> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        Item rs = null;
        for (Item item : cartItems) {
            if (item.getProduct().getId() == productId) {
                rs = item;
            }
        }
        return rs;
    }

    private int totalItems(Set<Item> cartItems) {
        int total = 0;
        for (Item item : cartItems) {
            total += item.getQuantity();
        }
        return total;
    }

    private double totalPrices(Set<Item> cartItems) {
        double totalPrice = 0.0;
        for (Item item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
