package com.sv.serfinsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sv.serfinsa.entity.Producto;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
