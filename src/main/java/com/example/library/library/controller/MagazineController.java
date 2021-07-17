package com.example.library.library.controller;

import com.example.library.library.model.Magazine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Collections;

@Controller
@RequestMapping("/references")
public class MagazineController {

    @GetMapping("/magazines")
    public String getAll(Model model){
        Magazine magazine = new Magazine(1L, "PlayBoy", Calendar.getInstance().getTime(),
                50L, 1000L);
        model.addAttribute("magazines", Collections.singletonList(magazine));
        return "references/magazine/magazine";
    }
}
