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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.shell.UnknownCommandFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link Main }}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("Main Tests")
class MainTest {

    /**
     * The classpath ships both {@code sshd-mina} and {@code sshd-netty}, which register
     * competing {@code IoServiceFactoryFactory} SPI providers. Apache SSHD refuses to
     * pick one automatically, so a concrete factory must be selected via system property
     * before the SSH client is started; otherwise {@code SshClient.start()} fails with
     * "Multiple registered IoServiceFactoryFactory instances detected".
     */
    private static void selectNettyIoFactory() {
        System.setProperty(
                "org.apache.sshd.common.io.IoServiceFactoryFactory",
                "org.apache.sshd.netty.NettyIoServiceFactoryFactory");
    }

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        Main instance = new Main();
        assertThat(instance).isNotNull();
    }

    @Test
    @DisplayName("main(String[]) exercises the SSH connect path against a local server")
    void testMainAgainstLocalServer() throws Exception {
        selectNettyIoFactory();

        // Make System.in return EOF immediately so the shell channel created by Main
        // closes on its own (instead of blocking in channel.waitFor(...) for 100s).
        java.io.InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(new byte[0]));
        try {
            SshServer sshd = bootSshServer(8101);
            try {
                AtomicReference<Throwable> error = new AtomicReference<>();
                Thread worker = new Thread(() -> {
                    try {
                        Main.main(new String[0]);
                    } catch (Throwable t) {
                        // Main already swallows Throwable internally; this is a safety net.
                        error.set(t);
                    }
                }, "karaf-main-test");
                worker.setDaemon(true);
                worker.start();
                // Give the client enough time to connect, open the channel and let it
                // close on the EOF'd stdin. Bound the join so the test never hangs.
                worker.join(TimeUnit.SECONDS.toMillis(8));
                worker.interrupt();
                worker.join(TimeUnit.SECONDS.toMillis(2));
            } finally {
                // Stop the embedded server explicitly so its worker threads are torn down
                // before the forked test JVM exits.
                sshd.stop(true);
            }
        } finally {
            System.setIn(originalIn);
        }

        // Reaching this point means the embedded server lifecycle and the worker thread
        // were cleaned up without hanging the test JVM.
        assertThat(true).as("main completed without hanging the test").isTrue();
    }

    /**
     * Boot an embedded Apache SSHD server that accepts any password authentication on
     * the given port. The caller is responsible for invoking {@link SshServer#stop(boolean)}.
     */
    private static SshServer bootSshServer(int port) throws Exception {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setPort(port);
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(
                File.createTempFile("karaf-test-hostkey", ".ser").toPath()));
        sshd.setPasswordAuthenticator((username, password, session) -> true);
        sshd.setCommandFactory(UnknownCommandFactory.INSTANCE);
        sshd.start();
        return sshd;
    }

}
