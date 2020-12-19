package com.example.bookmark.dto.request;

import lombok.Data;

@Data
public class BookCreateDto {

  private String name;

  private String author;

  private Long qty;

  private Long price;

  private String type;
}
