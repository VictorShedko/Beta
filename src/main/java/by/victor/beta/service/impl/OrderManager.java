package by.victor.beta.service.impl;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.TimeRepository;
import by.victor.beta.repository.specification.impl.orderspecification.FindNearestOrderSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Timer;

public enum OrderManager {
    INSTANCE;
    private static final Logger logger= LogManager.getLogger(OrderManager.class);
    private Timer updateTimer=new Timer();
    public void startTimer(){
        FindNearestOrderSpecification specification=new FindNearestOrderSpecification();
        try {
            List<Date> dates= TimeRepository.getInstance().findQuery(specification);
            if(dates.size()==1){
                Date updateDate=dates.get(0);
                UpdateTimerTask timerTask=new UpdateTimerTask();
                Date currentTime=new Date();
              if(updateDate.compareTo(currentTime)>0) {
                  updateTimer.schedule(timerTask, updateDate);// todo change name
              }else {
                  update();
              }
            }
        } catch (RepositoryException e) {
            logger.log(Level.FATAL,"Cant find time",e);
        }
    }

    public void update(){
        OrderService orderService=new OrderService();
        orderService.timeUpdate();
        startTimer();
    }
}
