package oht.chess.ui.cli;

public class DigitMenu extends Cli implements ICli {
	void printHelp() {
	}

	ICli onDigit(int digit) {
		return null;
	}

	@Override
	public ICli draw() {
		printHelp();
		for (String s : readSplit()) {
			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				continue;
			}

			return onDigit(i);
		}
                
                return this;
	}
}