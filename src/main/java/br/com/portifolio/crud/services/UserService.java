package br.com.portifolio.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portifolio.crud.entities.User;
import br.com.portifolio.crud.repositories.UserRepository;
import br.com.portifolio.crud.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	//Aplicando o findAll
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//Aplicando o metodo findByID
	public User findById(Long id) {
		Optional<User> usuario = repository.findById(id);
		return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//Aplicando o metodo Insert
	public User insert(User usuario) {
		return repository.save(usuario);
	}
	
	//Aplicando o metodo delete
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			//TODO: handle exception
		}
		
	}
	
	//Aplicando o metodo Update
	public User update(Long id, User usuario) {
		
		try {
		User cadastro = repository.getReferenceById(id);
		updateDados(cadastro, usuario);
		return repository.save(cadastro);
		} catch (RuntimeException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateDados(User cadastro, User usuario) {
		cadastro.setNome(usuario.getNome());
		cadastro.setEmail(usuario.getEmail());
		cadastro.setTelefone(usuario.getTelefone());
		
	}
}
