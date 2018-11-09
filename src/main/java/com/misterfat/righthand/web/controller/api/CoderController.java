package com.misterfat.righthand.web.controller.api;

import static com.misterfat.framework.web.response.ResponseResult.success;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misterfat.framework.web.response.HttpStatusCode;
import com.misterfat.framework.web.response.ResponseResult;
import com.misterfat.righthand.model.Coder;
import com.misterfat.righthand.service.CoderService;
import com.misterfat.righthand.web.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(description = "码农", tags = "CoderController")
@RequestMapping("/api")
public class CoderController extends BaseController {

	@Autowired
	private CoderService coderService;

	@GetMapping("coders")
	@ApiOperation("获取码农")
	@ApiResponses({ @ApiResponse(code = HttpStatusCode.OK, message = HttpStatusCode.OK_MESSAGE),
			@ApiResponse(code = HttpStatusCode.BAD_REQUEST, message = HttpStatusCode.BAD_REQUEST_MESSAGE),
			@ApiResponse(code = HttpStatusCode.UNAUTHORIZED, message = HttpStatusCode.UNAUTHORIZED_MESSAGE),
			@ApiResponse(code = HttpStatusCode.NOT_FOUND, message = HttpStatusCode.NOT_FOUND_MESSAGE),
			@ApiResponse(code = HttpStatusCode.INTERNAL_SERVER_ERROR, message = HttpStatusCode.INTERNAL_SERVER_ERROR_MESSAGE) })
	public ResponseResult<List<Coder>> coders() {
		List<Coder> coders = coderService.findCoders();
		return success("success", coders);
	}

	@PostMapping("coders")
	@ApiOperation("新增码农")
	@ApiResponses({ @ApiResponse(code = HttpStatusCode.OK, message = HttpStatusCode.OK_MESSAGE),
			@ApiResponse(code = HttpStatusCode.BAD_REQUEST, message = HttpStatusCode.BAD_REQUEST_MESSAGE),
			@ApiResponse(code = HttpStatusCode.UNAUTHORIZED, message = HttpStatusCode.UNAUTHORIZED_MESSAGE),
			@ApiResponse(code = HttpStatusCode.NOT_FOUND, message = HttpStatusCode.NOT_FOUND_MESSAGE),
			@ApiResponse(code = HttpStatusCode.INTERNAL_SERVER_ERROR, message = HttpStatusCode.INTERNAL_SERVER_ERROR_MESSAGE) })
	public ResponseResult<String> coders(@RequestBody @Valid Coder coder) {
		coderService.addCoder(coder);
		return success("success");
	}
}
