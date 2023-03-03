package com.lv.mall.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 17324
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @NotNull
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private String gender;
}
