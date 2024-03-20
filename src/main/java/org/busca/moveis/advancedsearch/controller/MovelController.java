package org.busca.moveis.advancedsearch.controller;

import jakarta.validation.Valid;
import org.busca.moveis.advancedsearch.dto.MovelDto;
import org.busca.moveis.advancedsearch.entities.Movel;
import org.busca.moveis.advancedsearch.exception.MovelNotFoundException;
import org.busca.moveis.advancedsearch.repository.MovelDAO;
import org.busca.moveis.advancedsearch.repository.MovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/moveis")
public class MovelController {

    private final MovelRepository repository;
    private final MovelDAO movelDAO;

    public MovelController(MovelRepository repository, MovelDAO movelDAO) {
        this.repository = repository;
        this.movelDAO = movelDAO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movel inserir(@RequestBody @Valid MovelDto movelDto) {
        Movel movel = Movel.builder()
                .id(UUID.randomUUID())
                .endereco(movelDto.getEndereco())
                .titulo(movelDto.getTitulo())
                .area(movelDto.getArea())
                .tipoImovel(movelDto.getTipoImovel())
                .preco(movelDto.getPreco())
                .createdAt(new Date())
                .condominio(movelDto.getCondominio())
                .iptu(movelDto.getIptu())
                .qtdeQuartos(movelDto.getQtdeQuartos())
                .qtdeBanheiros(movelDto.getQtdeBanheiros())
                .qtdeVagas(movelDto.getQtdeVagas())
                .descricao(movelDto.getDescricao())
                .tipoNegocio(movelDto.getTipoNegocio())
                .build();
        return repository.save(movel);
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listar2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam Map<String,String> allParams) {
        Pageable paging = PageRequest.of(page, size);
        Page<Movel> moveis = movelDAO.listAll(paging, allParams);
        Map<String, Object> response = new HashMap<>();

        response.put("content", moveis.getContent());
        response.put("currentPage", moveis.getNumber());
        response.put("totalItems", moveis.getTotalElements());
        response.put("totalPages", moveis.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> listar(
//                            @RequestParam(defaultValue = "0") int page,
//                            @RequestParam(defaultValue = "3") int size,
//                            @RequestParam(required = false) Integer banheiros,
//                            @RequestParam(required = false) Integer minArea,
//                            @RequestParam(required = false) Integer maxArea) {
//        Pageable paging = PageRequest.of(page, size);
//        Page<Movel> moveis = repository.findAll(paging);
//        Map<String, Object> response = new HashMap<>();
//
//        List<Movel> filtered = moveis.getContent();
//        if (banheiros != null) {
//            filtered = filtered.stream().filter(m -> Objects.equals(m.getQtdeBanheiros(), banheiros)).toList();
//        }
//
//        if (minArea != null && maxArea != null) {
//            filtered = filtered.stream().filter(m -> m.getArea() > minArea && m.getArea() < maxArea).toList();
//        } else {
//            if (minArea != null) {
//                filtered = filtered.stream().filter(m -> m.getArea() > minArea).toList();
//            }
//            if (maxArea != null) {
//                filtered = filtered.stream().filter(m -> m.getArea() < maxArea).toList();
//            }
//        }
//
//        response.put("content", filtered);
//        response.put("currentPage", moveis.getNumber());
//        response.put("totalItems", moveis.getTotalElements());
//        response.put("totalPages", moveis.getTotalPages());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movel buscarPorId(@PathVariable String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new MovelNotFoundException("Nenhum movel com o id " + id));
    }
}
