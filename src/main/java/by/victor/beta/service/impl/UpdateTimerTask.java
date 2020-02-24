package by.victor.beta.service.impl;

import java.util.TimerTask;

public class UpdateTimerTask extends TimerTask {
    @Override
    public void run() {
    OrderManager.INSTANCE.update();
    }
}
