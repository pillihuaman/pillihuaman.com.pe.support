package pillihuaman.com.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import pillihuaman.com.model.response.RespBase.Trace;

/**
 * Clase plantila para request
 * 
 * @author ttorres
 *
 * @param <T>
 *            Clase del payload
 */
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties("hibernateLazyInitializer")
@Getter
@Setter
public class ReqBase<T> {
	private Trace trace;
	private T data;

	public ReqBase() {//NOSONAR
		super();

	}

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
