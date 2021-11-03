package com.example.springapp.repository;

import com.example.springapp.entitys.Books;
import com.example.springapp.filters.MyCriteria;
import com.example.springapp.filters.MySpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooksSpecificationsBuilder {

    private final List<MyCriteria> params;

    public BooksSpecificationsBuilder() {
        params = new ArrayList<MyCriteria>();
    }
    public BooksSpecificationsBuilder with(String key, String value) {
        params.add(new MyCriteria(key, value));
        return this;
    }

    public Specification<Books> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(MySpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }


}
