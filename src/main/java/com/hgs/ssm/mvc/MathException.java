package com.hgs.ssm.mvc;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * �����Զ����쳣
 * @author hgs
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="��������ѧ�쳣�����޸���")
public class MathException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
