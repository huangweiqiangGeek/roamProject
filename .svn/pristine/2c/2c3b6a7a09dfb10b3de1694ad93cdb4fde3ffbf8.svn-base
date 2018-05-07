package com.guoll.modules.framework.log;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.web.util.WebUtils;

/**
 * 
 * 
 */
public abstract class LogbackWebConfigurer {
	

    /** Parameter specifying the location of the logback config file */
    public static final String CONFIG_LOCATION_PARAM     = "logbackConfigLocation";

    /** Parameter specifying the refresh interval for checking the logback config file */
    public static final String REFRESH_INTERVAL_PARAM    = "logbackRefreshInterval";

    /** Parameter specifying whether to expose the web app root system property */
    public static final String EXPOSE_WEB_APP_ROOT_PARAM = "logbackExposeWebAppRoot";

    /**
     * Initialize logback, including setting the web app root system property.
     * 
     * @param servletContext
     *            the current ServletContext
     * @see WebUtils#setWebAppRootSystemProperty
     */
    public static void initLogging(ServletContext servletContext) {
        // Expose the web app root system property.
        if (exposeWebAppRoot(servletContext)) {
            //WebUtils.setWebAppRootSystemProperty(servletContext);
        }

        // Only perform custom logback initialization in case of a config file.
        String location = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        if (location != null) {
            // Perform actual logback initialization; else rely on logback's default initialization.
            try {
						//location = servletContext.getResource(location).getPath();
            	InputStream is = LogbackWebConfigurer.class.getResourceAsStream(location);
                LogbackConfigurer.initLogging(is);

            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("Invalid 'logbackConfigLocation' parameter: " + ex.getMessage());
            }
        }
    }

    
    /**
     * Shut down logback, properly releasing all file locks and resetting the web app root system property.
     * 
     * @param servletContext
     *            the current ServletContext
     * @see WebUtils#removeWebAppRootSystemProperty
     */
    public static void shutdownLogging(ServletContext servletContext) {
        servletContext.log("Shutting down logback");
        try {
            LogbackConfigurer.shutdownLogging();
        } finally {
            // Remove the web app root system property.
            if (exposeWebAppRoot(servletContext)) {
                WebUtils.removeWebAppRootSystemProperty(servletContext);
            }
        }
    }

    /**
     * Return whether to expose the web app root system property, checking the corresponding ServletContext init parameter.
     * 
     * @see #EXPOSE_WEB_APP_ROOT_PARAM
     */
    private static boolean exposeWebAppRoot(ServletContext servletContext) {
        String exposeWebAppRootParam = servletContext.getInitParameter(EXPOSE_WEB_APP_ROOT_PARAM);
        return (exposeWebAppRootParam == null || Boolean.valueOf(exposeWebAppRootParam));
    }

}
