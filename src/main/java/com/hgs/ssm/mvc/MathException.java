package com.hgs.ssm.mvc;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 进行自定义异常
 * @author hgs
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="发生了数学异常，请修复。")
public class MathException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
