package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.beans.Category;
import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.beans.Customer;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;
import couponsys.pro2.couponsystem2.exception.ErrMsg;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CustomerServiceImplmnt extends ClientServices implements CustomerService {
    int customerId;

    @Override
    public boolean login(String email, String password) {
        return customerRepository.existsByEmail(email) && customerRepository.existsByPassword(password);

    }

    @Override
    public void addCoupon(Coupon c) throws CouponSystemException {


        if (c.getAmount() == 0) {

            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_ZERO);
        }
        if (c.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }

        if (customerRepository.findCouponPurchase(customerId, c.getId())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ALREADY_HAVE_COUPON);
        }
        customerRepository.purchaseCoupon(customerId, c.getId());
        c.setAmount(c.getAmount() - 1);
        couponRepository.saveAndFlush(c);


    }

    @Override
    public List<Coupon> getAllCustomerCoupons() {

        return customerRepository.getById(customerId).getCoupons();

    }

    @Override
    public List<Coupon> getAllCustomerCouponsByCategory(Category c) {

        List<Coupon> cops = getAllCustomerCoupons();
        List<Coupon> cops2 = new ArrayList<>();
        for (Coupon c1 : cops) {
            if (c1.getCategory().equals(c)) {
                cops2.add(c1);

            }

        }
        return cops2;

    }

    @Override
    public List<Coupon> getAllCustomerCouponsMaxPrice(double maxPrice) {
        List<Coupon> cops = customerRepository.getById(customerId).getCoupons();
        List<Coupon> cops2 = new ArrayList<>();
        for (Coupon cop : cops) {
            if (cop.getPrice() < maxPrice) {
                cops2.add(cop);
            }

        }
        return cops2;
    }

    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        return customerRepository.getById(customerId);
    }

    @Override
    public int getIdByEmailAndPassword(String email, String password) {
        return customerRepository.findFirstByEmailAndPassword(email, password).getId();
    }
}
