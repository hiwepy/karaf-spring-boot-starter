package org.apache.karaf.spring.boot.sshd;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;

public class Main {

	public static void main(String[] args) throws Exception {

		String host = "localhost";
		int port = 8101;
		String username = "karaf";
		String password = "karaf";

		SshClient client = null;
		try {

			client = SshClient.setUpDefaultClient();
			client.start();
			ConnectFuture future = client.connect(username, host, port);
			future.await();
			ClientSession session = future.getSession();
			session.addPasswordIdentity(password);
			ClientChannel channel = session.createChannel("shell");
			channel.setIn(System.in);
			channel.setOut(System.out);
			channel.setErr(System.err);
			channel.open();

			Set<ClientChannelEvent> VALUES = Collections.unmodifiableSet(EnumSet.of(ClientChannelEvent.CLOSED));
			channel.waitFor(VALUES, 100000);

		} catch (Throwable t) {
			t.printStackTrace();
			// System.exit(1);
		} finally {
			/*
			 * try { client.stop(); } catch (Throwable t) { }
			 */
		}
		// System.exit(0);
	}

}
