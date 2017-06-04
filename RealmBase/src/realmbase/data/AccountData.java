package realmbase.data;

import lombok.Getter;
import lombok.Setter;

public class AccountData {
	@Getter
	@Setter
	private String name = "";
	@Getter
	@Setter
	private ObjectData[] charakters = new ObjectData[0];
}
