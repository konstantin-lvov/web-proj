package ru.kl.proj.configs;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class MainWebAppInitializer implements WebApplicationInitializer {
    public void onStartup(final ServletContext servletContext) throws ServletException {
        // Creates context object
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Registers annotated configurations class
        ctx.register(WebConfig.class, SpringSecurity.class, SpringConfig.class);

        // Sets ContextLoaderListener to servletContext
        servletContext.addListener(new ContextLoaderListener(ctx));

        // Passes servlet context to context instance
        ctx.setServletContext(servletContext);

        //Registers dispatch servlet and passes context instance
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

        //Maps URL pattern
        servlet.addMapping("/");

        //Sets creation priority
        servlet.setLoadOnStartup(1);

        //Registers security filters
//        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());

        // Sets dispatcher types a security filters to be applied
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        security.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}