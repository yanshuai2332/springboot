package com.yan.test.dto;

import com.yan.test.group.groupA;
import com.yan.test.group.groupB;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by yanshuai on 2017/8/11.
 */
@ApiModel
@Data
@NoArgsConstructor
public class UserDTO {

    @ApiModelProperty(value = "活动id,发布时不传,编辑时必传")
    @NotNull(message = "活动id不能为空", groups = {groupA.class})
    @Null(message = "活动id只能为null", groups = {groupB.class})
    private Integer id;//id

}
