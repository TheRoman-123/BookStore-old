package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.PublisherRepository;
import md.bookstore.dto.PublisherDto;
import md.bookstore.entity.Publisher;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService {
    private PublisherRepository publisherRepository;

    public List<PublisherDto> getAll(
            Integer pageNumber,
            Integer pageSize,
            String sortCriteria,
            boolean desc
    ) {
        return new CommonService<Publisher>().getAll(
                pageNumber, pageSize, sortCriteria, desc,
                publisherRepository,
                PublisherDto::new
        );
    }

    public PublisherDto getPublisherDtoById(Long id) {
        return new PublisherDto(publisherRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Long createPublisher(PublisherDto publisherDto) {
        if (publisherRepository.existsPublisherByPublisherName(publisherDto.getPublisherName())) {
            throw new EntityExistsException("Publisher with provided name already exists");
        }
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherDto.getPublisherName());
        publisherRepository.save(publisher);
        return publisher.getId();
    }

    public void updatePublisher(Long id, PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        publisher.setPublisherName(publisherDto.getPublisherName());
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
