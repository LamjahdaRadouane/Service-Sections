package com.example.servicesections.repositories;

import com.example.servicesections.entities.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SectionRepository extends JpaRepository<Section, Long> {
    Page<Section> findByNomContains(String kw, Pageable pageable);
}