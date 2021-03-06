package com.yan.moudleA.entity;

import com.yan.moudleA.group.groupA;
import com.yan.moudleA.group.groupB;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 *
 * @author yanshuai
 * @date 2017/8/3
 */
@Table(name = "base_user")
@Data
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(name = "userName")
    private String userName;

    @NotNull(message = "password不能为空", groups = {groupA.class})
    @Null(message = "password只能为null", groups = {groupB.class})
    @Column(name = "password")
    private Integer password;
}
