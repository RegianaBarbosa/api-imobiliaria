package edu.ifma.lpweb.imobiliaria.domain.repository;

import edu.ifma.lpweb.imobiliaria.domain.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer>{
    
}
