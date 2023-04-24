package pl.coderslab.legoinvestormanager.legoSet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legoSet")
public class LegoSetController {

    private final LegoSetService service;

    public LegoSetController(LegoSetService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public LegoSetDTO getLegoSet(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LegoSetDTO createLegoSet(@RequestBody LegoSet legoSet) {
        return service.create(legoSet);
    }

    @PutMapping("/{id}")
    public LegoSetDTO updateLegoSet(@PathVariable Long id, @RequestBody LegoSet legoSet) {
        return service.update(id, legoSet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLegoSet(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/all")
    public List<LegoSetDTO> getAllLegoSets() {
        return service.readAll();
    }

    @PutMapping("/price/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void updateLegoSetPrice(@PathVariable Long id) {
        service.updateCurrentPrice(id);
    }

    @PutMapping("/price/all")
    @ResponseStatus(HttpStatus.FOUND)
    public void updateAllLegoSetPrices() {
        service.readAll().forEach( l -> service.updateCurrentPrice(l.getId()));
    }

}
