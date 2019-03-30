package com.reverie.tenant.application;

import com.reverie.tenant.domain.Tenant;
import com.reverie.tenant.domain.TenantException;
import com.reverie.tenant.domain.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TenantApplicationService {

    @Autowired
    private TenantRepository tenantRepository;

    /**
     * 创建租约
     */
    public void newTenant(
            String licenseNo,
            String name,
            String desc,
            String address,
            String cellphone,
            String contact) {

        licenseNoIsLegal(licenseNo);

        notCreateTenantYet(licenseNo);

        Tenant tenant = Tenant.Builder.of(
                licenseNo,
                name,
                desc,
                address,
                cellphone,
                contact,
                new Date(),
                null,
                Tenant.Status.VALID).getNewTenant();

        tenantRepository.save(tenant);
    }

    private static final String ALREADY_CREATED_TENANT_ERROR = "ALREADY_CREATED_TENANT_ERROR";

    private void notCreateTenantYet(String licenseNo) {
        Tenant alreadyCreated = tenantRepository.ofByLicenseNo(licenseNo);
        if (Objects.nonNull(alreadyCreated)) {
            throw new TenantException(ALREADY_CREATED_TENANT_ERROR);
        }
    }

    private void licenseNoIsLegal(String licenseNo) {
        //TODO
    }


}
