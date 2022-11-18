package com.example.servicesections.web;

import com.example.servicesections.entities.Section;
import com.example.servicesections.repositories.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class SectionController {
    private SectionRepository sectionRepository;
    @GetMapping(path = "/index")
    public String sections(Model model,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5") int size,
                            @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        Page<Section> pageSections=sectionRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listSections",pageSections.getContent());
        model.addAttribute("pages",new int[pageSections.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "sections";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        sectionRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "template";
    }
    @GetMapping("/sections")
    @ResponseBody
    public List<Section> lisSections(){
        return sectionRepository.findAll();
    }

    @RequestMapping(value="/form",method= RequestMethod.GET)
    public String formSection(Model model){
        model.addAttribute("section", new Section());
        return "FormSection";
    }
    @RequestMapping(value="/SaveSection",method= RequestMethod.POST)
    public String save(@Valid Section et, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "FormSection";
        }
        sectionRepository.save(et);
        return "redirect:/index";
    }
    @RequestMapping(value="/edit")
    public String edit(Long id, Model model){
        Section et = sectionRepository.getOne(id);
        model.addAttribute("section", et);
        return "EditSection";
    }
    @RequestMapping(value="/UpdateSection",method= RequestMethod.POST)
    public String update(@Valid Section et, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "EditSection";
        }
        sectionRepository.save(et);
        return "redirect:/index";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute("errorMessage", "Not Authorized");
        return "errors";
    }
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String fileName = "sections.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" +fileName;
        response.setHeader(headerKey, headerValue);
        List<Section> listSections = sectionRepository.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Nom", "Filiere", "Session", "Semestre"};
        String[] nameMapping = {"id", "nom", "filiere", "session", "semestre"};
        csvWriter.writeHeader(csvHeader);
        for (Section section : listSections) {
            csvWriter.write(section, nameMapping);
        }
        csvWriter.close();
    }

    /*
    @GetMapping("/ms")
    public String ms(Model model){
        PagedModel<Entity> pageMs = keycloakRestTemplate.getForObject("http://localhost:portMs/nomMs", PagedModel.class);
        model.addAttribute("ms", pageMs);
        return "ms";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String, String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
        Map<String, String> map = new HashMap<>();
        map.put("access_token", keycloakSecurityContext.getTokenString());
        return map;
    }*/
}