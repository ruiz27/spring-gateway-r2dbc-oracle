package com.gateway.mdw.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

/**
 * A MdwIPBlackList.
 */
@Data
@Table
public class UserBlackList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String user;
    private String ipAddress;
    private LocalDateTime sinceDate;
    private LocalDateTime untilDate;
   
}
