package com.ajinkyabhutkar.electronicstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "category_title",length = 50,unique = true,nullable = false)
    private String title;

    @Column(name = "category_description",length = 200)
    private String description;

    @Column
    private String coverImage;

    @Column
    private boolean deleted=false;

    //one category can have no of products
    //cascade type all means if you perform something on category it will reflect the product also
    //fetch type lazy means don't bring product information
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Product> productList=new ArrayList<>();


//    @Transient
//    private String etcInfo;

}
