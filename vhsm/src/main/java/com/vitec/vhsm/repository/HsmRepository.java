package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Admin;
import com.vitec.vhsm.domain.CmdProcessingRateItem;
import com.vitec.vhsm.domain.Hsmlog;

import java.util.List;

public interface HsmRepository {
    List<Hsmlog> findHsmlog();
}
