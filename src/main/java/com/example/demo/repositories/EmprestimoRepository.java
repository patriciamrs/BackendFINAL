package com.example.demo.repositories;

import com.example.demo.entities.EmprestimoEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoEntities, UUID> {
}
