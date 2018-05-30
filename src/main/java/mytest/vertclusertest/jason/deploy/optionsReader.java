package mytest.vertclusertest.jason.deploy;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import mytest.vertclusertest.jason.loader.ResourceLoader;

/**
 * 获取集群和部署配置类属性
 * 
 * @author 有泪的北极星 qq: 76598166
 * @date 2018年5月30日 上午11:44:19
 */
public class optionsReader {
	private static final String VX_PREFIX = "vertx.";
	private static ResourceLoader loader = ResourceLoader.getInstance();

	public static VertxOptions readOpts(final String name) throws Exception {
		final VertxOptions options = new VertxOptions();
		options.setEventLoopPoolSize((int) loader.getValue(VX_PREFIX + name + ".pool.size.event.loop", Integer.TYPE));
		options.setWorkerPoolSize((int) loader.getValue(VX_PREFIX + name + ".pool.size.worker", Integer.TYPE));
		options.setInternalBlockingPoolSize(
				(int) loader.getValue(VX_PREFIX + name + ".pool.size.internal.blocking", Integer.TYPE));

		options.setClustered((boolean) loader.getValue(VX_PREFIX + name + ".cluster.enabled", Boolean.TYPE));
		options.setClusterHost((String) loader.getValue(VX_PREFIX + name + ".cluster.host", String.class));
		options.setClusterPort((int) loader.getValue(VX_PREFIX + name + ".cluster.port", Integer.TYPE));

		options.setClusterPingInterval(
				(long) loader.getValue(VX_PREFIX + name + ".cluster.ping.interval", Boolean.TYPE));
		options.setClusterPingReplyInterval(
				(long) loader.getValue(VX_PREFIX + name + ".cluster.ping.interval.reply", Boolean.TYPE));

		options.setBlockedThreadCheckInterval(
				(long) loader.getValue(VX_PREFIX + name + ".blocked.thread.check.interval", Boolean.TYPE));
		options.setMaxEventLoopExecuteTime(
				(long) loader.getValue(VX_PREFIX + name + ".execute.time.max.event.loop", Boolean.TYPE));
		options.setMaxWorkerExecuteTime(
				(long) loader.getValue(VX_PREFIX + name + ".execute.time.max.worker", Boolean.TYPE));

		options.setHAEnabled((boolean) loader.getValue(VX_PREFIX + name + ".ha.enabled", Boolean.TYPE));
		options.setHAGroup((String) loader.getValue(VX_PREFIX + name + ".ha.group", String.class));
		options.setQuorumSize((int) loader.getValue(VX_PREFIX + name + ".quorum.size", Integer.TYPE));
		options.setWarningExceptionTime(
				(long) loader.getValue(VX_PREFIX + name + ".warning.exception.time", Boolean.TYPE));

		return options;
	}

	public static DeploymentOptions readOpts() throws Exception {
		final DeploymentOptions options = new DeploymentOptions();
		options.setConfig(new JsonObject().put("http.port", (int) loader.getValue("http.port", Integer.TYPE))
				.put("db_name", (String) loader.getValue("db_name", String.class))
				.put("connection_string", (String) loader.getValue("connection_string", String.class)));
		return options;
	}

}
