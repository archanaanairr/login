package com.jwt.LoginBackend.repo;

import com.jwt.LoginBackend.model.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepo extends JpaRepository<Emergency, Integer> {

}
