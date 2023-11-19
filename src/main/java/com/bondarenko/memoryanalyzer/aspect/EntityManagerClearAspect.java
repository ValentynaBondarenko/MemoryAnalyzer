package com.bondarenko.memoryanalyzer.aspect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Aspect
@Component
public class EntityManagerClearAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository+.saveAll(..))")
    public void saveAllPointcut() {
    }

    @After("saveAllPointcut()")
    public void clearEntityManager() {
        log.info("Clear cache first level after saving data to the database");
        entityManager.clear();
    }
}