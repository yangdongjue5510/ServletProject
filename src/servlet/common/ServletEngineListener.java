package servlet.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletEngineListener implements ServletContextListener {

    public ServletEngineListener() {
        System.out.println("===> ServletEngineListener created");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("---> contextInitialized() called");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("---> contextDestroyed() destroyed");
    }	
}














