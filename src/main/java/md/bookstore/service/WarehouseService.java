package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.WarehouseDAO;
import md.bookstore.dto.WarehouseToPrintDTO;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseService {
    private WarehouseDAO warehouseDAO;

    public WarehouseToPrintDTO getBook() {
        return null;
    }

    public List<WarehouseToPrintDTO> getAll() {
        return warehouseDAO.findAll()
                .stream()
                .map(WarehouseToPrintDTO::new)
                .collect(Collectors.toList());
    }


    public List<WarehouseToPrintDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return warehouseDAO.findAllWithOffsetAndLimit(offset, limit)
                .stream()
                .map(WarehouseToPrintDTO::new)
                .collect(Collectors.toList());
    }

//    public List<>
}
