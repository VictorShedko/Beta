package by.victor.beta.service.userservice;

import by.victor.beta.entity.Entity;
import by.victor.beta.entity.NotifyType;
import by.victor.beta.entity.User;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiConsumer;

public class CreateMessageStrategy {
    private static final String REPLACE_SEQUENCE="$$";
   private EnumMap<NotifyType, List< BiConsumer<String, Entity>>> consumerEnumMap=new EnumMap<>(NotifyType.class);
    private BiConsumer<String, Entity> INSERT_USERNAME= (pattern,entity)->{
        pattern.replaceFirst(REPLACE_SEQUENCE,((User)entity).getUsername());
    };
    private BiConsumer<String, Entity> INSERT_EMAIL= (pattern,entity)->{
        pattern.replaceFirst(REPLACE_SEQUENCE,((User)entity).getUsername());
    };
    private BiConsumer<String, Entity> INSERT_ORDER= (pattern,entity)->{
        pattern.replaceFirst(REPLACE_SEQUENCE,((User)entity).getUsername());
    };


    public CreateMessageStrategy(){
       consumerEnumMap.put(NotifyType.ADMIN_VALIDATION,List.of(INSERT_USERNAME,INSERT_USERNAME));
       consumerEnumMap.put(NotifyType.EMAIL_VALIDATION,List.of(INSERT_USERNAME,INSERT_EMAIL));
    }

    public List< BiConsumer<String, Entity>> getConsumerList(NotifyType type){
        return consumerEnumMap.get(type);
    }

}
