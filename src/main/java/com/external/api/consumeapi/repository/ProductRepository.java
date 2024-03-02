package com.external.api.consumeapi.repository;

import com.external.api.consumeapi.domain.dto.ProductDTO;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

  DSLContext create = DSL.using(SQLDialect.H2);
//  Result<Record> result = create.select().from(ProductDTO).fetch();
}
