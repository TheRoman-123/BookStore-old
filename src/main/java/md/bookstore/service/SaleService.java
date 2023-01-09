package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dto.SaleDto;
import md.bookstore.dto.converter.CustomerDtoConverter;
import md.bookstore.entity.Cart;
import md.bookstore.entity.Customer;
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
    private BookService bookService;
    private CartService cartService;
    private CustomerService customerService;

    // Method for registered users
    @Transactional
    public Long createSale(
            @NotNull Double cost,
            @NotNull Set<CartToSaveDto> cartToSaveDtoSet,
            @NotNull String email,
            String firstName,
            String phoneNumber
    ) {
        if (cartToSaveDtoSet.isEmpty()) {
            throw new IllegalArgumentException("Sale cart can't be empty!");
        }

        Customer customer;
        if (email.equals("anonymousUser")) {
            customer = CustomerDtoConverter.getCustomer(firstName, phoneNumber);
            customerService.saveCustomer(customer);
        } else {
            customer = customerService.getCustomerByUserEmail(email);
        }

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setCost(cost);
        sale.setConfirmed(false);
        sale.setDateTime(LocalDateTime.now());
        saleRepository.save(sale);

        cartService.saveCarts(cartToSaveDtoSet, sale);

        return sale.getId();
    }

    // ORDER_PROCESSOR, ADMIN
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

    // ORDER_PROCESSOR, ADMIN
    public void declineSale(@NotNull Long id) {
        List<Long> cartIds = saleRepository.getReferenceById(id)
                .getCarts()
                .stream()
                .map(Cart::getId)
                .collect(Collectors.toList());
        cartService.deleteById(cartIds);
        saleRepository.deleteById(id);
    }

    // USER
    public List<SaleDto> getSales(String email, Integer page, Integer size) {
        Sort sort = Sort.by("dateTime").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
//        User user = (User) userService.loadUserByUsername(email);

//        return saleRepository.findAllByCustomerUser(user, pageable)
        return saleRepository.findAllByCustomerEmail(email, pageable)
                .stream()
                .map(SaleDto::new)
                .collect(Collectors.toList());
    }

    public List<SaleDto> getSales(String email) {
        return saleRepository.findAllByCustomerEmail(email)
                .stream()
                .map(SaleDto::new)
                .collect(Collectors.toList());
    }


}
