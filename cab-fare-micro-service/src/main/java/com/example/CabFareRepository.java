package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CabFareRepository extends JpaRepository<CabFare, Long> {
    @Query("SELECT cf.amount FROM CabFare cf WHERE cf.fromLocation=:from AND cf.toLocation=:to AND cf.typeOfCab=:type")
    Double findFare(@Param("from") String from, @Param("to") String to, @Param("type") String type);
}