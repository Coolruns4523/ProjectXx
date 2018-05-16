package com.example.demo.Controller;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Apps.CloudinaryConfig;
import com.example.demo.Class.Journal;
import com.example.demo.Repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;

@Controller
public class CloudController {
    @Autowired
    JournalRepository journalRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listActors(Model model){
        model.addAttribute("jounals",journalRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String newJournal(Model model){
        model.addAttribute("journal", new Journal());
        return "form";
    }

    @PostMapping("/add")
    public String processJournal(@ModelAttribute Journal journal,
                                 @RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            journal.setImage(uploadResult.get("url").toString());
            journalRepository.save(journal);
        }catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }

}
