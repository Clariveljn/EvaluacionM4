package com.healthtrack.backend.service;

import com.healthtrack.backend.model.Usuario;
import com.healthtrack.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByNombre(usuario.getNombre())) {
            throw new RuntimeException("El usuario ya existe.");
        }
        usuario.setUltimaActualizacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarPeso(String nombre, double nuevoPeso) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombre(nombre);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado.");
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setPeso(nuevoPeso);
        usuario.setUltimaActualizacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuario(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
}

