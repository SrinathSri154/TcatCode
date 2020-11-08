Feature: CQI

@ID_TC_CQI_SMSFailureAnalysis_05
Scenario Outline: Verify Locations tab of CQI SMS Failure Analysis for all possible combinations of By pulldown 
#["REGRESSION"]
    Given Login into application as "User"
    And Navigate to Usecase Group "CUSTOMER QUALITY INSIGHT"
    And Navigate to Usecase "CQI SMS Failure Analysis"
    Then Reset filters
    And Select time filter as "<Time>" with value as "<TimeValue>"
    And Select technology filter as "<Technology>"
    And Select location filter as "<Location>" with value as "<LocationValue>"
    And Select Subscribers filter as "<Subscriber>" with value as "<SubscriberValue>"
    And Apply filters
    And Click on widget "cemboard-tab-494772" value "Locations"  
    And Validate Apply Button workflow "cemboard-apply-button-2195061" for all possible combinations of widget values "cemboard-pulldown-1564541"  
    And Logoff

Examples:
      | Data: | CQI_test_data.xlsx | SMS_Failure | A1:M2 |