package com.svsos.api;

import org.eclipse.jetty.server.Server;

import com.svsos.core.jetty.JettyFactory;
import com.svsos.core.spring.Profiles;

public class StartServer {

	public static final int PORT = 8080;
	public static final String CONTEXT = "/lianxi-api";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh",
			"spring-webmvc", "shiro-web", "lianxi-core" };

	public static void main(String[] args) throws Exception {

		System.setProperty("net.sf.ehcache.pool.sizeof.AgentSizeOf.bypass",
				"true");
		// 设定Spring的profile
		Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

		// 启动Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			server.start();

			System.out.println("[INFO] Server running at http://localhost:"
					+ PORT + CONTEXT);
			System.out
					.println("[HINT] Hit Enter to reload the application quickly");

			// 等待用户输入回车重载应用.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
