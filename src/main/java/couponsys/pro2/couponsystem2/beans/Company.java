package couponsys.pro2.couponsystem2.beans;

import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //       @Column(length = 40,nullable = false)
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    @Singular
    @ToString.Exclude
    private List<Coupon> coupons;


}
