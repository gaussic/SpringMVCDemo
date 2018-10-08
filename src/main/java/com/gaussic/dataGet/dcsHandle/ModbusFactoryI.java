package com.gaussic.dataGet.dcsHandle;

import com.gaussic.model.dcs.DeviceDcsPojo;
import com.serotonin.modbus4j.ModbusMaster;

public interface ModbusFactoryI {
     ModbusMaster getModBusMaster(DeviceDcsPojo deviceDcsPojo);
}
