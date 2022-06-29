package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired /*IC/ CD ou CDI - Injecao de depedencia*/
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String grretingText(@PathVariable String name) {
		return "Milhas Lucrativas: " + name + "!";
	}
	
	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		
		usuarioRepository.save(usuario);
		
		return "Olá mundo " + nome;
	}
	
	@GetMapping(value= "listatodos") /* Nosso primeiro método de API */
	@ResponseBody /* Retorna os dados para o corpo da resposta*/
	public ResponseEntity<List<Usuario>> listaUsuario (){
		
		List<Usuario> usuarios = usuarioRepository.findAll(); /* Executa a consulta no banco de dados */
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /* Retorna lista em JSON.*/
		
	}

}
