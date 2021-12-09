package ru.kl.proj.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.*;

public class MainWebAppInitializer implements WebApplicationInitializer {
    public void onStartup(final ServletContext servletContext){
        // Creates context object
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Registers annotated configurations class
        ctx.register(WebConfig.class, SpringSecurity.class, SpringConfig.class);

        // Sets ContextLoaderListener to servletContext
        servletContext.addListener(new ContextLoaderListener(ctx));

        // Passes servlet context to context instance
        ctx.setServletContext(servletContext);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);

        //Registers dispatch servlet and passes context instance
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);

        //Maps URL pattern
        servlet.addMapping("/");

        //Sets creation priority
        servlet.setLoadOnStartup(1);

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        //make sure encodingFilter is matched first
        filterRegistration.addMappingForUrlPatterns(null, false, "/");

        Logger logger = LoggerFactory.getLogger(MainWebAppInitializer.class);
        logger.info("STARTUP");
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
//
//        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());

        //Registers security filters
//        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());

        // Sets dispatcher types a security filters to be applied
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        security.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}