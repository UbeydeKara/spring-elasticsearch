package com.ubeydekara.elasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.ubeydekara.elasticsearch.exception.BadRequestException;
import com.ubeydekara.elasticsearch.request.SearchRequest;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/** utility class instead of repository */
@UtilityClass
public class ESUtil {

    /** Find all records */
    public static Query createAllMatchQuery() {
        return Query.of(q -> q.matchAll(new MatchAllQuery.Builder().build()));
    }

    /** Search specific field */
    public static Supplier<Query> matchQuery(String field, String value) {
        return () -> Query.of(q -> q.match(
                new MatchQuery.Builder()
                        .field(field)
                        .query(value)
                        .build()
        ));
    }

    /** A query that matches documents matching boolean combinations of other queries. */
    public static Supplier<Query> boolQuery(SearchRequest searchRequest) throws BadRequestException {

        if (searchRequest.getFields().size() != searchRequest.getValues().size())
            throw new BadRequestException("\"fields\" and \"values\" lists must have the same size.");

        else if (searchRequest.getFields().isEmpty() || searchRequest.getValues().isEmpty())
            throw new BadRequestException("\"fields\" and \"values\" lists must have the same size.");

        List<Query> searchQueries = new ArrayList<>();

        for (int i = 0; i < searchRequest.getFields().size(); i++) {
            searchQueries.add(matchQuery(
                    searchRequest.getFields().get(i),
                    searchRequest.getValues().get(i)
            ).get());
        }

        return () -> Query.of(q -> q.bool(
                new BoolQuery.Builder()
                        .must(searchQueries)
                        .build()
        ));
    }

    /** Returns documents that contain an exact term in a provided field. */
    public static Supplier<Query> termQuery(String field, String value) {
        return () -> Query.of(q -> q.term(
                new TermQuery.Builder()
                        .field(field)
                        .value(value)
                        .build()
                )
        );
    }
}
