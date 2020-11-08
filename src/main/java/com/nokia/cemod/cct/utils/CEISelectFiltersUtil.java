/*     */ package com.nokia.cemod.cct.utils;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CEISelectFiltersUtil
/*     */ {
/*     */   private static final String CSI = "CSI";
/*     */   private static final String CEI = "CE Index";
/*     */   private static final String BCSI = "BCSI";
/*     */   private static final String NEI = "NEI";
/*     */   private static final String DATA = "Data";
/*     */   private static final String RADIO = "Radio";
/*     */   private static final String SMS = "SMS";
/*     */   private static final String VOICE = "Voice";
/*     */   private static final String VOICECS = "Voice CS";
/*     */   private static final String VOLTE = "VoLTE";
/*     */   private static final String ACTIVATION_FAILURE_RATE = "activationFailurePerc";
/*     */   private static final String APPLICATION_BROWSING = "applicationResponseTimeBrowsing";
/*     */   private static final String APPLICATION_STREAMING = "applicationResponseTimeStreaming";
/*     */   private static final String ATTACH_FAILURE_RATE = "attachFailurePerc";
/*     */   private static final String AVERAGE_PDP = "avgPDPSetupTime";
/*     */   private static final String THROUGHPUT_BROWSING = "throughputBrowsing";
/*     */   private static final String THROUGHPUT_STREAMING = "throughputStreaming";
/*     */   private static final String SIGNAL_STRENGTH_POOR = "Signal Strength Poor";
/*     */   private static final String SIGNAL_QUALITY_POOR = "Signal Quality Poor";
/*     */   private static final String AVERAGE_CALL_SETUP_TIME = "avgCallSetupTime";
/*     */   private static final String CALLSETUP = "callsSetupSuccessPerc";
/*     */   private static final String INCOMING_CALLS_DROPPED = "incomingCallsDroppedPerc";
/*     */   private static final String MEAN_OPINION_SCORE = "meanOpinionScore";
/*     */   private static final String MUTE_CALLS = "muteCallsPerc";
/*     */   private static final String OUTGOING_CALLS_DROPPED = "outgoingCallsDroppedPerc";
/*     */   private static final String AVERAGE_CALL = "volteAvgCallSetupTime";
/*     */   private static final String AVERAGE_MOSDL = "volteAvgMOSDL";
/*     */   private static final String AVERAGE_MOSUL = "volteAvgMOSUL";
/*     */   private static final String AVERAGE_MOS = "volteAvgMOS";
/*     */   private static final String CALL_DROP_MO = "volteCallDropMOPerc";
/*     */   private static final String CALL_DROP_MT = "volteCallDropMTPerc";
/*     */   private static final String VOLTE_CALL_SETUP = "volteCallSetupFailurePerc";
/*     */   private static final String VOLTE_MUTE_CALL = "volteMuteCallPerc";
/*     */   private static final String REGISTRATION_FAILURE = "volteRegistrationFailurePerc";
/*     */   private static final String KENDO_DROPDOWN_LIST = "kendoDropDownList";
/*     */   private static final String KENDO_NUM_TEXT_BOX = "kendoNumericTextBox";
/*     */   private static final String KENDO_MULTI_SELECT = "kendoMultiSelect";
/*     */   private static final String KENDO_DATE_PICKER = "kendoDatePicker";
/*     */   
/*     */   public static void applyCustomerFilter(String customerFilter, String lifetimeFilter, String lifetimeValue, WebDriver driver) throws CCTException {
/*  94 */     selectCEIMenuByID("CEI filters", driver);
/*  95 */     selectCEIMenuByID("Customer", driver);
/*  96 */     if (lifetimeFilter.equals("Between")) {
/*  97 */       executeJSforClick(driver, "$('#filter_customer').find('[role=listbox]')[0].click();");
/*  98 */       executeJStoSetValue(driver, "lifetimevaluepulldown", "kendoDropDownList", lifetimeFilter);
/*  99 */       String[] test = lifetimeValue.split(",");
/* 100 */       executeJStoSetValue(driver, "lifetimevalueTextBox", "kendoNumericTextBox", test[0]);
/* 101 */       executeJStoSetValue(driver, "lifetimevalueTextBox1", "kendoNumericTextBox", test[1]);
/*     */     } else {
/* 103 */       executeJStoSetValue(driver, "lifetimevaluepulldown", "kendoDropDownList", lifetimeFilter);
/* 104 */       executeJStoSetValue(driver, "lifetimevalueTextBox", "kendoNumericTextBox", lifetimeValue);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void selectCEIMenuByID(String ceiFilter, WebDriver driver) throws CCTException {
/* 109 */     WebUtils.waitAndClickForElementBy(driver, 
/* 110 */         By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'" + ceiFilter + "')]"));
/*     */   }
/*     */   
/*     */   public static void applyAgeFilter(String ageFilter, String ageFilterValue, WebDriver driver) throws CCTException {
/* 114 */     selectCEIMenuByID("CEI filters", driver);
/* 115 */     selectCEIMenuByID("Customer", driver);
/* 116 */     executeJStoSetValue(driver, "ageMultiSelect", "kendoMultiSelect", ageFilterValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyGenderFilter(String genderFilter, String genderFilterValue, WebDriver driver) throws CCTException {
/* 121 */     selectCEIMenuByID("CEI filters", driver);
/* 122 */     selectCEIMenuByID("Customer", driver);
/* 123 */     executeJStoSetValue(driver, "genderDropdown", "kendoDropDownList", genderFilterValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applySubscriptionFilter(String subscriptionFilter, String enddatefilter, String enddatevalue, WebDriver driver) throws CCTException {
/* 128 */     selectCEIMenuByID("CEI filters", driver);
/* 129 */     selectCEIMenuByXpath("Subscription", driver);
/* 130 */     if (enddatefilter.equals("Between")) {
/* 131 */       executeJSforClick(driver, "$('#filter_subscription').find('[role=listbox]')[0].click();");
/* 132 */       executeJStoSetValue(driver, "subscriptionEndDateOperator", "kendoDropDownList", enddatefilter);
/* 133 */       String[] test = enddatevalue.split(",");
/* 134 */       WebUtils.waitAndSendKeysForElementBy(driver, By.id("subscriptionEndDatePicker"), test[0]);
/* 135 */       WebUtils.waitAndSendKeysForElementBy(driver, By.id("subscriptionEndDatePicker1"), test[1]);
/*     */     } else {
/* 137 */       executeJStoSetValue(driver, "subscriptionEndDateOperator", "kendoDropDownList", enddatefilter);
/* 138 */       executeJStoSetValue(driver, "subscriptionEndDatePicker", "kendoDatePicker", enddatevalue);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void selectCEIMenuByXpath(String ceiFilterSubscription, WebDriver driver) throws CCTException {
/* 143 */     WebUtils.waitAndClickForElementBy(driver, 
/* 144 */         By.xpath(".//*[@key='filter_subscription']//span[contains(text(),'" + ceiFilterSubscription + "')]"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyDataFilter(String usageFilter, String datafilter, String datafiltervalue, WebDriver driver) throws CCTException {
/* 149 */     selectCEIMenuByID("CEI filters", driver);
/* 150 */     selectCEIMenuByID("Usage", driver);
/* 151 */     selectCEIMenuByID("Data", driver);
/* 152 */     if (datafilter.equals("Between")) {
/* 153 */       executeJSforClick(driver, "$('#filter_usageData').find('[role=listbox]')[0].click();");
/* 154 */       executeJStoSetValue(driver, "totalVolumepulldown", "kendoDropDownList", datafilter);
/* 155 */       String[] test = datafiltervalue.split(",");
/* 156 */       executeJStoSetValue(driver, "totalVolumeTextBox", "kendoNumericTextBox", test[0]);
/* 157 */       executeJStoSetValue(driver, "totalVolumeTextBox1", "kendoNumericTextBox", test[1]);
/*     */     } else {
/* 159 */       executeJStoSetValue(driver, "totalVolumepulldown", "kendoDropDownList", datafilter);
/* 160 */       executeJStoSetValue(driver, "totalVolumeTextBox", "kendoNumericTextBox", datafiltervalue);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applySmsFilter(String smsFilter, String smssentorreceived, String smsValue, WebDriver driver) throws CCTException {
/* 166 */     selectCEIMenuByID("CEI filters", driver);
/* 167 */     selectCEIMenuByID("Usage", driver);
/* 168 */     selectCEIMenuByID("SMS", driver);
/* 169 */     if (smsFilter.equals("SMS sent")) {
/* 170 */       if (smssentorreceived.equals("Between")) {
/* 171 */         executeJSforClick(driver, "$('#filter_usageSMS').find('[role=listbox]')[1].click();");
/* 172 */         executeJStoSetValue(driver, "SMSsentpulldown", "kendoDropDownList", smssentorreceived);
/* 173 */         String[] test = smsValue.split(",");
/* 174 */         executeJStoSetValue(driver, "SMSsentTextBox", "kendoNumericTextBox", test[0]);
/* 175 */         executeJStoSetValue(driver, "SMSsentTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 177 */         executeJStoSetValue(driver, "SMSsentpulldown", "kendoDropDownList", smssentorreceived);
/* 178 */         executeJStoSetValue(driver, "SMSsentTextBox", "kendoNumericTextBox", smsValue);
/*     */       } 
/* 180 */     } else if (smsFilter.equals("SMS received")) {
/* 181 */       if (smssentorreceived.equals("Between")) {
/* 182 */         executeJSforClick(driver, "$('#filter_usageSMS').find('[role=listbox]')[0].click();");
/* 183 */         executeJStoSetValue(driver, "SMSreceivedpulldown", "kendoDropDownList", smssentorreceived);
/* 184 */         String[] test = smsValue.split(",");
/* 185 */         executeJStoSetValue(driver, "SMSreceivedTextBox", "kendoNumericTextBox", test[0]);
/* 186 */         executeJStoSetValue(driver, "SMSreceivedTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 188 */         executeJStoSetValue(driver, "SMSreceivedpulldown", "kendoDropDownList", smssentorreceived);
/* 189 */         executeJStoSetValue(driver, "SMSreceivedTextBox", "kendoNumericTextBox", smsValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyVoiceFilter(String voiceFilter, String voiceCalls, String voiceCallsValue, WebDriver driver) throws CCTException, InterruptedException {
/* 196 */     selectCEIMenuByID("CEI filters", driver);
/* 197 */     selectCEIMenuByID("Usage", driver);
/* 198 */     selectCEIMenuByID("Voice", driver);
/* 199 */     if (voiceFilter.equals("Incoming calls")) {
/* 200 */       if (voiceCalls.equals("Between")) {
/* 201 */         executeJSforClick(driver, "$('#filter_usageVoice').find('[role=listbox]')[0].click();");
/* 202 */         executeJStoSetValue(driver, "incomingCallspulldown", "kendoDropDownList", voiceCalls);
/* 203 */         String[] test = voiceCallsValue.split(",");
/* 204 */         executeJStoSetValue(driver, "incomingCallsTextBox", "kendoNumericTextBox", test[0]);
/* 205 */         executeJStoSetValue(driver, "incomingCallsTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 207 */         executeJStoSetValue(driver, "incomingCallspulldown", "kendoDropDownList", voiceCalls);
/* 208 */         executeJStoSetValue(driver, "incomingCallsTextBox", "kendoNumericTextBox", voiceCallsValue);
/*     */       } 
/* 210 */     } else if (voiceFilter.equals("Outgoing calls")) {
/* 211 */       if (voiceCalls.equals("Between")) {
/* 212 */         executeJSforClick(driver, "$('#filter_usageVoice').find('[role=listbox]')[1].click();");
/* 213 */         executeJStoSetValue(driver, "outgoingCallspulldown", "kendoDropDownList", voiceCalls);
/* 214 */         String[] test = voiceCallsValue.split(",");
/* 215 */         executeJStoSetValue(driver, "outgoingCallsTextBox", "kendoNumericTextBox", test[0]);
/* 216 */         executeJStoSetValue(driver, "outgoingCallsTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 218 */         executeJStoSetValue(driver, "outgoingCallspulldown", "kendoDropDownList", voiceCalls);
/* 219 */         executeJStoSetValue(driver, "outgoingCallsTextBox", "kendoNumericTextBox", voiceCallsValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyVolteFilter(String volteFilter, String volteCalls, String volteCallsValue, WebDriver driver) throws CCTException {
/* 226 */     selectCEIMenuByID("CEI filters", driver);
/* 227 */     selectCEIMenuByID("Usage", driver);
/* 228 */     selectCEIMenuByID("VoLTE", driver);
/* 229 */     if (volteFilter.equals("Incoming calls")) {
/* 230 */       if (volteCalls.equals("Between")) {
/* 231 */         executeJSforClick(driver, "$('#filter_usageVolte').find('[role=listbox]')[0].click();");
/* 232 */         executeJStoSetValue(driver, "volteIncomingCallspulldown", "kendoDropDownList", volteCalls);
/* 233 */         String[] test = volteCallsValue.split(",");
/* 234 */         executeJStoSetValue(driver, "volteIncomingCallsTextBox", "kendoNumericTextBox", test[0]);
/* 235 */         executeJStoSetValue(driver, "volteIncomingCallsTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 237 */         executeJStoSetValue(driver, "volteIncomingCallspulldown", "kendoDropDownList", volteCalls);
/* 238 */         executeJStoSetValue(driver, "volteIncomingCallsTextBox", "kendoNumericTextBox", volteCallsValue);
/*     */       } 
/* 240 */     } else if (volteFilter.equals("Outgoing calls")) {
/* 241 */       if (volteCalls.equals("Between")) {
/* 242 */         executeJSforClick(driver, "$('#filter_usageVolte').find('[role=listbox]')[1].click();");
/* 243 */         executeJStoSetValue(driver, "volteOutgoingCallspulldown", "kendoDropDownList", volteCalls);
/* 244 */         String[] test = volteCallsValue.split(",");
/* 245 */         executeJStoSetValue(driver, "volteOutgoingCallsTextBox", "kendoNumericTextBox", test[0]);
/* 246 */         executeJStoSetValue(driver, "volteOutgoingCallsTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 248 */         executeJStoSetValue(driver, "volteOutgoingCallspulldown", "kendoDropDownList", volteCalls);
/* 249 */         executeJStoSetValue(driver, "volteOutgoingCallsTextBox", "kendoNumericTextBox", volteCallsValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyQualityDataFilter(String qualityFilter, String qualityDataFilter, String qualityDataValue, WebDriver driver) throws CCTException {
/* 256 */     selectCEIMenuByID("CEI filters", driver);
/* 257 */     selectCEIMenuByID("Quality", driver);
/* 258 */     selectQualityDataByXpath("Data", driver);
/* 259 */     switch (qualityFilter) {
/*     */       case "activationFailurePerc":
/* 261 */         if (qualityDataFilter.equals("Between")) {
/* 262 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[0].click();");
/* 263 */           executeJStoSetValue(driver, "activationFailurePercpulldown", "kendoDropDownList", qualityDataFilter);
/* 264 */           String[] test = qualityDataValue.split(",");
/* 265 */           executeJStoSetValue(driver, "activationFailurePercTextBox", "kendoNumericTextBox", test[0]);
/* 266 */           executeJStoSetValue(driver, "activationFailurePercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 268 */         executeJStoSetValue(driver, "activationFailurePercpulldown", "kendoDropDownList", qualityDataFilter);
/* 269 */         executeJStoSetValue(driver, "activationFailurePercTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */       
/*     */       case "applicationResponseTimeBrowsing":
/* 273 */         if (qualityDataFilter.equals("Between")) {
/* 274 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[1].click();");
/* 275 */           executeJStoSetValue(driver, "applicationResponseTimeBrowsingpulldown", "kendoDropDownList", qualityDataFilter);
/*     */           
/* 277 */           String[] test = qualityDataValue.split(",");
/* 278 */           executeJStoSetValue(driver, "applicationResponseTimeBrowsingTextBox", "kendoNumericTextBox", test[0]);
/* 279 */           executeJStoSetValue(driver, "applicationResponseTimeBrowsingTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 281 */         executeJStoSetValue(driver, "applicationResponseTimeBrowsingpulldown", "kendoDropDownList", qualityDataFilter);
/*     */         
/* 283 */         executeJStoSetValue(driver, "applicationResponseTimeBrowsingTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */ 
/*     */       
/*     */       case "applicationResponseTimeStreaming":
/* 288 */         if (qualityDataFilter.equals("Between")) {
/* 289 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[2].click();");
/* 290 */           executeJStoSetValue(driver, "applicationResponseTimeStreamingpulldown", "kendoDropDownList", qualityDataFilter);
/*     */           
/* 292 */           String[] test = qualityDataValue.split(",");
/* 293 */           executeJStoSetValue(driver, "applicationResponseTimeStreamingTextBox", "kendoNumericTextBox", test[0]);
/* 294 */           executeJStoSetValue(driver, "applicationResponseTimeStreamingTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 296 */         executeJStoSetValue(driver, "applicationResponseTimeStreamingpulldown", "kendoDropDownList", qualityDataFilter);
/*     */         
/* 298 */         executeJStoSetValue(driver, "applicationResponseTimeStreamingTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */ 
/*     */       
/*     */       case "attachFailurePerc":
/* 303 */         if (qualityDataFilter.equals("Between")) {
/* 304 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[3].click();");
/* 305 */           executeJStoSetValue(driver, "attachFailurePercpulldown", "kendoDropDownList", qualityDataFilter);
/* 306 */           String[] test = qualityDataValue.split(",");
/* 307 */           executeJStoSetValue(driver, "attachFailurePercTextBox", "kendoNumericTextBox", test[0]);
/* 308 */           executeJStoSetValue(driver, "attachFailurePercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 310 */         executeJStoSetValue(driver, "attachFailurePercpulldown", "kendoDropDownList", qualityDataFilter);
/* 311 */         executeJStoSetValue(driver, "attachFailurePercTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */       
/*     */       case "avgPDPSetupTime":
/* 315 */         if (qualityDataFilter.equals("Between")) {
/* 316 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[4].click();");
/* 317 */           executeJStoSetValue(driver, "avgPDPSetupTimepulldown", "kendoDropDownList", qualityDataFilter);
/* 318 */           String[] test = qualityDataValue.split(",");
/* 319 */           executeJStoSetValue(driver, "avgPDPSetupTimeTextBox", "kendoNumericTextBox", test[0]);
/* 320 */           executeJStoSetValue(driver, "avgPDPSetupTimeTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 322 */         executeJStoSetValue(driver, "avgPDPSetupTimepulldown", "kendoDropDownList", qualityDataFilter);
/* 323 */         executeJStoSetValue(driver, "avgPDPSetupTimeTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */       
/*     */       case "throughputBrowsing":
/* 327 */         if (qualityDataFilter.equals("Between")) {
/* 328 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[5].click();");
/* 329 */           executeJStoSetValue(driver, "throughputBrowsingpulldown", "kendoDropDownList", qualityDataFilter);
/* 330 */           String[] test = qualityDataValue.split(",");
/* 331 */           executeJStoSetValue(driver, "throughputBrowsingTextBox", "kendoNumericTextBox", test[0]);
/* 332 */           executeJStoSetValue(driver, "throughputBrowsingTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 334 */         executeJStoSetValue(driver, "throughputBrowsingpulldown", "kendoDropDownList", qualityDataFilter);
/* 335 */         executeJStoSetValue(driver, "throughputBrowsingTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */       
/*     */       case "throughputStreaming":
/* 339 */         if (qualityDataFilter.equals("Between")) {
/* 340 */           executeJSforClick(driver, "$('#filter_qualityData').find('[role=listbox]')[5].click();");
/* 341 */           executeJStoSetValue(driver, "throughputStreamingpulldown", "kendoDropDownList", qualityDataFilter);
/* 342 */           String[] test = qualityDataValue.split(",");
/* 343 */           executeJStoSetValue(driver, "throughputStreamingTextBox", "kendoNumericTextBox", test[0]);
/* 344 */           executeJStoSetValue(driver, "throughputStreamingTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 346 */         executeJStoSetValue(driver, "throughputStreamingpulldown", "kendoDropDownList", qualityDataFilter);
/* 347 */         executeJStoSetValue(driver, "throughputStreamingTextBox", "kendoNumericTextBox", qualityDataValue);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void applyQualityRadioFilter(String qualityFilter, String qualityRadioFilter, String qualityRadioValue, WebDriver driver) throws CCTException {
/* 357 */     selectCEIMenuByID("CEI filters", driver);
/* 358 */     selectCEIMenuByID("Quality", driver);
/* 359 */     selectQualityRadioByXpath("Radio", driver);
/* 360 */     switch (qualityFilter) {
/*     */       case "Signal Strength Poor":
/* 362 */         if (qualityRadioFilter.equals("Between")) {
/* 363 */           executeJSforClick(driver, "$('#filter_qualityRadio').find('[role=listbox]')[0].click();");
/* 364 */           executeJStoSetValue(driver, "SignalStrengthPoorPercpulldown", "kendoDropDownList", qualityRadioFilter);
/* 365 */           String[] test = qualityRadioValue.split(",");
/* 366 */           executeJStoSetValue(driver, "SignalStrengthPoorPercTextBox", "kendoNumericTextBox", test[0]);
/* 367 */           executeJStoSetValue(driver, "SignalStrengthPoorPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 369 */         executeJStoSetValue(driver, "SignalStrengthPoorPercpulldown", "kendoDropDownList", qualityRadioFilter);
/* 370 */         executeJStoSetValue(driver, "SignalStrengthPoorPercTextBox", "kendoNumericTextBox", qualityRadioValue);
/*     */         break;
/*     */       
/*     */       case "Signal Quality Poor":
/* 374 */         if (qualityRadioFilter.equals("Between")) {
/* 375 */           executeJSforClick(driver, "$('#filter_qualityRadio').find('[role=listbox]')[1].click();");
/* 376 */           executeJStoSetValue(driver, "SignalQualityPoorPercpulldown", "kendoDropDownList", qualityRadioFilter);
/* 377 */           String[] test = qualityRadioValue.split(",");
/* 378 */           executeJStoSetValue(driver, "SignalQualityPoorPercTextBox", "kendoNumericTextBox", test[0]);
/* 379 */           executeJStoSetValue(driver, "SignalQualityPoorPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 381 */         executeJStoSetValue(driver, "SignalQualityPoorPercpulldown", "kendoDropDownList", qualityRadioFilter);
/* 382 */         executeJStoSetValue(driver, "SignalQualityPoorPercTextBox", "kendoNumericTextBox", qualityRadioValue);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectQualityDataByXpath(String qualityDataFilter, WebDriver driver) throws CCTException {
/* 391 */     WebUtils.waitAndClickForElementBy(driver, 
/* 392 */         By.xpath(".//*[@id='filter_qualityData']//span[contains(text(),'" + qualityDataFilter + "')]"));
/*     */   }
/*     */   
/*     */   private static void selectQualityRadioByXpath(String qualityRadioFilter, WebDriver driver) throws CCTException {
/* 396 */     WebUtils.waitAndClickForElementBy(driver, 
/* 397 */         By.xpath(".//*[@id='filter_qualityRadio']//span[contains(text(),'" + qualityRadioFilter + "')]"));
/*     */   }
/*     */   
/*     */   private static void selectCEIIndicesByXpath(String ceiIndiceFilter, WebDriver driver) throws CCTException {
/* 401 */     WebUtils.waitAndClickForElementBy(driver, 
/* 402 */         By.xpath(".//*[@id='filter_indices']//span[contains(text(),'" + ceiIndiceFilter + "')]"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyQualitySmsFilter(String qualityFilter, String qualitySmsFilter, String qualitySmsValue, WebDriver driver) throws CCTException {
/* 407 */     selectCEIMenuByID("CEI filters", driver);
/* 408 */     selectCEIMenuByID("Quality", driver);
/* 409 */     selectQualitySmsByXpath("SMS", driver);
/* 410 */     if (qualityFilter.equals("SMS sent")) {
/* 411 */       if (qualitySmsFilter.equals("Between")) {
/* 412 */         executeJSforClick(driver, "$('#filter_qualitySMS').find('[role=listbox]')[1].click();");
/* 413 */         executeJStoSetValue(driver, "SMSsentSuccessPercpulldown", "kendoDropDownList", qualitySmsFilter);
/* 414 */         String[] test = qualitySmsValue.split(",");
/* 415 */         executeJStoSetValue(driver, "SMSsentSuccessPercTextBox", "kendoNumericTextBox", test[0]);
/* 416 */         executeJStoSetValue(driver, "SMSsentSuccessPercTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 418 */         executeJStoSetValue(driver, "SMSsentSuccessPercpulldown", "kendoDropDownList", qualitySmsFilter);
/* 419 */         executeJStoSetValue(driver, "SMSsentSuccessPercTextBox", "kendoNumericTextBox", qualitySmsValue);
/*     */       } 
/* 421 */     } else if (qualityFilter.equals("SMS received")) {
/* 422 */       if (qualitySmsFilter.equals("Between")) {
/* 423 */         executeJSforClick(driver, "$('#filter_qualitySMS').find('[role=listbox]')[0].click();");
/* 424 */         executeJStoSetValue(driver, "SMSreceivedSuccessPercpulldown", "kendoDropDownList", qualitySmsFilter);
/* 425 */         String[] test = qualitySmsValue.split(",");
/* 426 */         executeJStoSetValue(driver, "SMSreceivedSuccessPercTextBox", "kendoNumericTextBox", test[0]);
/* 427 */         executeJStoSetValue(driver, "SMSreceivedSuccessPercTextBox1", "kendoNumericTextBox", test[1]);
/*     */       } else {
/* 429 */         executeJStoSetValue(driver, "SMSreceivedSuccessPercpulldown", "kendoDropDownList", qualitySmsFilter);
/* 430 */         executeJStoSetValue(driver, "SMSreceivedSuccessPercTextBox", "kendoNumericTextBox", qualitySmsValue);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void selectQualitySmsByXpath(String qualitySmsFilter, WebDriver driver) throws CCTException {
/* 436 */     WebUtils.waitAndClickForElementBy(driver, 
/* 437 */         By.xpath(".//*[@id='filter_qualitySMS']//span[contains(text(),'" + qualitySmsFilter + "')]"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyQualityVoiceFilter(String qualityFilter, String qualityVoiceFilter, String qualityVoiceValue, WebDriver driver) throws CCTException {
/* 442 */     selectCEIMenuByID("CEI filters", driver);
/* 443 */     selectCEIMenuByID("Quality", driver);
/* 444 */     selectQualityVoiceByXpath("Voice", driver);
/* 445 */     switch (qualityFilter) {
/*     */       case "avgCallSetupTime":
/* 447 */         if (qualityVoiceFilter.equals("Between")) {
/* 448 */           executeJSforClick(driver, "$('#filter_qualityVoice').find('[role=listbox]')[0].click();");
/* 449 */           executeJStoSetValue(driver, "avgCallSetupTimepulldown", "kendoDropDownList", qualityVoiceFilter);
/* 450 */           String[] test = qualityVoiceValue.split(",");
/* 451 */           executeJStoSetValue(driver, "avgCallSetupTimeTextBox", "kendoNumericTextBox", test[0]);
/* 452 */           executeJStoSetValue(driver, "avgCallSetupTimeTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 454 */         executeJStoSetValue(driver, "avgCallSetupTimepulldown", "kendoDropDownList", qualityVoiceFilter);
/* 455 */         executeJStoSetValue(driver, "avgCallSetupTimeTextBox", "kendoNumericTextBox", qualityVoiceValue);
/*     */         break;
/*     */       
/*     */       case "callsSetupSuccessPerc":
/* 459 */         if (qualityVoiceFilter.equals("Between")) {
/* 460 */           executeJSforClick(driver, "$('#filter_qualityVoice').find('[role=listbox]')[1].click();");
/* 461 */           executeJStoSetValue(driver, "callsSetupSuccessPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/* 462 */           String[] test = qualityVoiceValue.split(",");
/* 463 */           executeJStoSetValue(driver, "callsSetupSuccessPercTextBox", "kendoNumericTextBox", test[0]);
/* 464 */           executeJStoSetValue(driver, "callsSetupSuccessPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 466 */         executeJStoSetValue(driver, "callsSetupSuccessPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/* 467 */         executeJStoSetValue(driver, "callsSetupSuccessPercTextBox", "kendoNumericTextBox", qualityVoiceValue);
/*     */         break;
/*     */       
/*     */       case "incomingCallsDroppedPerc":
/* 471 */         if (qualityVoiceFilter.equals("Between")) {
/* 472 */           executeJSforClick(driver, "$('#filter_qualityVoice').find('[role=listbox]')[2].click();");
/* 473 */           executeJStoSetValue(driver, "incomingCallsDroppedPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/*     */           
/* 475 */           String[] test = qualityVoiceValue.split(",");
/* 476 */           executeJStoSetValue(driver, "incomingCallsDroppedPercTextBox", "kendoNumericTextBox", test[0]);
/* 477 */           executeJStoSetValue(driver, "incomingCallsDroppedPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 479 */         executeJStoSetValue(driver, "incomingCallsDroppedPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/*     */         
/* 481 */         executeJStoSetValue(driver, "incomingCallsDroppedPercTextBox", "kendoNumericTextBox", qualityVoiceValue);
/*     */         break;
/*     */       
/*     */       case "meanOpinionScore":
/* 485 */         if (qualityVoiceFilter.equals("Between")) {
/* 486 */           executeJSforClick(driver, "$('#filter_qualityVoice').find('[role=listbox]')[3].click();");
/* 487 */           executeJStoSetValue(driver, "meanOpinionScorepulldown", "kendoDropDownList", qualityVoiceFilter);
/* 488 */           String[] test = qualityVoiceValue.split(",");
/* 489 */           executeJStoSetValue(driver, "meanOpinionScoreTextBox", "kendoNumericTextBox", test[0]);
/* 490 */           executeJStoSetValue(driver, "meanOpinionScoreTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 492 */         executeJStoSetValue(driver, "meanOpinionScorepulldown", "kendoDropDownList", qualityVoiceFilter);
/* 493 */         executeJStoSetValue(driver, "meanOpinionScoreTextBox", "kendoNumericTextBox", qualityVoiceValue);
/*     */         break;
/*     */       
/*     */       case "muteCallsPerc":
/* 497 */         if (qualityVoiceFilter.equals("Between")) {
/* 498 */           executeJSforClick(driver, "$('#filter_qualityVoice').find('[role=listbox]')[4].click();");
/* 499 */           executeJStoSetValue(driver, "muteCallsPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/* 500 */           String[] test = qualityVoiceValue.split(",");
/* 501 */           executeJStoSetValue(driver, "muteCallsPercTextBox", "kendoNumericTextBox", test[0]);
/* 502 */           executeJStoSetValue(driver, "muteCallsPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 504 */         executeJStoSetValue(driver, "muteCallsPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/* 505 */         executeJStoSetValue(driver, "muteCallsPercTextBox", "kendoNumericTextBox", qualityVoiceValue);
/*     */         break;
/*     */       
/*     */       case "outgoingCallsDroppedPerc":
/* 509 */         if (qualityVoiceFilter.equals("Between")) {
/* 510 */           executeJSforClick(driver, "$('#filter_qualityVoice').find('[role=listbox]')[5].click();");
/* 511 */           executeJStoSetValue(driver, "outgoingCallsDroppedPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/*     */           
/* 513 */           String[] test = qualityVoiceValue.split(",");
/* 514 */           executeJStoSetValue(driver, "outgoingCallsDroppedPercTextBox", "kendoNumericTextBox", test[0]);
/* 515 */           executeJStoSetValue(driver, "outgoingCallsDroppedPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 517 */         executeJStoSetValue(driver, "outgoingCallsDroppedPercpulldown", "kendoDropDownList", qualityVoiceFilter);
/*     */         
/* 519 */         executeJStoSetValue(driver, "outgoingCallsDroppedPercTextBox", "kendoNumericTextBox", qualityVoiceValue);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectQualityVoiceByXpath(String qualityVoiceFilter, WebDriver driver) throws CCTException {
/* 528 */     WebUtils.waitAndClickForElementBy(driver, 
/* 529 */         By.xpath(".//*[@id='filter_qualityVoice']//span[contains(text(),'" + qualityVoiceFilter + "')]"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyQualityVolteFilter(String qualityFilter, String qualityVolteFilter, String qualityVolteValue, WebDriver driver) throws CCTException {
/* 534 */     selectCEIMenuByID("CEI filters", driver);
/* 535 */     selectCEIMenuByID("Quality", driver);
/* 536 */     selectQualityVolteByXpath("VoLTE", driver);
/* 537 */     switch (qualityFilter) {
/*     */       case "volteAvgCallSetupTime":
/* 539 */         if (qualityVolteFilter.equals("Between")) {
/* 540 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[0].click();");
/* 541 */           executeJStoSetValue(driver, "volteAvgCallSetupTimepulldown", "kendoDropDownList", qualityVolteFilter);
/* 542 */           String[] test = qualityVolteValue.split(",");
/* 543 */           executeJStoSetValue(driver, "volteAvgCallSetupTimeTextBox", "kendoNumericTextBox", test[0]);
/* 544 */           executeJStoSetValue(driver, "volteAvgCallSetupTimeTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 546 */         executeJStoSetValue(driver, "volteAvgCallSetupTimepulldown", "kendoDropDownList", qualityVolteFilter);
/* 547 */         executeJStoSetValue(driver, "volteAvgCallSetupTimeTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteAvgMOS":
/* 551 */         if (qualityVolteFilter.equals("Between")) {
/* 552 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[3].click();");
/* 553 */           executeJStoSetValue(driver, "volteAvgMOSpulldown", "kendoDropDownList", qualityVolteFilter);
/* 554 */           String[] test = qualityVolteValue.split(",");
/* 555 */           executeJStoSetValue(driver, "volteAvgMOSTextBox", "kendoNumericTextBox", test[0]);
/* 556 */           executeJStoSetValue(driver, "volteAvgMOSTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 558 */         executeJStoSetValue(driver, "volteAvgMOSpulldown", "kendoDropDownList", qualityVolteFilter);
/* 559 */         executeJStoSetValue(driver, "volteAvgMOSTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteAvgMOSDL":
/* 563 */         if (qualityVolteFilter.equals("Between")) {
/* 564 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[1].click();");
/* 565 */           executeJStoSetValue(driver, "volteAvgMOSDLpulldown", "kendoDropDownList", qualityVolteFilter);
/* 566 */           String[] test = qualityVolteValue.split(",");
/* 567 */           executeJStoSetValue(driver, "volteAvgMOSDLTextBox", "kendoNumericTextBox", test[0]);
/* 568 */           executeJStoSetValue(driver, "volteAvgMOSDLTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 570 */         executeJStoSetValue(driver, "volteAvgMOSDLpulldown", "kendoDropDownList", qualityVolteFilter);
/* 571 */         executeJStoSetValue(driver, "volteAvgMOSDLTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteAvgMOSUL":
/* 575 */         if (qualityVolteFilter.equals("Between")) {
/* 576 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[2].click();");
/* 577 */           executeJStoSetValue(driver, "volteAvgMOSULpulldown", "kendoDropDownList", qualityVolteFilter);
/* 578 */           String[] test = qualityVolteValue.split(",");
/* 579 */           executeJStoSetValue(driver, "volteAvgMOSULTextBox", "kendoNumericTextBox", test[0]);
/* 580 */           executeJStoSetValue(driver, "volteAvgMOSULTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 582 */         executeJStoSetValue(driver, "volteAvgMOSULpulldown", "kendoDropDownList", qualityVolteFilter);
/* 583 */         executeJStoSetValue(driver, "volteAvgMOSULTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteCallDropMOPerc":
/* 587 */         if (qualityVolteFilter.equals("Between")) {
/* 588 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[4].click();");
/* 589 */           executeJStoSetValue(driver, "volteCallDropMOPercpulldown", "kendoDropDownList", qualityVolteFilter);
/* 590 */           String[] test = qualityVolteValue.split(",");
/* 591 */           executeJStoSetValue(driver, "volteCallDropMOPercTextBox", "kendoNumericTextBox", test[0]);
/* 592 */           executeJStoSetValue(driver, "volteCallDropMOPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 594 */         executeJStoSetValue(driver, "volteCallDropMOPercpulldown", "kendoDropDownList", qualityVolteFilter);
/* 595 */         executeJStoSetValue(driver, "volteCallDropMOPercTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteCallDropMTPerc":
/* 599 */         if (qualityVolteFilter.equals("Between")) {
/* 600 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[5].click();");
/* 601 */           executeJStoSetValue(driver, "volteCallDropMTPercpulldown", "kendoDropDownList", qualityVolteFilter);
/* 602 */           String[] test = qualityVolteValue.split(",");
/* 603 */           executeJStoSetValue(driver, "volteCallDropMTPercTextBox", "kendoNumericTextBox", test[0]);
/* 604 */           executeJStoSetValue(driver, "volteCallDropMTPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 606 */         executeJStoSetValue(driver, "volteCallDropMTPercpulldown", "kendoDropDownList", qualityVolteFilter);
/* 607 */         executeJStoSetValue(driver, "volteCallDropMTPercTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteCallSetupFailurePerc":
/* 611 */         if (qualityVolteFilter.equals("Between")) {
/* 612 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[6].click();");
/* 613 */           executeJStoSetValue(driver, "volteCallSetupFailurePercpulldown", "kendoDropDownList", qualityVolteFilter);
/*     */           
/* 615 */           String[] test = qualityVolteValue.split(",");
/* 616 */           executeJStoSetValue(driver, "volteCallSetupFailurePercTextBox", "kendoNumericTextBox", test[0]);
/* 617 */           executeJStoSetValue(driver, "volteCallSetupFailurePercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 619 */         executeJStoSetValue(driver, "volteCallSetupFailurePercpulldown", "kendoDropDownList", qualityVolteFilter);
/*     */         
/* 621 */         executeJStoSetValue(driver, "volteCallSetupFailurePercTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteMuteCallPerc":
/* 625 */         if (qualityVolteFilter.equals("Between")) {
/* 626 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[7].click();");
/* 627 */           executeJStoSetValue(driver, "volteMuteCallPercpulldown", "kendoDropDownList", qualityVolteFilter);
/* 628 */           String[] test = qualityVolteValue.split(",");
/* 629 */           executeJStoSetValue(driver, "volteMuteCallPercTextBox", "kendoNumericTextBox", test[0]);
/* 630 */           executeJStoSetValue(driver, "volteMuteCallPercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 632 */         executeJStoSetValue(driver, "volteMuteCallPercpulldown", "kendoDropDownList", qualityVolteFilter);
/* 633 */         executeJStoSetValue(driver, "volteMuteCallPercTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */       
/*     */       case "volteRegistrationFailurePerc":
/* 637 */         if (qualityVolteFilter.equals("Between")) {
/* 638 */           executeJSforClick(driver, "$('#filter_qualityVolte').find('[role=listbox]')[8].click();");
/* 639 */           executeJStoSetValue(driver, "volteRegistrationFailurePercpulldown", "kendoDropDownList", qualityVolteFilter);
/*     */           
/* 641 */           String[] test = qualityVolteValue.split(",");
/* 642 */           executeJStoSetValue(driver, "volteRegistrationFailurePercTextBox", "kendoNumericTextBox", test[0]);
/* 643 */           executeJStoSetValue(driver, "volteRegistrationFailurePercTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 645 */         executeJStoSetValue(driver, "volteRegistrationFailurePercpulldown", "kendoDropDownList", qualityVolteFilter);
/*     */         
/* 647 */         executeJStoSetValue(driver, "volteRegistrationFailurePercTextBox", "kendoNumericTextBox", qualityVolteValue);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void applyIndexFilter(String ceIndex, String ceIndexFilter, String ceIndexValue, WebDriver driver) throws CCTException {
/* 658 */     selectCEIMenuByID("CEI filters", driver);
/* 659 */     selectCEIIndicesByXpath("CEI", driver);
/* 660 */     switch (ceIndex) {
/*     */       case "CE Index":
/* 662 */         if (ceIndexFilter.equals("Between")) {
/* 663 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[0].click();");
/* 664 */           executeJStoSetValue(driver, "ceindexpulldown", "kendoDropDownList", ceIndexFilter);
/* 665 */           String[] test = ceIndexValue.split(",");
/* 666 */           executeJStoSetValue(driver, "ceindexTextBox", "kendoNumericTextBox", test[0]);
/* 667 */           executeJStoSetValue(driver, "ceindexTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 669 */         executeJStoSetValue(driver, "ceindexpulldown", "kendoDropDownList", ceIndexFilter);
/* 670 */         executeJStoSetValue(driver, "ceindexTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "BCSI":
/* 674 */         if (ceIndexFilter.equals("Between")) {
/* 675 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[1].click();");
/* 676 */           executeJStoSetValue(driver, "bcsipulldown", "kendoDropDownList", ceIndexFilter);
/* 677 */           String[] test = ceIndexValue.split(",");
/* 678 */           executeJStoSetValue(driver, "bcsiTextBox", "kendoNumericTextBox", test[0]);
/* 679 */           executeJStoSetValue(driver, "bcsiTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 681 */         executeJStoSetValue(driver, "bcsipulldown", "kendoDropDownList", ceIndexFilter);
/* 682 */         executeJStoSetValue(driver, "bcsiTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "CSI":
/* 686 */         if (ceIndexFilter.equals("Between")) {
/* 687 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[1].click();");
/* 688 */           executeJStoSetValue(driver, "csipulldown", "kendoDropDownList", ceIndexFilter);
/* 689 */           String[] test = ceIndexValue.split(",");
/* 690 */           executeJStoSetValue(driver, "csiTextBox", "kendoNumericTextBox", test[0]);
/* 691 */           executeJStoSetValue(driver, "csiTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 693 */         executeJStoSetValue(driver, "csipulldown", "kendoDropDownList", ceIndexFilter);
/* 694 */         executeJStoSetValue(driver, "csiTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "NEI":
/* 698 */         if (ceIndexFilter.equals("Between")) {
/* 699 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[1].click();");
/* 700 */           executeJStoSetValue(driver, "neipulldown", "kendoDropDownList", ceIndexFilter);
/* 701 */           String[] test = ceIndexValue.split(",");
/* 702 */           executeJStoSetValue(driver, "neiTextBox", "kendoNumericTextBox", test[0]);
/* 703 */           executeJStoSetValue(driver, "neiTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 705 */         executeJStoSetValue(driver, "neipulldown", "kendoDropDownList", ceIndexFilter);
/* 706 */         executeJStoSetValue(driver, "neiTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "Data":
/* 710 */         if (ceIndexFilter.equals("Between")) {
/* 711 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[2].click();");
/* 712 */           executeJStoSetValue(driver, "datapulldown", "kendoDropDownList", ceIndexFilter);
/* 713 */           String[] test = ceIndexValue.split(",");
/* 714 */           executeJStoSetValue(driver, "dataTextBox", "kendoNumericTextBox", test[0]);
/* 715 */           executeJStoSetValue(driver, "dataTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 717 */         executeJStoSetValue(driver, "datapulldown", "kendoDropDownList", ceIndexFilter);
/* 718 */         executeJStoSetValue(driver, "dataTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "SMS":
/* 722 */         if (ceIndexFilter.equals("Between")) {
/* 723 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[4].click();");
/* 724 */           executeJStoSetValue(driver, "smspulldown", "kendoDropDownList", ceIndexFilter);
/* 725 */           String[] test = ceIndexValue.split(",");
/* 726 */           executeJStoSetValue(driver, "smsTextBox", "kendoNumericTextBox", test[0]);
/* 727 */           executeJStoSetValue(driver, "smsTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 729 */         executeJStoSetValue(driver, "smspulldown", "kendoDropDownList", ceIndexFilter);
/* 730 */         executeJStoSetValue(driver, "smsTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "Voice":
/* 734 */         if (ceIndexFilter.equals("Between")) {
/* 735 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[5].click();");
/* 736 */           executeJStoSetValue(driver, "voicepulldown", "kendoDropDownList", ceIndexFilter);
/* 737 */           String[] test = ceIndexValue.split(",");
/* 738 */           executeJStoSetValue(driver, "voiceTextBox", "kendoNumericTextBox", test[0]);
/* 739 */           executeJStoSetValue(driver, "voiceTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 741 */         executeJStoSetValue(driver, "voicepulldown", "kendoDropDownList", ceIndexFilter);
/* 742 */         executeJStoSetValue(driver, "voiceTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "Voice CS":
/* 746 */         if (ceIndexFilter.equals("Between")) {
/* 747 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[6].click();");
/* 748 */           executeJStoSetValue(driver, "voiceCSpulldown", "kendoDropDownList", ceIndexFilter);
/* 749 */           String[] test = ceIndexValue.split(",");
/* 750 */           executeJStoSetValue(driver, "voiceCSTextBox", "kendoNumericTextBox", test[0]);
/* 751 */           executeJStoSetValue(driver, "voiceCSTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 753 */         executeJStoSetValue(driver, "voiceCSpulldown", "kendoDropDownList", ceIndexFilter);
/* 754 */         executeJStoSetValue(driver, "voiceCSTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "VoLTE":
/* 758 */         if (ceIndexFilter.equals("Between")) {
/* 759 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[7].click();");
/* 760 */           executeJStoSetValue(driver, "voltepulldown", "kendoDropDownList", ceIndexFilter);
/* 761 */           String[] test = ceIndexValue.split(",");
/* 762 */           executeJStoSetValue(driver, "volteTextBox", "kendoNumericTextBox", test[0]);
/* 763 */           executeJStoSetValue(driver, "volteTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 765 */         executeJStoSetValue(driver, "voltepulldown", "kendoDropDownList", ceIndexFilter);
/* 766 */         executeJStoSetValue(driver, "volteTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */       
/*     */       case "Radio":
/* 770 */         if (ceIndexFilter.equals("Between")) {
/* 771 */           executeJSforClick(driver, "$('#filter_indices').find('[role=listbox]')[3].click();");
/* 772 */           executeJStoSetValue(driver, "radiopulldown", "kendoDropDownList", ceIndexFilter);
/* 773 */           String[] test = ceIndexValue.split(",");
/* 774 */           executeJStoSetValue(driver, "radioTextBox", "kendoNumericTextBox", test[0]);
/* 775 */           executeJStoSetValue(driver, "radioTextBox1", "kendoNumericTextBox", test[1]); break;
/*     */         } 
/* 777 */         executeJStoSetValue(driver, "radiopulldown", "kendoDropDownList", ceIndexFilter);
/* 778 */         executeJStoSetValue(driver, "radioTextBox", "kendoNumericTextBox", ceIndexValue);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void executeJSforClick(WebDriver webDriver, String jsScript) throws CCTException {
/* 787 */     WebUtils.executeJS(webDriver, jsScript);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void executeJStoSetValue(WebDriver webdriver, String identifier, String kendoComp, String value) throws CCTException {
/* 792 */     WebUtils.executeJS(webdriver, "$('#" + identifier + "').data('" + kendoComp + "').value('" + value + "');");
/* 793 */     WebUtils.executeJS(webdriver, "$('#" + identifier + "').data('" + kendoComp + "').trigger('change');");
/*     */   }
/*     */   
/*     */   private static void selectQualityVolteByXpath(String qualityVolteFilter, WebDriver driver) throws CCTException {
/* 797 */     WebUtils.waitAndClickForElementBy(driver, 
/* 798 */         By.xpath(".//*[@id='filter_qualityVolte']//span[contains(text(),'" + qualityVolteFilter + "')]"));
/*     */   }
/*     */   
/*     */   public static String convertDateFormat(String enddatevalue) throws ParseException, CCTException {
/* 802 */     DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
/*     */     
/* 804 */     Date date1 = new Date();
/* 805 */     Date date2 = new Date();
/* 806 */     if ((getElementValues(enddatevalue)).length == 1) {
/* 807 */       date1 = df.parse(enddatevalue);
/* 808 */       return df.format(date1);
/* 809 */     }  if ((getElementValues(enddatevalue)).length == 2) {
/* 810 */       date1 = df.parse(getElementValues(enddatevalue)[0]);
/* 811 */       date2 = df.parse(getElementValues(enddatevalue)[1]);
/* 812 */       return df.format(date1) + " 00:00:00 and " + df.format(date2) + " 00:00:00";
/*     */     } 
/* 814 */     throw new CCTException("Date entered is not in proper format");
/*     */   }
/*     */ 
/*     */   
/*     */   private static String[] getElementValues(String elementNames) {
/* 819 */     String[] multipleValues = null;
/* 820 */     if (elementNames != null && elementNames.length() > 0) {
/* 821 */       multipleValues = elementNames.split(",");
/*     */     }
/* 823 */     return multipleValues;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\CEISelectFiltersUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */