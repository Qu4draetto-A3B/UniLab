public class ListaCustom {
	public static void main(String[] args) {
		/*
		 *
		 * AreaGeografica ag1 = new AreaGeografica(-20 , -12, "Italia","Na" );
		 * AreaGeografica ag2 = new AreaGeografica(21 , -70, "Italia","Milano" );
		 * AreaGeografica ag3 = new AreaGeografica(42, 130, "italia", "Bergamo");
		 *
		 * lista.addFirst(ag1);
		 * lista.addFirst(ag2);
		 * lista.addFirst(ag3);
		 *
		 * //lista = lista.cercaAreaGeografica(null, "romania");
		 * lista.addFirst(lista.cercaAreeGeografiche(21, -190).get());
		 *
		 *
		 * for(AreaGeografica tmp : lista){
		 * System.out.println(tmp.getDenominazione());
		 * }
		 */

		CentroMonitoraggio ss = new CentroMonitoraggio("Camillone", null);
		ListaAree lista = ss.getListaAree();
		int i = 0;
		for (AreaGeografica tmp : lista)
			System.out.println("" + ++i + " " + tmp.toString());

	}
}
