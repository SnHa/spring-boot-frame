package com.atsun.coreapi.dto;

import com.atsun.coreapi.bean.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO implements Serializable {

    private static final long serialVersionUID = -6729307143234330897L;

    private Page page;

}
