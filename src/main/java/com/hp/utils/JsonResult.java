package com.hp.utils;



/**
 * 用于封装控制层（Controller与客户端交互的数据信息） 通过重载有参构造函数，可以选择不同类型的封装方法
 *
 * @author Yang
 *
 */



public class JsonResult {

	private Integer state;//状态码信息： state = 1 表示成功 state = 0 表示失败
	private String message;// 状态码对应的具体信息
	private Object data;// 封装具体数据对象

	public JsonResult() {
	}

	public JsonResult(Integer state, String message, Object data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public JsonResult(Integer state, Object data) {
		super();
		this.state = state;
		this.data = data;
	}

	public JsonResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
