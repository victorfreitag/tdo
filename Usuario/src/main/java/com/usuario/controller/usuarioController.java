package com.usuario.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.DTO.UsuarioDTO;
import com.usuario.entities.Usuario;
import com.usuario.service.usuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

	private final usuarioService usuarioService;

	@Autowired
	public usuarioController(usuarioService usuarioservice) {
		this.usuarioService = usuarioservice;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscarporId(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> buscaTodosUsuarioControl() {
		List<Usuario> Usuario = usuarioService.buscarTodos();
		return ResponseEntity.ok(Usuario);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO salvaUsuario = usuarioService.salvar(usuarioDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO UsuarioDTO) {
		UsuarioDTO alteraUsuario = usuarioService.atualizar(id, UsuarioDTO);
		if (alteraUsuario != null) {
			return ResponseEntity.ok(UsuarioDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> apagaUsuarioControl(@PathVariable Long id) {
		boolean apagar = usuarioService.deletar(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}