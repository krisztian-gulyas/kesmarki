package com.spring_boot.person_registry.term;


public enum Question {
	ONE_OR_SOME_QUERRY {
		@Override
		public String toString() {
			return "\nMely táblát vagy táblákat kell lekérdeznem? { elválasztás: ' ', ';'}\n";
		}
	},
	ONE_OR_SOME_DELETE {
		@Override
		public String toString() {
			return "\nMely táblából vagy táblákból kell törölnöm? { elválasztás: ' ', ';'}\n";
		}
	},
	WITCH_TABLE {
		@Override
		public String toString() {
			return "\nMelyik táblát? [person / személyek ; address / címek ; contact / elérhetőségek]\n> ";
		}
	},
	WITCH_TABLE_DELETE {
		@Override
		public String toString() {
			return "\nMelyik táblából töröljek? [person / személyek ; address / címek ; contact / elérhetőségek]\n> ";
		}
	},
	WITCH_TABLE_QUERRY {
		@Override
		public String toString() {
			return "\nMelyik táblából keressek? [person / személyek ; address / címek ; contact / elérhetőségek]\n> ";
		}
	},
	WITCH_TABLE_INSERT {
		@Override
		public String toString() {
			return "\nMelyik táblához adjak új rekordot? [person / személyek ; address / címek ; contact / elérhetőségek]\n> ";
		}
	},
	WITCH_TABLE_MODIFY {
		@Override
		public String toString() {
			return "\nMelyik táblában módosítsak rekordot? [person / személyek ; address / címek ; contact / elérhetőségek]\n> ";
		}
	},
	FIRSTH_QUEST {
		@Override
		public String toString() {
			String tab = "  ";
			return "\nVálasszon műveletet:\n"+ tab +"1.) Adatok lekérése\n"+ tab +"2.) Adatok rögzítése\n"+ tab +"3.) Adatok modosítása\n"+ tab +"4.) Adatok törlése\n"+ tab +"Müveletek befejezése: 'end'\n> ";
		}
	}
}