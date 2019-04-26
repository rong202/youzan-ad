package com.youzan.ad.index.adunit;

import com.youzan.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by baimugudu on 2019/4/2
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class AdUnitObject {

    private Long unitId;

    private Integer unitStatus;

    private Integer positionType;

    private Long planId;

    private AdPlanObject adPlanObject;

    public void update (AdUnitObject newObject){

        if(null!=newObject.getUnitId()){
            this.unitId = newObject.getUnitId();
        }

        if(null!=newObject.getUnitStatus()){
            this.unitStatus = newObject.getUnitStatus();
        }

        if(null!=newObject.getPositionType()){
            this.positionType = newObject.getPositionType();
        }

        if(null!=newObject.getPlanId()){
            this.planId = newObject.getPlanId();
        }

    }

    public static  boolean isKAIPING(int positionType){
        log.info(positionType+"");
        log.info((positionType & AdUnitConstants.POSITION_TYPE.KAIPING)+"");
        log.info(((positionType & AdUnitConstants.POSITION_TYPE.KAIPING)>0)+"");
        return (positionType & AdUnitConstants.POSITION_TYPE.KAIPING)>0;
    }

    public static  boolean isTIEPIAN(int positionType){
        log.info(positionType+"");
        log.info((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN)+"");
        log.info(((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN)>0)+"");
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN)>0;
    }

    public static  boolean isTIEPIAN_MIDDLE(int positionType){
        log.info(positionType+"");
        log.info((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE)+"");
        log.info(((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE)>0)+"");
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE)>0;
    }

    public static  boolean isTIEPIAN_PAUSE(int positionType){
        log.info(positionType+"");
        log.info((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE)+"");
        log.info(((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE)>0)+"");
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE)>0;
    }

    public static  boolean isTIEPIAN_POST(int positionType){
        log.info(positionType+"");
        log.info((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_POST)+"");
        log.info(((positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_POST)>0)+"");
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_POST)>0;
    }

    public static  boolean isAdSlotTypeOK(int slotType,int positionType){
        log.info("{}-{}",slotType,positionType);
        switch (slotType){
            case AdUnitConstants.POSITION_TYPE.KAIPING:
                return isKAIPING(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN:
                return isTIEPIAN(positionType);
            case  AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE:
                return isTIEPIAN_MIDDLE(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE:
                return isTIEPIAN_PAUSE(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_POST:
                return isTIEPIAN_POST(positionType);
            default:
                return false;
        }
    }

}
