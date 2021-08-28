package couponsys.pro2.couponsystem2.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.REFRESH)
    //  @JoinTable(name="customer_coupons",
    //           joinColumns = @JoinColumn (name="customer_id"), inverseJoinColumns = @JoinColumn(name ="coupon_id"))

    @Singular
    private List<Coupon> coupons = new ArrayList<>();
}
