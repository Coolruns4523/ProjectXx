package com.example.demo.Controller;

import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JournalController {
    @Autowired
    JournalRepository journalRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired

}
