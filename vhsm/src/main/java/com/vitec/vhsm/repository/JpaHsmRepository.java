package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Admin;
import com.vitec.vhsm.domain.CmdProcessingRateItem;
import com.vitec.vhsm.domain.Hsmlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class JpaHsmRepository implements HsmRepository {

    private final EntityManager em;

    @Autowired
    public JpaHsmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Hsmlog> findHsmlog() {
        //List<CmdProcessingRateItem> items = new ArrayList<>();
        return em.createQuery("select a from Hsmlog as a", Hsmlog.class).getResultList();
    }
}
