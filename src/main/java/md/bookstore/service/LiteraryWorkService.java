package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dto.LiteraryWorkDto;
import md.bookstore.entity.LiteraryWork;
import md.bookstore.repository.LiteraryWorkRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class LiteraryWorkService {
    private CommonService<LiteraryWork> commonService;
    private LiteraryWorkRepository literaryWorkRepository;

    public List<LiteraryWorkDto> findAll(Integer pageNumber, Integer pageSize, String sortCriteria, boolean desc) {
        return commonService.getAll(
                pageNumber, pageSize, sortCriteria, desc,
                literaryWorkRepository,
                LiteraryWorkDto::new
        );
    }

    public LiteraryWorkDto findLiteraryWorkById(Long id) {
        return new LiteraryWorkDto(literaryWorkRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    public Long createLiteraryWork(@Valid LiteraryWorkDto literaryWorkDto) {
        if (literaryWorkRepository.existsByTitle(literaryWorkDto.getTitle())) {
            throw new EntityExistsException("Literary work with title" + literaryWorkDto.getTitle() + " already exists");
        }
        LiteraryWork literaryWork = new LiteraryWork();
        literaryWork.setTitle(literaryWorkDto.getTitle());
        literaryWorkRepository.save(literaryWork);

        return literaryWork.getId();
    }

    public void updateLiteraryWork(Long id, LiteraryWorkDto literaryWorkDto) {
        LiteraryWork literaryWork = literaryWorkRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        literaryWork.setTitle(literaryWorkDto.getTitle());
        literaryWorkRepository.save(literaryWork);
    }

    public void deleteLiteraryWork(Long id) {
        literaryWorkRepository.deleteById(id);
    }
}
