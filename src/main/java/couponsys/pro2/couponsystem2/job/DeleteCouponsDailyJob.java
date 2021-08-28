package couponsys.pro2.couponsystem2.job;

import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;
import couponsys.pro2.couponsystem2.repos.CouponRepository;
import couponsys.pro2.couponsystem2.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteCouponsDailyJob {

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
//    private static final int DAY = 1000 * 60 * 60 * 24;
    //just for checking

    private static final int DAY = 5000;


    @Scheduled(fixedRate = DAY)
    public void deleteExpiredCoupon() {
        System.out.println("working");

        List<Coupon> expCoupons = new ArrayList<>();

        try {
            expCoupons = couponRepository.findByEndDateIsLessThan(Date.valueOf(LocalDate.now()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        if (!expCoupons.isEmpty()) {
            for (Coupon c : expCoupons) {
                customerRepository.deletePurchaseCoupon3(c.getId());
                couponRepository.deleteById(c.getId());

            }

        }

    }

}

