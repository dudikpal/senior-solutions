package com.example.jpatransaction;

import com.mysql.jdbc.log.Log;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class LogEntryDao {

    @PersistenceContext
    private EntityManager entityManager;

    // Akkor is lesz logbejegyzés, ha az employee tranzakció nem sikerül
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void save(LogEntry logEntry) {
        entityManager.persist(logEntry);
    }

    public List<LogEntry> list() {
        return entityManager
                .createQuery("select l from LogEntry l order by l.id", LogEntry.class).getResultList();
    }
}