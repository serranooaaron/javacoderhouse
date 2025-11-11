package com.serranoaaron.app.repository;

import com.serranoaaron.app.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Administrador, Long> {

}
