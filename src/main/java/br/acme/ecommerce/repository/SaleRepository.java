package br.acme.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.acme.ecommerce.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}