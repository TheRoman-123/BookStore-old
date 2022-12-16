package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.CartRepository;
import md.bookstore.dto.CartToSaveDTO;
import md.bookstore.entity.Cart;
import md.bookstore.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;

    public Long saveCart(CartToSaveDTO cartToSaveDTO, Sale sale) {
        Cart cart = new Cart(cartToSaveDTO);
        cart.setSale(sale);
        cartRepository.saveAndFlush(cart);
        return cart.getId();
    }

    public void saveCarts(Set<CartToSaveDTO> cartToSaveDTOSet, Sale sale) {
        Set<Cart> carts = cartToSaveDTOSet.stream()
                .map(Cart::new)
                .collect(Collectors.toSet());
        carts.forEach(a -> a.setSale(sale));
        cartRepository.saveAll(carts);
    }
}
