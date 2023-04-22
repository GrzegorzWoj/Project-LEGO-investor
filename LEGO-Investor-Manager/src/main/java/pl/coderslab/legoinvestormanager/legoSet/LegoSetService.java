package pl.coderslab.legoinvestormanager.legoSet;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LegoSetService {

    private final LegoSetRepository repository;
    private final LegoSetMapper mapper;


    public LegoSetService(LegoSetRepository repository, LegoSetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public LegoSetDTO read(Long id) {
        return mapper.mapToLegoSetDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegoSet not found")));
    }

    public LegoSetDTO create(LegoSet legoSet) {
        return mapper.mapToLegoSetDTO(repository.save(legoSet));
    }

    public LegoSetDTO update(Long id, LegoSet legoSet) {
        LegoSet lego = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegoSet not found"));
        if (!lego.getId().equals(legoSet.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        repository.save(legoSet);
        return mapper.mapToLegoSetDTO(legoSet);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
