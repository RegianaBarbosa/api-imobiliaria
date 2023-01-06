package edu.ifma.lpweb.imobiliaria.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ifma.lpweb.imobiliaria.domain.exception.NegocioException;
import edu.ifma.lpweb.imobiliaria.domain.model.Cliente;
import edu.ifma.lpweb.imobiliaria.domain.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;

    public Iterable<Cliente> todos(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscaPor(Integer id){
        return clienteRepository.findById(id);
    }

    public Iterable<Cliente> buscaPor(String nome){
        return clienteRepository.findByNomeContaining(nome );
    }

    @Transactional
    public Cliente salva(Cliente cliente){
        boolean emailEmUso = clienteRepository
        .findByEmail(cliente.getEmail())
        .stream()
        .anyMatch(clienteExistente -> !Objects.equals(clienteExistente, cliente));

        if(emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void removePelo(Integer id){
        clienteRepository.deleteById(id);
    }

    public boolean naoExisteClienteCom(Integer id ) {
        return !clienteRepository.existsById(id );
    }

    public Page<Cliente> buscaPaginada(Pageable page) {
        return clienteRepository.findAll(page );

    }

    public Page<Cliente> buscaPor(String nome, Pageable paginacao) {
        return clienteRepository.findByNomeContaining(nome, paginacao );
    }

}

