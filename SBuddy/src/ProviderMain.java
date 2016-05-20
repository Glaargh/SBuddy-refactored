import java.net.BindException;

public class ProviderMain {

	public static void main(String[] args) {
		int port = 8080;
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		}
		Provider server = new Provider(port);
		server.run();
	}
}
