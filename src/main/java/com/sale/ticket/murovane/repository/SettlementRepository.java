package com.sale.ticket.murovane.repository;

import com.sale.ticket.murovane.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SettlementRepository extends JpaRepository<Settlement,Integer>, CrudRepository<Settlement, Integer> {

    @Query (value = "SELECT s FROM Settlement s" +
            " JOIN Man m ON m.settlement = s.id" +
            " JOIN Woman w ON w.settlement = s.id" +
            " WHERE s.id = :id")
    Settlement getSettlementId(@Param ("id") Integer id);

}
