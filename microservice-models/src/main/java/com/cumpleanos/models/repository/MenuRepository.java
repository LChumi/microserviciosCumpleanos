package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
