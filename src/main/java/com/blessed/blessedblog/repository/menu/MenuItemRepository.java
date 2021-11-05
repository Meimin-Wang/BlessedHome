package com.blessed.blessedblog.repository.menu;

import com.blessed.blessedblog.entity.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
