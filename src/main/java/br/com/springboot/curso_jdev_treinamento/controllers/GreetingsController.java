package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarios() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);

	}

	@PostMapping("/salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}

	@DeleteMapping("/deletar")
	@ResponseBody
	public ResponseEntity<String> deletarUsuario(@RequestParam Integer idusuario) {
		usuarioRepository.deleteById(idusuario);

		return new ResponseEntity<>("Usuário deletado com sucesso ", HttpStatus.OK);

	}

	@GetMapping("/buscarusuario")
	@ResponseBody
	public ResponseEntity<Usuario> buscaUsuario(@RequestParam(value = "idusuario") Integer idusuario) {

		Usuario usuario = usuarioRepository.findById(idusuario).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	@PutMapping("/editarusuario")
	@ResponseBody
	public ResponseEntity<?> editarUsuario(@RequestBody Usuario usuario) {

		if (usuario.getId() == null) {
			return new ResponseEntity<String>("ID não Foi encontrado para atualização", HttpStatus.OK);
		}

		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}

	@GetMapping("/buscarpornome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {

		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}
}
