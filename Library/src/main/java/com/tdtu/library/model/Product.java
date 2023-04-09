package com.tdtu.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "image"}))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private double price;
    private String brand;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private String description;
    private double sale;
    private int currentQuantity;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "is_activated")
    private boolean isActivated;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
