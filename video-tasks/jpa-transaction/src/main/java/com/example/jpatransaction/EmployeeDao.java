package com.example.jpatransaction;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    private LogEntryDao logEntryDao;

    public EmployeeDao(LogEntryDao logEntryDao) {
        this.logEntryDao = logEntryDao;
    }

    @Transactional
    public void saveEmployeeById(Employee employee) throws IllegalArgumentException{
        logEntryDao.save(new LogEntry("Create employee: " + employee.getName()));
        entityManager.persist(employee);
        if (employee.getName().length() < 3) {
            throw new IllegalArgumentException("Name must be longer then 3 chars");
        }
    }

    public Employee findEmployeeById(long id) {
        return entityManager.find(Employee.class, id);
    }
}
