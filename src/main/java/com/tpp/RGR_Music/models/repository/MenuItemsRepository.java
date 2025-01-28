package com.tpp.RGR_Music.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpp.RGR_Music.models.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer> {
}
