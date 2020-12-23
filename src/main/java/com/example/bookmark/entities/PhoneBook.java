package com.example.bookmark.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
@Builder(toBuilder = true)
@Table(name = "phone_book")
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBook {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Type(type = "list-array")
  @Column(name = "phone_numbers")
  private List<Long> phoneNumbers = new ArrayList<>();

  @Type(type = "list-array")
  private List<Long> emails = new ArrayList<>();

  @Type(type = "list-array")
  private List<String> names = new ArrayList<>();
}
