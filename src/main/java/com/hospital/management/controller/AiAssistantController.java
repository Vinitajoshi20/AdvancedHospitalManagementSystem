package com.hospital.management.controller;

import com.hospital.management.entity.User;
import com.hospital.management.service.AiAssistantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AiAssistantController {

    private final AiAssistantService aiAssistantService;

    public AiAssistantController(AiAssistantService aiAssistantService) {
        this.aiAssistantService = aiAssistantService;
    }

    @GetMapping("/ai-assistant")
    public String aiAssistant(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("name", user.getName());

        return "ai-assistant";
    }

    @PostMapping("/ai-assistant")
    public String askAssistant(
            @RequestParam("question") String question,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        String answer = aiAssistantService.getResponse(question);

        model.addAttribute("name", user.getName());
        model.addAttribute("question", question);
        model.addAttribute("answer", answer);

        return "ai-assistant";
    }
}