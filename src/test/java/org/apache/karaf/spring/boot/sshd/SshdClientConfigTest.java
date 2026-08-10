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
package org.apache.karaf.spring.boot.sshd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link SshdClientConfig }}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("SshdClientConfig Tests")
class SshdClientConfigTest {

    @Test
    @DisplayName("Default constructor creates instance with expected defaults")
    void testDefaults() {
        SshdClientConfig config = new SshdClientConfig();
        assertThat(config).isNotNull();
        assertThat(config.getFile()).isNull();
        assertThat(config.getKeyFile()).isNull();
        assertThat(config.isBatch()).isFalse();
        assertThat(config.isInteractiveMode()).isFalse();
        assertThat(config.isInputPassword()).isFalse();
    }

    @Test
    @DisplayName("Getters and setters round-trip all fields")
    void testGettersAndSetters() {
        SshdClientConfig config = new SshdClientConfig();

        config.setHost("localhost");
        assertThat(config.getHost()).isEqualTo("localhost");

        config.setPort(8101);
        assertThat(config.getPort()).isEqualTo(8101);

        config.setUser("karaf");
        assertThat(config.getUser()).isEqualTo("karaf");

        config.setPassword("secret");
        assertThat(config.getPassword()).isEqualTo("secret");

        config.setLevel(2);
        assertThat(config.getLevel()).isEqualTo(2);

        config.setRetryAttempts(3);
        assertThat(config.getRetryAttempts()).isEqualTo(3);

        config.setRetryDelay(500);
        assertThat(config.getRetryDelay()).isEqualTo(500);

        config.setIdleTimeout(60_000L);
        assertThat(config.getIdleTimeout()).isEqualTo(60_000L);

        config.setBatch(true);
        assertThat(config.isBatch()).isTrue();

        config.setFile("/tmp/script.karaf");
        assertThat(config.getFile()).isEqualTo("/tmp/script.karaf");

        config.setKeyFile("/tmp/key.pem");
        assertThat(config.getKeyFile()).isEqualTo("/tmp/key.pem");

        config.setCommand("feature:list");
        assertThat(config.getCommand()).isEqualTo("feature:list");

        config.setInteractiveMode(true);
        assertThat(config.isInteractiveMode()).isTrue();

        config.setInputPassword(true);
        assertThat(config.isInputPassword()).isTrue();
    }

}
