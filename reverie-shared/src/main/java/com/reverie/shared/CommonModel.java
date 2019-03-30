package com.reverie.shared;

import lombok.Data;

import java.util.Date;

@Data
public class CommonModel extends DomainConcern {

    private Long id;

    private Date gmtCreateTime;
    private Date gmtUpdateTime;
}
