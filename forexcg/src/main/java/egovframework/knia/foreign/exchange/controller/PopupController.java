package egovframework.knia.foreign.exchange.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.knia.foreign.exchange.service.PopupService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class PopupController {
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@Resource(name = "popupService")
	private PopupService popupService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;
}
