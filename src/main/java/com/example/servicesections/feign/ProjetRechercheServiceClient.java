package com.example.servicesections.feign;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="Service-ProjetRecherche")
public interface ProjetRechercheServiceClient {
    @GetMapping("/projetRecherches/{id}")
    public ProjetRecherche findProjetRecherechesById(@PathVariable(name="id") Long id);
    @GetMapping("/projetRecherches")
    public PagedModel<ProjetRecherche> findAllProjetRechereches();
}