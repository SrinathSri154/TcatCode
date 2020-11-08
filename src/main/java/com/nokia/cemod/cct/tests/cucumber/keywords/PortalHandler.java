/*     */ package com.nokia.cemod.cct.tests.cucumber.keywords;
/*     */ 
/*     */ import com.nokia.cemod.cct.cgf.CGFPageFilters;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.pages.CCTPortalBasePage;
/*     */ import com.nokia.cemod.cct.pages.CCTPortalPage;
/*     */ import com.nokia.cemod.cct.tests.base.CCTBaseTest;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.CGFSelectFiltersUtil;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
/*     */ import cucumber.api.java.en.Then;
/*     */ import cucumber.api.java.en.When;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PortalHandler
/*     */   extends CCTBaseTest
/*     */ {
/*     */   @Given("^Navigate to login page and verify content$")
/*     */   public void navigateToLoginPage() throws Throwable {
/*  29 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  30 */     cctPortalBasePage.navigateToLoginPage();
/*  31 */     cctPortalBasePage.verifyLoginPageContent();
/*     */   }
/*     */   
/*     */   @Given("^Verify forgot password page$")
/*     */   public void verifyForgotPassword() throws Throwable {
/*  36 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  37 */     cctPortalBasePage.navigateToLoginPage();
/*  38 */     cctPortalBasePage.verifyForgotPasswordPage();
/*     */   }
/*     */   
/*     */   @Given("^Login into application as \"([^\"]*)\"$")
/*     */   public void login(String user) throws Throwable {
/*  43 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  44 */     cctPortalBasePage.loginToPortal(user, "");
/*     */   }
/*     */   
/*     */   @Then("^Configure end-points$")
/*     */   public void configureEndpoints() throws Throwable {
/*  49 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/*  50 */     cctPortalPage.navigateToControlPanel("Admin");
/*  51 */     cctPortalPage.configure();
/*     */   }
/*     */   
/*     */   @Then("^Logout$")
/*     */   public void logOut() throws Throwable {
/*  56 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  57 */     cctPortalBasePage.logout();
/*     */   }
/*     */   
/*     */   @Then("^Create new organisation \"([^\"]*)\"$")
/*     */   public void createNewOrganisation(String org) throws Exception {
/*  62 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/*  63 */     cctPortalPage.navigateToControlPanel("Admin");
/*  64 */     cctPortalPage.navigateTo("Users and Organizations");
/*  65 */     cctPortalPage.navigateTo("Add");
/*  66 */     cctPortalPage.navigateTo("Regular Organization");
/*  67 */     cctPortalPage.addOrganisation(org);
/*     */   }
/*     */   
/*     */   @Then("^Create new user for the organization \"([^\"]*)\"$")
/*     */   public void createNewUserWithDesignerRole(String org) throws Exception {
/*  72 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/*  73 */     cctPortalPage.navigateToControlPanel("Admin");
/*  74 */     cctPortalPage.createUserForOrganization(org);
/*     */   }
/*     */   
/*     */   @Then("^Update Content Pack Role for Use case library \"([^\"]*)\" and Portal as \"([^\"]*)\"$")
/*     */   public void updateContentPackRole(String usecaseLibrary, String portal) throws Exception {
/*  79 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/*  80 */     cctPortalPage.navigateToControlPanel("Admin");
/*  81 */     cctPortalPage.updateContentPackRole("Use Case Library", usecaseLibrary);
/*  82 */     cctPortalPage.updateContentPackRole("Portal", portal);
/*     */   }
/*     */   
/*     */   @Then("^Update Content Pack Manager$")
/*     */   public void updateContentPackManager() throws Exception {
/*  87 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/*  88 */     cctPortalPage.navigateToControlPanel("Admin");
/*  89 */     cctPortalPage.updateContentPackManager();
/*     */   }
/*     */   
/*     */   @Then("^Import Usecases into the system$")
/*     */   public void importUsecases() throws Exception {
/*  94 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/*  95 */     cctPortalPage.navigateToControlPanel("Designer");
/*  96 */     cctPortalPage.navigateToUsecaseManager();
/*  97 */     cctPortalPage.importUsecases();
/*     */   }
/*     */   
/*     */   @Then("^Subscribe all usecases as \"([^\"]*)\"$")
/*     */   public void subscriberimportedUsecases(String user) throws Exception {
/* 102 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 103 */     cctPortalPage.navigateToControlPanel(user);
/* 104 */     cctPortalPage.navigateToCemboardDesigner();
/* 105 */     cctPortalPage.selectOrganisation("CSP");
/* 106 */     cctPortalPage.selectItemsPerPage("all");
/* 107 */     cctPortalPage.subscribeUsecases();
/* 108 */     cctPortalPage.updateForOrganization();
/*     */   }
/*     */   
/*     */   @Then("^Verify Roles in portal \"([^\"]*)\"$")
/*     */   public void verifyRolesInPortal(String user) throws Exception {
/* 113 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 114 */     cctPortalPage.navigateToControlPanel(user);
/* 115 */     cctPortalPage.verifyRolesInPortal();
/*     */   }
/*     */   
/*     */   @Then("^Verify CCT links as \"([^\"]*)\"$")
/*     */   public void verifyCCTLinksInPortal(String user) throws Exception {
/* 120 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 121 */     cctPortalPage.navigateToControlPanel(user);
/* 122 */     cctPortalPage.verifyCCTRelatedLinks(user);
/*     */   }
/*     */   
/*     */   @Then("^Import Metadata$")
/*     */   public void importMetadata() throws Exception {
/* 127 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 128 */     cctPortalPage.navigateToControlPanel("Designer");
/* 129 */     cctPortalPage.importMetadataAsDesigner();
/*     */   }
/*     */   
/*     */   @When("^Navigate to Usecase Group \"([^\"]*)\"$")
/*     */   public void navigateToUsecaseGroup(String usecaseGroup) throws Exception {
/* 134 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 135 */     cctPortalBasePage.navigateToUsecaseGroup(usecaseGroup);
/*     */   }
/*     */   
/*     */   @When("^Navigate to CEMBoard Configuration$")
/*     */   public void navigateToConfiguration() throws Exception {
/* 140 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 141 */     cctPortalPage.navigateToControlPanel("Admin");
/* 142 */     cctPortalPage.navigateToCemboardConfiguration();
/*     */   }
/*     */   
/*     */   @When("^Navigate to Manage DB Export$")
/*     */   public void navigateToManageDBExport() throws Exception {
/* 147 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 148 */     cctPortalPage.navigateToControlPanel("Admin");
/* 149 */     cctPortalPage.navigateToManageDBExport();
/*     */   }
/*     */   
/*     */   @When("^Navigate to Usecase \"([^\"]*)\"$")
/*     */   public void navigateToUsecase(String usecaseName) throws Exception {
/* 154 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 155 */     cctPortalBasePage.navigateToUsecasePage(usecaseName);
/*     */     
/* 157 */     WebDriver driver = cctPortalBasePage.getWebDriver();
/* 158 */     WebUtils.waitForUseCaseToLoad(driver);
/* 159 */     CGFSelectFiltersUtil.clearCGFNotificationErrorMsg(driver);
/* 160 */     cctPortalBasePage.setUseCase(usecaseName);
/*     */   }
/*     */   
/*     */   @When("^Navigate to CEMBoard Designer page \"([^\"]*)\"$")
/*     */   public void navigateToCEMBoardDesignerPage(String user) throws Throwable {
/* 165 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 166 */     cctPortalPage.navigateToControlPanel(user);
/* 167 */     cctPortalPage.navigateToCEMBoardDesigner();
/*     */   }
/*     */   
/*     */   @When("^Navigate to New Use case$")
/*     */   public void navigateToNewUsecase() throws CCTException {
/* 172 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 173 */     cctPortalBasePage.navigateToNewUsecase();
/*     */   }
/*     */   
/*     */   @When("^Create New Usecase \"([^\"]*)\"$")
/*     */   public void createNewUsecase(String useCaseName) throws Throwable {
/* 178 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 179 */     cctPortalBasePage.createNewUsecase(useCaseName);
/*     */   }
/*     */   
/*     */   @When("^Select Organisation in CEMBoard Designer page as \"([^\"]*)\"$")
/*     */   public void selectOrganisationInDesigner(String org) throws CCTException {
/* 184 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 185 */     cctPortalPage.selectOrganisation(org);
/*     */   }
/*     */   
/*     */   @Then("^Publish Use case$")
/*     */   public void publishUsecase() throws Throwable {
/* 190 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 191 */     cctPortalPage.publishUsecaseForOrg();
/*     */   }
/*     */   
/*     */   @Then("^Update Organisation$")
/*     */   public void updateOrganisation() throws Throwable {
/* 196 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 197 */     cctPortalPage.updateOrganization();
/*     */   }
/*     */   
/*     */   @Then("^Subscribe Use case$")
/*     */   public void subscribeUsecase() throws Throwable {
/* 202 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 203 */     cctPortalPage.subscribeUsecaseForOrg();
/*     */   }
/*     */   
/*     */   @When("^Verify Subscribed Usecase \"([^\"]*)\"$")
/*     */   public void verifySubscribedUsecase(String usecaseName) throws CCTException {
/* 208 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 209 */     cctPortalPage.verifySubscribedUsecase(usecaseName);
/*     */   }
/*     */   
/*     */   @Then("^Edit Usecase \"([^\"]*)\"$")
/*     */   public void verifyEditUsecase(String usecaseName) throws CCTException {
/* 214 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 215 */     cctPortalBasePage.editUsecase(usecaseName);
/*     */   }
/*     */   
/*     */   @Then("^Delete usecase \"([^\"]*)\"$")
/*     */   public void verifyDeleteUsecase(String usecaseName) throws Throwable {
/* 220 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 221 */     cctPortalBasePage.deleteUsecase(usecaseName);
/*     */   }
/*     */   
/*     */   @Then("^Verify Export of usecases$")
/*     */   public void verifyExportOfUsecase() throws CCTException {
/* 226 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 227 */     cctPortalPage.navigateToControlPanel("Designer");
/* 228 */     cctPortalPage.navigateToUsecaseManager();
/* 229 */     cctPortalPage.exportUsecases();
/*     */   }
/*     */   
/*     */   @Then("^Add subgroup \"([^\"]*)\" under group \"([^\"]*)\" as \"([^\"]*)\" user$")
/*     */   public void addSubgroupUnderGroup(String subGroup, String group, String user) throws Throwable {
/* 234 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 235 */     cctPortalPage.navigateToControlPanel(user);
/* 236 */     cctPortalPage.navigateToCEMBoardGroups();
/* 237 */     cctPortalPage.addUsecaseSubGroup(subGroup, group);
/*     */   }
/*     */   
/*     */   @Then("^Delete subgroup \"([^\"]*)\" under group \"([^\"]*)\" as \"([^\"]*)\" user$")
/*     */   public void deleteSubgroupUnderGroup(String subGroup, String group, String user) throws Throwable {
/* 242 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 243 */     cctPortalPage.navigateToControlPanel(user);
/* 244 */     cctPortalPage.navigateToCEMBoardGroups();
/* 245 */     cctPortalPage.deleteUsecaseSubGroup(subGroup, group);
/*     */   }
/*     */   
/*     */   @Then("^Validate workflow with CGF")
/*     */   public void validate_workflow_with_CGF() throws Throwable {
/* 250 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 251 */     CGFPageFilters cgfPageFilters = cctPortalPage.getCGFPageFilters();
/* 252 */     applyCGFPageFilters(cgfPageFilters, cctPortalPage);
/* 253 */     cctPortalPage.selectApplyButton(getCGFFiltersForGivenFeature(), getCEIFiltersForGivenFeature());
/* 254 */     cctPortalPage.validateUseCase();
/* 255 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^\"([^\"]*)\" bulk users$")
/*     */   public void verifyBulkUserimport(String operation) throws CCTException {
/* 260 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 261 */     cctPortalPage.navigateToControlPanel("CSPOwner");
/* 262 */     cctPortalPage.navigateTo("BulkUserManager");
/* 263 */     cctPortalPage.manageBulkUsers(operation);
/*     */   }
/*     */   
/*     */   @Then("^Assign User \"([^\"]*)\" for organization \"([^\"]*)\" with Role \"([^\"]*)\"$")
/*     */   public void assignUserWithRole(String user, String org, String role) throws CCTException {
/* 268 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 269 */     cctPortalPage.navigateToControlPanel("Admin");
/* 270 */     cctPortalPage.navigateTo("Users and Organizations");
/* 271 */     cctPortalPage.navigateTo(org);
/* 272 */     WebUtils.scrollElementDown(cctPortalPage.getWebDriver(), 
/* 273 */         WebUtils.findElementBy(cctPortalPage.getWebDriver(), By.linkText(user)));
/* 274 */     cctPortalPage.navigateTo(user);
/* 275 */     cctPortalPage.assignRole(role);
/*     */   }
/*     */   
/*     */   @Then("^Verify table \"([^\"]*)\" row \"([^\"]*)\" and column \"([^\"]*)\" data is masked$")
/*     */   public void verifyTableDataForMaskingFeature(String tableId, String row, String column) throws CCTException {
/* 280 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 281 */     cctPortalPage.verifyTableCellDataForMasking(tableId, row, column);
/*     */   }
/*     */ 
/*     */   
/*     */   @Then("^User Creation for the organization \"([^\"]*)\" with email id as \"([^\"]*)\" password as \"([^\"]*)\" firstname as \"([^\"]*)\" and lastname as \"([^\"]*)\"$")
/*     */   public void createNewUserWithcemportal(String org, String email, String password, String firstname, String lastname) throws Exception {
/* 287 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 288 */     cctPortalPage.navigateToControlPanel("Admin");
/* 289 */     cctPortalPage.createNewUser(org, email, password, firstname, lastname);
/*     */   }
/*     */   
/*     */   @Then("^Configure LDAP as per Active Directory configuration$")
/*     */   public void configureLDAPforPortal() throws CCTException {
/* 294 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 295 */     cctPortalPage.navigateToControlPanel("Admin");
/* 296 */     cctPortalPage.navigateTo("Portal Settings");
/* 297 */     cctPortalPage.navigateTo("Authentication");
/* 298 */     cctPortalPage.navigateTo("LDAP");
/* 299 */     cctPortalPage.addLDAPEntries();
/*     */   }
/*     */   
/*     */   @Then("^Search Usecase \"([^\"]*)\" in Dashboard$")
/*     */   public void verifySearchUsecaseInDashboard(String usecase) throws CCTException {
/* 304 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 305 */     cctPortalBasePage.searchUsecase(usecase);
/*     */   }
/*     */   
/*     */   @Then("^Navigate to Usecase \"([^\"]*)\" from Favourites$")
/*     */   public void navigateToUsecaseFromFavourites(String usecase) throws CCTException {
/* 316 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 317 */     cctPortalPage.navigateToUsecaseFromFavourites(usecase);
/*     */   }
/*     */   
/*     */   @Then("^Verify About window$")
/*     */   public void verifyAboutWindow() throws CCTException {
/* 322 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 323 */     cctPortalPage.verifyAbout();
/*     */   }
/*     */   
/*     */   @Given("^Navigate to Portal Settings and \"([^\"]*)\"$")
/*     */   public void navigate_to(String string) throws Throwable {
/* 328 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 329 */     cctPortalPage.navigateToControlPanel("Admin");
/* 330 */     cctPortalPage.navigateTo("Portal Settings");
/* 331 */     cctPortalPage.navigateTo(string);
/*     */   }
/*     */   
/*     */   @When("^Select \"([^\"]*)\" for users to authenticate$")
/*     */   public void select_for_users_to_authenticate(String userCredential) throws Throwable {
/* 336 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 337 */     cctPortalPage.selectUserAuthentication(userCredential);
/*     */   }
/*     */   
/*     */   @Then("^Verify \"([^\"]*)\" as placeholder in user authentication$")
/*     */   public void verify_as_placeholder_in_user_authentication(String placeholder) throws Throwable {
/* 342 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 343 */     cctPortalBasePage.navigateToLoginPage();
/* 344 */     cctPortalBasePage.verifyPlaceholderInUserAuthentication(placeholder);
/*     */   }
/*     */   
/*     */   @Then("^Login with Screen Name$")
/*     */   public void login_with_Screen_Name() throws Throwable {
/* 349 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 350 */     cctPortalPage.loginToPortalWithScreenName();
/*     */   }
/*     */   
/*     */   @When("^Unsubscribe usecase \"([^\"]*)\"$")
/*     */   public void unsubscribe_usecase(String usecase) throws Throwable {
/* 355 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 356 */     cctPortalPage.unsubscribeUsecase(usecase);
/*     */   }
/*     */   
/*     */   @Then("^Verify Usecase \"([^\"]*)\" not available in dashboard$")
/*     */   public void verify_Usecase_not_available_in_dashboard(String usecase) throws Throwable {
/* 361 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 362 */     cctPortalBasePage.verifyUsecaseNotPresentInDashboard(usecase);
/*     */   }
/*     */   
/*     */   @Then("^Navigate to Usecase view \"([^\"]*)\"$")
/*     */   public void navigateToUsecaseView(String view) throws Throwable {
/* 367 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 368 */     cctPortalBasePage.navigateToUsecaseView(view);
/*     */   }
/*     */   
/*     */   @Then("^Load all usecases for Usecase Group \"([^\"]*)\"$")
/*     */   public void loadAllUsecases(String view) throws Throwable {
/* 373 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 374 */     cctPortalBasePage.loadAllUsecases(view);
/*     */   }
/*     */   
/*     */   @Given("^Login into application with \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\"$")
/*     */   public void login(String username, String password, String firstName) throws Throwable {
/* 379 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 380 */     String userDetails = "UsersInline-" + username + "-" + password + "-" + firstName;
/* 381 */     cctPortalBasePage.loginToPortal(userDetails, "");
/*     */   }
//CCT New keywords
            @And("^Navigate to Usecase Tab \"([^\"]*)\"$")
            public void navigateToUsecaseTab(String UsecaseTab) throws CCTException {
                CCTPortalBasePage cctPage = (CCTPortalBasePage)getCCTPage();
                WebDriver driver = cctPage.getWebDriver();
                try {
                    WebUtils.waitAndClickForElementBy(driver, By.xpath("//button[@label='" + UsecaseTab + "']"));
                } catch (CCTException var5) {
                    throw new CCTException("Failed to find and click on usecase Tab: " + UsecaseTab);
                }
            }

            @And("^Add Usecase \"([^\"]*)\" to Favourites$")
            public void add_to_favourites(String usecasename) throws CCTException {
                CCTPortalBasePage cctPage = (CCTPortalBasePage)getCCTPage();
                WebDriver driver = cctPage.getWebDriver();
                String[] values = usecasename.trim().split("\\s*,\\s*");
                for (int i=0; i<values.length; i++) {
                    String xpath = "//a[text()=\'" + values[i] + "\']//parent::li//div[@class=\'img-no-favourite\']";
                    try {
                        if (driver.findElements(By.xpath(xpath)).size() > 0) {
                            WebUtils.waitAndClickForElementBy(driver, By.xpath(xpath));
                        }
                    } catch (CCTException var5) {
                        throw new CCTException("Failed to add usecase to Favourites: " + values[i]);
                    }
                }
            }

            @And("^Verify Usecase \"([^\"]*)\" in Dashboard$")
            public void check_usecase_in_dashboard(String usecasename) throws CCTException {
                CCTPortalBasePage cctPage = (CCTPortalBasePage)getCCTPage();
                WebDriver driver = cctPage.getWebDriver();
                String[] values = usecasename.trim().split("\\s*,\\s*");
                for (int i=0; i<values.length; i++) {
                    try {
                        WebUtils.waitAndAssertForElementBy(driver, By.linkText(values[i]), values[i]);
                    } catch (CCTException var5) {
                        throw new CCTException("Usecase not available in Dashboard: " + values[i]);
                    }
                }
            }

            @And("^Validate CGF filter \"([^\"]*)\"$")
            public void verifyCGFFilters(String cgfFilter) throws CCTException {
                CCTPortalBasePage cctPage = (CCTPortalBasePage)getCCTPage();
                WebDriver driver = cctPage.getWebDriver();
                CGFSelectFiltersUtil.selectCGFMenuByID(cgfFilter, driver);
                if (cgfFilter.equals("Time")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Recent')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Day')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Week')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Month')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Date range')]"));
                } else if (cgfFilter.equals("Technologies")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'2G')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'3G')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'4G')]"));
//                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Technology')]"));
//                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Sub Technology')]"));
                } else if (cgfFilter.equals("Locations")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'State')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Cluster')]"));
//                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Regions')]"));
//                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Cities')]"));
//                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Areas')]"));
                } else if (cgfFilter.equals("APNs")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf-apns_taglist']"));
                } else if (cgfFilter.equals("Devices")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Manufacturer names')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Device types')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Device models')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Device capabilities')]"));
                } else if (cgfFilter.equals("Subscribers")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Segments')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Corporations')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Corporation subgroups')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscription types')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscription plan A')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscription plan B')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscriber group name')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Premium subscribers')]"));
                } else if (cgfFilter.equals("Roaming category")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Home')]"));
        //            WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Partner operators')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Inbound roamers')]"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Outbound roamers')]"));
                } else if (cgfFilter.equals("Preferences")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'Filter unmapped data')]"));
                } else if (cgfFilter.equals("CEI filters")) {
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//li[@key='filter_customer']/span/span[text()='Customer']"));
                    WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//li[@key='filter_subscription']/span/span[text()='Subscription']"));
                }
            }
/*     */   
/*     */   private void applyCGFPageFilters(CGFPageFilters cgfPageFilters, CCTPortalPage cctPortalPage) throws CCTException {
/* 385 */     List<String> technologyFilters = cgfPageFilters.getRandomTechnologyFilters();
/* 386 */     List<String> apnFilters = cgfPageFilters.getRandomApnFilters();
/* 387 */     HashMap<String, List<String>> locationFilters = cgfPageFilters.getRandomLocationFilters();
/* 388 */     HashMap<String, List<String>> timeFilters = cgfPageFilters.getRandomTimeFilters();
/* 389 */     HashMap<String, List<String>> subscriberFilters = cgfPageFilters.getRandomSubscriberFilters();
/* 390 */     HashMap<String, List<String>> deviceFilters = cgfPageFilters.getRandomdeviceFilters();
/* 391 */     iterateFiltersAndApply(technologyFilters, "Technologies");
/* 392 */     iterateFiltersAndApply(apnFilters, "APNs");
/* 393 */     iterateFiltersAndApply(locationFilters, (String)null);
/* 394 */     iterateFiltersAndApply(timeFilters, (String)null);
/* 395 */     iterateFiltersAndApply(subscriberFilters, (String)null);
/* 396 */     iterateFiltersAndApply(deviceFilters, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void iterateFiltersAndApply(Object filterItem, String cgfKey) throws CCTException {
/* 401 */     if (filterItem instanceof HashMap) {
/* 402 */       String mapKey = (String) ((HashMap)filterItem).keySet().iterator().next();
/*     */       
/* 404 */       for (Iterator<String> iterator = ((List<String>)((HashMap)filterItem).get(mapKey)).iterator(); iterator.hasNext(); ) {
/* 405 */         String filter = iterator.next();
/* 406 */         applyCGFFilter(mapKey, filter);
/*     */       } 
/* 408 */     } else if (filterItem instanceof List) {
/* 409 */       for (Iterator<String> iterator = ((List<String>)filterItem).iterator(); iterator.hasNext();) {
/* 410 */         applyCGFFilter(cgfKey, iterator.next());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void applyCGFFilter(String key, String filter) throws CCTException {
/* 416 */     CCTPortalPage cctPortalPage = (CCTPortalPage)getCCTPage();
/* 417 */     addCGFFiltersForGivenFeature(key, filter);
/* 418 */     cctPortalPage.applyGivenCgfFilter(key, filter);
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\cucumber\keywords\PortalHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */