package com.plm.platform.common.entity;

import com.plm.platform.common.entity.system.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends Tree<Dept>{

    private Integer orderNum;
}
