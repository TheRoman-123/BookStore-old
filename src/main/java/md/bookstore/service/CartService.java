package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.BookRepository;
import md.bookstore.repository.CartRepository;
import md.bookstore.dto.CartToSaveDTO;
import md.bookstore.entity.Book;
import md.bookstore.entity.Cart;
import md.bookstore.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private final BookRepository bookRepository;

    public Long saveCart(CartToSaveDTO cartToSaveDTO, Sale sale) {
        Book book = bookRepository.getReferenceById(cartToSaveDTO.getBookId());
        Cart cart = new Cart(cartToSaveDTO.getAmount(), sale, book);
        cartRepository.save(cart);
        return cart.getId();
    }

    public void saveCarts(Set<CartToSaveDTO> cartToSaveDTOSet, Sale sale) {
        Set<Cart> carts = new HashSet<>();
        for (CartToSaveDTO cartToSaveDTO : cartToSaveDTOSet) {
            Book book = bookRepository.getReferenceById(cartToSaveDTO.getBookId());
            carts.add(new Cart(cartToSaveDTO.getAmount(), sale, book));
        }
        cartRepository.saveAll(carts);
    }
}
