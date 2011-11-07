package ca.uoit.kenpu;

public class SimulationException extends Exception {
	public String message;
	public SimulationException(String m) {
		this.message = m;
	}
	@Override
	public String toString() {
		return this.message;
	}
}
