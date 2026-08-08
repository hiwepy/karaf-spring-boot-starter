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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

/**
 * Unit tests for {{ @link JMXDemo }}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("JMXDemo Tests")
class JMXDemoTest {

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        JMXDemo instance = new JMXDemo();
        assertThat(instance).isNotNull();
    }

    @Test
    @DisplayName("main(String[]) runs without throwing even with no args")
    void testMain() {
        // The body of main is effectively empty (commented-out body), so it must return
        // without side effects. Invoke it to cover the method.
        assertThatNoException().isThrownBy(() -> JMXDemo.main(new String[0]));
    }

}
