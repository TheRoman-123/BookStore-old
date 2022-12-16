package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.CustomerRepository;
import md.bookstore.repository.SaleRepository;
import md.bookstore.dto.CartToSaveDTO;
import md.bookstore.dto.SaleDTO;
import md.bookstore.entity.Cart;
import md.bookstore.entity.Customer;
import md.bookstore.entity.Sale;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleService {
    private SaleRepository saleRepository;
    private CustomerRepository customerRepository;

    private CartService cartService;


//    public SaleDTO get(Long id) {
//        return null;
//    }

    public Long createSale(
            Double cost,
            Long customer_id,
            Set<CartToSaveDTO> cartToSaveDTOSet
    ) {
        if (cost == null || customer_id == null || cartToSaveDTOSet == null || cartToSaveDTOSet.isEmpty()) {
            throw new IllegalArgumentException("Invalid values during sale creation!");
        }
        Sale sale = new Sale();
        sale.setCost(cost);
        sale.setCustomer(customerRepository.getReferenceById(customer_id));
        sale.setDateTime(LocalDateTime.now());
        saleRepository.saveAndFlush(sale);

        cartService.saveCarts(cartToSaveDTOSet, sale);

        return sale.getId();
    }
}
