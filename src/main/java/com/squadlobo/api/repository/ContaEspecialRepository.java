package com.squadlobo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squadlobo.api.model.ContaEspecial;

@Repository
public interface ContaEspecialRepository extends JpaRepository<ContaEspecial, String> {

}
