package by.victor.beta.service.impl;

import by.victor.beta.service.IOrderService;

import java.util.TimerTask;

/**
 *Update timer task call update method every time any order state out of date.
 */
public class UpdateTimerTask extends TimerTask {
    IOrderService service;

    public UpdateTimerTask(IOrderService service) {
        this.service = service;
    }

    @Override
    public void run() {
    OrderUpdateManager.INSTANCE.update(service);
    }
}
