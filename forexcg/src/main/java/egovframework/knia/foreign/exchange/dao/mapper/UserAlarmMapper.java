package egovframework.knia.foreign.exchange.dao.mapper;

import java.util.List;

import egovframework.knia.foreign.exchange.vo.UserAlarmVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userAlarmMapper")
public interface UserAlarmMapper {

	List<?> selecUserAlarmList(UserAlarmVO userAlarmVO) throws Exception;
	
	UserAlarmVO selecUserAlarm(UserAlarmVO userAlarmVO) throws Exception;
	
	void insertUserAlarm(UserAlarmVO userAlarmVO) throws Exception;
	
	void updateUserAlarm(UserAlarmVO userAlarmVO) throws Exception;
	
	void deleteUserAlarm(UserAlarmVO userAlarmVO) throws Exception;
}
