package com.coweco.server.repositories;

import com.coweco.server.models.SessionAvito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionsRepository extends JpaRepository<SessionAvito, Long> {

}
