/*     */ package com.nokia.cemod.cct.pages;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeLogin;
/*     */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeLogout;
/*     */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.PortalUtils;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*     */ import java.util.Calendar;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.junit.Assert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.WebDriverException;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.support.ui.Select;
/*     */ 
/*     */ 
/*     */ public class CEMNovaPage
/*     */   extends CCTPortalBasePage
/*     */ {
/*  26 */   private static final Logger logger = Logger.getLogger(CEMNovaPage.class);
/*     */   
/*     */   public CEMNovaPage(SharedDriver driver) {
/*  29 */     super(driver);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getUseCaseJSON(String useCaseName) throws CCTException {
/*  34 */     return PortalUtils.getUseCaseJSON(this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getChildUseCaseJSON(String useCaseName) throws CCTException {
/*  39 */     return PortalUtils.getChildUseCaseJSON(useCaseName, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void launchUseCase(String useCaseName) throws CCTException {
/*  44 */     loginToPortal("User", useCaseName);
/*  45 */     navigateToUsecasePage(useCaseName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void navigateToLoginPage() throws CCTException {
/*     */     try {
/*  51 */       this.driver.get(String.format(TestConfiguration.getProperty("cct.env.portal.url"), new Object[0]));
/*  52 */     } catch (WebDriverException e) {
/*  53 */       throw new CCTException("Unable to launch page - " + 
/*  54 */           TestConfiguration.getProperty("cct.env.portal.url") + " Message ~ " + e
/*  55 */           .getMessage());
/*     */     } 
/*  57 */     WebUtils.waitForElementBy(this.driver, By.name("username"), 30000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void forceLogout() throws CCTException {
/*     */     try {
/*  63 */       String loggedInUser = WebUtils.findElementBy(this.driver, By.className("button__text")).getText();
/*  64 */       if (loggedInUser != null) {
/*  65 */         logout();
/*     */       }
/*  67 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void verifyForgotPasswordPage() throws CCTException {
/*  73 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Forgot password?"));
/*     */     
/*  75 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Back to Login"));
/*  76 */     WebUtils.waitForElementBy(this.driver, By.name("username"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void verifyLoginPageContent() throws CCTException {
/*  82 */     String logo = WebUtils.findElementBy(this.driver, By.xpath(".//*[@id='kc-logo-wrapper']")).getCssValue("background-image");
/*  83 */     Assert.assertTrue(logo.contains("nokia_logo"));
/*  84 */     Assert.assertEquals(WebUtils.findElementBy(this.driver, By.name("username")).getAttribute("placeholder"), "Username");
/*     */     
/*  86 */     Assert.assertEquals(WebUtils.findElementBy(this.driver, By.name("password")).getAttribute("placeholder"), "Password");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     String copyRight = WebUtils.executeJS(this.driver, "return window.getComputedStyle(document.getElementById('kc-header'),':after').getPropertyValue('content');").toString();
/*  94 */     Assert.assertTrue(copyRight.contains(Calendar.getInstance().get(1) + " Nokia"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void verifyPlaceholderInUserAuthentication(String placeholder) throws CCTException {
/*  99 */     WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Forgot password?"));
/* 100 */     if (placeholder.equals("Username")) {
/* 101 */       Assert.assertEquals(WebUtils.findElementBy(this.driver, By.name("username")).getAttribute("placeholder"), "Username");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("Back to Login"));
/* 110 */     WebUtils.waitForElementBy(this.driver, By.name("username"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void logout() throws CCTException {
/*     */     RenderingTimeLogout logRenderTime;
/* 116 */     if (getUseCase() != null) {
/* 117 */       logRenderTime = new RenderingTimeLogout(getUseCase().getUseCaseName());
/*     */     } else {
/* 119 */       logRenderTime = new RenderingTimeLogout("");
/*     */     } 
/* 121 */     logRenderTime.start();
/* 122 */     WebElement userAccntElement = WebUtils.findElementBy(this.driver, 
/* 123 */         By.xpath(".//*[contains(@id,'userAccount-content')]"));
/* 124 */     WebUtils.scrollElementUp(this.driver, userAccntElement);
/* 125 */     userAccntElement.click();
/* 126 */     WebElement element = WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@id,'logout-content')]"));
/* 127 */     WebUtils.scrollElementUp(this.driver, element);
/* 128 */     element.click();
/*     */     
/* 130 */     logRenderTime.end();
/*     */   }
/*     */   
/*     */   public void loginToPortal(String user, String useCaseName) throws CCTException {
/*     */     try {
/* 135 */       this.driver.get(String.format(TestConfiguration.getProperty("cct.env.portal.url"), new Object[0]));
/* 136 */     } catch (WebDriverException e) {
/* 137 */       throw new CCTException("Unable to launch page - " + 
/* 138 */           TestConfiguration.getProperty("cct.env.portal.url") + " Message ~ " + e
/* 139 */           .getMessage());
/*     */     } 
/* 141 */     RenderingTimeLogin logRenderTime = new RenderingTimeLogin(user, useCaseName);
/* 142 */     logRenderTime.start();
/* 143 */     if (user != null && user.split("-")[0].equalsIgnoreCase("UsersInline")) {
/* 144 */       loginCredentialsByKeywords(user);
/*     */     } else {
/* 146 */       loginCredentialsByConfigurations(user);
/*     */     } 
/* 148 */     logRenderTime.end();
/*     */   }
/*     */   
/*     */   private void loginCredentialsByKeywords(String user) throws CCTException {
/* 152 */     String userName = user.split("-")[1];
/* 153 */     String password = user.split("-")[2];
/* 154 */     String assertUser = user.split("-")[3];
/* 155 */     login(userName, password, assertUser);
/*     */   }
/*     */   
/*     */   private void loginCredentialsByConfigurations(String user) throws CCTException {
/* 159 */     if (user.equals("User")) {
/* 160 */       Map<String, String> userDetails = PortalUtils.getUserDetails();
/* 161 */       login(userDetails.get("%s.portal.user"), userDetails
/* 162 */           .get("%s.portal.password"), (String)userDetails
/* 163 */           .get("%s.portal.user.firstName") + " " + (String)userDetails
/* 164 */           .get("%s.portal.user.lastName"));
/* 165 */     } else if (user.equals("Designer")) {
/* 166 */       login(String.format(TestConfiguration.getProperty("cct.env.portal.designer.user"), new Object[0]), 
/* 167 */           String.format("%s", new Object[] {
/* 168 */               TestConfiguration.getProperty("cct.env.portal.designer.password")
/* 169 */             }), String.format(
/* 170 */             TestConfiguration.getProperty("cct.env.portal.designer.firstName"), new Object[0]) + " " + 
/* 171 */           String.format(
/* 172 */             TestConfiguration.getProperty("cct.env.portal.designer.lastName"), new Object[0]));
/* 173 */     } else if (user.equals("Admin")) {
/* 174 */       login(String.format(TestConfiguration.getProperty("cct.env.portal.admin.user"), new Object[0]), 
/* 175 */           String.format("%s", new Object[] {
/* 176 */               TestConfiguration.getProperty("cct.env.portal.admin.password")
/*     */             }), "Administrator");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void login(String user, String pwd, String assertUser) throws CCTException {
/*     */     try {
/* 183 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.name("username"), user);
/* 184 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.name("password"), pwd);
/* 185 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("sbtbtn"));
/* 186 */       WebUtils.waitAndAssertForElementBy(this.driver, By.className("button__text"), assertUser);
/* 187 */     } catch (CCTException e) {
/* 188 */       throw new CCTException("Login failed");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void navigateToNewUsecase() throws CCTException {
/* 193 */     WebUtils.waitAndClickForElementBy(this.driver, By.partialLinkText("CREATE USECASE"));
/*     */   }
/*     */   
/*     */   public void createNewUsecase(String usecaseName) throws CCTException {
/* 197 */     String base = this.driver.getWindowHandle();
/* 198 */     Set<String> set = this.driver.getWindowHandles();
/* 199 */     set.remove(base);
/* 200 */     String currentTab = (String)set.toArray()[0];
/* 201 */     this.driver.switchTo().window(currentTab);
/*     */     try {
/* 203 */       WebUtils.waitForWidgetsToRender();
/* 204 */       WebUtils.waitForWidgetsToRender();
/* 205 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseName')]"), usecaseName);
/* 206 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'applicationName')]"), usecaseName);
/*     */       
/* 208 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseDisplayName')]"), usecaseName);
/*     */       
/* 210 */       WebUtils.waitAndSendKeysForElementBy(this.driver, By.xpath(".//*[contains(@id, 'usecaseDescription')]"), usecaseName);
/*     */       
/* 212 */       WebUtils.waitForWidgetsToRender();
/* 213 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("add-pane"));
/* 214 */       WebUtils.executeJS(this.driver, "return $($('#board-content > div > ul > li > header > ul > li')[0]).trigger('click')");
/*     */       
/* 216 */       WebUtils.waitAndAssertForElementBy(this.driver, By.xpath(".//*[@id='modal_overlay']/div/header/h2"), "WIDGET");
/* 217 */       (new Select(this.driver.findElement(By.xpath(".//*[@id='setting-value-container-plugin-types']/div/select"))))
/* 218 */         .selectByValue("cemboard-table");
/* 219 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("dialog-ok"));
/* 220 */       WebUtils.waitForWidgetsToRender();
/* 221 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("useCaseSave"));
/* 222 */       WebUtils.waitAndAssertForElementBy(this.driver, By.id("responseAlert"), "Usecase saved successfully");
/* 223 */       this.driver.switchTo().window(currentTab).close();
/* 224 */       this.driver.switchTo().window(base);
/* 225 */     } catch (Exception e) {
/* 226 */       this.driver.switchTo().window(currentTab).close();
/* 227 */       this.driver.switchTo().window(base);
/* 228 */       throw new CCTException("Exception while creating use case:" + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void editUsecase(String usecase) throws CCTException {
/* 233 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//table//*[local-name() = 'td' and ./text() = '" + usecase + "'][2]/following-sibling::td[3]/a[1]"));
/*     */     
/* 235 */     String base = this.driver.getWindowHandle();
/* 236 */     Set<String> set = this.driver.getWindowHandles();
/* 237 */     set.remove(base);
/* 238 */     String currentTab = (String)set.toArray()[0];
/* 239 */     this.driver.switchTo().window(currentTab);
/*     */     try {
/* 241 */       WebUtils.waitForWidgetsToRender();
/* 242 */       WebUtils.waitForElementBy(this.driver, By.id("board-tools"));
/* 243 */       Assert.assertEquals(WebUtils.findElementBy(this.driver, By.xpath(".//*[contains(@id, 'useCaseName')]"))
/* 244 */           .getAttribute("value"), usecase);
/* 245 */       WebUtils.waitAndClickForElementBy(this.driver, By.id("useCaseSave"));
/* 246 */       WebUtils.waitAndAssertForElementBy(this.driver, By.id("responseAlert"), "Usecase saved successfully");
/* 247 */       this.driver.switchTo().window(currentTab).close();
/* 248 */       this.driver.switchTo().window(base);
/* 249 */     } catch (Exception e) {
/* 250 */       this.driver.switchTo().window(currentTab).close();
/* 251 */       this.driver.switchTo().window(base);
/* 252 */       throw new CCTException("Exception while editing use case:" + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void deleteUsecase(String usecaseName) throws CCTException {
/* 257 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//table//*[local-name() = 'td' and ./text() = '" + usecaseName + "'][2]/following-sibling::td[3]/a[2]"));
/*     */     
/*     */     try {
/* 260 */       Thread.sleep(3000L);
/* 261 */     } catch (InterruptedException e) {
/* 262 */       throw new CCTException("Alert window to confirm Delete usecase not displayed");
/*     */     } 
/* 264 */     WebUtils.executeJS(this.driver, "$('#yesButton').trigger('click')");
/* 265 */     WebUtils.waitAndAssertForElementBy(this.driver, By.className("customMessage"), "Deleted succesfully");
/*     */   }
/*     */   
/*     */   public void verifyUsecaseNotPresentInDashboard(String useCaseName) throws CCTException {
/* 269 */     PortalUtils.searchUsecase(useCaseName, this.driver);
/*     */     try {
/* 271 */       WebElement element = this.driver.findElement(
/* 272 */           By.xpath("//*[contains(@class, 'usecase-list')]/li/a[./text()='" + useCaseName + "']"));
/* 273 */       if (element == null) {
/* 274 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false).as("Usecase Doesn't Exists: " + useCaseName + " ", new Object[0]))
/* 275 */           .isEqualTo(true);
/*     */       }
/* 277 */     } catch (Exception e) {
/* 278 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(true).as("Usecase Exists: " + useCaseName + " ", new Object[0])).isEqualTo(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\pages\CEMNovaPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */