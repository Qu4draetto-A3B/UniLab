
public class ListaCustom{
    public static void main(String[] args){
        ListaAree lista = new ListaAree();

        AreaGeografica ag1 = new AreaGeografica(7.0 , 0, "Italia","Napoli" );
        AreaGeografica ag2 = new AreaGeografica(7.0 , 0, "Italia","Milano" );

        lista.addLast(ag1);
        lista.addLast(ag2);
        lista.addFirst(ag1);

        for(AreaGeografica tmp : lista){
            System.out.println(tmp.getDenominazione());
        }

    }

}