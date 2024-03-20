package org.busca.moveis.advancedsearch.repository;

import org.busca.moveis.advancedsearch.entities.Movel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Repository
public class MovelDAOImp implements MovelDAO {

    private final MongoTemplate mongoTemplate;

    public MovelDAOImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Movel> listAll(Pageable pageable, Map<String, String> params) {
        Query query = new Query();
        if (params.containsKey("banheiros")) {
            int value = Integer.parseInt(params.get("banheiros"));
            query.addCriteria(Criteria.where("qtdeBanheiros").is(value));
        }
        if (params.containsKey("minArea") && params.containsKey("maxArea")) {
            int minArea = Integer.parseInt(params.get("minArea"));
            int maxArea = Integer.parseInt(params.get("maxArea"));
            query.addCriteria(Criteria.where("area").gt(minArea).lt(maxArea));
        } else if (params.containsKey("minArea")) {
            int minArea = Integer.parseInt(params.get("minArea"));
            query.addCriteria(Criteria.where("area").gt(minArea));
        } else if (params.containsKey("maxArea")) {
            int maxArea = Integer.parseInt(params.get("maxArea"));
            query.addCriteria(Criteria.where("area").lt(maxArea));
        }
        if (params.containsKey("tipoNegocio")) {
            String tipoNegocio = params.get("tipoNegocio");
            query.addCriteria(Criteria.where("tipoNegocio").is(tipoNegocio));
        }
        long count = mongoTemplate.count(query, Movel.class);
        query.with(pageable);
        List<Movel> moveis = mongoTemplate.find(query, Movel.class);
        return PageableExecutionUtils.getPage(moveis, pageable, () -> count);
    }
}
