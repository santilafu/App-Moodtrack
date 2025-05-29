package com.moodtrack.moodtrack.controller;


import com.moodtrack.moodtrack.model.MoodEntry;
import com.moodtrack.moodtrack.repository.MoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller // Le dice a Spring que esta clase es un controlador web
public class Web {

    @Autowired
    private MoodEntryRepository moodEntryRepository;

    // Página de inicio
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Mostrar el formulario para registrar emoción
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("moodEntry", new MoodEntry());
        return "form";
    }

    // Guardar la emoción enviada desde el formulario
    @PostMapping("/form")
    public String submitForm(@ModelAttribute MoodEntry moodEntry) {
        moodEntry.setFecha(LocalDate.now());
        moodEntryRepository.save(moodEntry);
        return "redirect:/list";
    }

    // Mostrar historial de emociones
    @GetMapping("/list")
    public String showList(Model model) {
        List<MoodEntry> lista = moodEntryRepository.findAll();
        model.addAttribute("moodList", lista);
        return "list";
    }

    // Mostrar estadísticas de emociones
    @GetMapping("/stats")
    public String showStats(Model model) {
        List<MoodEntry> lista = moodEntryRepository.findAll();

        // Agrupar emociones y contarlas
        Map<String, Long> resumen = lista.stream()
                .collect(Collectors.groupingBy(MoodEntry::getEmocion, Collectors.counting()));

        model.addAttribute("stats", resumen);
        return "stats";
    }
}
