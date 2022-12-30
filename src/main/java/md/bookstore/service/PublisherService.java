package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.PublisherRepository;
import md.bookstore.dto.PublisherDto;
import md.bookstore.entity.Publisher;
import md.bookstore.exception.IllegalPageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherService {
    private PublisherRepository publisherRepository;

    // Later implement Pageable!
    public List<PublisherDto> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new IllegalPageException(offset, limit);
        }
        return publisherRepository.findPublisherEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(PublisherDto::new)
                .collect(Collectors.toList());
    }

    public List<PublisherDto> getAll() {
        return publisherRepository.findAll()
                .parallelStream()
                .map(PublisherDto::new)
                .collect(Collectors.toList());
    }

    public PublisherDto get(Long id) {
        return new PublisherDto(publisherRepository.findById(id).orElseThrow());
    }

    public void createPublisher(PublisherDto publisherDto) {
        if (publisherDto == null) {
            throw new NullPointerException("Publisher is null. Publisher not created.");
        }
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherDto.getPublisherName());
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Long id, PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.getReferenceById(id);
        publisher.setPublisherName(publisherDto.getPublisherName());
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
