package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.beans.Category;
import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.beans.Customer;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon c) throws CouponSystemException;

    void updateCoupon(int id, Coupon c) throws CouponSystemException;

    void deleteCoupon(int id) throws CouponSystemException;

    List<Coupon> getAllCompanyCoupons();

    List<Coupon> getAllCompanyCouponsByCategory(Category c);

    List<Coupon> getAllCompanyCouponsMaxPrice(double maxPrice);

    Company getCompanyDetails() throws CouponSystemException;

    int getIdByEmailAndPassword(String email, String password);
}
