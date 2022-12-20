package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.entity.Cart;
import md.bookstore.repository.CustomerRepository;
import md.bookstore.repository.SaleRepository;
import md.bookstore.dto.CartToSaveDTO;
import md.bookstore.entity.Sale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleService {
    private SaleRepository saleRepository;
    private CustomerRepository customerRepository;
    private BookService bookService;

    private CartService cartService;


//    public SaleDTO get(Long id) {
//        return null;
//    }

    @Transactional
    public Long createSale(
            @NotNull Double cost,
            @NotNull Long customer_id,
            @NotNull Set<CartToSaveDTO> cartToSaveDTOSet
    ) {
        if (cartToSaveDTOSet.isEmpty()) {
            throw new IllegalArgumentException("Sale cart can't be empty!");
        }
        Sale sale = new Sale();
        sale.setCost(cost);
        sale.setCustomer(customerRepository.getReferenceById(customer_id));
        sale.setDateTime(LocalDateTime.now());
        saleRepository.save(sale);

        cartService.saveCarts(cartToSaveDTOSet, sale);

        return sale.getId();
    }

    @Transactional
    public void confirmSale(@NotNull Long id) {
        Sale sale = saleRepository.getReferenceById(id);
        if (sale.getConfirmed()) {
            throw new IllegalStateException("Sale #" + id + " is already confirmed");
        }
        Set<Cart> carts = sale.getCarts();
        for (Cart cart : carts) {
            bookService.takeFromWarehouse(cart.getBook(), cart.getAmount());
        }
        sale.setConfirmed(true);
        saleRepository.save(sale);
        cartService.saveCarts(carts);
    }

    public void declineSale(@NotNull Long id) {
        List<Long> cartIds = saleRepository.getReferenceById(id)
                .getCarts()
                .stream()
                .map(Cart::getId)
                .collect(Collectors.toList());
        cartService.deleteById(cartIds);
        saleRepository.deleteById(id);
    }
}
