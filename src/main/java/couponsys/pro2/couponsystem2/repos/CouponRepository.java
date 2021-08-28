package couponsys.pro2.couponsystem2.repos;

import couponsys.pro2.couponsystem2.beans.Category;
import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByEndDateIsLessThan(Date now);

    //List<Coupon> findByTitle(String title);
    List<Coupon> findByCompany(Company company);

    List<Coupon> findByCompanyAndCategory(Company c, Category cat);

    List<Coupon> findByCompanyAndId(Company c, int id);

    boolean existsByCompanyAndId(Company c, int id);

    List<Coupon> findByCompanyAndPriceLessThanEqual(Company c, double maxprice);

    boolean existsById(int id);

    boolean existsByTitleAndCompany(String title,Company company);

    boolean existsByCompany(Company company);
}
