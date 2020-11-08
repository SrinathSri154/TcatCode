package com.nokia.cemod.test;

import com.nokia.cemod.cct.utils.conf.TestConfiguration;
import org.jruby.RubyProcess;

public class TestConf {
   public static void main(String args[]){

       String browserType = TestConfiguration.getProperty("cct.run.browser");

       System.out.println(browserType);
       System.out.println(TestConfiguration.getProperty("cct.env.portal.url"));

   }
}
