package com.example.servicesections;

import com.example.servicesections.entities.Section;
import com.example.servicesections.repositories.SectionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceSectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSectionsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(SectionRepository sectionRepository){
		return args -> {
			sectionRepository.save(
					new Section(null, "SectionA","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionB","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionC","SMPC", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionA","SVTU", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionB","SVTU", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionC","SMPC", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionA","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionB","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionC","SMPC", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionA","SVTU", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionB","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionC","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionA","SMIA", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionB","SVTU", "Autaumne", "S1"));
			sectionRepository.save(
					new Section(null, "SectionC","SMIA", "Autaumne", "S1"));

			sectionRepository.findAll().forEach(s->{
				System.out.println(s.getNom());
			});
		};
	}
}
