package com.eternalinfo.tern.Impl.sql;

import org.springframework.stereotype.Service;

/**
 * @author 王诚沣
 * @下午8:55:31
 * @description 
 * @version
 */
@Service
public class SqlHandler {
	
	public final StringBuffer getStdBaseAllListSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT UPPER( TSIB.STD_EN_NAME ) AS STD_EN_NAME,UPPER( TSIB.STD_CN_NAME ) AS STD_CN_NAME, ");
		sql.append("UPPER( IFNULL( TSR.RANGE_DATA_TYPE, '' ) ) AS RANGE_DATA_TYPE,IFNULL(RANGE_DATA_LENGTH , '') AS RANGE_DATA_LENGTH, ");
		sql.append("( SELECT GROUP_CONCAT( SYSTEM_NAME ) FROM TN_QM_SOURCE_SYSTEM WHERE FIND_IN_SET( SYSTEM_CODE, TSIB.SOURCE_SYSTEM ) ) AS SOURCE_SYSTEM , ");
		sql.append("TCDC.DIM_CN_NAME AS SCOPE  ");
		sql.append("FROM TN_STD_INFO_BASE TSIB ");
		sql.append("LEFT JOIN TN_STD_RANGE TSR ON TSIB.RANGE_CODE = TSR.RANGE_CODE ");
		sql.append("LEFT JOIN ( SELECT DIM_CN_NAME, DIM_VALUE FROM TN_COM_DIM_CFG WHERE DIM_EN_CODE = 'SCOPE_ATTRIBUTION' ) TCDC ON TSIB.SCOPE = TCDC.DIM_VALUE  ");
		sql.append("WHERE 1=1 ");
		return sql;
	}
	
	public final StringBuffer getStdKpmgAllListSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TSIKE.EXPRESSION ,TSIKE.INDEX_SRC_MOD_NAME, TSIKE.INDEX_SRC_COL_NAME , TSIK.SCOPE,TSIK.SOURCE_SYSTEM,  ");
        sql.append(" UPPER( IFNULL( TSR.RANGE_DATA_TYPE, '' ) ) AS RANGE_DATA_TYPE,IFNULL(RANGE_DATA_LENGTH , '' ) AS RANGE_DATA_LENGTH, ");
        sql.append(" ( SELECT GROUP_CONCAT( SYSTEM_NAME ) FROM TN_QM_SOURCE_SYSTEM WHERE FIND_IN_SET( SYSTEM_CODE, TSIK.SOURCE_SYSTEM ) ) AS SOURCE_SYSTEM, ");
        sql.append("TCDC.DIM_CN_NAME AS SCOPE  ");
        sql.append("FROM TN_STD_INDEX_KPMG_EXP TSIKE LEFT JOIN TN_STD_INDEX_KPMG TSIK ON TSIKE.INDEX_CODE = TSIK.INDEX_CODE  ");
        sql.append("LEFT JOIN TN_STD_RANGE TSR ON TSIK.RANGE_CODE = TSR.RANGE_CODE LEFT JOIN ( SELECT DIM_CN_NAME, DIM_VALUE FROM TN_COM_DIM_CFG WHERE DIM_EN_CODE = 'SCOPE_ATTRIBUTION' ) TCDC ON TSIK.SCOPE = TCDC.DIM_VALUE  ");
        sql.append("WHERE TSIK.INDEX_STATE != '99'   ");
        return sql;
	}
	
}
