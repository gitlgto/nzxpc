package com.nzxpc.mem.entity.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker {
    private String name;
}
