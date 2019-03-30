package com.reverie.tenant.domain;

import org.springframework.stereotype.Repository;

@Repository
public class TenantRepository {

    public Tenant of(Long id) {
        return null;
    }

    public Tenant of(String tenantId) {
        return null;
    }

    public Tenant ofByLicenseNo(String licenseNo) {
        return null;
    }

    public void save(Tenant tenant) {

    }

}
