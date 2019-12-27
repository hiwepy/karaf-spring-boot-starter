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
package org.apache.karaf.spring.boot;

import org.apache.karaf.spring.boot.jmx.JmxClientConfig;
import org.apache.karaf.spring.boot.sshd.SshdClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(KarafClientProperties.PREFIX)
public class KarafClientProperties {

	public static final String PREFIX = "karaf.client";

	private JmxClientConfig jmx = new JmxClientConfig();
	private SshdClientConfig sshd = new SshdClientConfig();

	public JmxClientConfig getJmx() {
		return jmx;
	}

	public void setJmx(JmxClientConfig jmx) {
		this.jmx = jmx;
	}

	public SshdClientConfig getSshd() {
		return sshd;
	}

	public void setSshd(SshdClientConfig sshd) {
		this.sshd = sshd;
	}

}
