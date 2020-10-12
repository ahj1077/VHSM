package com.vitec.vhsm.service;

import com.vitec.vhsm.domain.Admin;
import com.vitec.vhsm.domain.CmdProcessingRateItem;
import com.vitec.vhsm.domain.Hsmlog;
import com.vitec.vhsm.repository.AdminRepository;
import com.vitec.vhsm.repository.HsmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class HsmService {
    private final HsmRepository hsmRepository;

    @Autowired
    public HsmService(HsmRepository hsmRepository) {
        this.hsmRepository = hsmRepository;
    }

    public List<Hsmlog> findCmdProcessingRate(){
        return hsmRepository.findHsmlog();
    }

}
