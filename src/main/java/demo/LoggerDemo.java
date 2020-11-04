package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerDemo {
	
	private static Logger log = LogManager.getLogger(LoggerDemo.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		log.debug("Debug Message");
		if(5>4) {
			log.info("Info Message");
			
			log.error("Error..");
			log.fatal("Fatal..");
		}
	}
}