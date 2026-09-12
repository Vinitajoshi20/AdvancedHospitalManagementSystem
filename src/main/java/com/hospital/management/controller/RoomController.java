package com.hospital.management.controller;

import com.hospital.management.entity.Room;
import com.hospital.management.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String roomPage(Model model) {

        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomService.getAllRooms());

        return "rooms";
    }

    @PostMapping("/rooms")
    public String saveRoom(@ModelAttribute("room") Room room) {

        roomService.saveRoom(room);

        return "redirect:/rooms";
    }

    @GetMapping("/rooms/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {

        model.addAttribute("room", roomService.getRoomById(id));
        model.addAttribute("rooms", roomService.getAllRooms());

        return "rooms";
    }

    @GetMapping("/rooms/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {

        roomService.deleteRoom(id);

        return "redirect:/rooms";
    }
}