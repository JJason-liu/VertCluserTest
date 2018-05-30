package mytest.vertclusertest.jason.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hazelcast.util.function.Consumer;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import mytest.vertclusertest.jason.deploy.optionsReader;

/**
 * 部署Vertical ，启动集群类
 * 
 * @author 有泪的北极星 qq: 76598166
 * @date 2018年5月30日 下午2:36:21
 */
public class ClusterStarter {

	private static final Logger log = LogManager.getLogger(ClusterStarter.class);

	public static void main(String[] args) throws Exception {
		final VertxOptions options = new VertxOptions();
		// 设置参数，启用集群
		options.setClustered(true);

		DeploymentOptions readOpts = optionsReader.readOpts();

		Consumer<Vertx> runner = vertx -> {
			vertx.deployVerticle("mytest.vertclusertest.jason.verticle.RootVerticle", readOpts);
			vertx.deployVerticle("mytest.vertclusertest.jason.verticle.PollVerticle", readOpts);
		};

		if (options.isClustered()) {
			Vertx.clusteredVertx(options, result -> {

				if (result.succeeded()) {
					Vertx vertx = result.result();
					runner.accept(vertx);
					log.info("pushservice is running with cluster by hazelcast.");
				} else {
					log.error("cluster running with error: " + result.cause().getMessage());
				}
			});
		} else {
			Vertx vertx = Vertx.vertx(options);
			runner.accept(vertx);
		}
	}
}
