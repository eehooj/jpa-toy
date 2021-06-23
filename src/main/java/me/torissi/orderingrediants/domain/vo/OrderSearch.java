package me.torissi.orderingrediants.domain.vo;

import lombok.Data;

@Data
public class OrderSearch {

  private Integer page;
  private Integer limit;
  private String name;
  private String starget;
  private String stype;

}
