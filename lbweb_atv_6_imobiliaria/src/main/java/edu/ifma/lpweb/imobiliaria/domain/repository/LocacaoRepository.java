package edu.ifma.lpweb.imobiliaria.domain.repository;

import edu.ifma.lpweb.imobiliaria.domain.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer>{
    
}