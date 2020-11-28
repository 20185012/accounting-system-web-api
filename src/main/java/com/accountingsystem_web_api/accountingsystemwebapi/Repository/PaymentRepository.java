package com.accountingsystem_web_api.accountingsystemwebapi.Repository;

import com.accountingsystem_web_api.accountingsystemwebapi.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
