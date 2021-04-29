package com.cicero.heroesapi.controller;

import com.cicero.heroesapi.service.HeroesService;
import com.cicero.heroesapi.repository.HeroesRepository;
import com.cicero.heroesapi.model.Heroes;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.cicero.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;


@RequiredArgsConstructor
@RestController
@Slf4j
public class HeroesController {

    private final HeroesService heroesService;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    //buscar todos
    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
        log.info("Request the list off all heroes");
        return heroesService.findAll();
    }

    //buscar por id
    @GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        log.info("Request the hero with id {}", id);
        return heroesService.findByIdHero(id)
                .map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //criar um novo herói
    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("A new heto was created");
        return heroesService.save(heroes);
    }

    //deletar um herói
    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(code=HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteByIdHero(@PathVariable String id){
        heroesService.deleteByIdHero(id);
        log.info("deleting a hero with id {}", id);
        return  Mono.just(HttpStatus.CONTINUE);
    }

}
