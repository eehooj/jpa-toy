package me.torissi.orderingrediants.domain.vo;

import lombok.Data;

@Data
public class OrderSearch {

  private Integer page;
  private Integer limit;
  private String name;
  private String sortTarget;
  private String sortType;

}
