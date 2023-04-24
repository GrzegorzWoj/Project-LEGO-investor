package pl.coderslab.legoinvestormanager.legoSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<LegoSetDTO> readAll() {
        return repository.findAll().stream()
                .map(mapper::mapToLegoSetDTO)
                .collect(Collectors.toList());
    }

    public void updateCurrentPrice(Long id) {
        LegoSet legoSet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegoSet not found"));
        try {
            Document doc = Jsoup.connect("https://promoklocki.pl/" + legoSet.getLegoSetNumber())
                    .userAgent("Mozilla" /* /5.0 (X11; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0"*/)
                    .get();
            String price = doc.select("div.col-md-6 dd a.bprice").text();
            legoSet.setLowestCurrentPrice(Double.parseDouble(price.substring(0, price.length()-2).replace(',', '.')));
            repository.save(legoSet);
        } catch (IOException e) {
            System.out.println("******************** Nie znaleziono źródła ceny *******************");
//            throw new RuntimeException(e);
        }
    }


}

