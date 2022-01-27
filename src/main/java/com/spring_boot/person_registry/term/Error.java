package com.spring_boot.person_registry.term;

public enum Error {
	SPACE_OR_TAB {
		@Override
		public String toString() {
			return "\nA console-ra vagy terminal-ra beírt szöveg nem állhat csak space-ből vagy tab-ból!\n";
		}
	},
	IS_NOT_EXISTS_THE_ANSWER_OPTION {
		@Override
		public String toString() {
			return "\nA megadott lehetőségekből válasszon!\n";
		}
	},
	IS_NOT_EXISTS_THE_COLUMN {
		@Override
		public String toString() {
			return "\nNincsen több oszlop a táblában!\n";
		}
	},
	IS_NULL_VALUE {
		@Override
		public String toString() {
			return "\nA tábla ki lett választva, de a keresett érték az nem lett megadva!\n";
		}
	}
}

