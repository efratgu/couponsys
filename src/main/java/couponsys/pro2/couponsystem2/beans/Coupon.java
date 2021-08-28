package couponsys.pro2.couponsystem2.beans;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne//(fetch = FetchType.LAZY)
    //@JoinColumn(name ="company_id")
    @ToString.Exclude
    private Company company;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
    //  @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
//   @JoinTable(name = "coupons_customers",
//    joinColumns = @JoinColumn(name = "coupon_id"),inverseJoinColumns = @JoinColumn(name="customer_id"))
//@Singular


 //   private List<Customer> customers;
}
