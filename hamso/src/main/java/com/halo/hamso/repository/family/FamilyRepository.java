package com.halo.hamso.repository.family;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family,Long> {

    Family findByFamilyName(String familyName);
}
