package com.ecommerce.ECommerceSite.domain.dtos;

import com.ecommerce.ECommerceSite.domain.dtos.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
public class Product extends BaseEntity {

    private String firstName;
    private String lastName;
    private String description;

}
