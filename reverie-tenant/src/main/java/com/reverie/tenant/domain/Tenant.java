package com.reverie.tenant.domain;

import com.reverie.shared.CommonModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Tenant extends CommonModel {

    private String tenantId;

    private String licenseNo;
    private String name;
    private String desc;
    private String address;
    private String cellphone;
    /**
     * 默认为微信
     */
    private String contact;

    private Date beginTime;
    private Date endTime;

    private Status status;

    /**
     * 租约状态
     */
    public enum Status {
        VALID,
        FREEZ,
        CLOSED,
        ;
    }

    @Data
    public static class Builder {

        private static final String LICENSE_NO_IS_EMPTY = "LICENSE_NO_IS_EMPTY";
        private static final String NAME_IS_EMPTY = "NAME_IS_EMPTY";
        private static final String ADDRESS_IS_EMPTY = "ADDRESS_IS_EMPTY";
        private static final String CONTACT_IS_EMPTY = "CONTACT_IS_EMPTY";

        private Tenant newTenant;

        public static Builder of(
                String licenseNo,
                String name,
                String desc,
                String address,
                String cellphone,
                String contact,
                Date beginTime,
                Date endTime,
                Status status) {

            if (StringUtils.isEmpty(licenseNo)) {
                throw new TenantException(LICENSE_NO_IS_EMPTY);
            }
            if (StringUtils.isEmpty(name)) {
                throw new TenantException(NAME_IS_EMPTY);
            }
            if (StringUtils.isEmpty(address)) {
                throw new TenantException(ADDRESS_IS_EMPTY);
            }
            if (StringUtils.isEmpty(contact)) {
                throw new TenantException(CONTACT_IS_EMPTY);
            }
            if (Objects.isNull(beginTime)) {
                beginTime = new Date();
            }

            Builder builder = new Builder();
            Tenant tenant = new Tenant(
                    newTenantId(licenseNo),
                    licenseNo,
                    name,
                    desc,
                    address,
                    cellphone,
                    contact,
                    beginTime,
                    endTime,
                    status
            );
            builder.setNewTenant(tenant);
            return builder;
        }


        private static String newTenantId(String licenseNo) {
            return UUID.nameUUIDFromBytes(licenseNo.getBytes()).toString();
        }
    }
}
