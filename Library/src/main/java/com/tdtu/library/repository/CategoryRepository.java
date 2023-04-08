package com.tdtu.library.repository;

import com.tdtu.library.dto.CategoryDto;
import com.tdtu.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /* Admin */
    @Query("SELECT c FROM Category c WHERE c.isActivated=true and c.isDeleted=false")
    List<Category> findAllByActivated();

    /* Customer */
    @Query("SELECT new com.tdtu.library.dto.CategoryDto(c.id, c.name, count(p.category.id)) FROM Category c INNER JOIN Product p on p.category.id=c.id" +
            " WHERE c.isActivated=true and c.isDeleted=false GROUP BY c.id")
    List<CategoryDto> getCategoryAndProducts();
}
