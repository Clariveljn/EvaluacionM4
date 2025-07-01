package com.healthtrack.backend.service;

import com.healthtrack.backend.model.Usuario;
import com.healthtrack.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioEjemplo;

    @BeforeEach
    void setUp() {
        usuarioEjemplo = new Usuario("juan", 70);
    }

    @Test
    void registrarUsuario_CuandoNoExiste_DeberiaRegistrar() {
        when(usuarioRepository.existsByNombre("juan")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEjemplo);

        Usuario resultado = usuarioService.registrarUsuario(usuarioEjemplo);

        assertEquals("juan", resultado.getNombre());
        assertEquals(70, resultado.getPeso());
        verify(usuarioRepository).save(usuarioEjemplo);
    }

    @Test
    void registrarUsuario_CuandoExiste_DeberiaLanzarExcepcion() {
        when(usuarioRepository.existsByNombre("juan")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.registrarUsuario(usuarioEjemplo);
        });

        assertEquals("El usuario ya existe.", exception.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void actualizarPeso_CuandoUsuarioExiste_DeberiaActualizar() {
        when(usuarioRepository.findByNombre("juan")).thenReturn(Optional.of(usuarioEjemplo));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEjemplo);

        Usuario resultado = usuarioService.actualizarPeso("juan", 75);

        assertEquals(75, resultado.getPeso());
        verify(usuarioRepository).save(usuarioEjemplo);
    }

    @Test
    void actualizarPeso_CuandoUsuarioNoExiste_DeberiaLanzarExcepcion() {
        when(usuarioRepository.findByNombre("pedro")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.actualizarPeso("pedro", 80);
        });

        assertEquals("Usuario no encontrado.", exception.getMessage());
        verify(usuarioRepository, never()).save(any());
    }
}

