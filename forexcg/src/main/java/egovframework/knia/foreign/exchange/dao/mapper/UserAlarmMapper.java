package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.UserAlarmVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userAlarmMapper")
public interface UserAlarmMapper {

	List<?> selectUserAlarmList(UserAlarmVO userAlarmVO) throws Exception;
	
	void insertUserAlarm(UserAlarmVO userAlarmVO) throws Exception;
	
	void updateUserAlarm(UserAlarmVO userAlarmVO) throws Exception;
}
