package realmbase.data;

import lombok.Getter;
import lombok.Setter;

public class AccountData {
	@Getter
	@Setter
	private String name = "";
	@Getter
	@Setter
	private Char[] charakters = new Char[0];

	public class Char{
		public String id;
		public int type;
	}
}
