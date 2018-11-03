package org.apache.karaf.spring.boot.jmx;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXDemo {

	public static void main(String[] args) throws Exception {
		
		
		mbeanServer.invoke(systemMBean, "halt", null, null);
		connector.close();
	}

}
