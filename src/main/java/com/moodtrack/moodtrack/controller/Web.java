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

@Controller
public class Web {

    @Autowired
    private MoodEntryRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("moodEntry", new MoodEntry());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute MoodEntry moodEntry) {
        moodEntry.setFecha(LocalDate.now());
        repository.save(moodEntry);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("moodList", repository.findAll());
        return "list";
    }

    @GetMapping("/stats")
    public String showStats(Model model) {
        List<MoodEntry> entradas = repository.findAll();
        Map<String, Long> resumen = entradas.stream()
                .collect(Collectors.groupingBy(MoodEntry::getEmocion, Collectors.counting()));
        model.addAttribute("stats", resumen);
        return "stats";
    }
}
