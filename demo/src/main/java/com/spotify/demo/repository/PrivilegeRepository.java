package com.spotify.demo.repository;

import com.spotify.demo.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>  {
    Privilege findByName(String name);
}
