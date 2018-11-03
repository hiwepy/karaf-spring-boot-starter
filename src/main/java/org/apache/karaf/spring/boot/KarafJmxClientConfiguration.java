package org.apache.karaf.spring.boot;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KarafClientProperties.class)
public class KarafJmxClientConfiguration implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	
	
	
	/*
    @ConditionalOnMissingBean
    //@ConditionalOnClass(org.owasp.csrfguard.CsrfGuard.class)
	public ServletRegistrationBean<JavaScriptServlet> javaScriptServlet(KarafClientProperties properties) throws Exception {

		ServletRegistrationBean<JavaScriptServlet> registrationBean = new ServletRegistrationBean<JavaScriptServlet>();
        
		JavaScriptServlet javaScriptServlet = new JavaScriptServlet();
		
		registrationBean.setServlet(javaScriptServlet);
		
		// 默认参数
		CsrfguardJavascriptServletProperties javascript = properties.getJavascript();
		registrationBean.addInitParameter("cache-control", javascript.getCacheControl());
		registrationBean.addInitParameter("domain-strict", Boolean.toString(javascript.isDomainStrict()));
		registrationBean.addInitParameter("inject-into-attributes", Boolean.toString(javascript.isInjectIntoAttributes()));
		registrationBean.addInitParameter("inject-get-forms", Boolean.toString(javascript.isInjectGetForms()));
		registrationBean.addInitParameter("inject-form-attributes", Boolean.toString(javascript.isInjectFormAttributes()));
		registrationBean.addInitParameter("inject-into-forms", Boolean.toString(javascript.isInjectIntoForms()));
		registrationBean.addInitParameter("referer-pattern", javascript.getRefererPattern());
		registrationBean.addInitParameter("referer-match-domain", Boolean.toString(javascript.isRefererMatchDomain()));
		registrationBean.addInitParameter("source-file", javascript.getSourceFile());
		registrationBean.addInitParameter("x-requested-with", javascript.getXRequestedWith());
		registrationBean.addUrlMappings(javascript.getPattern());

        return registrationBean;
    }
	
	@Bean
	@ConditionalOnMissingBean
	protected ServletListenerRegistrationBean<CsrfGuardHttpSessionListener> csrfGuardHttpSessionListener()
			throws Exception {

		ServletListenerRegistrationBean<CsrfGuardHttpSessionListener> registrationBean = new ServletListenerRegistrationBean<CsrfGuardHttpSessionListener>();
		registrationBean.setListener(new CsrfGuardHttpSessionListener());
		registrationBean.setOrder(Integer.MIN_VALUE);

		return registrationBean;
	}
	
	
	@Bean
    @ConditionalOnMissingBean
    protected FilterRegistrationBean<CsrfGuardFilter> csrfGuardFilter() throws Exception {

        FilterRegistrationBean<CsrfGuardFilter> filterRegistrationBean = new FilterRegistrationBean<CsrfGuardFilter>();
        filterRegistrationBean.setFilter(new CsrfGuardFilter());
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);
        
        return filterRegistrationBean;
    }
	*/
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
 