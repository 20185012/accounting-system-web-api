package com.accountingsystem_web_api.accountingsystemwebapi.Repository;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Receivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivableRepository extends JpaRepository<Receivable, Integer> {
}
