package com.healthtrack.backend.controller;

import com.healthtrack.backend.model.Usuario;
import com.healthtrack.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @PutMapping("/actualizar/{nombre}")
    public Usuario actualizarPeso(@PathVariable String nombre, @RequestParam double nuevoPeso) {
        return usuarioService.actualizarPeso(nombre, nuevoPeso);
    }

    @GetMapping("/{nombre}")
    public Optional<Usuario> obtenerUsuario(@PathVariable String nombre) {
        return usuarioService.obtenerUsuario(nombre);
    }
}

