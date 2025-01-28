package com.tpp.RGR_Music.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpp.RGR_Music.models.Visitors;

@Repository
public interface VisitorsRepository extends JpaRepository<Visitors, Integer> {
}