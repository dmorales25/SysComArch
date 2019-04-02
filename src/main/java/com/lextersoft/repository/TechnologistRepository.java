package com.lextersoft.repository;

import com.lextersoft.domain.Technologist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Technologist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TechnologistRepository extends JpaRepository<Technologist, Long> {

}
