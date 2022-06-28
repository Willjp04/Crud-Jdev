package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {

		return "Bem vindo a sua API " + name + "!";

	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{nome}",method = RequestMethod.GET)

	public String retornaOlaMundo(@PathVariable String nome, Integer idade) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setIdade(15);
		usuarioRepository.save(usuario);
				
		return  nome;

	}
	
	@GetMapping("/listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		
	}

}
