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

/**
 * TODO
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

public class JmxClientConfig {

    private String host;
    private int port;
    private String user;
    private String password;
    private int level;
    private int retryAttempts;
    private int retryDelay;
    private long idleTimeout;
    private boolean batch;
    private String file = null;
    private String keyFile = null;
    private String command;
    private boolean interactiveMode = false;
    private boolean inputPassword = false;
	/** Gets the host. */
	public String getHost() {
		return host;
	}
	/** Sets the host. */
	public void setHost(String host) {
		this.host = host;
	}
	/** Gets the port. */
	public int getPort() {
		return port;
	}
	/** Sets the port. */
	public void setPort(int port) {
		this.port = port;
	}
	/** Gets the user. */
	public String getUser() {
		return user;
	}
	/** Sets the user. */
	public void setUser(String user) {
		this.user = user;
	}
	/** Gets the password. */
	public String getPassword() {
		return password;
	}
	/** Sets the password. */
	public void setPassword(String password) {
		this.password = password;
	}
	/** Gets the level. */
	public int getLevel() {
		return level;
	}
	/** Sets the level. */
	public void setLevel(int level) {
		this.level = level;
	}
	/** Gets the retry attempts. */
	public int getRetryAttempts() {
		return retryAttempts;
	}
	/** Sets the retry attempts. */
	public void setRetryAttempts(int retryAttempts) {
		this.retryAttempts = retryAttempts;
	}
	/** Gets the retry delay. */
	public int getRetryDelay() {
		return retryDelay;
	}
	/** Sets the retry delay. */
	public void setRetryDelay(int retryDelay) {
		this.retryDelay = retryDelay;
	}
	/** Gets the idle timeout. */
	public long getIdleTimeout() {
		return idleTimeout;
	}
	/** Sets the idle timeout. */
	public void setIdleTimeout(long idleTimeout) {
		this.idleTimeout = idleTimeout;
	}
	/**
	 * <p>Is batch.</p>
	 * @return the boolean
	 */
	public boolean isBatch() {
		return batch;
	}
	/** Sets the batch. */
	public void setBatch(boolean batch) {
		this.batch = batch;
	}
	/** Gets the file. */
	public String getFile() {
		return file;
	}
	/** Sets the file. */
	public void setFile(String file) {
		this.file = file;
	}
	/** Gets the key file. */
	public String getKeyFile() {
		return keyFile;
	}
	/** Sets the key file. */
	public void setKeyFile(String keyFile) {
		this.keyFile = keyFile;
	}
	/** Gets the command. */
	public String getCommand() {
		return command;
	}
	/** Sets the command. */
	public void setCommand(String command) {
		this.command = command;
	}
	/**
	 * <p>Is interactive mode.</p>
	 * @return the boolean
	 */
	public boolean isInteractiveMode() {
		return interactiveMode;
	}
	/** Sets the interactive mode. */
	public void setInteractiveMode(boolean interactiveMode) {
		this.interactiveMode = interactiveMode;
	}
	/**
	 * <p>Is input password.</p>
	 * @return the boolean
	 */
	public boolean isInputPassword() {
		return inputPassword;
	}
	/** Sets the input password. */
	public void setInputPassword(boolean inputPassword) {
		this.inputPassword = inputPassword;
	}
    
    
}
