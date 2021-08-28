package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.beans.Category;
import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.beans.Customer;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;

import java.util.List;

public interface CustomerService {

    void addCoupon(Coupon c) throws CouponSystemException;

    List<Coupon> getAllCustomerCoupons();

    List<Coupon> getAllCustomerCouponsByCategory(Category c);

    List<Coupon> getAllCustomerCouponsMaxPrice(double maxPrice);

    Customer getCustomerDetails() throws CouponSystemException;

    int getIdByEmailAndPassword(String email, String password);
}
