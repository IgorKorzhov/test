package com.example.library.library.repository;

import com.example.library.library.model.Author;
import com.example.library.library.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> search(String str) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);

        List<Predicate> predicates = new ArrayList<>();

        if (str != null) {

            predicates.add(cb.like(root.get("firstName"), "%" + str + "%"));
            predicates.add(cb.like(root.get("middleName"), "%" + str+ "%"));
            predicates.add(cb.like(root.get("lastName"), "%" + str + "%"));
            predicates.add(cb.like(root.join("books").get("pid"), "%" + str + "%"));
            cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));
        }
        return em.createQuery(cq).getResultList();
    }

}

