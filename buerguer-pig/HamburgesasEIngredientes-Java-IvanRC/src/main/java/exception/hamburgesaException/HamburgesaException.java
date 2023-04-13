package exception.hamburgesaException;

public sealed class HamburgesaException extends RuntimeException{
    public HamburgesaException(String mes) {
        super(mes);
    }
    public static final class HamburgesaNotFoundException extends HamburgesaException{
        public HamburgesaNotFoundException(String mes) {
            super("Error al buscar hamburgesa, la hamburgesa de id: "+mes+", no se ha encontrado.");
        }
    }
    public static final class HamburgesaBadRequestException extends HamburgesaException{
        public HamburgesaBadRequestException(String mes) {
            super("Error al introducir datos de la hamburgesa, el dato "+mes+", no es valido.");
        }
    }
    public static final class HamburgesaAlreadyExistsException extends HamburgesaException{
        public HamburgesaAlreadyExistsException(String mes) {
            super("Error al añadir hamburgesa, la hamburgesa de id: "+mes+", ya existe.");
        }
    }
}
