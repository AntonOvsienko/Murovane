package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Woman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WomanRepository extends JpaRepository<Woman,Integer>, CrudRepository<Woman, Integer> {

    @Query (value = "SELECT w FROM Woman w" +
            " WHERE w.settlement.id = :id AND NOT EXISTS (" +
            " SELECT m FROM Man m WHERE m.wife.id = w.id)")
    List<Woman> getWomanUnderMarried(@Param ("id") Integer id);

    @Query (value = "SELECT w FROM Woman w" +
            " WHERE (w.settlement.id = :id AND w.pregnant IS FALSE AND w.pregnantRecess = 0)" +
            " AND EXISTS (SELECT m FROM Man m WHERE m.wife.id = w.id)")
    List<Woman> getWomanMarriedNotPregnant(@Param ("id") Integer id);

    @Query (value = "SELECT w FROM Woman w" +
            " WHERE (w.settlement.id = :id AND w.pregnant IS TRUE)")
    List<Woman> getWomanPregnant(@Param ("id") Integer id);

    @Query (value = "SELECT w FROM Woman w" +
            " WHERE (w.settlement.id = :id AND w.pregnantRecess > 0)")
    List<Woman> getWomanOnPregnantRecess(Integer id);
}
