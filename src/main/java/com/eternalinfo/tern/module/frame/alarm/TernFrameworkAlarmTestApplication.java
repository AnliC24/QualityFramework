package com.eternalinfo.tern.module.frame.alarm;

import com.eternalinfo.tern.ServerApplication;


public class TernFrameworkAlarmTestApplication extends ServerApplication{
	
	@Override
    public void run(String... args) {
        super.run(args);
    }

    public static void main(String[] args) {
    	TernFrameworkAlarmTestApplication application = new TernFrameworkAlarmTestApplication();
        application.run(args);
    }

}
