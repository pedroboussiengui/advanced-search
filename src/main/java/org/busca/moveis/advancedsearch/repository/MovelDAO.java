package org.busca.moveis.advancedsearch.repository;

import org.busca.moveis.advancedsearch.entities.Movel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MovelDAO {

    Page<Movel> listAll(Pageable pageable, Map<String,String> params);
}
