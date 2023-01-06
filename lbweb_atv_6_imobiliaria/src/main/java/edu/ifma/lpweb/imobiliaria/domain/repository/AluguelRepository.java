package edu.ifma.lpweb.imobiliaria.domain.repository;

import edu.ifma.lpweb.imobiliaria.domain.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer>{
    
}
