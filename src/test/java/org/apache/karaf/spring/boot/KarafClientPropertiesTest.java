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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link KarafClientProperties }}.
 *
 * <p>Verifies default values, getters/setters and POJO contract.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("KarafClientProperties Tests")
class KarafClientPropertiesTest {

    @Test
    @DisplayName("Default constructor creates instance with non-null jmx/sshd configs")
    void testDefaults() {
        KarafClientProperties props = new KarafClientProperties();
        assertThat(props).isNotNull();
        assertThat(props.getJmx()).isNotNull().isInstanceOf(JmxClientConfig.class);
        assertThat(props.getSshd()).isNotNull().isInstanceOf(SshdClientConfig.class);
    }

    @Test
    @DisplayName("Getters and setters round-trip jmx/sshd config objects")
    void testGettersAndSetters() {
        KarafClientProperties props = new KarafClientProperties();

        JmxClientConfig jmx = new JmxClientConfig();
        jmx.setHost("jmx-host");
        props.setJmx(jmx);
        assertThat(props.getJmx()).isSameAs(jmx);

        SshdClientConfig sshd = new SshdClientConfig();
        sshd.setHost("sshd-host");
        props.setSshd(sshd);
        assertThat(props.getSshd()).isSameAs(sshd);
    }

    @Test
    @DisplayName("Public constant 'PREFIX' has expected value")
    void testPREFIXConstant() {
        assertThat(KarafClientProperties.PREFIX).isEqualTo("karaf.client");
    }

}
