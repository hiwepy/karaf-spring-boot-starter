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

import java.lang.reflect.Field;
import java.util.Map;

import javax.management.ObjectName;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.cache.LoadingCache;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Unit tests for {{ @link KarafJmxClient }}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("KarafJmxClient Tests")
class KarafJmxClientTest {

    @Test
    @DisplayName("Default constructor creates instance and runs the instance initializer block")
    void testInstantiation() {
        // The constructor executes the instance initializer block which builds an
        // ObjectName for "org.apache.karaf:type=system,name=karaf-root". It must not throw.
        KarafJmxClient client = new KarafJmxClient();
        assertThat(client).isNotNull();
        assertThat(client.mbeans).isEmpty();
        assertThat(client.mbeansCaches).isNull();
        assertThat(client.limiter).isNull();
    }

    @Test
    @DisplayName("afterPropertiesSet builds the mbeans cache and rate limiter")
    void testAfterPropertiesSet() throws Exception {
        KarafJmxClient client = new KarafJmxClient();
        client.afterPropertiesSet();

        assertThat(client.mbeansCaches).isNotNull();
        assertThat(client.limiter).isNotNull();

        // Cache resolves "feature:pattern" form -> org.apache.karaf:type=feature:name=pattern
        ObjectName featureName = client.mbeansCaches.get("feature:root");
        assertThat(featureName)
                .isEqualTo(new ObjectName("org.apache.karaf:type=feature,name=root"));

        // Cache resolves bare "feature" form -> type=feature:name=*
        ObjectName bareName = client.mbeansCaches.get("bundle");
        assertThat(bareName)
                .isEqualTo(new ObjectName("org.apache.karaf:type=bundle,name=*"));
    }

    @Test
    @DisplayName("connecting() attempts a JMX connection to localhost:1099 and fails with IOException")
    void testConnectingFailsWithoutServer() {
        KarafJmxClient client = new KarafJmxClient();
        assertThatExceptionOfType(java.io.IOException.class)
                .isThrownBy(client::connecting);
    }

    @Test
    @DisplayName("execute(feature,command) attempts a JMX connection and fails with IOException")
    void testExecuteFailsWithoutServer() {
        KarafJmxClient client = new KarafJmxClient();
        assertThatExceptionOfType(java.io.IOException.class)
                .isThrownBy(() -> client.execute("feature", "list"));
    }

    @Test
    @DisplayName("mbeans map is the instance field exposed for subclass configuration")
    void testMbeansMapFieldAccessible() throws Exception {
        KarafJmxClient client = new KarafJmxClient();
        Field field = KarafJmxClient.class.getDeclaredField("mbeans");
        field.setAccessible(true);
        Object value = field.get(client);
        assertThat(value).isInstanceOf(Map.class);
    }

}
