package edu.ifma.lpweb.imobiliaria.domain.repository;
import edu.ifma.lpweb.imobiliaria.domain.model.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Iterable<Cliente> findByNomeContaining(String nome );
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);
    Optional<Cliente> findByEmail(String email);

}