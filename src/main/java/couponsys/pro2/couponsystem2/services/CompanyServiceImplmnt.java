package couponsys.pro2.couponsystem2.services;

import couponsys.pro2.couponsystem2.beans.Category;
import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;
import couponsys.pro2.couponsystem2.exception.ErrMsg;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CompanyServiceImplmnt extends ClientServices implements CompanyService {
    int companyId;

    @Override
    public boolean login(String email, String password) {
        return companyRepository.existsByEmail(email) && companyRepository.existsByPassword(password);

    }

    @Override
    public void addCoupon(Coupon c) throws CouponSystemException {
        if (couponRepository.existsByTitleAndCompany(c.getTitle(),c.getCompany()))  {
            throw new CouponSystemException(ErrMsg.COMPANY_TITLE_EXIST);
        }
        couponRepository.save(c);
    }

    @Override
    public void updateCoupon(int id, Coupon c) throws CouponSystemException {
        if (!couponRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST);
        }
        if (!couponRepository.existsByCompanyAndId(c.getCompany(), id)) {
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXIST);
        }


        couponRepository.saveAndFlush(c);

    }

    @Override
    public void deleteCoupon(int id) throws CouponSystemException {
        if (!couponRepository.existsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST);
        }
        customerRepository.deletePurchaseCoupon3(id);
        couponRepository.deleteById(id);
    }

    @Override
    public List<Coupon> getAllCompanyCoupons() {

        Company c = companyRepository.getById(companyId);
        // System.out.println(c);
        return couponRepository.findByCompany(c);

    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(Category c) {
        Company c1 = companyRepository.getById(companyId);
        return couponRepository.findByCompanyAndCategory(c1, c);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsMaxPrice(double maxPrice) {
        Company c1 = companyRepository.getById(companyId);
        return couponRepository.findByCompanyAndPriceLessThanEqual(c1, maxPrice);

    }

    @Override
    public Company getCompanyDetails() throws CouponSystemException {
        return companyRepository.getById(companyId);

    }

    @Override
    public int getIdByEmailAndPassword(String email, String password) {
        return companyRepository.findFirstByEmailAndPassword(email, password).getId();
    }
}
