package egovframework.knia.foreign.exchange.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDateUtil;
import egovframework.knia.foreign.exchange.dao.mapper.KaTalkMapper;
import egovframework.knia.foreign.exchange.service.KaTalkService;
import egovframework.knia.foreign.exchange.vo.KaTalkVO;

@Service("kaTalkService")
public class KaTalkServiceImpl implements KaTalkService {

	@Resource(name = "kaTalkMapper")
	KaTalkMapper kaTalkMapper;
	
	private void initKaTalk(KaTalkVO kaTalkVO) {
		
		kaTalkVO.setServiceSeqno(EgovProperties.getProperty("Globals.katalk.serviceSeqno"));
		kaTalkVO.setSubject(EgovProperties.getProperty("Globals.katalk.subject"));
		kaTalkVO.setBackupProcessCode(EgovProperties.getProperty("Globals.katalk.backupProcessCode"));
		kaTalkVO.setMessageType(EgovProperties.getProperty("Globals.katalk.messageType"));
		kaTalkVO.setContentsType(EgovProperties.getProperty("Globals.katalk.contentType"));
		kaTalkVO.setCallbackNo(EgovProperties.getProperty("Globals.katalk.callbackNo"));
		kaTalkVO.setJobType(EgovProperties.getProperty("Globals.katalk.jobType"));
		kaTalkVO.setTemplateCode(EgovProperties.getProperty("Globals.katalk.templateCode"));
		kaTalkVO.setRegisterBy(EgovProperties.getProperty("Globals.katalk.registerBy"));
		kaTalkVO.setImgAttachFlag(EgovProperties.getProperty("Globals.katalk.imgAttachFlag"));
		kaTalkVO.setTaxLevel1Nm(EgovProperties.getProperty("Globals.katalk.taxLevel1Nm"));
		kaTalkVO.setTaxLevel2Nm("");
		
	}
	
	public void sendOTPforJoin(HashMap<String, Object> hMap) throws Exception {
		
		KaTalkVO kaTalkVO = new KaTalkVO();
		this.initKaTalk(kaTalkVO);
		
		kaTalkVO.setSendMessage(EgovProperties.getProperty("Globals.katalk.join.title")+" "+hMap.get("authNum").toString()+"#");
		kaTalkVO.setSubject(EgovProperties.getProperty("Globals.katalk.subject"));
		kaTalkVO.setReceiveMobileNo(hMap.get("cellNum").toString());
		kaTalkVO.setSendReserveDateStr(EgovDateUtil.currentDateTimeString());
		
		kaTalkMapper.insertKaTalk(kaTalkVO);
	}
	
	public void sendFindUserId(HashMap<String, Object> hMap) throws Exception {
		
		KaTalkVO kaTalkVO = new KaTalkVO();
		this.initKaTalk(kaTalkVO);
		
		kaTalkVO.setSendMessage(EgovProperties.getProperty("Globals.katalk.findUserId.title")+" "+hMap.get("loginId").toString()+"");
		kaTalkVO.setSubject(EgovProperties.getProperty("Globals.katalk.subject"));
		kaTalkVO.setReceiveMobileNo(hMap.get("cellNum").toString());
		kaTalkVO.setSendReserveDateStr(EgovDateUtil.currentDateTimeString());
		
		kaTalkMapper.insertKaTalk(kaTalkVO);
	}
	
	public void sendFindPassword(HashMap<String, Object> hMap) throws Exception {
		
		KaTalkVO kaTalkVO = new KaTalkVO();
		this.initKaTalk(kaTalkVO);
		
		kaTalkVO.setSendMessage(EgovProperties.getProperty("Globals.katalk.findPassword.title")+" "+hMap.get("loginId").toString()+"");
		kaTalkVO.setSubject(EgovProperties.getProperty("Globals.katalk.subject"));
		kaTalkVO.setReceiveMobileNo(hMap.get("cellNum").toString());
		kaTalkVO.setSendReserveDateStr(EgovDateUtil.currentDateTimeString());
		
		kaTalkMapper.insertKaTalk(kaTalkVO);
	}
}
