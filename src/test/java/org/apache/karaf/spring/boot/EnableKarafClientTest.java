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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link EnableKarafClient }}.
 *
 * <p>{@code EnableKarafClient} is an annotation type, so it cannot be instantiated
 * directly; its meta-annotations are verified via reflection instead.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("EnableKarafClient Tests")
class EnableKarafClientTest {

    @Test
    @DisplayName("Is retained at runtime and targets types")
    void testRetentionAndTarget() {
        AnnotatedElement elem = EnableKarafClient.class;

        assertThat(elem.isAnnotationPresent(Retention.class)).isTrue();
        assertThat(elem.getAnnotation(Retention.class).value()).isEqualTo(RetentionPolicy.RUNTIME);

        assertThat(elem.isAnnotationPresent(Target.class)).isTrue();
        assertThat(elem.getAnnotation(Target.class).value()).contains(ElementType.TYPE);
    }

    @Test
    @DisplayName("Is documented and inherited")
    void testDocumentedAndInherited() {
        AnnotatedElement elem = EnableKarafClient.class;
        assertThat(elem.isAnnotationPresent(Documented.class)).isTrue();
        assertThat(elem.isAnnotationPresent(Inherited.class)).isTrue();
    }

    @Test
    @DisplayName("Imports the JMX client configuration")
    void testImportsJmxClientConfiguration() {
        org.springframework.context.annotation.Import importAnno =
                EnableKarafClient.class.getAnnotation(org.springframework.context.annotation.Import.class);
        assertThat(importAnno).isNotNull();
        assertThat(importAnno.value()).contains(KarafJmxClientConfiguration.class);
    }

}
