package org.busca.moveis.advancedsearch.repository;

import org.busca.moveis.advancedsearch.entities.Movel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovelRepository extends MongoRepository<Movel, UUID> {

    @Override
    Page<Movel> findAll(Pageable pageable);
}
