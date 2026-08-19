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
/**
 * <p>Configuration properties for Karaf Client.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class KarafClientProperties {

	public static final String PREFIX = "karaf.client";

	private JmxClientConfig jmx = new JmxClientConfig();
	private SshdClientConfig sshd = new SshdClientConfig();
	/** Gets the jmx. */

	public JmxClientConfig getJmx() {
		return jmx;
	}
	/** Sets the jmx. */

	public void setJmx(JmxClientConfig jmx) {
		this.jmx = jmx;
	}
	/** Gets the sshd. */

	public SshdClientConfig getSshd() {
		return sshd;
	}
	/** Sets the sshd. */

	public void setSshd(SshdClientConfig sshd) {
		this.sshd = sshd;
	}

}
