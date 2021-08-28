package couponsys.pro2.couponsystem2.repos;

import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    boolean existsById(int id);

    Customer findFirstByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query(value = "insert into `couponsys-pro2`.customers_coupons (customer_id, coupons_id) VALUES (?1, ?2)", nativeQuery = true)
    void purchaseCoupon(int customerid, int couponid);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons  where customer_id=?1", nativeQuery = true)
    void deletePurchaseCoupon(int id);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons  where coupons_id in (select id from coupons where company_id=?1)", nativeQuery = true)
    void deletePurchaseCoupon2(int companyid);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons  where coupons_id =?1", nativeQuery = true)
    void deletePurchaseCoupon3(int couponid);

    @Transactional

    @Query(value = "SELECT CASE WHEN (count(*) > 0) THEN 'true' ELSE 'false' END FROM  `couponsys-pro2`.customers_coupons WHERE customer_id =?1 and coupons_id =?2", nativeQuery = true)
    boolean findCouponPurchase(int customerid, int couponid);


}
