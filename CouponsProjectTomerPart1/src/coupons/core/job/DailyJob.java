package coupons.core.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Background thread job to cleanup expired coupons once every rate hours
 */
public class DailyJob {
	private static int rate;
	private static Runnable CouponExpirationDailyJob = new CouponExpirationDailyJob();
	private static Timer t = new Timer(true);
	private static TimerTask task = new TimerTask() {

		@Override
		public synchronized void run() {
			CouponExpirationDailyJob.run();
		}
	};

	static {
		// loads default job rate from default configuration file
		Properties defaultProperties = new Properties();
		File defaultPropertiesFile = new File("files/default.app.properties");
		try (FileInputStream defaultFin = new FileInputStream(defaultPropertiesFile)) {
			defaultProperties.load(defaultFin);
		} catch (IOException e) {
			System.out.println("failed to initialize default properties" + e.getMessage());
		}
		// loads job rate from configuration file
		Properties properties = new Properties(defaultProperties);
		File propertiesFile = new File("files/app.properties");
		try (FileInputStream fin = new FileInputStream(propertiesFile)) {
			properties.load(fin);
			rate = Integer.parseInt(properties.getProperty("job.rate"));
		} catch (IOException e1) {
			System.out.println("failed to initialize properties" + e1.getMessage());
		}
	}

	public synchronized static void startJob() {
		t.scheduleAtFixedRate(task, 0, TimeUnit.HOURS.toMillis(rate));
	}

	// maybe unnecessary since timer starts a daemon thread
	public synchronized static void stopJob() {
		t.cancel();
	}
}