import EXCEPTIONS.FondiInsufficientiException;
import EXCEPTIONS.PrelievoEccessivoException;

public class ContoCorrente {

    String IBAN;
    double Saldo=0.0d;
    Intestatario Owner;


    public ContoCorrente(String ib, Intestatario ow) {
        IBAN=ib;
        Owner=ow;
    }


    void Preleva(double Amount) throws FondiInsufficientiException, PrelievoEccessivoException {
        if(Amount <= 0)
            throw new IllegalArgumentException();
        if(Amount > 500)
            throw new PrelievoEccessivoException();
        if(Amount<=Saldo) {
            Saldo=Saldo-Amount;
            System.out.println("Prelievo Effettuato");
        }
        else
        {
            throw new FondiInsufficientiException("Errore");
        }
    }
}