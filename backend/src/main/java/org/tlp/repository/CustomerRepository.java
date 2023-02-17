package org.tlp.repository;

import org.springframework.data.repository.CrudRepository;
import org.tlp.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAll();
}
