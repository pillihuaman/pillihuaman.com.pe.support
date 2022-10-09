package pillihuaman.com.Help;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RegisterStatus {

	ACTIVE("1"), 
	INACTIVE("0"), 
	PENDIENTE_ACTIVE("a"), 
	PENDIENTE_INACTIVE("i");

	RegisterStatus(String string) {
		// TODO Auto-generated constructor stub
	}

	private String code;

}
