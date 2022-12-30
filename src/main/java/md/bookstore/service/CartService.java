package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.BookRepository;
import md.bookstore.repository.CartRepository;
import md.bookstore.dto.CartToSaveDto;
import md.bookstore.entity.Book;
import md.bookstore.entity.Cart;
import md.bookstore.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private final BookRepository bookRepository;

    public Long saveCart(CartToSaveDto cartToSaveDto, Sale sale) {
        Book book = bookRepository.getReferenceById(cartToSaveDto.getBookId());
        Cart cart = new Cart(cartToSaveDto.getAmount(), sale, book);
        cartRepository.save(cart);
        return cart.getId();
    }

//    @Transactional
//    TODO: Переделаешь так, чтобы список книг загружался одним запросом к базе
    public void saveCarts(Set<CartToSaveDto> cartToSaveDtoSet, Sale sale) {
        Set<Cart> carts = new HashSet<>();
        for (CartToSaveDto cartToSaveDto : cartToSaveDtoSet) {
            Book book = bookRepository.getReferenceById(cartToSaveDto.getBookId());
            carts.add(new Cart(cartToSaveDto.getAmount(), sale, book));
        }
        cartRepository.saveAll(carts);
    }

    public void saveCarts(Set<Cart> carts) {
        cartRepository.saveAll(carts);
    }

    public void deleteById(List<Long> cartIds) {
        cartRepository.deleteAllById(cartIds);
    }
}
