package com.example.PS40753_TranHoang_SD19308.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "productId")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "category", nullable = false)
    Set<String> category;
}
