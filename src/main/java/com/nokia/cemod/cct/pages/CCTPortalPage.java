/*     */ package com.nokia.cemod.cct.pages;
/*     */ 
/*     */ import com.nokia.cemod.cct.cgf.CGFPageFilters;
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeLogin;
/*     */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeLogout;
/*     */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*     */ import com.nokia.cemod.cct.utils.PortalUtils;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import com.nokia.cemod.cct.utils.conf.PortalTestConfiguration;
/*     */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*     */ import java.io.File;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.junit.Assert;
/*     */ import org.openqa.selenium.Alert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.Keys;
/*     */ import org.openqa.selenium.NoAlertPresentException;
/*     */ import org.openqa.selenium.WebDriverException;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.support.ui.Select;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCTPortalPage
/*     */   extends CCTPortalBasePage
/*     */ {
/*  35 */   private static final Logger logger = Logger.getLogger(CCTPortalPage.class);
/*     */   
/*  37 */   private CGFPageFilters cgfPageFilters = null;
/*     */ 
/*     */   
/*     */   public CCTPortalPage(SharedDriver driver) {
/*  41 */     super(driver);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void launchUseCase(String useCaseName) throws CCTException {
/*  46 */     loginToPortal("User", useCaseName);
/*  47 */     navigateToUsecasePage(useCaseName);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getUseCaseJSON(String useCaseName) throws CCTException {
/*  52 */     return PortalUtils.getUseCaseJSON(this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getChildUseCaseJSON(String useCaseName) throws CCTException {
/*  57 */     return PortalUtils.getChildUseCaseJSON(useCaseName, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void navigateToLoginPage() throws CCTException {
/*     */     try {
/*  63 */       this.driver.get(String.format(TestConfiguration.getProperty("cct.env.portal.url"), new Object[0]));
/*  64 */     } catch (WebDriverException e) {
/*  65 */       throw new CCTException("Unable to launch page - " + 
/*  66 */           TestConfiguration.getProperty("cct.env.portal.url") + " Message ~ " + e
/*  67 */           .getMessage());
/*     */     } 
/*  69 */     WebUtils.waitForElementBy(this.driver, By.id("userlogin"), 30000L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceLogout() throws CCTException {}
/*     */ 
/*     */   
/*     */   public CGFPageFilters getCGFPageFilters() throws CCTException {
/*  78 */     this.cgfPageFilters = new CGFPageFilters(this.driver);
/*  79 */     return this.cgfPageFilters;
/*     */   }
/*     */   
/*     */   public void validateUsecase(String useCaseName) throws CCTException {
/*  83 */     setUseCase(useCaseName);
/*  84 */     List<WidgetConfiguration> renderedWidgets = getRenderedWidgets();
/*  85 */     assertWidgetsRendered(renderedWidgets);
/*  86 */     performClickOperationsAndValidateAllScenarios(renderedWidgets);
/*  87 */     assertListOfNonTestedWidgets();
/*     */   }
/*     */   
/*     */   public void verifyLoginPageContent() throws CCTException {
/*  91 */     Assert.assertEquals(WebUtils.findElementBy(this.driver, By.id("co-brand")).getAttribute("class"), "cemportallogo");
/*  92 */     Assert.assertEquals(WebUtils.findElementBy(this.driver, By.id("userlogin")).getAttribute("placeholder"), "Email address");
/*     */     
/*  94 */     Assert.assertEquals(WebUtils.findElementBy(this.driver, By.id("password")).getAttribute("placeholder"), "Password");
/*  95 */     Assert.assertEquals(WebUtils.findElementBy(this.driver, By.id("btnLogin")).getAttribute("disabled"), "true");
/*  96 */     String copyRight = WebUtils.findElementBy(this.driver, By.xpath(".//*[@id='column-4']/div[2]")).getText();
/*  97 */     Assert.assertTrue(copyRight
/*  98 */         .contains(Calendar.getInstance().get(1) + " Nokia. All rights reserved."));
/*     */   }
/*     */ 
/*     */   
/*     */   public void verifyForgotPasswordPage() throws CCTException {
/* 103 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Forgot password"));
/* 104 */     WebUtils.waitForElementBy(this.driver, By.name("emailAddress"));
/* 105 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Back"));
/* 106 */     WebUtils.waitForElementBy(this.driver, By.id("userlogin"));
/*     */   }
/*     */   
/*     */   public void loginToPortal(String user, String usecaseName) throws CCTException {
/*     */     try {
/* 111 */       this.driver.get(String.format(TestConfiguration.getProperty("cct.env.portal.url"), new Object[0]));
/* 112 */     } catch (WebDriverException e) {
/* 113 */       throw new CCTException("Unable to launch page - " + 
/* 114 */           TestConfiguration.getProperty("cct.env.portal.url") + " Message ~ " + e
/* 115 */           .getMessage());
/*     */     } 
/* 117 */     RenderingTimeLogin logRenderTime = new RenderingTimeLogin(user, usecaseName);
/* 118 */     logRenderTime.start();
/*     */     
/* 120 */     if (!this.driver.getPageSource().contains("You are signed in as")) {
/* 121 */       if (user.equals("User")) {
/* 122 */         Map<String, String> userDetails = PortalUtils.getUserDetails();
/* 123 */         login(userDetails.get("%s.portal.user"), userDetails
/* 124 */             .get("%s.portal.password"), (String)userDetails
/* 125 */             .get("%s.portal.user.firstName") + " " + (String)userDetails
/* 126 */             .get("%s.portal.user.lastName"));
/* 127 */       } else if (user.equals("Designer")) {
/* 128 */         login(String.format(TestConfiguration.getProperty("cct.env.portal.designer.user"), new Object[0]), 
/* 129 */             String.format("%s", new Object[] {
/* 130 */                 TestConfiguration.getProperty("cct.env.portal.designer.password")
/* 131 */               }), String.format(
/* 132 */               TestConfiguration.getProperty("cct.env.portal.designer.firstName"), new Object[0]) + " " + 
/* 133 */             String.format(
/* 134 */               TestConfiguration.getProperty("cct.env.portal.designer.lastName"), new Object[0]));
/* 135 */       } else if (user.equals("Admin")) {
/* 136 */         login(String.format(TestConfiguration.getProperty("cct.env.portal.admin.user"), new Object[0]), 
/* 137 */             String.format("%s", new Object[] {
/* 138 */                 TestConfiguration.getProperty("cct.env.portal.admin.password")
/*     */               }), "CEMPortal Admin");
/* 140 */       } else if (user.equals("CSPOwner")) {
/* 141 */         login(String.format(TestConfiguration.getProperty("cct.env.portal.cspowner.user"), new Object[0]), 
/* 142 */             String.format("%s", new Object[] {
/* 143 */                 TestConfiguration.getProperty("cct.env.portal.cspowner.password")
/*     */               }), "CSP Owner");
/*     */       } 
/*     */     } else {
/*     */       
/* 148 */       WebUtils.waitAndClickForElementBy(this.driver, By.linkText("here"));
/*     */     } 
/*     */     
/* 151 */     logRenderTime.end();
/*     */   }
/*     */   
/*     */   private void login(String user, String pwd, String assertUser) throws CCTException {
/*     */     try {
/* 156 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.name("login"), user);
/* 157 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.name("password"), pwd);
/* 158 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("btnLogin"));
/* 159 */   //    WebUtils.waitAndAssertForElementBy(this.driver, By.partialLinkText(assertUser), assertUser);
/* 160 */     } catch (CCTException e) {
/* 161 */       throw new CCTException("Login failed");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void configure() throws CCTException {
/* 166 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Users and Organizations"));
/* 167 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("CSP"));
/* 168 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Edit"));
/* 169 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'customFieldsLink')]"));
/* 170 */     WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'sai-subscription-endpoint')]"));
/* 171 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'analytics-webservice-endpoint')]"), 
/* 172 */         String.format(TestConfiguration.getProperty("cemod.analytics.webservice.endpoint"), new Object[0]));
/* 173 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'cei-configurations-root')]"), 
/* 174 */         String.format(TestConfiguration.getProperty("cemod.cei.configuration.root"), new Object[0]));
/* 175 */     WebUtils.waitAndSendKeysForElementBy(this.driver, 
/* 176 */         By.xpath(".//*[contains(@id, 'cei-configurations-webservice-endpoint')]"), 
/* 177 */         String.format(TestConfiguration.getProperty("cemod.cei.configuration.webservice.endpoint"), new Object[0]));
/* 178 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'map-geocoding-url')]"), 
/* 179 */         String.format(TestConfiguration.getProperty("cemod.sai.map.geocoding.url"), new Object[0]));
/* 180 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'map-template-url')]"), 
/* 181 */         String.format(TestConfiguration.getProperty("cemod.sai.map.template.url"), new Object[0]));
/* 182 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'offline-map-url')]"), 
/* 183 */         String.format(TestConfiguration.getProperty("cemod.offline.map.url"), new Object[0]));
/* 184 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'sai-password')]"), 
/* 185 */         String.format("%s", new Object[] { TestConfiguration.getProperty("cct.portal.sai.password") }));
/* 186 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'sai-session-endpoint')]"), 
/* 187 */         String.format(TestConfiguration.getProperty("cemod.sai.session.endpoint"), new Object[0]));
/* 188 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'sai-subscription-endpoint')]"), 
/* 189 */         String.format(TestConfiguration.getProperty("cemod.sai.subscription.endpoint"), new Object[0]));
/* 190 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'sai-tnp-alert-monitor-endpoint')]"), 
/* 191 */         String.format(TestConfiguration.getProperty("cemod.tnp.alert.monitor.endpoint"), new Object[0]));
/* 192 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'sai-user-name')]"), 
/* 193 */         String.format(TestConfiguration.getProperty("cct.portal.sai.username"), new Object[0]));
/* 194 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'SQM-Url')]"), 
/* 195 */         String.format(TestConfiguration.getProperty("cemod.sqm.url"), new Object[0]));
/* 196 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'unmapped-response-values')]"), 
/* 197 */         String.format(TestConfiguration.getProperty("cemod.unmapped.response.values"), new Object[0]));
/* 198 */     WebUtils.scrollElementUp(this.driver, WebUtils.findElementBy(this.driver, By.xpath(".//input[@value='Save']")));
/* 199 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 200 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   public void createUserForOrganization(String org) throws CCTException {
/* 204 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Users and Organizations"));
/* 205 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText(org));
/* 206 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Add User"));
/* 207 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'screenName')]"), 
/* 208 */         String.format(TestConfiguration.getProperty("cct.env.portal.designer.firstName"), new Object[0]));
/* 209 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'emailAddress')]"), 
/* 210 */         String.format(TestConfiguration.getProperty("cct.env.portal.designer.user"), new Object[0]));
/* 211 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'firstName')]"), 
/* 212 */         String.format(TestConfiguration.getProperty("cct.env.portal.designer.firstName"), new Object[0]));
/* 213 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'lastName')]"), 
/* 214 */         String.format(TestConfiguration.getProperty("cct.env.portal.designer.lastName"), new Object[0]));
/* 215 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 216 */     verifySuccessfulMessage();
/* 217 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'passwordLink')]"));
/* 218 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'password1')]"), String.format("%s", new Object[] {
/* 219 */             TestConfiguration.getProperty("cct.env.portal.designer.password") }));
/* 220 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'password2')]"), String.format("%s", new Object[] {
/* 221 */             TestConfiguration.getProperty("cct.env.portal.designer.password") }));
/* 222 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 223 */     verifySuccessfulMessage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void createNewUser(String org, String email, String password, String firstname, String lastname) throws CCTException {
/* 228 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Users and Organizations"));
/* 229 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText(org));
/* 230 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Add User"));
/* 231 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'screenName')]"), firstname);
/* 232 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'emailAddress')]"), email);
/* 233 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'firstName')]"), firstname);
/* 234 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'lastName')]"), lastname);
/* 235 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 236 */     verifySuccessfulMessage();
/* 237 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'passwordLink')]"));
/* 238 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'password1')]"), password);
/* 239 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'password2')]"), password);
/* 240 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 241 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   public void navigateToControlPanel(String user) throws CCTException {
/* 245 */     if (user.equals("Admin")) {
/* 246 */       WebUtils.waitAndClickForElementBy(this.driver, By.linkText("CEMPortal Admin"));
/* 247 */     } else if (user.equals("Designer")) {
/* 248 */       WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Designer User"));
/* 249 */     } else if (user.equals("User")) {
/* 250 */       WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CSP User"));
/* 251 */     } else if (user.equals("CSPOwner")) {
/* 252 */       WebUtils.waitAndClickForElementBy(this.driver, By.linkText("CSP Owner"));
/*     */     } 
/* 254 */     WebUtils.waitAndClickForElementBy(this.driver, By.className("control-panel"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateContentPackRole(String cp, String permission) throws CCTException {
/* 260 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("ContentPack Role"));
/* 261 */     WebUtils.waitForElementBy(this.driver, By.id("selectpermission"));
/* 262 */     if (cp.equals("Use Case Library") && permission.equals("true")) {
/* 263 */       (new Select(this.driver.findElement(By.name("ContentPacks")))).selectByValue("Insights Library");
/* 264 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("selectpermission"));
/* 265 */       WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'save')]"));
/* 266 */       verifySuccessfulMessage();
/* 267 */     } else if (cp.equals("Portal") && permission.equals("true")) {
/* 268 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("selectpermission"));
/* 269 */       WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'save')]"));
/* 270 */       verifySuccessfulMessage();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateContentPackManager() throws CCTException {
/* 275 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("ContentPack Manager"));
/* 276 */     (new Select(this.driver.findElement(By.xpath(".//*[contains(@id, 'organization')]")))).selectByValue("CSP");
/* 277 */     WebUtils.waitAndClickForElementBy(this.driver, 
/* 278 */         By.xpath(".//*[@id='checkboxTree']/descendant::label[text()='Insights Library']/parent::li/input"));
/* 279 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("update"));
/* 280 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   public void navigateToUsecaseManager() throws CCTException {
/* 284 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("UseCase Manager"));
/* 285 */     WebUtils.waitForElementBy(this.driver, By.id("import"));
/*     */   }
/*     */   
/*     */   public void importUsecases() throws CCTException {
/* 289 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("import"));
/* 290 */     WebUtils.waitAndAssertForElementBy(this.driver, By.id("responseAlert"), "UseCases are imported successfully!!");
/*     */   }
/*     */   
/*     */   public void navigateToCemboardDesigner() throws CCTException {
/* 294 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CEMBoard Designer"));
/* 295 */     WebUtils.waitForElementBy(this.driver, By.partialLinkText("Create New UseCase"));
/*     */   }
/*     */   
/*     */   public void selectOrganisation(String org) throws CCTException {
/* 299 */     WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'organization')]"));
/* 300 */     (new Select(WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@id, 'organization')]")))).selectByValue(org);
/*     */   }
/*     */   
/*     */   public void subscribeUsecases() throws CCTException {
/* 304 */     WebElement subscribeAll = WebUtils.waitAndFindElementBy(this.driver, By.id("subscribe-all-checkbox"));
/* 305 */     WebUtils.scrollElementUp(this.driver, subscribeAll);
/* 306 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("subscribe-all-checkbox"));
/*     */   }
/*     */   
/*     */   public void updateForOrganization() throws CCTException {
/* 310 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("updateButton"));
/* 311 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   public void verifyRolesInPortal() throws CCTException {
/* 315 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Roles"));
/* 316 */     WebUtils.waitForElementBy(this.driver, By.linkText("Administrator"));
/* 317 */     WebUtils.waitForElementBy(this.driver, By.linkText("CEM User"));
/* 318 */     WebUtils.waitForElementBy(this.driver, By.linkText("Customer Administrator"));
/* 319 */     WebUtils.waitForElementBy(this.driver, By.linkText("Guest"));
/* 320 */     WebUtils.waitForElementBy(this.driver, By.linkText("Organization Administrator"));
/* 321 */     WebUtils.waitForElementBy(this.driver, By.linkText("Organization Owner"));
/* 322 */     WebUtils.waitForElementBy(this.driver, By.linkText("Owner"));
/* 323 */     WebUtils.waitForElementBy(this.driver, By.linkText("Power User"));
/* 324 */     WebUtils.waitForElementBy(this.driver, By.linkText("Site Administrator"));
/* 325 */     WebUtils.waitForElementBy(this.driver, By.linkText("Site Member"));
/* 326 */     WebUtils.waitForElementBy(this.driver, By.linkText("Site Owner"));
/* 327 */     WebUtils.waitForElementBy(this.driver, By.linkText("UNMASK_SUBSCRIBER_INFORMATION"));
/* 328 */     WebUtils.waitForElementBy(this.driver, By.linkText("UseCase Designer"));
/* 329 */     WebUtils.waitForElementBy(this.driver, By.linkText("User"));
/*     */   }
/*     */   
/*     */   public void verifyCCTRelatedLinks(String user) throws CCTException {
/* 333 */     if (user.equals("Admin")) {
/* 334 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Users and Organizations"), "Users and Organizations");
/*     */       
/* 336 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("BulkUserManager"), "BulkUserManager");
/* 337 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("ContentPack Role"), "ContentPack Role");
/* 338 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Organization Settings"), "Organization Settings");
/* 339 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Scheduling Manager"), "Scheduling Manager");
/* 340 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("ContentPack Manager"), "ContentPack Manager");
/* 341 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("CEMBoard Designer"), "CEMBoard Designer");
/* 342 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("CEMBoard Configuration"), "CEMBoard Configuration");
/* 343 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Manage DB Export"), "Manage DB Export");
/* 344 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Export Usecase Help"), "Export Usecase Help");
/* 345 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Usecase Language Translator"), "Usecase Language Translator");
/*     */       
/* 347 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("CEMBoard Metadata"), "CEMBoard Metadata");
/* 348 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("UseCase Manager"), "UseCase Manager");
/* 349 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Export Users and Roles"), "Export Users and Roles");
/* 350 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Sites"), "Sites");
/* 351 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Site Templates"), "Site Templates");
/* 352 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Page Templates"), "Page Templates");
/* 353 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("User Groups"), "User Groups");
/* 354 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Roles"), "Roles");
/* 355 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Password Policies"), "Password Policies");
/* 356 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Portal Settings"), "Portal Settings");
/* 357 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Custom Fields"), "Custom Fields");
/* 358 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Monitoring"), "Monitoring");
/* 359 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Plugins Configuration"), "Plugins Configuration");
/* 360 */       Assert.assertTrue((this.driver.findElements(By.linkText("License Manager")).size() < 1));
/* 361 */       Assert.assertTrue((this.driver.findElements(By.linkText("CEMBoard Groups")).size() < 1));
/* 362 */     } else if (user.equals("User")) {
/*     */       
/* 364 */       List<WebElement> links = WebUtils.findElementBy(this.driver, By.className("category-portlets")).findElements(By.tagName("li"));
/* 365 */       Assert.assertEquals(links.size(), 1L);
/* 366 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Scheduling Manager"), "Scheduling Manager");
/* 367 */       Assert.assertTrue((this.driver.findElements(By.linkText("CEMBoard Configuration")).size() < 1));
/* 368 */       Assert.assertTrue((this.driver.findElements(By.linkText("Users and Organizations")).size() < 1));
/* 369 */     } else if (user.equals("Designer")) {
/* 370 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Scheduling Manager"), "Scheduling Manager");
/* 371 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("CEMBoard Designer"), "CEMBoard Designer");
/* 372 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("CEMBoard Configuration"), "CEMBoard Configuration");
/* 373 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Export Usecase Help"), "Export Usecase Help");
/* 374 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Usecase Language Translator"), "Usecase Language Translator");
/*     */       
/* 376 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("CEMBoard Metadata"), "CEMBoard Metadata");
/* 377 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("UseCase Manager"), "UseCase Manager");
/* 378 */       Assert.assertTrue((this.driver.findElements(By.linkText("License Manager")).size() < 1));
/* 379 */       Assert.assertTrue((this.driver.findElements(By.linkText("CEMBoard Groups")).size() < 1));
/* 380 */     } else if (user.equals("CSPOwner")) {
/* 381 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Users and Organizations"), "Users and Organizations");
/*     */       
/* 383 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("BulkUserManager"), "BulkUserManager");
/* 384 */       WebUtils.waitAndAssertForElementBy(this.driver, By.linkText("Scheduling Manager"), "Scheduling Manager");
/* 385 */       Assert.assertTrue((this.driver.findElements(By.linkText("License Manager")).size() < 1));
/* 386 */       Assert.assertTrue((this.driver.findElements(By.linkText("CEMBoard Groups")).size() < 1));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void importMetadataAsDesigner() throws CCTException {
/* 391 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CEMBoard Metadata"));
/* 392 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'cemboardportlet_url')]"), 
/* 393 */         String.format(TestConfiguration.getProperty("cct.portal.metadata.boxi.url"), new Object[0]));
/* 394 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'username')]"), 
/* 395 */         String.format(TestConfiguration.getProperty("cct.portal.sai.username"), new Object[0]));
/* 396 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'password')]"), 
/* 397 */         String.format("%s", new Object[] { TestConfiguration.getProperty("cct.portal.sai.password") }));
/* 398 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Import']"));
/* 399 */     WebUtils.waitAndAssertForElementBy(this.driver, By.id("responseAlert"), "Import may take little longer, Wait for the successful response !!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCGFFilterSelection() throws CCTException {
/* 405 */     resetCGFFilter();
/* 406 */     applyCGFFilter();
/* 407 */     WebUtils.waitAndClickForElementBy(this.driver, 
/* 408 */         By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Technologies')]"));
/* 409 */     WebUtils.waitAndClickForElementBy(this.driver, 
/* 410 */         By.xpath(".//*[@id='cgf_menu_mn_active']//label[contains(text(),'3G')]"));
/* 411 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("CommonGlobalFilter_Apply"));
/* 412 */     WebUtils.waitAndAssertForElementBy(this.driver, 
/* 413 */         By.xpath(".//div[@class='selected-filters-items']//span[contains(text(),'Last week')]"), "Last week");
/*     */   }
/*     */   
/*     */   public void resetCGFFilter() throws CCTException {
/* 417 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("CommonGlobalFilter_ResetAll"));
/*     */   }
/*     */   
/*     */   public void applyCGFFilter() throws CCTException {
/* 421 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("CommonGlobalFilter_Apply"));
/*     */   }
/*     */   
/*     */   public void applyGivenCgfFilter(String filterKey, String filterValue) throws CCTException {
/* 425 */     switch (filterKey) {
/*     */       case "Technologies":
/* 427 */         applyTechnologyFilter(filterValue);
/*     */         return;
/*     */       case "APNs":
/* 430 */         applyAPNs(filterValue);
/*     */         return;
/*     */       case "Areas":
/*     */       case "Cities":
/*     */       case "Regions":
/* 435 */         applyLocationFilter(filterKey, filterValue);
/*     */         return;
/*     */       case "Corporation subgroups":
/*     */       case "Subscription types":
/*     */       case "Corporations":
/*     */       case "Segments":
/* 441 */         applySubscribers(filterKey, filterValue);
/*     */         return;
/*     */       case "Manufacturer name":
/*     */       case "Device types":
/* 445 */         applyDevices(filterKey, filterValue);
/*     */         return;
/*     */       case "Time":
/*     */       case "Recent":
/*     */       case "Hour":
/*     */       case "Day":
/*     */       case "Week":
/*     */       case "Month":
/*     */       case "Current month":
/*     */       case "Last month":
/*     */       case "Last 3 months":
/*     */       case "Date range":
/* 457 */         applyTimeFilter(filterKey, filterValue);
/*     */         return;
/*     */       case "Network Topologies":
/*     */       case "Aggregation Level 1":
/*     */       case "Aggregation Level 2":
/* 462 */         applyNetworkTopologies(filterKey, filterValue);
/*     */         return;
/*     */       case "Roaming category":
/*     */       case "Home":
/*     */       case "Inbound roamers":
/*     */       case "Outbound roamers":
/*     */       case "Partner operators":
/* 469 */         applyRoamingCategory(filterKey, filterValue);
/*     */         return;
/*     */     } 
/* 472 */     Assert.fail("CGF filter key not found: " + filterKey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logout() throws CCTException {
/*     */     RenderingTimeLogout logRenderTime;
/* 480 */     if (getUseCase() != null) {
/* 481 */       logRenderTime = new RenderingTimeLogout(getUseCase().getUseCaseName());
/*     */     } else {
/* 483 */       logRenderTime = new RenderingTimeLogout("");
/*     */     } 
/* 485 */     logRenderTime.start();
/* 486 */     WebElement element = WebUtils.findElementBy(this.driver, By.xpath(".//*[@id='sign-out']"));
/* 487 */     WebUtils.scrollElementUp(this.driver, element);
/* 488 */     element.click();
/* 489 */     WebUtils.waitForElementBy(this.driver, By.id("userlogin"));
/* 490 */     logRenderTime.end();
/*     */   }
/*     */   
/*     */   public void navigateToCEMBoardDesigner() throws CCTException {
/* 494 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CEMBoard Designer"));
/* 495 */     WebUtils.waitForElementBy(this.driver, By.partialLinkText("Create New UseCase"));
/*     */   }
/*     */   
/*     */   public void navigateToNewUsecase() throws CCTException {
/* 499 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Create New UseCase"));
/* 500 */     WebUtils.waitForElementBy(this.driver, By.id("board-tools"));
/*     */   }
/*     */   
/*     */   public void createNewUsecase(String usecaseName) throws CCTException {
/* 504 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseName')]"), usecaseName);
/* 505 */     (new Select(this.driver.findElement(By.xpath(".//*[contains(@id, 'contentPack')]")))).selectByValue("5");
/* 506 */     (new Select(this.driver.findElement(By.xpath(".//*[contains(@id, 'functional')]")))).selectByValue("21");
/* 507 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("add-pane"));
/* 508 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[@id='board-content']/div/ul/li/header/ul/li[1]"));
/* 509 */     WebUtils.waitAndAssertForElementBy(this.driver, By.xpath(".//*[@id='modal_overlay']/div/header/h2"), "WIDGET");
/* 510 */     (new Select(this.driver.findElement(By.xpath(".//*[@id='setting-value-container-plugin-types']/div/select"))))
/* 511 */       .selectByValue("cemboard-table");
/* 512 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("dialog-ok"));
/* 513 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("useCaseSave"));
/* 514 */     WebUtils.waitAndAssertForElementBy(this.driver, By.id("responseAlert"), "New UseCase Created Successfully !");
/* 515 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("back"));
/* 516 */     WebUtils.waitForElementBy(this.driver, By.id("updateButton"));
/*     */   }
/*     */   
/*     */   public void publishUsecaseForOrg() throws CCTException {
/* 520 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("subscribe-all-checkbox"));
/*     */   }
/*     */   
/*     */   public void updateOrganization() throws CCTException {
/* 524 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("updateButton"));
/* 525 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   private void verifySuccessfulMessage() throws CCTException {
/* 529 */     WebUtils.waitAndAssertForElementBy(this.driver, By.className("portlet-msg-success"), "Your request completed successfully.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void subscribeUsecaseForOrg() throws CCTException {
/* 534 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("subscribedCheck"));
/*     */   }
/*     */   
/*     */   public void verifySubscribedUsecase(String usecaseName) throws CCTException {
/* 538 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Use Case Library"));
/* 539 */     WebUtils.waitAndAssertForElementBy(this.driver, By.partialLinkText(usecaseName), usecaseName);
/*     */   }
/*     */   
/*     */   public void editUsecase(String usecase) throws CCTException {
/* 543 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Edit"));
/* 544 */     WebUtils.waitForElementBy(this.driver, By.id("board-tools"));
/* 545 */     Assert.assertEquals(
/* 546 */         WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseName')]")).getAttribute("value"), usecase);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteUsecase(String usecaseName) throws CCTException {
/* 551 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Delete"));
/*     */     try {
/* 553 */       Thread.sleep(3000L);
/* 554 */     } catch (InterruptedException e) {
/* 555 */       throw new CCTException("Alert window to confirm Delete usecase not displayed");
/*     */     } 
/*     */     try {
/* 558 */       Alert alt = this.driver.switchTo().alert();
/* 559 */       alt.accept();
/* 560 */     } catch (NoAlertPresentException noe) {
/* 561 */       Assert.fail("No Alert message to end user before deleting the usecase.");
/*     */     } 
/* 563 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   public void exportUsecases() throws CCTException {
/* 567 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("export"));
/* 568 */     String exportAlert = WebUtils.waitAndFindElementBy(this.driver, By.id("responseAlert")).getText();
/* 569 */     Assert.assertTrue(exportAlert.contains("UseCases are Exported under"));
/*     */   }
/*     */   
/*     */   public void navigateToCEMBoardGroups() throws CCTException {
/* 573 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CEMBoard Groups"));
/* 574 */     WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseGroup')]"));
/*     */   }
/*     */   
/*     */   public void addUsecaseSubGroup(String subGroupName, String group) throws CCTException {
/* 578 */     (new Select(WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseGroup')]"))))
/* 579 */       .selectByValue(group);
/* 580 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'subgroup')]"), subGroupName);
/* 581 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Create']"));
/* 582 */     WebUtils.waitAndAssertForElementBy(this.driver, By.className("portlet-msg-info"), "UseCase SubGroup created Successfully !!");
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteUsecaseSubGroup(String subGroupName, String group) throws CCTException, InterruptedException {
/* 587 */     (new Select(WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseGroup')]"))))
/* 588 */       .selectByValue(group);
/* 589 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("_cemboardgroups_WAR_cemboardportlet_useCaseSubGroup"));
/* 590 */     WebUtils.waitForWidgetsToRender();
/* 591 */     (new Select(WebUtils.findElementBy(this.driver, By.id("_cemboardgroups_WAR_cemboardportlet_useCaseSubGroup"))))
/* 592 */       .selectByVisibleText(subGroupName);
/* 593 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Delete']"));
/* 594 */     WebUtils.waitAndAssertForElementBy(this.driver, By.className("portlet-msg-info"), "UseCase SubGroup Deleted Successfully !!");
/*     */   }
/*     */ 
/*     */   
/*     */   public int findNumberOfUsecasePages() throws CCTException {
/* 599 */     WebElement pageDropDown = WebUtils.findElementBy(this.driver, 
/* 600 */         By.xpath(".//*[contains(@id, 'cemDashboardsSearchContainerPageIteratorBottom_page')]"));
/* 601 */     return pageDropDown.findElements(By.tagName("option")).size();
/*     */   }
/*     */   
/*     */   public void selectItemsPerPage(String itemsPerPage) throws CCTException {
/* 605 */     WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='grid']/div/span[1]/span"));
/* 606 */     element.click();
/* 607 */     WebUtils.waitForWidgetsToRender();
/* 608 */     element.sendKeys(new CharSequence[] { (CharSequence)Keys.DOWN });
/* 609 */     WebUtils.waitForWidgetsToRender();
/* 610 */     element.sendKeys(new CharSequence[] { (CharSequence)Keys.RETURN });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void navigateTo(String section) throws CCTException {
/* 620 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText(section));
/* 621 */     if (section.equals("UseCase Manager")) {
/* 622 */       WebUtils.waitForElementBy(this.driver, By.id("import"));
/* 623 */     } else if (section.equals("BulkUserManager")) {
/* 624 */       WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'bulkuserform')]"));
/* 625 */     } else if (section.equals("CEMBoard Designer")) {
/* 626 */       WebUtils.waitForElementBy(this.driver, By.partialLinkText("Create New UseCase"));
/* 627 */     } else if (section.equals("Use Case Library")) {
/* 628 */       WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'portal-contentpack-widgets')]"));
/* 629 */     } else if (section.equals("ContentPack Role")) {
/* 630 */       WebUtils.waitForElementBy(this.driver, By.id("selectpermission"));
/* 631 */     } else if (section.equals("CEMBoard Designer")) {
/* 632 */       WebUtils.waitForElementBy(this.driver, By.partialLinkText("Create New UseCase"));
/* 633 */     } else if (section.equals("Roles")) {
/* 634 */       WebUtils.waitForElementBy(this.driver, By.partialLinkText("UseCase Designer"));
/* 635 */     } else if (section.equals("Create New UseCase")) {
/* 636 */       WebUtils.waitForElementBy(this.driver, By.id("board-tools"));
/* 637 */     } else if (section.equals("CEMBoard Groups")) {
/* 638 */       WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseGroup')]"));
/* 639 */     } else if (section.equals("Users and Organizations")) {
/* 640 */       WebUtils.waitForElementBy(this.driver, By.xpath(".//*[contains(@id, 'usersAdminOrganizationsPanel')]"));
/* 641 */     } else if (section.equals("About")) {
/* 642 */       WebUtils.waitForElementBy(this.driver, By.id("js-about-cem"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void manageBulkUsers(String operation) throws CCTException {
/* 647 */     if (operation.equals("Add")) {
/* 648 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("bulkUserAction1"));
/* 649 */       WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.name("fileName"));
/* 650 */       File folder = new File(TestConfiguration.getProperty("cct.portal.bulk.user.add.filepath"));
/* 651 */       element.sendKeys(new CharSequence[] { folder.getAbsolutePath() });
/* 652 */     } else if (operation.equals("Update")) {
/* 653 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("bulkUserAction2"));
/* 654 */       WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.name("fileName"));
/*     */       
/* 656 */       File folder = new File(TestConfiguration.getProperty("cct.portal.bulk.user.update.filepath"));
/* 657 */       element.sendKeys(new CharSequence[] { folder.getAbsolutePath() });
/* 658 */     } else if (operation.equals("Delete")) {
/* 659 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("bulkUserAction3"));
/* 660 */       WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.name("fileName"));
/*     */       
/* 662 */       File folder = new File(TestConfiguration.getProperty("cct.portal.bulk.user.delete.filepath"));
/* 663 */       element.sendKeys(new CharSequence[] { folder.getAbsolutePath() });
/* 664 */     } else if (operation.equals("AddWithRoles")) {
/* 665 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("bulkUserAction4"));
/* 666 */       WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.name("fileName"));
/*     */       
/* 668 */       File folder = new File(TestConfiguration.getProperty("cct.portal.bulk.user.addwithroles.filepath"));
/* 669 */       element.sendKeys(new CharSequence[] { folder.getAbsolutePath() });
/*     */     } 
/* 671 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("submitButton"));
/* 672 */     WebUtils.waitForWidgetsToRender();
/* 673 */     String parentWindowHandler = this.driver.getWindowHandle();
/* 674 */     Alert alert = this.driver.switchTo().alert();
/* 675 */     WebUtils.waitForWidgetsToRender();
/* 676 */     alert.accept();
/* 677 */     this.driver.switchTo().window(parentWindowHandler);
/* 678 */     WebUtils.waitAndAssertForElementBy(this.driver, By.className("portlet-msg-success"), "Successfully performed Bulk User Operation.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void assignRole(String role) throws CCTException {
/* 683 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'rolesLink')]"));
/* 684 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'fmzi')]"));
/*     */     try {
/* 686 */       Thread.sleep(5000L);
/* 687 */     } catch (InterruptedException e) {
/* 688 */       logger.error("InterruptedException", e);
/*     */     } 
/* 690 */     String parentWindowHandler = this.driver.getWindowHandle();
/* 691 */     String subWindowHandler = null;
/* 692 */     Set<String> handles = this.driver.getWindowHandles();
/* 693 */     Iterator<String> iterator = handles.iterator();
/* 694 */     while (iterator.hasNext()) {
/* 695 */       subWindowHandler = iterator.next();
/*     */     }
/* 697 */     this.driver.switchTo().window(subWindowHandler);
/* 698 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText(role));
/* 699 */     this.driver.switchTo().window(parentWindowHandler);
/*     */     
/* 701 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 702 */     verifySuccessfulMessage();
/* 703 */     Assert.assertTrue(this.driver.getPageSource().contains(role));
/*     */   }
/*     */   
/*     */   public void addOrganisation(String org) throws CCTException {
/* 707 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'name')]"), org);
/* 708 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 709 */     verifySuccessfulMessage();
/*     */   }
/*     */   
/*     */   public void navigateToUsecaseFromFavourites(String useCase) throws CCTException {
/* 713 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("Favourites2"));
/* 714 */     WebElement favourites = WebUtils.waitAndFindElementBy(this.driver, 
/* 715 */         By.xpath(".//a[contains(text(),'" + useCase + "')]/preceding-sibling::div"));
/* 716 */     Assert.assertEquals("img-favourite", favourites.getAttribute("class"));
/* 717 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText(useCase));
/*     */   }
/*     */   
/*     */   public void verifyAbout() throws CCTException {
/* 721 */     navigateTo("About");
/* 722 */     Assert.assertTrue(
/* 723 */         WebUtils.findElementBy(this.driver, 
/* 724 */           By.xpath(".//h1[contains(text(),'Customer Experience Management on Demand')]")).getText(), true);
/*     */     
/* 726 */     Assert.assertEquals("Version 18", 
/* 727 */         WebUtils.findElementBy(this.driver, By.xpath(".//h2[contains(text(),'Version 18')]")).getText());
/* 728 */     WebUtils.waitAndClickForElementBy(this.driver, By.id("aboutClose"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void verifyTableCellDataForMasking(String tableId, String row, String column) throws CCTException {
/* 735 */     String cellData = WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='" + tableId + "']/div[2]/table/tbody/tr[" + row + "]/td[" + column + "]")).getText();
/* 736 */     if (cellData.contains("xxxx")) {
/* 737 */       Assert.assertTrue(true);
/*     */     } else {
/* 739 */       Assert.assertTrue(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLDAPEntries() throws CCTException {
/* 745 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.auth.enabled')]"))
/* 746 */       .getAttribute("value").equals("false")) {
/* 747 */       WebUtils.findElementBy(this.driver, By.xpath(".//input[contains(@id, 'ldap.auth.enabledCheckbox')]")).click();
/*     */     }
/*     */     
/* 750 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldapImportEnabled')]"))
/* 751 */       .getAttribute("value").equals("false")) {
/* 752 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldapImportEnabledCheckbox')]")).click();
/*     */     }
/*     */     
/* 755 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.import.on.startup')]"))
/* 756 */       .getAttribute("value").equals("false")) {
/* 757 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.import.on.startupCheckbox')]"))
/* 758 */         .click();
/*     */     }
/*     */     
/* 761 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.password.policy.enabled')]"))
/* 762 */       .getAttribute("value").equals("false")) {
/* 763 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 764 */           By.xpath(".//*[contains(@id, 'ldap.password.policy.enabledCheckbox')]")).click();
/*     */     }
/*     */     
/* 767 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'addButton')]"));
/*     */     
/* 769 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.server.name.0')]"), 
/* 770 */         PortalTestConfiguration.getProperty("ldap.server.name"));
/*     */     
/* 772 */     WebUtils.waitAndClickForElementBy(this.driver, 
/* 773 */         By.xpath(".//label[contains(text(), 'Microsoft Active Directory Server')]"));
/*     */ 
/*     */     
/* 776 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.base.provider.url.0')]"), 
/* 777 */         PortalTestConfiguration.getProperty("ldap.base.provider.url"));
/*     */     
/* 779 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.base.dn.0')]"), 
/* 780 */         PortalTestConfiguration.getProperty("ldap.base.dn"));
/*     */     
/* 782 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.security.principal.0')]"), 
/* 783 */         PortalTestConfiguration.getProperty("ldap.principal"));
/*     */     
/* 785 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.security.credentials.0')]"), 
/* 786 */         PortalTestConfiguration.getProperty("ldap.credentials"));
/*     */     
/* 788 */     WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@value, 'Test LDAP Connection')]")).click();
/* 789 */     WebElement dialog = WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//div[contains(@role, 'dialog')]"));
/* 790 */     dialog.findElement(
/* 791 */         By.xpath(".//div[contains(text(), 'Liferay has successfully connected to the LDAP server.')]"));
/* 792 */     dialog.findElement(By.xpath(".//button")).click();
/*     */ 
/*     */     
/* 795 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.auth.search.filter.0')]"), 
/* 796 */         PortalTestConfiguration.getProperty("ldap.authentication.search.filter"));
/*     */     
/* 798 */     WebUtils.waitAndSendKeysForElementBy(this.driver, 
/* 799 */         By.xpath(".//*[contains(@id, 'ldap.import.user.search.filter.0')]"), 
/* 800 */         PortalTestConfiguration.getProperty("ldap.import.search.filter"));
/*     */ 
/*     */     
/* 803 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingUuid')]"), 
/* 804 */         PortalTestConfiguration.getProperty("ldap.user.mapping.uuid"));
/*     */     
/* 806 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingScreenName')]"), 
/* 807 */         PortalTestConfiguration.getProperty("ldap.user.mapping.screen.name"));
/*     */     
/* 809 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingEmailAddress')]"), 
/* 810 */         PortalTestConfiguration.getProperty("ldap.user.mapping.email.address"));
/*     */     
/* 812 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingPassword')]"), 
/* 813 */         String.format("%s", new Object[] { PortalTestConfiguration.getProperty("ldap.user.mapping.password") }));
/*     */     
/* 815 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingFirstName')]"), 
/* 816 */         PortalTestConfiguration.getProperty("ldap.user.mapping.first.name"));
/*     */     
/* 818 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingMiddleName')]"), 
/* 819 */         PortalTestConfiguration.getProperty("ldap.user.mapping.middle.name"));
/*     */     
/* 821 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingLastName')]"), 
/* 822 */         PortalTestConfiguration.getProperty("ldap.user.mapping.last.name"));
/*     */     
/* 824 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingFullName')]"), 
/* 825 */         PortalTestConfiguration.getProperty("ldap.user.mapping.full.name"));
/*     */     
/* 827 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingJobTitle')]"), 
/* 828 */         PortalTestConfiguration.getProperty("ldap.user.mapping.job.title"));
/*     */     
/* 830 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingStatus')]"), 
/* 831 */         PortalTestConfiguration.getProperty("ldap.user.mapping.status"));
/*     */     
/* 833 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingGroup')]"), 
/* 834 */         PortalTestConfiguration.getProperty("ldap.user.mapping.group"));
/*     */     
/* 836 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'userMappingPortrait')]"), 
/* 837 */         PortalTestConfiguration.getProperty("ldap.user.mapping.potrait"));
/*     */     
/* 839 */     WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@value, 'Test LDAP Users')]")).click();
/* 840 */     verifyDialog();
/*     */ 
/*     */     
/* 843 */     WebUtils.waitAndSendKeysForElementBy(this.driver, 
/* 844 */         By.xpath(".//*[contains(@id, 'ldap.import.group.search.filter.0')]"), 
/* 845 */         PortalTestConfiguration.getProperty("ldap.groups.import.search.filter"));
/*     */ 
/*     */     
/* 848 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'groupMappingGroupName')]"), 
/* 849 */         PortalTestConfiguration.getProperty("ldap.group.mapping.group.name"));
/*     */     
/* 851 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'groupMappingDescription')]"), 
/* 852 */         PortalTestConfiguration.getProperty("ldap.group.mapping.description"));
/*     */     
/* 854 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'groupMappingUser')]"), 
/* 855 */         PortalTestConfiguration.getProperty("ldap.group.mapping.user"));
/*     */     
/* 857 */     WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@value, 'Test LDAP Groups')]")).click();
/* 858 */     verifyDialog();
/*     */ 
/*     */     
/* 861 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.users.dn.0')]"), 
/* 862 */         PortalTestConfiguration.getProperty("ldap.export.users.dn"));
/*     */     
/* 864 */     WebUtils.waitAndSendKeysForElementBy(this.driver, 
/* 865 */         By.xpath(".//*[contains(@id, 'ldap.user.default.object.classes.0')]"), 
/* 866 */         PortalTestConfiguration.getProperty("ldap.export.user.default.object.classes"));
/*     */     
/* 868 */     WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'ldap.groups.dn.0')]"), 
/* 869 */         PortalTestConfiguration.getProperty("ldap.export.groups.dn"));
/*     */     
/* 871 */     WebUtils.waitAndSendKeysForElementBy(this.driver, 
/* 872 */         By.xpath(".//*[contains(@id, 'ldap.group.default.object.classes.0')]"), 
/* 873 */         PortalTestConfiguration.getProperty("ldap.export.group.default.object.classes"));
/*     */     
/* 875 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[contains(@id, 'saveButton')]"));
/*     */     
/* 877 */     WebElement backTab = WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'TabsBack')]"));
/* 878 */     WebUtils.scrollElementUp(this.driver, backTab);
/* 879 */     backTab.click();
/* 880 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//*[contains(@id, 'TabsBack')]"));
/*     */     
/* 882 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("General"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 889 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'company.security.auto.login')]"))
/* 890 */       .getAttribute("value").equals("true")) {
/* 891 */       WebUtils.findElementBy(this.driver, By.xpath(".//input[contains(@id, 'company.security.auto.loginCheckbox')]"))
/* 892 */         .click();
/*     */     }
/*     */     
/* 895 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'company.security.send.password')]"))
/* 896 */       .getAttribute("value").equals("true")) {
/* 897 */       WebUtils.findElementBy(this.driver, 
/* 898 */           By.xpath(".//input[contains(@id, 'company.security.send.passwordCheckbox')]")).click();
/*     */     }
/*     */ 
/*     */     
/* 902 */     if (WebUtils.waitAndFindElementBy(this.driver, 
/* 903 */         By.xpath(".//*[contains(@id, 'company.security.send.password.reset.link')]"))
/* 904 */       .getAttribute("value").equals("true")) {
/* 905 */       WebUtils.findElementBy(this.driver, 
/* 906 */           By.xpath(".//input[contains(@id, 'company.security.send.password.reset.linkCheckbox')]")).click();
/*     */     }
/*     */     
/* 909 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'company.security.strangers')]"))
/* 910 */       .getAttribute("value").equals("true")) {
/* 911 */       WebUtils.findElementBy(this.driver, By.xpath(".//input[contains(@id, 'company.security.strangersCheckbox')]"))
/* 912 */         .click();
/*     */     }
/*     */     
/* 915 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'company.security.strangers.with.mx')]"))
/* 916 */       .getAttribute("value").equals("true")) {
/* 917 */       WebUtils.findElementBy(this.driver, 
/* 918 */           By.xpath(".//input[contains(@id, 'company.security.strangers.with.mxCheckbox')]")).click();
/*     */     }
/*     */     
/* 921 */     if (WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[contains(@id, 'company.security.strangers.verify')]"))
/* 922 */       .getAttribute("value").equals("true")) {
/* 923 */       WebUtils.findElementBy(this.driver, 
/* 924 */           By.xpath(".//input[contains(@id, 'company.security.strangers.verifyCheckbox')]")).click();
/*     */     }
/*     */     
/* 927 */     WebUtils.findElementBy(this.driver, By.xpath(".//input[contains(@value, 'Save')]")).click();
/*     */   }
/*     */   
/*     */   private void verifyDialog() throws CCTException {
/* 931 */     WebElement dialog = WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//div[contains(@role, 'dialog')]"));
/* 932 */     dialog.findElement(By.xpath(".//button")).click();
/*     */   }
/*     */   
/*     */   public void navigateToCemboardConfiguration() throws CCTException {
/* 936 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CEMBoard Configuration"));
/* 937 */     WebUtils.waitForElementBy(this.driver, By.id("widgetHeader"));
/*     */   }
/*     */   
/*     */   public void navigateToManageDBExport() throws CCTException {
/* 941 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Manage DB Export"));
/* 942 */     WebUtils.waitForElementBy(this.driver, By.id("cemboard-pulldown-689901-label"));
/*     */   }
/*     */   
/*     */   public void selectUserAuthentication(String userCredential) throws CCTException {
/* 946 */     (new Select(this.driver.findElement(By.xpath(".//*[contains(@id, 'company.security.auth.type')]"))))
/* 947 */       .selectByValue(userCredential);
/* 948 */     WebUtils.scrollElementDown(this.driver, WebUtils.findElementBy(this.driver, By.xpath(".//input[@value='Save']")));
/* 949 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//input[@value='Save']"));
/* 950 */     verifySuccessfulMessage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void verifyPlaceholderInUserAuthentication(String placeholder) throws CCTException {
/* 955 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Forgot password"));
/* 956 */     if (placeholder.equals("Email address")) {
/* 957 */       Assert.assertEquals(WebUtils.findElementBy(this.driver, By.name("emailAddress")).getAttribute("placeholder"), "Email address");
/*     */     }
/* 959 */     else if (placeholder.equals("Screen Name")) {
/* 960 */       Assert.assertEquals(WebUtils.findElementBy(this.driver, By.name("screenName")).getAttribute("placeholder"), "Screen Name");
/*     */     } 
/*     */     
/* 963 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Back"));
/* 964 */     WebUtils.waitForElementBy(this.driver, By.id("userlogin"));
/*     */   }
/*     */   
/*     */   public void loginToPortalWithScreenName() throws CCTException {
/*     */     try {
/* 969 */       this.driver.get(String.format(TestConfiguration.getProperty("cct.env.portal.url"), new Object[0]));
/* 970 */     } catch (WebDriverException e) {
/* 971 */       throw new CCTException("Unable to launch page - " + 
/* 972 */           TestConfiguration.getProperty("cct.env.portal.url") + " Message ~ " + e
/* 973 */           .getMessage());
/*     */     } 
/* 975 */     String userName = String.format(TestConfiguration.getProperty("cct.env.portal.admin.user"), new Object[0]);
/* 976 */     login(userName.substring(0, userName.indexOf("@")), 
/* 977 */         String.format("%s", new Object[] { TestConfiguration.getProperty("cct.env.portal.admin.password") }), "CEMPortal Admin");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsubscribeUsecase(String usecase) throws CCTException {
/* 983 */     List<WebElement> usecaseList = WebUtils.findElementBy(this.driver, By.xpath(".//*[@id='grid']/table/tbody")).findElements(By.tagName("tr"));
/* 984 */     for (int i = 0; i < usecaseList.size(); i++) {
/* 985 */       if (((WebElement)usecaseList.get(i)).findElement(By.tagName("td")).getText().equals(usecase)) {
/* 986 */         ((WebElement)usecaseList.get(i)).findElement(By.xpath(".//input")).click();
/*     */       }
/*     */     } 
/* 989 */     updateForOrganization();
/*     */   }
/*     */   
/*     */   public void verifyUsecaseNotPresentInDashboard(String usecase) throws CCTException {
/* 993 */     PortalUtils.searchUsecase(usecase, this.driver);
/* 994 */     Assert.assertTrue((this.driver.findElements(By.linkText(usecase)).size() < 1));
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\pages\CCTPortalPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */