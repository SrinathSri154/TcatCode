/*    */ package com.nokia.cemod.cct.utils.conf;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortalTestConfiguration
/*    */ {
/* 12 */   private static Logger logger = Logger.getLogger(TestConfiguration.class);
/*    */   
/*    */   private static final String CONFIGURATION_FILE = "conf/portal_config.properties";
/*    */   
/* 16 */   private static Properties properties = new Properties();
/*    */   static {
/*    */     try {
/* 19 */       properties.load(new FileInputStream("conf/portal_config.properties"));
/* 20 */     } catch (IOException error) {
/* 21 */       logger.error("Could not load configuration file conf/portal_config.properties", error);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static final String LDAP_SERVER_NAME = "ldap.server.name";
/*    */   
/*    */   public static final String LDAP_BASE_PROVIDER_URL = "ldap.base.provider.url";
/*    */   
/*    */   public static final String LDAP_BASE_DN = "ldap.base.dn";
/*    */   
/*    */   public static final String LDAP_PRINCIPAL = "ldap.principal";
/*    */   
/*    */   public static final String LDAP_CREDENTIALS = "ldap.credentials";
/*    */   
/*    */   public static final String LDAP_AUTHENTICATION_SEARCH_FILTER = "ldap.authentication.search.filter";
/*    */   
/*    */   public static final String LDAP_IMPORT_SEARCH_FILTER = "ldap.import.search.filter";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_UUID = "ldap.user.mapping.uuid";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_SCREEN_NAME = "ldap.user.mapping.screen.name";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_EMAIL_ADDRESS = "ldap.user.mapping.email.address";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_PASSWORD = "ldap.user.mapping.password";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_FIRST_NAME = "ldap.user.mapping.first.name";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_MIDDLE_NAME = "ldap.user.mapping.middle.name";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_LAST_NAME = "ldap.user.mapping.last.name";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_FULL_NAME = "ldap.user.mapping.full.name";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_JOB_TITLE = "ldap.user.mapping.job.title";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_STATUS = "ldap.user.mapping.status";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_GROUP = "ldap.user.mapping.group";
/*    */   
/*    */   public static final String LDAP_USER_MAPPING_POTRAIT = "ldap.user.mapping.potrait";
/*    */   
/*    */   public static final String LDAP_GROUPS_IMPORT_SEARCH_FILTER = "ldap.groups.import.search.filter";
/*    */   
/*    */   public static final String LDAP_GROUP_MAPPING_GROUP_NAME = "ldap.group.mapping.group.name";
/*    */   
/*    */   public static final String LDAP_GROUP_MAPPING_DESCRIPTION = "ldap.group.mapping.description";
/*    */   
/*    */   public static final String LDAP_GROUP_MAPPING_USER = "ldap.group.mapping.user";
/*    */   
/*    */   public static final String LDAP_EXPORT_USERS_DN = "ldap.export.users.dn";
/*    */   
/*    */   public static final String LDAP_EXPORT_USER_DAFAULT_OBJ = "ldap.export.user.default.object.classes";
/*    */   
/*    */   public static final String LDAP_EXPORT_GROUPS_DN = "ldap.export.groups.dn";
/*    */   public static final String LDAP_EXPORT_GROUP_DEFAULT_OBJ = "ldap.export.group.default.object.classes";
/*    */   
/*    */   public static String getProperty(String key) {
/* 80 */     return properties.getProperty(key);
/*    */   }
/*    */   
/*    */   public static String getProperty(String key, String defaultValue) {
/* 84 */     return properties.getProperty(key, defaultValue);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\conf\PortalTestConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */