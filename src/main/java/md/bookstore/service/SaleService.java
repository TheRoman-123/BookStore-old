package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dto.SaleDto;
import md.bookstore.entity.Cart;
import md.bookstore.entity.User;
import md.bookstore.repository.CustomerRepository;
import md.bookstore.repository.SaleRepository;
import md.bookstore.dto.CartToSaveDto;
import md.bookstore.entity.Sale;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Transactional
    public Long createSale(
            @NotNull Double cost,
            @NotNull Long customer_id,
            @NotNull Set<CartToSaveDto> cartToSaveDtoSet
    ) {
        if (cartToSaveDtoSet.isEmpty()) {
            throw new IllegalArgumentException("Sale cart can't be empty!");
        }
        Sale sale = new Sale();
        sale.setCost(cost);
        sale.setCustomer(customerRepository.getReferenceById(customer_id));
        sale.setDateTime(LocalDateTime.now());
        saleRepository.save(sale);

        cartService.saveCarts(cartToSaveDtoSet, sale);

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

    public List<SaleDto> getSales(User user) {
        int page = 0; // zero-based page index
//        TODO: Later get page size from argument
        int size = 20;
        Sort sort = Sort.by("dateTime").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return saleRepository.findAllByCustomerUser(user, pageable)
                .stream()
                .map(SaleDto::new)
                .collect(Collectors.toList());
    }
}
