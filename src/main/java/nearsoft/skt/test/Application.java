package nearsoft.skt.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.cloud.config.client.ConfigClientAutoConfiguration;

@Component
@PropertySource("classpath:application.properties")
public class Application{

	private static final Logger LOGGER = Logger.getLogger(Application.class);

	@Autowired ActionPerformer actionPerformer;

	public ActionPerformer getActionPerformer() {
		return actionPerformer;
	}

	public static void main(String[] args) {
		ApplicationContext appContext = new AnnotationConfigApplicationContext("nearsoft");
		for(String profile: appContext.getEnvironment().getActiveProfiles()){
			LOGGER.info("Active profile:" + profile);
		}
		Application application = appContext.getBean(Application.class);
		application.getActionPerformer().execute(Integer.parseInt(args[0]));
		LOGGER.info("App running");
	}

}