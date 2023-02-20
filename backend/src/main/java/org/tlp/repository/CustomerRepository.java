package org.tlp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tlp.entity.CustomerEntity;

import java.util.List;
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAll();
}
