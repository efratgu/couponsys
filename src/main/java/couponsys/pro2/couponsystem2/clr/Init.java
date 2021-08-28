package couponsys.pro2.couponsystem2.clr;

import couponsys.pro2.couponsystem2.beans.Category;
import couponsys.pro2.couponsystem2.beans.Company;
import couponsys.pro2.couponsystem2.beans.Coupon;
import couponsys.pro2.couponsystem2.beans.Customer;
import couponsys.pro2.couponsystem2.exception.CouponSystemException;
import couponsys.pro2.couponsystem2.exception.ErrMsg;

import couponsys.pro2.couponsystem2.repos.CompanyRepository;
import couponsys.pro2.couponsystem2.repos.CouponRepository;

import couponsys.pro2.couponsystem2.repos.CustomerRepository;
import couponsys.pro2.couponsystem2.security.ClientType;
import couponsys.pro2.couponsystem2.security.LogInManager;
import couponsys.pro2.couponsystem2.services.*;
import couponsys.pro2.couponsystem2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

@Component
@RequiredArgsConstructor
@Order(1)
public class Init implements CommandLineRunner {


    private final CompanyRepository companyRepository;

    private final CouponRepository couponRepository;

    private final CustomerRepository customerRepository;
    @Autowired
    private LogInManager logInManager;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        Company com1 = Company.builder()
                .email("b&j@gmail.com")
                .password("1111")
                .name("b&j")
                .build();
        Company com2 = Company.builder()
                .email("ksp@gmail.com")
                .password("2222")
                .name("ksp")
                .build();
        Company com3 = Company.builder()
                .email("isrotel@gmail.com")
                .password("3333")
                .name("isrotel")
                .build();
        Coupon cop1 = Coupon.builder()
                .amount(10)
                .category(Category.food)
                .description("free ice cream")
                .company(companyRepository.getById(1))
                .endDate(Date.valueOf(LocalDate.now().plusDays(90)))
                .image("jhjkhhkj")
                .price(20)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("ice cream")
                .build();
        Coupon cop2 = Coupon.builder()
                .amount(11)
                .category(Category.electricity)
                .description("20% off")
                .company(companyRepository.getById(2))
                .endDate(Date.valueOf(LocalDate.now().plusDays(100)))
                .image("dsdssa")
                .price(100)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("iphon")
                .build();
        Coupon cop3 = Coupon.builder()
                .amount(12)
                .category(Category.vacation)
                .description("bed&breakfast")
                .company(companyRepository.getById(3))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .image("jhjkhhkj")
                .price(1000)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("isrotel eilat")
                .build();
        Customer cus1 = Customer.builder()
                .email("admin@admin.com")
                .firstName("efrat")
                .lastName("gutman")
                .password("1234")
                //   .coupons(Arrays.asList(cop1,cop2,cop3))

                .build();
        Customer cus2 = Customer.builder()
                .email("a@gmail.com")
                .firstName("avi")
                .lastName("iczkovits")
                .password("1235")
                //    .coupons(Arrays.asList(cop2))

                .build();
        Customer cus3 = Customer.builder()
                .email("b@gmail.com")
                .firstName("benny")
                .lastName("cohen")
                .password("1236")
                //.coupons(Arrays.asList(cop3))

                .build();


        System.out.println(Art.INSERT);

        companyRepository.saveAll(Arrays.asList(com1, com2, com3));
        companyRepository.findAll().forEach(System.out::println);
        couponRepository.saveAll(Arrays.asList(cop1, cop2, cop3));
        couponRepository.findAll().forEach(System.out::println);
        customerRepository.saveAll(Arrays.asList(cus1, cus2, cus3));
        customerRepository.findAll().forEach(System.out::println);
        System.out.println(Art.UPDATE);
        com1 = companyRepository.findById(1).get();
        com1.setEmail("ice@gmail.com");
        companyRepository.saveAndFlush(com1);
        companyRepository.findAll().forEach(System.out::println);
        cop1 = couponRepository.findById(1).get();
        cop1.setAmount(1);
        couponRepository.saveAndFlush(cop1);
        couponRepository.findAll().forEach(System.out::println);
        cus1 = customerRepository.findById(1).get();
        cus1.setFirstName("itzick");
        customerRepository.saveAndFlush(cus1);
        customerRepository.findAll().forEach(System.out::println);


        System.out.println(Art.GET_SINGLE);
        System.out.println(companyRepository.getById(3));
        System.out.println(customerRepository.getById(1));
        System.out.println("-------------coupon purchase--------------");
        // cus1.setCoupons(Arrays.asList(cop2,cop3));
        //  customerRepository.saveAndFlush(cus1);
        cus3.setCoupons(Arrays.asList(cop1, cop3));
        customerRepository.saveAndFlush(cus3);
        System.out.println(couponRepository.getById(3));

        System.out.println(Art.GET_ALL);
        couponRepository.findAll().forEach(System.out::println);
        companyRepository.findAll().forEach(System.out::println);
        customerRepository.findAll().forEach(System.out::println);
        System.out.println(Art.DELETE);

        companyRepository.deleteById(2);
        //  couponRepository.deleteById(2);
        couponRepository.findAll().forEach(System.out::println);

        companyRepository.findAll().forEach(System.out::println);
        customerRepository.deleteById(2);
        customerRepository.findAll().forEach(System.out::println);

        companyRepository.save(com2);
        Coupon cop4 = Coupon.builder()
                .amount(11)
                .category(Category.electricity)
                .description("20% off")
                .company(companyRepository.getById(4))
                .endDate(Date.valueOf(LocalDate.now().plusDays(100)))
                .image("dsdssa")
                .price(100)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("iphon")
                .build();
        couponRepository.save(cop4);
        customerRepository.save(cus2);


        System.out.println("---------------try get admin facade wrong email--------------");
        try {
            System.out.println("start");


            AdminService adminService = (AdminService) logInManager.login("efrat@ggg", "admin", ClientType.administrator);
            if (adminService == null) {
                throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);

            } else {
                System.out.println("---------------login success------------------------------- ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("---------------try get admin facade good email--------------");

        try {
            AdminService adminService = (AdminService) logInManager.login("admin@admin.com", "admin", ClientType.administrator);
            if (adminService == null) {
                throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);

            } else {
                System.out.println("---------------login success------------------------------- ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-----------try add company with same name-------------------------");


        Company com4 = Company.builder()
                .email("k@gmail.com")
                .password("2222")
                .name("ksp")
                .build();

        try {
            adminService.addCompany(com4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }
        System.out.println("-----------try add company with same email-------------------------");
        com4.setName("leonardo");
        com4.setEmail("isrotel@gmail.com");
        try {
            adminService.addCompany(com4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }
        System.out.println("-----------try add new company-------------------------");
        com4.setEmail("leo@gmail.com");
        try {
            adminService.addCompany(com4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }

        System.out.println(adminService.getAllCompanies());
        System.out.println("---------------try update company id-------------------");

        try {
            adminService.updateCompany(10, com4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }

        System.out.println("---------------try update company name-------------------");

        com4.setName("efrat");
        try {
            adminService.updateCompany(5, com4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }

        System.out.println("---------------try update company password-------------------");
        com4.setName("leonardo");
        com4.setPassword("9876");
        try {
            adminService.updateCompany(5, com4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adminService.getAllCompanies());
        System.out.println("-------------coupon purchase--------------");
        cus1.setCoupons(Arrays.asList(cop1, cop3));
        customerRepository.saveAndFlush(cus1);
        System.out.println("---------------delete company-------------------");

        try {
            adminService.deleteCompany(3);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }


        System.out.println("---------------get all companies-------------------");
        System.out.println(adminService.getAllCompanies());
        System.out.println("---------------get one company-------------------");

        try {
            System.out.println(adminService.getOneCompany(1));
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------add customer with same email-------------------");
        Customer cus4 = Customer.builder()
                .email("b@gmail.com")
                .firstName("avi")
                .lastName("cohen")
                .password("555")
                //  .coupons(Arrays.asList(cop3))

                .build();
        try {
            adminService.addCCustomer(cus4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------add customer ------------------");
        cus4.setEmail("avi@net.com");
        try {
            adminService.addCCustomer(cus4);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adminService.getAllCustomers());
        System.out.println("--------------try update customer id ------------------");


        try {
            adminService.updateCustomer(12, cus4);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--------------try update customer password ------------------");

        cus4.setPassword("0000");
        try {
            adminService.updateCustomer(4, cus4);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adminService.getAllCustomers());
        System.out.println("-------------coupon purchase--------------");
        cus4.setCoupons(Arrays.asList(cop1, cop4));
        customerRepository.saveAndFlush(cus4);
        System.out.println("---------------delete customer-------------------");

        try {
            adminService.deleteCustomer(5);
        } catch (CouponSystemException e) {
            ;
        }

        System.out.println("---------------get all customer-------------------");
        System.out.println(adminService.getAllCustomers());
        System.out.println("---------------get one customer-------------------");

        try {
            System.out.println(adminService.getOneCustomer(4));
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
            ;
        }


        System.out.println("---------------try get company facade wrong email--------------");
        try {


            CompanyService companyService = (CompanyService) logInManager.login("efrat@ggg", "admin", ClientType.company);
            if (companyService == null) {
                throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);

            } else {
                System.out.println("---------------login success------------------------------- ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("---------------try get company facade good email--------------");

        try {
            companyService = (CompanyService) logInManager.login("ice@gmail.com", "1111", ClientType.company);
            if (companyService == null) {
                System.out.println("wrong email or password");

            } else {
                System.out.println("---------------login success------------------------------- ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("-------------try add coupon with same title-------------------");
        Coupon cop5 = Coupon.builder()
                .amount(12)
                .category(Category.vacation)
                .description("pizza")
                .company(companyRepository.getById(1))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .image("jhjkhhkj")
                .price(1000)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("ice cream")
                .build();
        try {
            companyService.addCoupon(cop5);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-------------try add coupon with same title but other company-------------------");
        Coupon cop6 = Coupon.builder()
                .amount(12)
                .category(Category.food)
                .description("golda")
                .company(companyRepository.getById(4))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .image("jhjkhhkj")
                .price(1000)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("ice cream")
                .build();
        try {
            companyService.addCoupon(cop6);
            System.out.println(couponRepository.findAll());
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------try update code coupon-------------------");

        try {
            companyService.updateCoupon(10, cop5);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------try update coupon company code-------------------");
        cop5 = couponRepository.getById(1);
        cop5.setCompany(companyRepository.getById(4));

        try {
            companyService.updateCoupon(1, cop5);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------try update coupon price-------------------");
        cop5 = couponRepository.getById(1);
        cop5.setPrice(10);
        try {
            companyService.updateCoupon(1, cop5);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------coupon purchase--------------");
        cus4.setCoupons(Arrays.asList(cop4, cop5));
        customerRepository.saveAndFlush(cus4);
        System.out.println("-------------delete coupon-------------------");
        try {
            companyService.deleteCoupon(4);

        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-------------get all company coupons-----------------");
        //add coupons
        Coupon cop7 = Coupon.builder()
                .amount(100)
                .category(Category.restaurant)
                .description("dessert")
                .company(companyRepository.getById(1))
                .endDate(Date.valueOf(LocalDate.now().plusDays(30)))
                .image("jhjkhhkj")
                .price(12)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("joe")
                .build();
        couponRepository.save(cop7);

        System.out.println(companyService.getAllCompanyCoupons());


        System.out.println("-------------get all company coupons by category-----------------");
        System.out.println(companyService.getAllCompanyCouponsByCategory(Category.restaurant));
        System.out.println("-------------get all company coupons less than price-----------------");
        System.out.println(companyService.getAllCompanyCouponsMaxPrice(11));
        System.out.println("-------------get all company details-----------------");
        System.out.println(companyService.getCompanyDetails());

        System.out.println("---------------try get customer facade wrong email--------------");
        try {
            CustomerService customerService = (CustomerService) logInManager.login("m@gmail.com", "1236", ClientType.customer);
            if (customerService == null) {
                throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);

            } else {
                System.out.println("---------------login success------------------------------- ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("---------------try get customer facade good email--------------");
        try {
            CustomerService customerService = (CustomerService) logInManager.login("b@gmail.com", "1236", ClientType.customer);
            if (customerService == null) {
                throw new CouponSystemException(ErrMsg.WRONG_EMAIL_OR_PASSWORD);

            } else {
                System.out.println("---------------login success------------------------------- ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------add expired coupon-----------------");
        Coupon expcoup = Coupon.builder()
                .amount(100)
                .category(Category.food)
                .description("1+1")
                .company(companyRepository.getById(1))
                .endDate(Date.valueOf(LocalDate.now().minusDays(100)))
                .image("dsdssa")
                .price(100)
                .startDate(Date.valueOf(LocalDate.now()))
                .title("cocacola")
                .build();
        couponRepository.save(expcoup);
        System.out.println("---------------try buy coupon-----------------");
        System.out.println("-------------coupon purchase--------------");

        Coupon c = couponRepository.getById(5);
        Coupon c2 = couponRepository.getById(6);

        cus1.setCoupons(Arrays.asList(c, c2));

        customerRepository.saveAndFlush(cus1);

        System.out.println("---------------try buy zero amount coupon -----------------");

        c.setAmount(0);
        try {
            customerService.addCoupon(c);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------try buy expired coupon -----------------");
        c.setAmount(10);
        c = couponRepository.getById(5);

        c.setEndDate(Date.valueOf(LocalDate.now().minusDays(30)));
        try {
            customerService.addCoupon(c);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------try buy good coupon -----------------");
        c.setEndDate(Date.valueOf(LocalDate.now().plusDays(30)));
        c.setAmount(10);
        System.out.println(c);
        try {
            customerService.addCoupon(c);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------------try buy coupon twice-----------------");
        try {
            customerService.addCoupon(c);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("---------------get all customer coupons-----------------");
        System.out.println(customerService.getAllCustomerCoupons());
        System.out.println("---------------get all customer coupons from 1 category-----------------");
        System.out.println(customerService.getAllCustomerCouponsByCategory(Category.food));
        System.out.println("---------------get all customer coupons till max price-----------------");
        System.out.println(customerService.getAllCustomerCouponsMaxPrice(1000));
        System.out.println("---------------get all customer details-----------------");
        System.out.println(customerService.getCustomerDetails());

    }

}