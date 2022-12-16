package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.PublisherRepository;
import md.bookstore.dto.PublisherDTO;
import md.bookstore.entity.Publisher;
import md.bookstore.exception.OffsetOrLimitException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherService {
    private PublisherRepository publisherRepository;

    // Later implement Pageable!
    public List<PublisherDTO> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new OffsetOrLimitException(offset, limit);
        }
        return publisherRepository.findPublisherEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(PublisherDTO::new)
                .collect(Collectors.toList());
    }

    public List<PublisherDTO> getAll() {
        return publisherRepository.findAll()
                .parallelStream()
                .map(PublisherDTO::new)
                .collect(Collectors.toList());
    }

    public PublisherDTO get(Long id) {
        return new PublisherDTO(publisherRepository.findById(id).orElseThrow());
    }

    public void createPublisher(PublisherDTO publisherDTO) {
        if (publisherDTO == null) {
            throw new NullPointerException("Publisher is null. Publisher not created.");
        }
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherDTO.getPublisherName());
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        publisher.setPublisherName(publisherDTO.getPublisherName());
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
