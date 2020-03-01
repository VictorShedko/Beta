package by.victor.beta.service.impl;

import java.util.TimerTask;

/**
 *Update timer task call update method every time any order state out of date.
 */
public class UpdateTimerTask extends TimerTask {
    @Override
    public void run() {
    OrderManager.INSTANCE.update();
    }
}
