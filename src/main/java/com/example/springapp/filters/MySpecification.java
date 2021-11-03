package com.example.springapp.filters;

import com.example.springapp.entitys.Books;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MySpecification implements Specification<Books> {

    private MyCriteria criteria;

    public MySpecification(MyCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Books> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        return builder.equal(root.<String>get(criteria.getKey()), criteria.getValue());
    }



}
