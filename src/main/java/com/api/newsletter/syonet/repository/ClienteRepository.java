package com.api.newsletter.syonet.repository;

import com.api.newsletter.syonet.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
