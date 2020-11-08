/*     */ package com.nokia.cemod.cct.utils.conf;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import java.util.Properties;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class TestConfiguration
/*     */ {
/*  17 */   private static Logger logger = Logger.getLogger(TestConfiguration.class);
/*     */   
/*     */   private static final String CONFIGURATION_FILE = "conf/config.properties";
/*     */   
/*  21 */   private static Properties properties = new Properties();
/*     */   static {
/*     */     try {
/*  24 */       properties.load(new FileInputStream("conf/config.properties"));
/*  25 */       if (System.getProperty("testEnv") == null) {
/*  26 */         Path path = Paths.get("conf/config.properties_bak", new String[0]);
/*  27 */
    OutputStream stream = Files.newOutputStream(path, new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING });
/*     */           
/*  29 */          //properties.setProperty("cct.run.browser", System.getProperty("browser"));
/*  30 */          //properties.setProperty("cct.run.os", System.getProperty("platform"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  36 */           String url = System.getProperty("sut.bcmt.protocol") + "://" + System.getProperty("domain") + "/" + System.getProperty("release") + "/portal-ui/usecase-library.html";
/*  37 */         //  properties.setProperty("cct.env.portal.url", url);
/*  38 */          // properties.store(stream, (String)null);
/*     */
/*     */       } 
/*  41 */     } catch (IOException error) {
/*  42 */       logger.error("Could not load configuration file conf/config.properties", error);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_RUN_PLATFORM = "cct.run.os";
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_PROXY = "cct.proxy";
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_RUN_BROWSER = "cct.run.browser";
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_RUN_BROWSER_HEADLESS = "cct.run.browser.headless";
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_RUN_BROWSER_WINDOWS_CHROME_DRIVER_PATH = "cct.run.browser.windows.chromeDriverPath";
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_RUN_BROWSER_LINUX_CHROME_DRIVER_PATH = "cct.run.browser.linux.chromeDriverPath";
/*     */ 
/*     */   
/*     */   public static final String KEY_CCT_RUN_BROWSER_WINDOWS_FIREFOX_PATH = "cct.run.browser.windows.firefoxPath";
/*     */   
/*     */   public static final String KEY_CCT_RUN_BROWSER_LINUX_FIREFOX_PATH = "cct.run.browser.linux.firefoxPath";
/*     */   
/*     */   public static final String KEY_CCT_ENV_TYPE = "cct.env.type";
/*     */   
/*     */   public static final String KEY_CCT_REPORT_NO_DATA_AND_STALE_WIDGET_AS_FAILURES = "cct.env.reportNoDataAndStaleWidgetAsFailures";
/*     */   
/*     */   public static final String KEY_CCT_CAPTURE_SCREENSHOTS = "cct.env.captureScreenshots";
/*     */   
/*     */   public static final String KEY_CCT_ENV_DEVELOPMENT_USE_CASE_LAUNCH_URL = "cct.env.development.useCaseLaunchURL";
/*     */   
/*     */   public static final String KEY_CCT_ENV_DEVELOPMENT_USE_JSON_URL = "cct.env.development.useCaseJsonURL";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_URL = "cct.env.portal.url";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_USER = "cct.env.portal.user";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_PASSWORD = "cct.env.portal.password";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_USER_FIRST_NAME = "cct.env.portal.user.firstName";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_USER_LAST_NAME = "cct.env.portal.user.lastName";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_DESIGNER_FIRST_NAME = "cct.env.portal.designer.firstName";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_DESIGNER_LAST_NAME = "cct.env.portal.designer.lastName";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_DESIGNER_USER = "cct.env.portal.designer.user";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_DESIGNER_PASSWORD = "cct.env.portal.designer.password";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_ADMIN_USER = "cct.env.portal.admin.user";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_ADMIN_PASSWORD = "cct.env.portal.admin.password";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_CSPOWNER_USER = "cct.env.portal.cspowner.user";
/*     */   
/*     */   public static final String KEY_CCT_ENV_PORTAL_CSPOWNER_PASSWORD = "cct.env.portal.cspowner.password";
/*     */   
/*     */   public static final String KEY_CCT_ENV_DEVELOPMENT_TAB_1_USE_CASE_LAUNCH_URL = "cct.env.development.useCaseLaunchURL.tabUseCase1";
/*     */   
/*     */   public static final String KEY_CCT_ENV_DEVELOPMENT_TAB_2_USE_CASE_LAUNCH_URL = "cct.env.development.useCaseLaunchURL.tabUseCase2";
/*     */   
/*     */   public static final String KEY_CCT_ENV_DEVELOPMENT_TAB_3_USE_CASE_LAUNCH_URL = "cct.env.development.useCaseLaunchURL.tabUseCase3";
/*     */   
/*     */   public static final String KEY_CCT_ENV_DEVELOPMENT_TAB_4_USE_CASE_LAUNCH_URL = "cct.env.development.useCaseLaunchURL.tabUseCase4";
/*     */   
/*     */   public static final String KEY_CCT_ENV_SAI_USERNAME = "cct.portal.sai.username";
/*     */   
/*     */   public static final String KEY_CCT_ENV_SAI_PASSWORD = "cct.portal.sai.password";
/*     */   
/*     */   public static final String KEY_CCT_ENV_METADATA_URL = "cct.portal.metadata.boxi.url";
/*     */   
/*     */   public static final String KEY_CEMOD_ANALYTICS_ENDPOINT = "cemod.analytics.webservice.endpoint";
/*     */   
/*     */   public static final String KEY_CEMOD_SAI_SESSION_ENDPOINT = "cemod.sai.session.endpoint";
/*     */   
/*     */   public static final String KEY_CEMOD_SAI_SUBSCRIPTION_ENDPOINT = "cemod.sai.subscription.endpoint";
/*     */   
/*     */   public static final String KEY_CEMOD_SAI_MAP_GEOCODING_URL = "cemod.sai.map.geocoding.url";
/*     */   
/*     */   public static final String KEY_CEMOD_SAI_MAP_TEMPLATE_URL = "cemod.sai.map.template.url";
/*     */   
/*     */   public static final String KEY_CEMOD_CEI_CONF_ROOT = "cemod.cei.configuration.root";
/*     */   
/*     */   public static final String KEY_CEMOD_CEI_CONF_WEBSERV_ENDPOINT = "cemod.cei.configuration.webservice.endpoint";
/*     */   
/*     */   public static final String KEY_CEMOD_OFFLINE_MAP_URL = "cemod.offline.map.url";
/*     */   
/*     */   public static final String KEY_CEMOD_TNP_ALERT_MON_ENDPOINT = "cemod.tnp.alert.monitor.endpoint";
/*     */   
/*     */   public static final String KEY_CEMOD_UNMAPPED_RESPONSE_VALUES = "cemod.unmapped.response.values";
/*     */   
/*     */   public static final String KEY_CEMOD_SQM_URL = "cemod.sqm.url";
/*     */   
/*     */   public static final String KEY_CCT_WAIT_FOR_WEB_ELEMENT_TIME_OUT = "cct.waitForWebElementTimeOut";
/*     */   
/*     */   public static final String KEY_CCT_WAIT_FOR_WIDGETS_TO_RENDER_TIME = "cct.waitForWidgetsToRenderTime";
/*     */   
/*     */   public static final String KEY_CCT_WAIT_FOR_USE_CASE_TO_LOAD_TIME = "cct.waitForUseCaseToLoadTime";
/*     */   
/*     */   public static final String KEY_CCT_WAIT_FOR_QUERY_RESPONSE_TIME_OUT = "cct.waitForQueryResponseTimeOut";
/*     */   
/*     */   public static final String VALUE_CCT_RUN_BROWSER_CHROME = "chrome";
/*     */   
/*     */   public static final String VALUE_CCT_RUN_BROWSER_FIREFOX = "firefox";
/*     */   
/*     */   public static final String VALUE_CCT_RUN_BROWSER_REMOTE = "remote";
/*     */   
/*     */   public static final String VALUE_CCT_ENV_TYPE_DEVELOPMENT = "development";
/*     */   
/*     */   public static final String VALUE_CCT_ENV_TYPE_PORTAL = "portal";
/*     */   
/*     */   public static final String VALUE_CCT_ENV_TYPE_CEMNova = "CEMNova";
/*     */   
/*     */   public static final String CGF_TIME_FILTER_KEYS = "filter_time";
/*     */   
/*     */   public static final String CGF_TECHNOLOGY_FILTER_KEYS = "filter_technology";
/*     */   
/*     */   public static final String CGF_LOCATION_FILTER_KEYS = "filter_location";
/*     */   
/*     */   public static final String CGF_APN_FILTER_KEYS = "filter_apns";
/*     */   
/*     */   public static final String CGF_DEVICE_FILTER_KEYS = "filter_devices";
/*     */   
/*     */   public static final String CGF_SUBSCRIBER_FILTER_KEYS = "filter_subscribers";
/*     */   
/*     */   public static final String CGF_ROAMING_CATEGORY = "filter_roamingcategory";
/*     */   
/*     */   public static final long CGF_TIME_OUT = 30L;
/*     */   
/*     */   public static final String CCT_FEATURES_PATH = "%s.features";
/*     */   
/*     */   public static final String CCT_PARALLEL_EXECUTION_NODES = "cct.parallelExecution.nodes";
/*     */   
/*     */   public static final String CCT_NODE_PORTAL_USER = "%s.portal.user";
/*     */   
/*     */   public static final String CCT_NODE_PORTAL_USER_FIRST_NAME = "%s.portal.user.firstName";
/*     */   
/*     */   public static final String CCT_NODE_PORTAL_USER_LAST_NAME = "%s.portal.user.lastName";
/*     */   
/*     */   public static final String CCT_NODE_PORTAL_USER_PASSWORD = "%s.portal.password";
/*     */   
/*     */   public static final String CCT_STABILITY_ROUNDS = "cct.stability.numberOfRounds";
/*     */   
/*     */   public static final String KEY_PORTAL_BULK_USER_ADD_FILE_PATH = "cct.portal.bulk.user.add.filepath";
/*     */   
/*     */   public static final String KEY_PORTAL_BULK_USER_UPDATE_FILE_PATH = "cct.portal.bulk.user.update.filepath";
/*     */   
/*     */   public static final String KEY_PORTAL_BULK_USER_DELETE_FILE_PATH = "cct.portal.bulk.user.delete.filepath";
/*     */   
/*     */   public static final String KEY_PORTAL_BULK_USER_ADD_WITH_ROLES_FILE_PATH = "cct.portal.bulk.user.addwithroles.filepath";
/*     */   
/*     */   public static final String CCT_DRILLDOWN_TEST = "cct.drilldown.test";
/*     */ 
/*     */   
/*     */   public static String getProperty(String key) {
/* 204 */     return properties.getProperty(key);
/*     */   }
/*     */   
/*     */   public static String getProperty(String key, String defaultValue) {
/* 208 */     return properties.getProperty(key, defaultValue);
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\conf\TestConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */