package pl.coderslab.legoinvestormanager.legoSet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public LegoSetDTO createLegoSet(@RequestBody LegoSetDTO legoSetDTO) {
        return service.create(legoSetDTO);
    }

    @PutMapping("/{id}")
    public LegoSetDTO updateLegoSet(@PathVariable Long id, @RequestBody LegoSetDTO legoSetDTO) {
        return service.update(id, legoSetDTO);
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

    @Operation(summary = "Update price", description = "Update lowest price of LEGO Set with provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "successfully updated price",
                    content = @Content(schema = @Schema(implementation = LegoSetDTO.class))),
            @ApiResponse(responseCode = "500", description = "LegoSet not found")})
    @PutMapping("/price/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public String updateLegoSetPrice(@PathVariable Long id) {
        return service.updateCurrentPrice(id);
    }

    @Operation(summary = "Update all prices", description = "Update lowest price of all LEGO Sets in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "successfully updated prices",
                    content = @Content(schema = @Schema(implementation = LegoSetDTO.class))),
            @ApiResponse(responseCode = "500", description = "Something goes wrong. Some of the prices may not have been updated.")})
    @PutMapping("/price/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<String> updateAllLegoSetPrices() {
        return service.readAll().stream()
                .map(l -> service.updateCurrentPrice(l.getId()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Update prices in portfolio", description = "Update lowest price of all LEGO Sets in portfolio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "successfully updated prices",
                    content = @Content(schema = @Schema(implementation = LegoSetDTO.class))),
            @ApiResponse(responseCode = "500", description = "Something goes wrong. Some of the prices may not have been updated.")})
    @PutMapping("/price/all/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<String> updateAllByPortfolioId(@PathVariable Long id) {
        return service.readAllByPortfolioId(id).stream()
                .map(l -> service.updateCurrentPrice(l.getId()))
                .collect(Collectors.toList());
    }

}
