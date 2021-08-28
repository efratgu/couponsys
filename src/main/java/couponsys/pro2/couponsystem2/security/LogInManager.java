package couponsys.pro2.couponsystem2.security;

import couponsys.pro2.couponsystem2.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service

public class LogInManager {

    @Autowired
    private ApplicationContext ctx;


    public ClientServices login(String email, String password, ClientType clienttype) throws Exception {


        switch (clienttype) {
            case administrator:

                AdminService adminService = ctx.getBean(AdminService.class);
                if ((((AdminServiceImplmnt) (adminService)).login(email, password))) {


                    return (AdminServiceImplmnt) adminService;
                }
                break;
            case company:
                CompanyService companyService = ctx.getBean(CompanyService.class);

                if ((((CompanyServiceImplmnt) (companyService)).login(email, password))) {
                    int id = companyService.getIdByEmailAndPassword(email, password);

                    ((CompanyServiceImplmnt) companyService).setCompanyId(id);

                    return ((CompanyServiceImplmnt) companyService);
                }
                break;


            case customer:

                CustomerService customerService = ctx.getBean(CustomerService.class);

                if ((((CustomerServiceImplmnt) (customerService)).login(email, password))) {
                    int id = customerService.getIdByEmailAndPassword(email, password);

                    ((CustomerServiceImplmnt) customerService).setCustomerId(id);

                    return ((CustomerServiceImplmnt) customerService);


                }
                break;
            default:
                break;


        }

        return null;


    }

}