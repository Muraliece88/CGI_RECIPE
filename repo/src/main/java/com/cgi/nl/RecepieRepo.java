package com.cgi.nl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;


@Repository
public interface RecepieRepo extends JpaRepository<Recipe,Long> {
    @Query("SELECT DISTINCT r FROM Recipe r " +
            "JOIN FETCH r.ingredients i " +
            "WHERE (:suitableFor IS NULL OR r.suitableFor = :suitableFor) " +
            "AND (:veg IS NULL OR r.veg = :veg) " +
            "AND (:instructions IS NULL OR r.instructions LIKE %:instructions%) " +
            "AND (:ingredientsIn IS NULL  OR i.itemName IN :ingredientsIn)" +
            "AND (:ingredientsEx IS NULL  OR i.itemName NOT IN :ingredientsEx)")
    List<Recipe> findByConditions(
            @Param("suitableFor") Integer suitableFor,
            @Param("veg") Boolean veg,
            @Param("instructions") String instructions,
            @Param("ingredientsIn") String ingredientsIn,
            @Param("ingredientsEx")String ingredientsEx);

}