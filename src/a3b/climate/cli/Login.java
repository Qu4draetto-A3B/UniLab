package a3b.climate.cli;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;

public class Login extends View {
    public Login() {
        super();
    }

    @Override
    public void start() {
        term.clear();

        String user = term.readWhile((str) -> str.length() < 6, "Inserire il nome utente");

        String pwd = term.readPasswordWhile((str) -> str.length() < 9, "Inserire la password");

        Result<Operatore> rop = DataBase.operatore.login(user, pwd);

        if (!rop.isValid()) {
            //TODO
        }
    }
}
