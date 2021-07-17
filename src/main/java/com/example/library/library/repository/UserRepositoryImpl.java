package com.example.library.library.repository;

import com.example.library.library.model.User;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> search(String str) {
       /* Session session = em.unwrap(Session.class);
        Criteria c = session.createCriteria(User.class);
        c.add(Restrictions.eq("login", str));
        c.add(Restrictions.eq("firstName", str));
        c.add(Restrictions.eq("middleName", str));
        c.add(Restrictions.eq("lastName", str));
        c.setFetchMode("roles", FetchMode.JOIN); // присоеденяем cities с помощью fetch join
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); //Убираем дубликаты
        return c.list();
        Criterion restrictions = Restrictions.and(Restrictions.ilike("login", str, MatchMode.ANYWHERE));
        Criterion restrictions1 = Restrictions.and(Restrictions.ilike("firstName", str, MatchMode.ANYWHERE));
        Criterion restrictions2 = Restrictions.and(Restrictions.ilike("middleName", str, MatchMode.ANYWHERE));
        Criterion restrictions3 = Restrictions.and(Restrictions.ilike("lastName", str, MatchMode.ANYWHERE));
        //Criterion restrictions4 = Restrictions.and(Restrictions.ilike("role.pid", str, MatchMode.ANYWHERE));


        Criterion cr = Restrictions.or(restrictions,restrictions1,restrictions2,restrictions3);

        criteria.add(cr);
        return criteria.list();*/
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        //cq.select(root.get(User)).distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        if (str != null) {

            predicates.add(cb.like(root.get("login"), "%" + str + "%"));
            predicates.add(cb.like(root.get("firstName"), "%" + str + "%"));
            predicates.add(cb.like(root.get("middleName"), "%" + str+ "%"));
            predicates.add(cb.like(root.get("lastName"), "%" + str + "%"));

            //predicates.add(cb.like(root.get("roles"), "%" + searchString + "%"));

            predicates.add(cb.like(root.join("roles").get("pid"), "%" + str + "%"));

            //predicates.add(cb.like(root.get("status"), "%" + searchString + "%"));

            // cq.select(root).where(cb.or(predicates.toArray(new Predicate[0])));


            //cq.select(root.get("pid")).distinct(true); ПОДУМАТЬ


            cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));
//
        }
        //cq.select(root).where(cb.or(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getResultList();
    }

}

