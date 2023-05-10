package pl.coderslab.legoinvestormanager.legoSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import pl.coderslab.legoinvestormanager.investment.InvestmentDTO;
import pl.coderslab.legoinvestormanager.investment.InvestmentService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LegoSetService {

    private final LegoSetRepository repository;
    private final InvestmentService investmentService;
    private final LegoSetMapper mapper;


    public LegoSetService(LegoSetRepository repository, InvestmentService investmentService, LegoSetMapper mapper) {
        this.repository = repository;
        this.investmentService = investmentService;
        this.mapper = mapper;
    }

    public LegoSetDTO read(Long id) {
        return mapper.mapLegoSetToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegoSet not found")));
    }

    public LegoSetDTO create(LegoSetDTO legoSetDTO) {
        LegoSet legoSet = mapper.mapDTOToLegoSet(legoSetDTO);
        return mapper.mapLegoSetToDTO(repository.save(legoSet));
    }

    public LegoSetDTO update(Long id, LegoSetDTO legoSetDTO) {
        LegoSet legoSet = mapper.mapDTOToLegoSet(legoSetDTO);
        LegoSet lego = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegoSet not found"));
        if (!lego.getId().equals(legoSet.getId())) {
            throw new IllegalArgumentException("Ids mismatch");
        }
        repository.save(legoSet);
        return mapper.mapLegoSetToDTO(legoSet);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<LegoSetDTO> readAll() {
        return repository.findAll().stream()
                .map(mapper::mapLegoSetToDTO)
                .collect(Collectors.toList());
    }

    public List<LegoSetDTO> readAllByPortfolioId(Long id) {
        List<InvestmentDTO> investmentDTOList = investmentService.readAllByPortfolioId(id);
        return investmentDTOList.stream()
                .map(l -> read(l.getLegoSetId()))
//                .map(l -> mapper.mapLegoSetToDTO(repository.findById(l.getLegoSetId())
//                        .orElseThrow(() -> new EntityNotFoundException("LegoSet not found"))))
                .distinct()
                .collect(Collectors.toList());
    }

    public String updateCurrentPrice(Long id) {
        LegoSet legoSet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LegoSet not found"));
        Long legoSetNumber = legoSet.getLegoSetNumber();
        String url = "https://promoklocki.pl/" + legoSetNumber;
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla" /* /5.0 (X11; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0"*/)
                    .get();
            String price = doc.select("div.col-md-6 dd a.bprice").text();
            legoSet.setLowestCurrentPrice(Double.parseDouble(price.substring(0, price.length() - 2).replace(',', '.')));
            repository.save(legoSet);
            return legoSetNumber + " - zaktualizowano cenę";
        } catch (IOException e) {
            return legoSetNumber + " - Nie znaleziono źródła ceny: " + url;
//            FOR THE FUTURE: content of exception should be saved in log files
//            throw new NotFoundException(e.getMessage());
        }
    }

    //TODO change createLegoSet method to upload all lego set information from url
//    private LegoSetDTO loadLegoSetDataFromUrl(String legoSetNumber) {
//        LegoSetDTO legoSet = new LegoSetDTO();
//        String url = "https://promoklocki.pl/" + legoSetNumber;
//        try {
//            Document doc = Jsoup.connect(url)
//                    .userAgent("Mozilla" /* /5.0 (X11; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0"*/)
//                    .get();
//            String price = doc.select("div.col-md-6 dd a.bprice").text();
//            legoSet.setLowestCurrentPrice(Double.parseDouble(price.substring(0, price.length() - 2).replace(',', '.')));
//            legoSet.setLegoSetName();
//            legoSet.setSeries();
//            legoSet.setReleaseYear();
//            legoSet.setAmountOfElements();
//            legoSet.setAmountOfMinifigures();
//            legoSet.setOriginalPrice();
//            legoSet.setLowestCurrentPrice();
//            return legoSet;
//        } catch (IOException e) {
//            return legoSet;
//        }
//    }


}

