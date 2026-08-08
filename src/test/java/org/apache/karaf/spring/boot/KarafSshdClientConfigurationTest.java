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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link KarafSshdClientConfiguration }}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("KarafSshdClientConfiguration Tests")
class KarafSshdClientConfigurationTest {

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        KarafSshdClientConfiguration instance = new KarafSshdClientConfiguration();
        assertThat(instance).isNotNull();
        assertThat(instance.getApplicationContext()).isNull();
    }

    @Test
    @DisplayName("setApplicationContext stores and getApplicationContext returns it")
    void testApplicationContextAware() {
        KarafSshdClientConfiguration config = new KarafSshdClientConfiguration();
        ApplicationContext context = new GenericApplicationContext();

        config.setApplicationContext(context);

        assertThat(config.getApplicationContext()).isSameAs(context);
    }

}
