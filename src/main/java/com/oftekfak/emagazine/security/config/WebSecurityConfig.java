package com.oftekfak.emagazine.security.config;

import com.oftekfak.emagazine.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AppUserService appUserService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v*/**","/login")
//                .permitAll()
//                .anyRequest()
//                .authenticated().and()
//                .formLogin();
//    }
//

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationEntryPoint authEntryPoint = new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        };
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and().anonymous()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**").authenticated()
                .antMatchers(HttpMethod.HEAD).denyAll()

                .antMatchers("/auth").permitAll()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/api**").permitAll()
                .antMatchers("/auth/inquireCsrMenu").authenticated()
                .antMatchers("/businessFlow/afterLogin").authenticated()
                .antMatchers("/contactUs**").authenticated()
                .antMatchers("/media/fileUpload").authenticated()
                .antMatchers("/offer/saveComment").authenticated()
                .antMatchers("/offer/updateOfferSearchDatamart").authenticated()
                .antMatchers("/offer/updatePriceDatamart").authenticated()
                .antMatchers("/offer//updatePriceI2I").authenticated()
                .antMatchers("/auth/inquireUser").authenticated()
                .antMatchers("/auth/social/**").authenticated()
                .antMatchers("/auth/loginOperations").authenticated()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/notify/**").authenticated()
                .antMatchers("/customer/relation/**").authenticated()
                .antMatchers("/csr/forCustomer").authenticated()
                .antMatchers("/notify/inquireUserNotification").authenticated()
                .antMatchers("/user/inquirereferfriend").authenticated()
                .antMatchers("/crm/loyalty/management/profileOverview/**").authenticated()
                .antMatchers("/user/products").authenticated()
                .antMatchers("/analytics/prod/usage/daily").authenticated()
                .antMatchers("/integration/custrel/**").permitAll()
                .antMatchers("/integration/**").authenticated()
                .antMatchers("/receiverDetail/**").authenticated()
                .antMatchers("/user/allowedbsninterspeclist").authenticated()
                .antMatchers("/wallet/balance").authenticated()
                .antMatchers("/user/planproducts/**").authenticated()
                .antMatchers("/api/usageDailyAggregated").authenticated()
                .antMatchers("/api/usageSummary").authenticated()
                .antMatchers("/productChars/msisdn/list").authenticated()
                .antMatchers("/user/inquireReferCode").authenticated()
                .antMatchers("/quote/submitQuote").authenticated()
                .antMatchers("/quote/detail").authenticated()
                .antMatchers("/quote/search").authenticated()
                .antMatchers("/quote/cancel").authenticated()
                .antMatchers(HttpMethod.POST, "/customer").permitAll()
                .antMatchers("/customer/**").authenticated()
                .antMatchers(HttpMethod.POST, "/marvel/customer/validate/username").permitAll()
                .antMatchers("/marvel/customer/validate/partyUniqueness").permitAll()
                .antMatchers("/marvel/customer/validate/exists/email").permitAll()
                .antMatchers("/marvel/customer/consent/unsubscription/**").permitAll()
                .antMatchers("/marvel/customer/registerUser").permitAll()
                .antMatchers("/marvel/customer/**").authenticated()
                .antMatchers("/marvel/invoice/inquireInvoiceInfo").authenticated()
                .antMatchers("/paymentsystem/**").authenticated()
                .antMatchers("/product/**").authenticated()
                .antMatchers("/marvel/product/detail/**").authenticated()
                .antMatchers("/customerinformation/inquire/**").authenticated()
                .antMatchers("/customerSearchInfo/**").authenticated()
                .antMatchers("/searchLog/**").authenticated()
                .antMatchers("/customerServiceDetail/config/search/**").authenticated()
                .antMatchers("/customerorder/omintegration/**").permitAll()
                .antMatchers("/customerorder/**").authenticated()
                .antMatchers("/equipment/**").authenticated()
                .antMatchers("/marvel/utility/**").authenticated()

                .antMatchers("/test/**").authenticated()

                .antMatchers("/marvel/quote/submitQuote").authenticated()
                .antMatchers("/marvel/quote/applyExternalCreditProfile").authenticated()
                .antMatchers("/marvel/quote/applyCreditProfileByScore").authenticated()
                .antMatchers("/marvel/quote/byPassDownPayment/**").authenticated()
                .antMatchers("/marvel/quote/inquire/orderStatus/**").authenticated()
                .antMatchers("/marvel/quote/inquireInitialChannelSelection/**").authenticated()
                .antMatchers("/marvel/quote/inquire/orderSummary/**").authenticated()
                .antMatchers("/marvel/quote/proceedToCheckout").authenticated()
                .antMatchers("/marvel/quote/installationMethod").authenticated()
                .antMatchers("/marvel/quote/deliveryMethod").authenticated()
                .antMatchers("/marvel/quote/retrieveMethod").authenticated()
                .antMatchers("/marvel/quote/deliverySelection").authenticated()

                .antMatchers("/marvel/shipment/**").authenticated()
                .antMatchers("/marvel/productOrder/submitProductOrderDrupal").authenticated()
                .antMatchers("/mytask/**").authenticated()

                .antMatchers("/marvel/admin/utility/**").authenticated()
                .antMatchers("/marvel/inquirePaymentOption").authenticated()

                .antMatchers("/marvel/account/**").authenticated()
                .antMatchers("/integration/inquireBillAcctAddress").authenticated()
                .antMatchers("/address/**").authenticated()

                .antMatchers("/marvel/invoice/inquireInvoiceInfo").authenticated()
                .antMatchers("/marvel/invoice/document").authenticated()
                // utility apis
                .antMatchers("/utility/inquireUsefulLinks").authenticated()
                .antMatchers("/utility/inquireUserByUserType").authenticated()
                .antMatchers("/utility/monprfl").authenticated()
                .antMatchers("/utility/lov/customerType").authenticated()
                .antMatchers("/utility/lov/unionTypes").authenticated()
                .antMatchers("/utility/lov/inquireCreditScoreTypes").authenticated()
                .antMatchers("/utility/lov/inquireCreditSolvencyTypes").authenticated()
                .antMatchers("/utility/inquireGTMDataLayer").authenticated()
                .antMatchers("/utility/allSite").authenticated()
                .antMatchers("/utility/inquireCsrUsers").authenticated()
                .antMatchers("/utility/usageType").authenticated()

                .antMatchers("/marvel/devicereplaceconfig/inquireByCustomerOrderId/**").authenticated()

                .antMatchers("/quote/mergeCustomer").authenticated()
                .antMatchers("/quote/updateQuote").authenticated()
                .antMatchers("/quote/inquireQuotePartyPrivileges").authenticated()
                .antMatchers("/quote/inquireComponentGroupRelation").authenticated()
                .antMatchers("/quote/isCancelable/**").authenticated()
                .antMatchers("/quote/inFlightCancelOrder").authenticated()
                .antMatchers("/quote/cancelMoveOrder").authenticated()
                .antMatchers("/quote/orderreview/**").authenticated()
                .antMatchers("/quote/updatePaymentInfo").authenticated()
                .antMatchers("/quote/inquireOrdersWithPortabilityStatus").authenticated()
                .antMatchers("/quote/inquireOrdersToSubmit").authenticated()

                .antMatchers("/marvel/offer/inquireCustomerEquipmentOfferList").authenticated()
                .antMatchers("/marvel/offer/inquireCustomerProductsRankingCategories").authenticated()
                .antMatchers("/marvel/offer/inquireTransferableRankingCategories").authenticated()
                .antMatchers("/marvel/product/customerproductsandservices").authenticated()
                .antMatchers("/card/fetch").authenticated()
                .antMatchers("/marvel/offer/**").permitAll()
                .antMatchers("/layout/**").permitAll()
                .antMatchers("/quote/**").permitAll()
                .antMatchers("/marvel/quote/**").permitAll()
                .antMatchers("/marvel/customerQuote/**").permitAll()
                .antMatchers("/preference/update/**").permitAll()

                .antMatchers("/marvel/opsupport/**").authenticated()
                .antMatchers("/api-documentation/**").authenticated()

                //appointment apis
                .antMatchers("/appointment/inquireAppointmentDetail").authenticated()
                .antMatchers("/appointment/inquireMissedAppointment").authenticated()
                .antMatchers("/appointment/inquireCustomerAppointments").authenticated()
                .antMatchers("/appointment/inquireCalendar").authenticated()
                .antMatchers("/marvel/appointment/**").authenticated()
                .antMatchers("/marvel/temp/clearCache").authenticated()
                .antMatchers("/marvel/techform/state/fetch/*").authenticated()
                .antMatchers("/marvel/submitorder/submitproductorder").authenticated()
                .antMatchers("/customerServiceDetail/search").authenticated()
                .antMatchers("/marvel/product/inquireChannelCompositionDetail").authenticated()
                .antMatchers("/marvel/product/validate/byodEquipmentSerialNumber").authenticated()
                .antMatchers("/marvel/productOrder/sendEquipmentRetrievalStatus").authenticated()
                .antMatchers("/bsninterspec/inquireSuspendResumeReason/**").authenticated()
                .antMatchers(HttpMethod.GET, "/inquireBsnFlowReasons").authenticated()
                .antMatchers("/terms/legalClause/**").permitAll()
                .antMatchers("/terms/**").authenticated()
                .antMatchers("/marvel/utility/lov/inquireSector").authenticated()
                .antMatchers("/custQuote/**").authenticated()
                .antMatchers("/marvel/corporate/**").authenticated()
                .antMatchers("/marvel/contact/**").authenticated()
                .antMatchers("/marvel/user/**").authenticated()
                .antMatchers("/marvel/partyRole/involvement").authenticated()
                .antMatchers("/marvel/organizationContract/**").authenticated()
                .antMatchers("/marvel/organization/bulkOrder/**").authenticated()
                .antMatchers("/marvel/organization/deliveryGroup/**").authenticated()
                .antMatchers("/marvel/organization/contact/**").authenticated()
                .antMatchers("/marvel/product/line/**").authenticated()
                .antMatchers("/marvel/organization/account/**").authenticated()
                .antMatchers("/marvel/organization/services/**").authenticated()
                .antMatchers("/marvel/organization/order/inquireManageAddonNotificationGroupOrderStatus/**").authenticated()

                // Commerce Permission end

                // ODF Permission start

                .antMatchers("/odf/product/**").authenticated()
                .antMatchers("/odf/customerSearchInfo/**").authenticated()
                .antMatchers("/odf/customer/**").authenticated()
                .antMatchers("/odf/bsninterspec/**").authenticated()
                .antMatchers("/odf/submitorder/**").authenticated()
                .antMatchers("/odf/productQualification/**").authenticated()
                .antMatchers("/odf/quote/ongoingQuote").authenticated()
                .antMatchers("/odf/priceGroup/**").authenticated()
                //.antMatchers("/odf/user/**").authenticated()
                //.antMatchers("/odf/utility/**").authenticated()
                .antMatchers("/odf/**").permitAll()


                // ODF Permission end
                .and()
                .headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}