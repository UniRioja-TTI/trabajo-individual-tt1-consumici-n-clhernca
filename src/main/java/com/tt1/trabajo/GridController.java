package com.tt1.trabajo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import interfaces.InterfazGrid;
import modelo.CeldaColor;

@Controller
public class GridController {

    private final InterfazGrid gridService;

    public GridController(InterfazGrid gridService) {
        this.gridService = gridService;
    }

    @GetMapping("/grid")
    public String grid(@RequestParam int tok, Model model) {
        int ancho = gridService.obtenerAncho(tok, "usuario");
        Map<Integer, List<CeldaColor>> celdasPorTiempo = 
            gridService.obtenerCeldasPorTiempo(tok, "usuario");

        model.addAttribute("ancho", ancho);
        model.addAttribute("celdasPorTiempo", celdasPorTiempo);
        return "grid";
    }
}