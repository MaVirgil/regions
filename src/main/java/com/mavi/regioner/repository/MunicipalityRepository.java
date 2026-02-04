package com.mavi.regioner.repository;

import com.mavi.regioner.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalityRepository extends JpaRepository<Municipality, String> {
}
