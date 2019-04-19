package com.youzan.ad.index.adunit;

import com.youzan.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by baimugudu on 2019/4/2
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
        return (positionType & AdUnitConstants.POSITION_TYPE.KAIPING)>0;
    }

    public static  boolean isTIEPIAN(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN)>0;
    }

    public static  boolean isTIEPIAN_MIDDLE(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE)>0;
    }

    public static  boolean isTIEPIAN_PAUSE(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE)>0;
    }

    public static  boolean isTIEPIAN_POST(int positionType){
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_POST)>0;
    }

    public static  boolean isAdSlotTypeOK(int slotType,int positionType){
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
