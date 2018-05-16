package com.example.demo.Controller;

import com.example.demo.Class.Category;
import com.example.demo.Class.Journal;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    JournalRepository journalRepository;



    @RequestMapping("/")
    public String showIndex(Model model HttpServletRequest request)
    {
        String theName = request.getParameter("name");
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("journal",journalRepository.findJournalByArtistNameIgnoreCase(theName));
        return "index";
    }
    @PostMapping("/savecategories")
    public String saveCategories(@Valid @ModelAttribute("journal") JournalRepository journalRepository, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "index";
        }
        journalRepository.save(Journal);
        return "redirect:/?name="+journalRepository.getArtistName();
    }

    @PostConstruct
    public void addUsersAndColours()
    {

        //Colours
        Category c = new Category();
        c.setName("Illustration");
        categoryRepository.save(c);

        c = new Category();
        c.setName("Graphic Design");
        categoryRepository.save(c);

        c = new Category();
        c.setName("Identity");
        categoryRepository.save(c);

        c = new Category();
        c.setName("Branding");
        categoryRepository.save(c);

        c = new Category();
        c.setName("Web Design");
        categoryRepository.save(c);


    }
}
