/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.karaf.spring.boot.jmx;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.InitializingBean;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

/**
 * http://karaf.apache.org/manual/latest/#_monitoring_and_management_using_jmx
 * http://karaf.apache.org/manual/latest/#_mbeans
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 */
public class KarafJmxClient implements InitializingBean {
	
	// 
	protected Map<String,String> mbeans = Maps.newHashMap();
	protected LoadingCache<String, ObjectName> mbeansCaches;
	protected RateLimiter limiter;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		/*
		org.apache.karaf:type=[feature],name=[instance]
	    org.apache.karaf:type=bundle,name=*: management of the OSGi bundles.
	    org.apache.karaf:type=config,name=*: management of the configurations.
	    org.apache.karaf:type=diagnostic,name=*: creation of dumps containing the current Apache Karaf activity (used for diagnostic).
	    org.apache.karaf:type=feature,name=*: management of the Apache Karaf features.
	    org.apache.karaf:type=http,name=*: management of the HTTP service (provided by the http feature).
	    org.apache.karaf:type=instance,name=*: management of the instances.
	    org.apache.karaf:type=jdbc,name=*: management of the JDBC service (provided by the jdbc feature).
	    org.apache.karaf:type=jms,name=*: management of the JMS service (provided by the jms feature).
	    org.apache.karaf:type=jndi,name=*: management of the JNDI service (provided by the jndi feature).
	    org.apache.karaf:type=kar,name=*: management of the KAR file.
	    org.apache.karaf:type=log,name=*: management of the log service.
	    org.apache.karaf:type=obr,name=*: management of the OBR service (provided by the obr feature).
	    org.apache.karaf:type=package,name=*: details about packages exported/imported.
	    org.apache.karaf:type=service,name=*: management of the OSGi services.
	    org.apache.karaf:type=system,name=*: management of the Apache Karaf container itself (halt, restart, etc).
	    org.apache.karaf:type=web,name=*: management of WebApplications (provided by the war feature).
	    org.apache.karaf:type=wrapper,name=*: management of the service wrapper (provided by the wrapper feature).
		*/
		this.mbeansCaches = CacheBuilder.newBuilder()
			.expireAfterWrite(1, TimeUnit.DAYS)
			.build(new CacheLoader<String, ObjectName>() {
				@Override
				public ObjectName load(String pattern) throws Exception {
					Object[] args = pattern.contains(":") ? pattern.split(":") : new String[] { pattern, "*"};
					return new ObjectName(String.format("org.apache.karaf:type=%s,name=%s", args));
				}
			});
		limiter = RateLimiter.create(10);
	}
	 
	
	{
		try {
			ObjectName systemMBean = new ObjectName("org.apache.karaf:type=system,name=karaf-root");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
	}
	
	
	// http://karaf.apache.org/manual/latest/#_connecting
	public MBeanServerConnection connecting() throws IOException {
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/karaf-root");
		JMXConnector connector = JMXConnectorFactory.connect(url, null);
		return connector.getMBeanServerConnection();
	}
	
	// http://karaf.apache.org/manual/latest/#_mbeans
		
	public void execute(String feature,String command) throws IOException {
		
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/karaf-root");
		JMXConnector connector = JMXConnectorFactory.connect(url, null);
		//mbeanServer = connector.getMBeanServerConnection();
		
	}
	
	
	
}
