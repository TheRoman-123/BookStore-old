package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.PublisherDto;
import md.bookstore.entity.Publisher;
import md.bookstore.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final CommonService<Publisher> commonService;
    private final PublisherRepository publisherRepository;

    public List<PublisherDto> findAll(
            Integer pageNumber,
            Integer pageSize,
            String sortCriteria,
            boolean desc
    ) {
        return commonService.getAll(
                pageNumber, pageSize, sortCriteria, desc,
                publisherRepository,
                PublisherDto::new
        );
    }

    public PublisherDto getPublisherDtoById(Long id) {
        return new PublisherDto(publisherRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.getReferenceById(id);
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
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        publisher.setPublisherName(publisherDto.getPublisherName());
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
