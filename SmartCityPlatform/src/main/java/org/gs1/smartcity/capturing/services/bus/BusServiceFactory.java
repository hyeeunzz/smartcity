package org.gs1.smartcity.capturing.services.bus;

public class BusServiceFactory {
	
	//bus master data information
	public static final String BUS_LINE_INFO = "busInfo";
	public static final String BUS_STOP_INFO = "busStopInfo";
	public static final String BUS_DRIVER_INFO = "busDriverInfo";
	public static final String BUS_COMPANY_INFO = "busCompanyInfo";
	public static final String BUS_LINE_ROUTE = "busLineRoute";
	public static final String BUS_VEHICLE_INFO = "busVehicleInfo";
	
	//bus event data information
	public static final String BUS_LIFE_INFO = "busLifeInfo";
	public static final String BUS_RT_POS_INFO = "busRealTimePosInfo";
	public static final String BUS_DRIVER_LIFE_INFO = "busDriverLifeInfo";
	
	//busan bus information
	public static final String BUSAN_BUS_LINE_INFO = "busInfo";			//���� �뼱 ����
	public static final String BUSAN_BUS_STOP_INFO = "busStop";			//���� ������ ����
	public static final String BUSAN_BUS_LINE_ROUTE = "busInfoRoute";	//���� �뼱�� ������ ���� �� �ǽð� ���� ��ġ ����
	public static final String BUSAN_BUS_STOP_ARR = "stopArr";			//���� ������ ���� ���� ����
	public static final String BUSAN_BUS_LINE_STOP = "busStopArr";		//���� �뼱-������ ���� ����
	public static final String BUSAN_BUS_LINE_INFO_ALL = "busInfoAll";
	public static final String BUSAN_BUS_STOP_INFO_ALL = "busStopAll";

	//daejeon bus information
	public static final String DAEJEON_BUS_LINE_ROUTE = "busRouteInfo/getStaionByRoute";			//���� �뼱�� ������ ����
	public static final String DAEJEON_BUS_LINE_ROUTE_ALL = "busRouteInfo/getStaionByRouteAll";	//��� ���� �뼱�� ������ ����
	public static final String DAEJEON_BUS_LINE_INFO = "busRouteInfo/getRouteInfo";					//���� �뼱 ����
	public static final String DAEJEON_BUS_LINE_INFO_ALL = "busRouteInfo/getRouteInfoAll";			//��� ���� �뼱 ����
	public static final String DAEJEON_BUS_STOP_INFO = "stationinfo/getStationBy";					//���� ������ ����
	public static final String DAEJEON_BUS_LINE_POS = "busposinfo/getBusPosByRtid";					//�뼱�� ���� ��ġ ����
	public static final String DAEJEON_BUS_STOP_ARR = "arrive/getArrInfoBy";						//���� ������ ���� ���� ����
	public static final String DAEJEON_BUS_REG_INFO_ALL = "busreginfo/getBusRegInfoAll";			//��ü ���� ��� ���� ����
	public static final String DAEJEON_BUS_REG_INFO = "busreginfo/getBusRegInfoByRouteId";			//�뼱�� ��� ���� ����
	public static final String DAEJEON_BUS_COMPANY_INFO = "buscompinfo/getBusCompInfo";				//���� ȸ�� ����
	
}
