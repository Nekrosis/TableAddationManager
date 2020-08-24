package com.example.table.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer>, PersonRepositoryExt {

    List<PersonEntity> findAllByPartnerIdIsNull();

    @Modifying
    @Transactional
    @Query("UPDATE PersonEntity SET partnerId = :partnerId WHERE id = :id")
    void setMarried(Integer partnerId, Integer id);

}
